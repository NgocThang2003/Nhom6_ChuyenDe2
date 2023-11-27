package com.example.nhom6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChonDiaChi_Adapter extends RecyclerView.Adapter<ThemDiaChiMoi_Holder> {
    Context context;
    List<ThemDiaChiMoi> data;

    public  static  String diaChi = "53 Vo Van Ngan";

    public ChonDiaChi_Adapter(Context context, List<ThemDiaChiMoi> data) {
        this.context = context;
        this.data = data;
    }
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListenner(OnItemClickListener listenner) {
        mListener = listenner;
    }


    @NonNull
    @Override
    public ThemDiaChiMoi_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThemDiaChiMoi_Holder(LayoutInflater.from(context).inflate(R.layout.item_themdiachimoi,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThemDiaChiMoi_Holder holder, int position) {
        ThemDiaChiMoi themDiaChiMoi = data.get(position);
        holder.tvTen.setText(themDiaChiMoi.getTen());
        holder.tvSDT.setText(themDiaChiMoi.getSdt());
        holder.tvTinh.setText(themDiaChiMoi.getTinh());
        holder.tvQuan.setText(themDiaChiMoi.getQuan());
        holder.tvPhuong.setText(themDiaChiMoi.getPhuong());
        holder.tvSoNha.setText(themDiaChiMoi.getSoNha());
        holder.cbCheck.setText(themDiaChiMoi.getCheck());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    MainActivity_GioHang.tvDiaChiGiaoHang.setText("Họ tên: " + themDiaChiMoi.ten + " - SDT: " + themDiaChiMoi.sdt + ", Địa chỉ: " + themDiaChiMoi.soNha + ", " + themDiaChiMoi.tinh + ", " + themDiaChiMoi.quan + ", " + themDiaChiMoi.phuong);
                    mListener.onItemClick(position);

                }
            }
        });

//        holder.linearLayoutChonDiaChi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                diaChi = "Họ tên: "+themDiaChiMoi.ten+" - SDT: "+themDiaChiMoi.sdt+", "+themDiaChiMoi.soNha+", "+themDiaChiMoi.tinh+", "+themDiaChiMoi.quan+", "+themDiaChiMoi.phuong;
//                Intent intent = new Intent(context,MainActivity_ChiTietSanPham.class);
//                MainActivity_ChiTietSanPham.tvDiaChi.setText(diaChi);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
