package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DanhGiaSP_Adappter extends RecyclerView.Adapter<DanhgiaSP_Holder>{
    Context context;
    public DanhGiaSP_Adappter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }
    List<DonHang> data;
    @NonNull
    @Override
    public DanhgiaSP_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DanhgiaSP_Holder(LayoutInflater.from(context).inflate(R.layout.item_danhgia,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DanhgiaSP_Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}