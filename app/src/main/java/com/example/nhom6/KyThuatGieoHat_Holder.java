package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KyThuatGieoHat_Holder extends RecyclerView.ViewHolder {
    //ImageView ivHinh;
    TextView tvTenKyThuat, tvMoTa;
    public KyThuatGieoHat_Holder(@NonNull View itemView) {
        super(itemView);
        tvTenKyThuat =itemView.findViewById(R.id.tvTenKyThuat);
        tvMoTa =itemView.findViewById(R.id.tvMoTa);
    }
}
