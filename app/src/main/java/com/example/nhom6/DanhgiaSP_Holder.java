package com.example.nhom6;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhgiaSP_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh;
    TextView tvTen,tvMoTa,tvGia,tvSoLuong,tvDiaChi,tvBinhLuanDanhGia,tvMotaShop,tvSua;
    Button btnDanhGia;
    public DanhgiaSP_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh=itemView.findViewById(R.id.ivHinhDanhGia);
        tvTen=itemView.findViewById(R.id.tvTenSPDG);
        tvMoTa=itemView.findViewById(R.id.tvTenMoTaDG);
        tvGia=itemView.findViewById(R.id.tvGiaDG);
        tvSoLuong=itemView.findViewById(R.id.tvSoLuongDG);
        tvDiaChi=itemView.findViewById(R.id.tvDiaChiDanhGia);
        tvBinhLuanDanhGia=itemView.findViewById(R.id.tvBinhLuanDanhGia);
        tvMotaShop=itemView.findViewById(R.id.tvMoTaDanhGia);
        tvSua=itemView.findViewById(R.id.tvSuaDG);
        btnDanhGia=itemView.findViewById(R.id.btnDanhGia_chua);
    }
}
