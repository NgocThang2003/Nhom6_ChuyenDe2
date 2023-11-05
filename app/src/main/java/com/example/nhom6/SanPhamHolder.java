package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SanPhamHolder extends RecyclerView.ViewHolder {

    ImageView ivHinh;

    TextView tvTenSP, tvChuThich, tvLoaiSP, tvDonVi, tvKhoiLuong, tvGia, tvSoLuong;


    public SanPhamHolder(@NonNull View itemView) {
        super(itemView);
        ivHinh = itemView.findViewById(R.id.ivHinh1);

        tvTenSP = itemView.findViewById(R.id.tvTenSP1);
        tvChuThich = itemView.findViewById(R.id.tvChuThich);
        tvLoaiSP = itemView.findViewById(R.id.tvLoaiSanPham);

        tvDonVi = itemView.findViewById(R.id.tvDonVi);
        tvKhoiLuong = itemView.findViewById(R.id.tvKhoiLuong);
        tvGia = itemView.findViewById(R.id.tvGia);

        tvSoLuong = itemView.findViewById(R.id.tvSL);

    }
}
