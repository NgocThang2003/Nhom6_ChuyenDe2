package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiongCayTrong_Adapter extends RecyclerView.Adapter<GiongCayTrong_Holder> {
    Context context;
    List<GiongCayTrong> data;
    public GiongCayTrong_Adapter(Context context,  List<GiongCayTrong> data) {
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
        GiongCayTrong giongCayTrong = data.get(position);
        holder.ivHinh1.setImageResource(Integer.parseInt(giongCayTrong.hinh1));
        holder.ivHinh2.setImageResource(Integer.parseInt(giongCayTrong.hinh2));
        holder.tvTenSP1.setText(giongCayTrong.tenSP1);
        holder.tvTenSP2.setText(giongCayTrong.tenSP2);
        holder.tvGia1.setText(giongCayTrong.gia1);
        holder.tvGia2.setText(giongCayTrong.gia2);


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
