package com.example.nhom6;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ShipperDonHangCuaBan_Adapter extends RecyclerView.Adapter<Shipper_DonHangCuaBan_Holder> {
    Context context;
    List<DonHang> data;
    FirebaseDatabase database;
    DatabaseReference data_DonHang;

    public ShipperDonHangCuaBan_Adapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Shipper_DonHangCuaBan_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Shipper_DonHangCuaBan_Holder(LayoutInflater.from(context).inflate(R.layout.item_shipper_donhangcuaban,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Shipper_DonHangCuaBan_Holder holder, int position) {
        DonHang donHang = data.get(position);
        database = FirebaseDatabase.getInstance();
        data_DonHang = database.getReference("DonHang");

        holder.tvTenKH.setText(donHang.tenKhachHang);
        if (donHang.hinh.trim().equals("")){
            holder.ivHinh.setImageResource(R.drawable.giongngo);
        }
        else {
            try {
                byte[] bytes = chuyenStringSangByte(donHang.hinh);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);
            }
            catch (Exception e){
                holder.ivHinh.setImageResource(R.drawable.giongngo);
            }
        }
        holder.tvDiaChi.setText(donHang.diaChi);
        holder.tvTenSP.setText(donHang.tenSanPham);
        holder.tvMoTa.setText(donHang.moTa);
        holder.tvSDT.setText(donHang.sDT);
        holder.tvGia.setText(donHang.gia);
        holder.tvSoLuong.setText(donHang.soLuong);
        holder.tvNgay.setText(donHang.ngay);
        holder.tvTrangThai.setText(donHang.trangThai);
        int gia = Integer.parseInt(donHang.gia.trim());
        int soLuong = Integer.parseInt(donHang.soLuong.trim());

        holder.tvThanhTien.setText("" + gia * soLuong);

        if(donHang.trangThai.equals("Đã đóng gói")){
            holder.btnXacNhanLayHang.setEnabled(true);
            holder.btnXacNhanLayHang.setBackgroundColor(Color.rgb(95,166,93));
        }
        else {
            holder.btnXacNhanLayHang.setEnabled(false);
            holder.btnXacNhanLayHang.setBackgroundColor(Color.rgb(214,229,213));
        }
        holder.btnXacNhanLayHang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Xác nhận đã lấy đơn hàng từ kho ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(context, "Lấy đơn hàng thành công ", Toast.LENGTH_SHORT).show();
                                data_DonHang.child(donHang.maDonHang).child("trangThai").setValue("Đang giao hàng");
//                                MainActivity_ShipperDonHangCuaBan.maShipper = MainActivity_DangNhap.maNguoiDung;
//                                Intent intent = new Intent(context,MainActivity_ShipperDonHangCuaBan.class);
//                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    private byte[] chuyenStringSangByte(String str) {
        byte[] byteArray = android.util.Base64.decode(str, android.util.Base64.NO_PADDING | android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
        return byteArray;
    }

    //Chuyen byte[] sang bitMap
    private Bitmap chuyenByteSangBitMap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }
}
