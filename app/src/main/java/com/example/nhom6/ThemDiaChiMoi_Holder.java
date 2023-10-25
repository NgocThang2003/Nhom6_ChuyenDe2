package com.example.nhom6;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThemDiaChiMoi_Holder extends RecyclerView.ViewHolder {
   TextView tvDiaChi;
   CheckBox cbCheck;
    public ThemDiaChiMoi_Holder(@NonNull View itemView) {
        super(itemView);
        tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
        cbCheck = itemView.findViewById(R.id.cbCheck);

    }
}
