package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class TrangChuKhachHang_Adapter extends RecyclerView.Adapter<TrangChuKhachHang_Holder> {
    Context context;

    public TrangChuKhachHang_Adapter(Context context, List<TrangChuKhachHang> data) {
        this.context = context;
        this.data = data;
    }

    List<TrangChuKhachHang> data;
    @NonNull
    @Override
    public TrangChuKhachHang_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrangChuKhachHang_Holder(LayoutInflater.from(context).inflate(R.layout.item_trangchukhachhang,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrangChuKhachHang_Holder holder, int position) {
        TrangChuKhachHang trangChuKhachHang = data.get(position);
        holder.tvMoTa.setText(trangChuKhachHang.tenMoTa);
        holder.tvTenKyThuat.setText(trangChuKhachHang.tenKyThuat);
        holder.ivHinh.setImageResource(trangChuKhachHang.hinh);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
