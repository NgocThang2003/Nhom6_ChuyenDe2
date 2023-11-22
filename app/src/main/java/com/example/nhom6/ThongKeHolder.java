package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ThongKeHolder extends RecyclerView.ViewHolder {
    TextView tvTen, tvGia, tvDaBan, tvChuThich,tvSoLuong;
    ImageView ivHinh;

    public ThongKeHolder(@NonNull View itemView) {
        super(itemView);

        tvTen = itemView.findViewById(R.id.tvTen);
        tvGia = itemView.findViewById(R.id.tvGia);
        tvDaBan = itemView.findViewById(R.id.tvDaBan);
        tvChuThich = itemView.findViewById(R.id.tvChuThich);
        tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        ivHinh = itemView.findViewById(R.id.ivHinh);
    }
}
