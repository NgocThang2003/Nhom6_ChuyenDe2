package com.example.nhom6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KhoDonHangChoXacNhan_Adapter extends RecyclerView.Adapter<KhoDonHangChoXacNhan_Holder> {
    Context context;
    List<DonHang> data;

    FirebaseDatabase database;
    DatabaseReference data_DH;
    public static DatabaseReference data_SP;
    public static List<SanPham> dataSanPham;

    public KhoDonHangChoXacNhan_Adapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KhoDonHangChoXacNhan_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KhoDonHangChoXacNhan_Holder(LayoutInflater.from(context).inflate(R.layout.item_khodonhangchoxacnhan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KhoDonHangChoXacNhan_Holder holder, int position) {
        DonHang donHang = data.get(position);
        database = FirebaseDatabase.getInstance();
        data_DH = database.getReference("DonHang");
        data_SP = database.getReference("SanPham");

        //DocDL(donHang);

        holder.tvTenKH.setText(donHang.tenKhachHang);
        if (donHang.hinh.trim().equals("")) {
            holder.ivHinh.setImageResource(R.drawable.giongngo);
        } else {
            try {
                byte[] bytes = chuyenStringSangByte(donHang.hinh);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
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
        //holder.tvTonKho.setText(dataSanPham.get(0).soLuong);

        int gia = Integer.parseInt(donHang.gia.trim());
        int soLuong = Integer.parseInt(donHang.soLuong.trim());


        holder.tvThanhTien.setText("đ" + gia * soLuong);

        holder.btnTinNhan.setVisibility(View.GONE);
        holder.btnGoiDien.setVisibility(View.GONE);

        holder.btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn sửa đơn hàng này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(context, "Sửa dữ liệu thành công", Toast.LENGTH_SHORT).show();

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                                String currentDateandTime = sdf.format(new Date());
                                data_DH.child(donHang.maDonHang).child("trangThai").setValue("Đang đóng gói");
                                data_DH.child(donHang.maDonHang).child("thongTinVanChuyen").setValue(donHang.thongTinVanChuyen + "\nĐang đóng gói " + currentDateandTime);
                                data_DH.child(donHang.maDonHang).child("nhanVienDuyetHang").setValue(donHang.nhanVienDuyetHang + " \nĐang đóng gói - Mã nhân viên: " + MainActivity_DangNhap.maNguoiDung + " " + currentDateandTime);
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

        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_LyDoHuyDon_ThuKho.class);
                intent.putExtra("donHang_ThuKho", donHang);
                context.startActivity(intent);
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

    public static void DocDL(DonHang donHang) {
        dataSanPham.clear();
        data_SP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSanPham.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (donHang.maSanPham.trim().equals(sanPham.maSanPham)) {
                        dataSanPham.add(sanPham);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
