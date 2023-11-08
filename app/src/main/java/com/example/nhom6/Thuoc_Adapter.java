package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Thuoc_Adapter extends RecyclerView.Adapter<Thuoc_Holder> {
    Context context;
    List<Thuoc> data;
    public Thuoc_Adapter( Context context,  List<Thuoc> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Thuoc_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Thuoc_Holder(LayoutInflater.from(context).inflate(R.layout.item_thuoc,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Thuoc_Holder holder, int position) {
        Thuoc thuoc = data.get(position);
        holder.ivHinh1.setImageResource(thuoc.hinh1);
        holder.ivHinh2.setImageResource(thuoc.hinh2);
        holder.tvTenSP1.setText(thuoc.tenSP1);
        holder.tvTenSP2.setText(thuoc.tenSP2);
        holder.tvMoTa1.setText(thuoc.moTa1);
        if(thuoc.moTa1.trim().length() > 20){
            holder.tvMoTa1.setText(thuoc.moTa1.substring(0,20) + "...");
        }
        holder.tvMoTa2.setText(thuoc.moTa2);
        if(thuoc.moTa2.trim().length() > 20){
            holder.tvMoTa2.setText(thuoc.moTa2.substring(0,20) + "...");
        }
        holder.tvGia1.setText(thuoc.gia1);
        holder.tvGia2.setText(thuoc.gia2);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
