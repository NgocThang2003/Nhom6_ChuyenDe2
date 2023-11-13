package com.example.nhom6;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucHolder> {
    Context context;
    List<DanhMuc> data = new ArrayList<>();

    public DanhMucAdapter(Context context, List<DanhMuc> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DanhMucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DanhMucHolder(LayoutInflater.from(context).inflate(R.layout.item_danhmuc_donhang,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucHolder holder, int position) {
        DanhMuc danhMuc = data.get(position);
        holder.tvDanhMuc1.setText(danhMuc.getTenDangMuc1());

        if(danhMuc.getDangChon1().toString().equals("1")){
            holder.tvDanhMuc1.setTextColor(Color.WHITE);
            holder.tvDanhMuc1.setBackgroundColor(Color.rgb(95,166,93));
        }
        else{
            holder.tvDanhMuc1.setTextColor(Color.BLACK);
            holder.tvDanhMuc1.setBackgroundColor(Color.WHITE);
        }

        if(danhMuc.getDangChon2().toString().equals("1")){
            holder.tvDanhMuc2.setTextColor(Color.WHITE);
            holder.tvDanhMuc2.setBackgroundColor(Color.rgb(95,166,93));
        }
        else{
            holder.tvDanhMuc2.setTextColor(Color.BLACK);
            holder.tvDanhMuc2.setBackgroundColor(Color.WHITE);
        }

        if(danhMuc.getDangChon3().toString().equals("1")){
            holder.tvDanhMuc3.setTextColor(Color.WHITE);
            holder.tvDanhMuc3.setBackgroundColor(Color.rgb(95,166,93));
        }
        else{
            holder.tvDanhMuc3.setTextColor(Color.BLACK);
            holder.tvDanhMuc3.setBackgroundColor(Color.WHITE);
        }

        holder.tvDanhMuc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).setDangChon1("1");
                data.get(position).setDangChon2("0");
                data.get(position).setDangChon3("0");
                if(position == 0){
                    MainActivity_DonHang.DocDL();
                    data.get(1).setDangChon3("0");
                    data.get(1).setDangChon1("0");
                    data.get(1).setDangChon2("0");
                }
                if(position == 1){
                    MainActivity_DonHang.DocDLDonHangDangGiaoHang();
                    data.get(0).setDangChon3("0");
                    data.get(0).setDangChon1("0");
                    data.get(0).setDangChon2("0");
                }
                MainActivity_DonHang.rcvRecyclerViewDanhMuc.getAdapter().notifyDataSetChanged();
            }
        });
        holder.tvDanhMuc2.setText(danhMuc.getTenDangMuc2());

        holder.tvDanhMuc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).setDangChon2("1");

                data.get(position).setDangChon1("0");
                data.get(position).setDangChon3("0");

                if(position == 0){
                    MainActivity_DonHang.DocDLDonHangDangChoXacNhan();
                    data.get(1).setDangChon3("0");
                    data.get(1).setDangChon1("0");
                    data.get(1).setDangChon2("0");
                }
                if(position == 1){
                    MainActivity_DonHang.DocDLDonHangDaGiaoHang();
                    data.get(0).setDangChon3("0");
                    data.get(0).setDangChon1("0");
                    data.get(0).setDangChon2("0");
                }
                MainActivity_DonHang.rcvRecyclerViewDanhMuc.getAdapter().notifyDataSetChanged();
            }
        });

        holder.tvDanhMuc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).setDangChon3("1");
                data.get(position).setDangChon1("0");
                data.get(position).setDangChon2("0");
                if(position == 0){
                    MainActivity_DonHang.DocDLDonHangDangDongGoi();
                    data.get(1).setDangChon3("0");
                    data.get(1).setDangChon1("0");
                    data.get(1).setDangChon2("0");
                }
                if(position == 1){
                    MainActivity_DonHang.DocDLDonHangDaHuy();
                    data.get(0).setDangChon3("0");
                    data.get(0).setDangChon1("0");
                    data.get(0).setDangChon2("0");
                }
                MainActivity_DonHang.rcvRecyclerViewDanhMuc.getAdapter().notifyDataSetChanged();
            }
        });
        holder.tvDanhMuc3.setText(danhMuc.getTenDangMuc3());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
