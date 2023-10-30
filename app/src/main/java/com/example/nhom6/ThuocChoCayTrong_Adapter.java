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
        return new ThuocChoCayTrong_Holder(LayoutInflater.from(context).inflate(R.layout.item_thuocchocaytrong, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThuocChoCayTrong_Holder holder, int position) {
        ThuocChoCayTrong thuocChoCayTrong = data.get(position);
        holder.txtTenSP1.setText(thuocChoCayTrong.tenSP1);
        holder.txtTenSP2.setText(thuocChoCayTrong.tenSP2);
        holder.txtMoTa1.setText(thuocChoCayTrong.moTa1);
        holder.txtMoTa2.setText(thuocChoCayTrong.moTa2);
        holder.txtGiaSP1.setText(thuocChoCayTrong.gia1);
        holder.txtGiaSP2.setText(thuocChoCayTrong.gia2);
        holder.imgHinh1.setImageResource(thuocChoCayTrong.hinh1);
        holder.imgHinh2.setImageResource(thuocChoCayTrong.hinh2);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
