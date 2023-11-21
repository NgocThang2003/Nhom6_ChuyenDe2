package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LienHeAdapter extends RecyclerView.Adapter<LienHeHolder> {
    Context context;
    List<LienHe> data;

    public LienHeAdapter(Context context, List<LienHe> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public LienHeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LienHeHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_gopytukhachhang,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LienHeHolder holder, int position) {
        LienHe lienHe = data.get(position);
        holder.tvTen.setText(lienHe.getTen());
        holder.tvSDT.setText(lienHe.getSdt());
        holder.tvNgay.setText(lienHe.getDate());
        holder.tvGmail.setText(lienHe.getEmail());

        holder.tvCongTy.setText(lienHe.getCongTy());
        holder.tvGopY.setText(lienHe.getGopY());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
