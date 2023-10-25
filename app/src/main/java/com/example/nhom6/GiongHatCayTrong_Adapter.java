package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiongHatCayTrong_Adapter extends RecyclerView.Adapter<GiongHatCayTrong_Holder> {
    Context context;
    List<GiongHatCayTrong> data;

    public GiongHatCayTrong_Adapter(Context context, List<GiongHatCayTrong> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GiongHatCayTrong_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GiongHatCayTrong_Holder(LayoutInflater.from(context).inflate(R.layout.item_gionghatcaytrong, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GiongHatCayTrong_Holder holder, int position) {
        GiongHatCayTrong giongHatCayTrong = data.get(position);
        holder.tvTenSP1.setText(giongHatCayTrong.tenSP1);
        holder.tvTenSP2.setText(giongHatCayTrong.tenSP2);
        holder.tvGiaSP1.setText(giongHatCayTrong.giaSP1);
        holder.tvGiaSP2.setText(giongHatCayTrong.giaSP2);
        holder.ivHinh1.setImageResource(giongHatCayTrong.hinh1);
        holder.ivHinh2.setImageResource(giongHatCayTrong.hinh2);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

