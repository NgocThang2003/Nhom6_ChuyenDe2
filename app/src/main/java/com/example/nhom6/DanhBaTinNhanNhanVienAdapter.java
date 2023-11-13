package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DanhBaTinNhanAdapter extends RecyclerView.Adapter<DanhBaTinNhanNhanVienHolder> {
    Context context;
    List<TinNhanNhanVien> data;
    @NonNull
    @Override
    public DanhBaTinNhanNhanVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DanhBaTinNhanNhanVienHolder(LayoutInflater.from(context).inflate(R.layout.item_danhbatinnhan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DanhBaTinNhanNhanVienHolder holder, int position) {
        TinNhanNhanVien tinNhanNhanVien = data.get(position);
        holder.tvHoTen.setText(tinNhanNhanVien.hoTenKhachHang);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
