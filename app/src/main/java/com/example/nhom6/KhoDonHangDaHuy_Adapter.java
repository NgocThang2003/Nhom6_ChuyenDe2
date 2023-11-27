package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KhoDonHangDaHuy_Adapter extends RecyclerView.Adapter<KhoDonHangChoXacNhan_Holder> {
    Context context;
    List<DonHang> data;

    FirebaseDatabase database;
    DatabaseReference data_DH;

    public KhoDonHangDaHuy_Adapter(Context context, List<DonHang> data) {
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
        holder.tvTonKho.setVisibility(View.GONE);
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

        holder.tvThanhTien.setText("đ" + gia * soLuong);


        holder.tvLyDoHuyDon.setVisibility(View.VISIBLE);
        holder.tvLyDoHuyDon.setText(donHang.lyDoHuyDon);
        holder.btnTinNhan.setVisibility(View.GONE);
        holder.btnGoiDien.setVisibility(View.GONE);
        holder.btnXacNhanDonHang.setVisibility(View.GONE);
        holder.btnHuy.setVisibility(View.GONE);

        if (!donHang.getMaShipper().trim().equals("")) {
            holder.tvMaShipper.setVisibility(View.VISIBLE);
            holder.tvMaShipper.setText("Mã shipper: " + donHang.maShipper + "- Tên shipper: " + donHang.tenShipper);
            if (donHang.thuTien.trim().equals("")) {
                holder.tvTrangThai.setText(donHang.trangThai + " - Chưa trả hàng");
            } else {
                holder.tvTrangThai.setText(donHang.trangThai + " - " + donHang.thuTien);
            }
        } else {
            holder.tvMaShipper.setVisibility(View.GONE);
        }
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
