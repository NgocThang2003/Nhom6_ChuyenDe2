package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GioiThieu_Adapter extends RecyclerView.Adapter<GioiThieu_Holder> {
    Context context;
    List<GioiThieu> data;
    public GioiThieu_Adapter(Context context, List<GioiThieu> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GioiThieu_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GioiThieu_Holder(LayoutInflater.from(context).inflate(R.layout.item_gioithieu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GioiThieu_Holder holder, int position) {
        GioiThieu gioiThieu = data.get(position);
        holder.tvTen1.setText(gioiThieu.ten1);
        holder.tvTen2.setText(gioiThieu.ten2);
        holder.ivHinh1.setImageResource(gioiThieu.hinh1);
        holder.ivHinh2.setImageResource(gioiThieu.hinh2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
