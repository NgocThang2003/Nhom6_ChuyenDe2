package com.example.nhom6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShipperDonHangDaHuy_Adapter extends RecyclerView.Adapter<ShipperDonHangDaHuy_Holder> {
    Context context;
    List<DonHang> data;
    public ShipperDonHangDaHuy_Adapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ShipperDonHangDaHuy_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShipperDonHangDaHuy_Holder(LayoutInflater.from(context).inflate(R.layout.item_shipperdonhangdahuy,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShipperDonHangDaHuy_Holder holder, int position) {
        DonHang donHang = data.get(position);

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

        holder.btnThongTinKH.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ThongTinNguoiNhan.class);
                intent.putExtra("Dia Chi", donHang.diaChi);
                MainActivity_ThongTinNguoiNhan.maNguoiDung = donHang.maKhachHang;
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
}
