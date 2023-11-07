package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangHolder> {

    Context context;
    List<GioHang> data = new ArrayList<>();

    public GioHangAdapter(Context context, List<GioHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GioHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GioHangHolder(LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangHolder holder, int position) {
       GioHang gioHang = data.get(position);

       holder.tvTenSP.setText(gioHang.getTenSanPham());
       holder.tvChuThich.setText(gioHang.getMoTa());
       holder.tvKhoiLuong.setText(gioHang.getKhoiLuong());
       holder.tvDonVi.setText(gioHang.getDonVi());
       holder.tvGia.setText(gioHang.getGia());
       holder.tvSoLuong.setText(gioHang.getSoLuong());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
