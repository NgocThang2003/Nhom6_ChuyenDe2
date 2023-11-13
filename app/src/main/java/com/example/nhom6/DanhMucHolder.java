package com.example.nhom6;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhMucHolder extends RecyclerView.ViewHolder {
    TextView tvDanhMuc1, tvDanhMuc2, tvDanhMuc3;

    public DanhMucHolder(@NonNull View itemView) {
        super(itemView);

        tvDanhMuc1 = itemView.findViewById(R.id.tvdanhmuc1);
        tvDanhMuc2 = itemView.findViewById(R.id.tvdanhmuc2);
        tvDanhMuc3 = itemView.findViewById(R.id.tvdanhmuc3);

    }
}
