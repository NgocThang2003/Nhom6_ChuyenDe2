package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DonHangChoXacNhan_BanHang_Adapter extends RecyclerView.Adapter<DonHangChoXacNhan_BanHang_Holder> {
    Context context;
    List<DonHangChoXacNhan_BanHang> data;
    public DonHangChoXacNhan_BanHang_Adapter( Context context, List<DonHangChoXacNhan_BanHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DonHangChoXacNhan_BanHang_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonHangChoXacNhan_BanHang_Holder(LayoutInflater.from(context).inflate(R.layout.item_donhangchoxacnhan_banhang,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangChoXacNhan_BanHang_Holder holder, int position) {
        DonHangChoXacNhan_BanHang donHangChoXacNhan_banHang = data.get(position);

        holder.tvTenKH.setText(donHangChoXacNhan_banHang.tenKH);
        holder.ivHinh.setImageResource(R.drawable.duahau);
        holder.tvDiaChi.setText(donHangChoXacNhan_banHang.diaChi);
        holder.tvTenSP.setText(donHangChoXacNhan_banHang.tenSP);
        holder.tvMoTa.setText(donHangChoXacNhan_banHang.moTa);
        holder.tvSDT.setText(donHangChoXacNhan_banHang.sdt);
        holder.tvGia.setText(donHangChoXacNhan_banHang.gia);
        holder.tvSoLuong.setText(donHangChoXacNhan_banHang.soLuong);
        holder.tvNgay.setText(donHangChoXacNhan_banHang.ngay);
        holder.tvTrangThai.setText(donHangChoXacNhan_banHang.trangThai);
        holder.tvThanhTien.setText(donHangChoXacNhan_banHang.thanhTien);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
