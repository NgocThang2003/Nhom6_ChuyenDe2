package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhgiaSP_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh;
    TextView tvTen,tvMoTa,tvGia,tvSoLuong;
    public DanhgiaSP_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh=itemView.findViewById(R.id.ivHinh1);
        tvTen=itemView.findViewById(R.id.tvTenHatGiong);
        tvMoTa=itemView.findViewById(R.id.tvMoTa);
        tvGia=itemView.findViewById(R.id.tvGia);
        tvSoLuong=itemView.findViewById(R.id.tvSoLuong);
    }
}
