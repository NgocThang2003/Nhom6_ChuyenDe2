package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamHolder> {
    Context context;
    List<SanPham> data;

    public SanPhamAdapter(Context context, List<SanPham> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SanPhamHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_sanpham, parent, false));
    }

    public static int count = 0;

    @Override
    public void onBindViewHolder(@NonNull SanPhamHolder holder, int position) {

        SanPham sanPham = data.get(count);


        holder.ivHinh1.setImageResource(Integer.parseInt(sanPham.hinh.trim()));
        holder.tvSP1.setText(sanPham.tenSP.trim());

        count = count + 1;
        if (count < data.size()) {

            sanPham = data.get(count);
            holder.tvSP2.setText(sanPham.tenSP.trim());
            holder.ivHinh2.setImageResource(Integer.parseInt(sanPham.hinh.trim()));

        } else {
            holder.tvSP2.setVisibility(View.INVISIBLE);
            holder.ivHinh2.setVisibility(View.INVISIBLE);
        }

        count = count + 1;
        if (count < data.size()) {

            sanPham = data.get(count);
            holder.tvSP3.setText(sanPham.tenSP.trim());
            holder.ivHinh3.setImageResource(Integer.parseInt(sanPham.hinh.trim()));

        } else {
            holder.tvSP3.setVisibility(View.INVISIBLE);
            holder.ivHinh3.setVisibility(View.INVISIBLE);
        }
        count = count + 1;

    }

    @Override
    public int getItemCount() {
        if (data.size() % 3 == 2 || data.size() % 3 == 1) {
            return data.size() / 3 + 1;
        } else {
            return data.size() / 3;
        }
    }
}
