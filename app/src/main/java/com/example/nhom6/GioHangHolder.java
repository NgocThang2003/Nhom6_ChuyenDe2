package com.example.nhom6;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GioHangHolder extends RecyclerView.ViewHolder {

    TextView tvTenSP, tvKhoiLuong,tvDonVi, tvChuThich, tvSoLuong, tvGia;
    ImageView ivHinh;
    Button btnCong , btnTru, btnXoa;


    public GioHangHolder(@NonNull View itemView) {
        super(itemView);

        tvTenSP = itemView.findViewById(R.id.tvTenSP);
        tvKhoiLuong = itemView.findViewById(R.id.tvKhoiLuong);
        tvDonVi = itemView.findViewById(R.id.tvDonVi);
        tvChuThich = itemView.findViewById(R.id.tvMoTa);

        tvSoLuong = itemView.findViewById(R.id.tvSL);
        tvGia = itemView.findViewById(R.id.tvGia);
        ivHinh = itemView.findViewById(R.id.ivHinh);

        btnCong = itemView.findViewById(R.id.btnCong);
        btnTru = itemView.findViewById(R.id.btnTru);
        btnXoa = itemView.findViewById(R.id.btnXoa);

    }
}
