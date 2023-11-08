package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Thuoc_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh1, ivHinh2;
    TextView tvTenSP1, tvTenSP2, tvMoTa1, tvMoTa2, tvGia1, tvGia2;
    public Thuoc_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh1 = itemView.findViewById(R.id.ivHinh1);
        ivHinh2 = itemView.findViewById(R.id.ivHinh2);
        tvTenSP1 = itemView.findViewById(R.id.tvTenSP1);
        tvTenSP2 = itemView.findViewById(R.id.tvTenSP2);
        tvMoTa1 = itemView.findViewById(R.id.tvMoTa1);
        tvMoTa2 = itemView.findViewById(R.id.tvMoTa2);
        tvGia1 = itemView.findViewById(R.id.tvGia1);
        tvGia2 = itemView.findViewById(R.id.tvGia2);
    }
}
