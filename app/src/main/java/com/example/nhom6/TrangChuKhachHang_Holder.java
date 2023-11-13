package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrangChuKhachHang_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh;
    TextView tvTenKyThuat, tvMoTa;
    public TrangChuKhachHang_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh =itemView.findViewById(R.id.ivHinh);
        tvTenKyThuat =itemView.findViewById(R.id.tvTenKyThuat);
        tvMoTa =itemView.findViewById(R.id.tvMoTa);
    }
}
