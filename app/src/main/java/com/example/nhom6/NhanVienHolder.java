package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NhanVienHolder extends RecyclerView.ViewHolder {

    TextView tvSTT, tvNgaySinh, tvTenNV, tvSDT;
    ImageView ivHinh;
    public NhanVienHolder(@NonNull View itemView) {
        super(itemView);

        tvSTT = itemView.findViewById(R.id.tvSTT);
        tvNgaySinh = itemView.findViewById(R.id.tvNgaySinh);
        tvTenNV = itemView.findViewById(R.id.tvTenNV);
        tvSDT = itemView.findViewById(R.id.tvSDT);
        ivHinh = itemView.findViewById(R.id.ivHinhNV);


    }
}
