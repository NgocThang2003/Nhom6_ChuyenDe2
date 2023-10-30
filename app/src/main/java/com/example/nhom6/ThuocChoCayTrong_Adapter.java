package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThuocChoCayTrong_Adapter extends RecyclerView.Adapter<ThuocChoCayTrong_Holder> {
    Context context;
    List<ThuocChoCayTrong> data;
    public ThuocChoCayTrong_Adapter( Context context,List<ThuocChoCayTrong> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ThuocChoCayTrong_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThuocChoCayTrong_Holder(LayoutInflater.from(context).inflate(R.layout.item_gionghatcaytrong, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThuocChoCayTrong_Holder holder, int position) {
        ThuocChoCayTrong thuocChoCayTrong = data.get(position);
        holder.tvTenSP1.setText(thuocChoCayTrong.tenSP1);
        holder.tvTenSP2.setText(thuocChoCayTrong.tenSP2);
        holder.tvMoTa1.setText(thuocChoCayTrong.moTa1);
        holder.tvMoTa2.setText(thuocChoCayTrong.moTa2);
        holder.tvGia1.setText(thuocChoCayTrong.gia1);
        holder.tvGia2.setText(thuocChoCayTrong.gia2);
        holder.ivHinh1.setImageResource(thuocChoCayTrong.hinh1);
        holder.ivHinh2.setImageResource(thuocChoCayTrong.hinh2);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
