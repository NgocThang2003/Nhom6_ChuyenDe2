package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiongCayTrong_Adapter extends RecyclerView.Adapter<GiongCayTrong_Holder> {
    Context context;
    List<SanPham> data;
    public GiongCayTrong_Adapter(Context context,  List<SanPham> data) {
        this.context =  context;
        this.data = data;
    }

    @NonNull
    @Override
    public GiongCayTrong_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GiongCayTrong_Holder(LayoutInflater.from(context).inflate(R.layout.item_giongcaytrong,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GiongCayTrong_Holder holder, int position) {
        SanPham sanPham = data.get(position);
        holder.ivHinh1.setImageResource(R.drawable.giongngo);
        holder.tvTenSP1.setText(sanPham.tenSP);
        holder.tvGia1.setText(sanPham.gia);

        holder.ivHinh2.setImageResource(R.drawable.giongngo);

        holder.tvTenSP2.setText(sanPham.tenSP);

        holder.tvGia2.setText(sanPham.gia);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
