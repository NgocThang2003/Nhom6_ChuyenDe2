package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SanPhamHolder extends RecyclerView.ViewHolder {

    ImageView ivHinh;

    TextView tvTenSP, tvChuThich, tvLoaiSP;


    public SanPhamHolder(@NonNull View itemView) {
        super(itemView);
        ivHinh = itemView.findViewById(R.id.ivHinh1);


        tvTenSP = itemView.findViewById(R.id.tvTenSP1);
        tvChuThich = itemView.findViewById(R.id.tvChuThich);
        tvLoaiSP = itemView.findViewById(R.id.tvLoaiSanPham);


    }
}
