package com.example.nhom6;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThemDiaChiMoi_Holder extends RecyclerView.ViewHolder {
   TextView tvTen, tvSDT, tvTinh, tvQuan, tvPhuong, tvSoNha;
   LinearLayout linearLayoutChonDiaChi;
  CheckBox cbCheck;
    public ThemDiaChiMoi_Holder(@NonNull View itemView) {
        super(itemView);
        tvTen = itemView.findViewById(R.id.tvTen);
        tvSDT = itemView.findViewById(R.id.tvSDT);
        tvTinh = itemView.findViewById(R.id.tvTinh);
        tvQuan = itemView.findViewById(R.id.tvQuan);
        tvPhuong = itemView.findViewById(R.id.tvPhuong);
        tvSoNha = itemView.findViewById(R.id.tvSoNha);
        cbCheck = itemView.findViewById(R.id.cbCheck);
        linearLayoutChonDiaChi = itemView.findViewById(R.id.linearLayoutChonDiaChi);
    }
}
