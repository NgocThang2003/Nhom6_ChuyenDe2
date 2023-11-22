package com.example.nhom6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipperDonHangDaHuy_Adapter extends RecyclerView.Adapter<ShipperDonHangDaHuy_Holder> {
    Context context;
    List<DonHang> data;
    FirebaseDatabase database;
    DatabaseReference data_DH;
    public static DatabaseReference data_SP;
    public static List<SanPham> dataSanPham = new ArrayList<>();

    public ShipperDonHangDaHuy_Adapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ShipperDonHangDaHuy_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShipperDonHangDaHuy_Holder(LayoutInflater.from(context).inflate(R.layout.item_shipperdonhangdahuy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShipperDonHangDaHuy_Holder holder, int position) {
        DonHang donHang = data.get(position);
        database = FirebaseDatabase.getInstance();
        data_DH = database.getReference("DonHang");
        data_SP = database.getReference("SanPham");
        data_SP.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDL(donHang, holder);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDL(donHang, holder);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDL(donHang, holder);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

        holder.tvLyDoHuyDon.setVisibility(View.VISIBLE);
        holder.tvLyDoHuyDon.setText(donHang.lyDoHuyDon);
        holder.btnGoiDien.setVisibility(View.GONE);
        holder.btnTinNhan.setVisibility(View.GONE);
        int gia = Integer.parseInt(donHang.gia.trim());
        int soLuong = Integer.parseInt(donHang.soLuong.trim());

        holder.tvThanhTien.setText("" + gia * soLuong);

        holder.btnThongTinKH.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ThongTinNguoiNhan.class);
                intent.putExtra("Dia Chi", donHang.diaChi);
                MainActivity_ThongTinNguoiNhan.maNguoiDung = donHang.maKhachHang;
                context.startActivity(intent);
            }
        });

        if (donHang.thuTien.equals("")) {
            holder.btnTraHang.setVisibility(View.VISIBLE);
        } else {
            holder.btnTraHang.setVisibility(View.GONE);
            holder.tvTrangThai.setText("Đã trả hàng");
        }

        holder.btnTraHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn trả đơn hàng này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(context, "Sửa dữ liệu thành công", Toast.LENGTH_SHORT).show();

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                                String currentDateandTime = sdf.format(new Date());
                                int soLuongHienTai = Integer.parseInt(holder.tvSoLuongHienTai.getText().toString().trim());
                                int soLuongSanPhamDonHang = Integer.parseInt(donHang.soLuong.trim());
                                int soLuongConLai = soLuongHienTai + soLuongSanPhamDonHang;

                                if (soLuongConLai < 0) {
                                    soLuongConLai = 0;
                                }

                                data_SP.child(donHang.maSanPham).child("soLuong").setValue("" + soLuongConLai);
                                data_DH.child(donHang.maDonHang).child("thuTien").setValue("Đã trả hàng");
                                data_DH.child(donHang.maDonHang).child("thongTinVanChuyen").setValue(donHang.thongTinVanChuyen + "\nĐã trả hàng " + currentDateandTime);
                                data_DH.child(donHang.maDonHang).child("nhanVienDuyetHang").setValue(donHang.nhanVienDuyetHang + " \nĐã trả hàng - Mã nhân viên: " + MainActivity_DangNhap.maNguoiDung + " " + currentDateandTime);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                //Toast.makeText(context, "" + dataSanPham.size(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(context, ""+holder.tvSoLuongHienTai.getText(), Toast.LENGTH_SHORT).show();
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

    private static void DocDL(DonHang donHang, ShipperDonHangDaHuy_Holder holder) {
        dataSanPham.clear();
        data_SP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSanPham.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (donHang.maSanPham.trim().equals(sanPham.maSanPham)) {
                        dataSanPham.add(sanPham);
                        holder.tvSoLuongHienTai.setText("" + sanPham.soLuong);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
