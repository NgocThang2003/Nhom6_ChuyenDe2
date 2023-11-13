package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhBaTinNhanNhanVienHolder extends RecyclerView.ViewHolder {
    TextView tvHoTen, tvTinNhan, tvThoiGian;
    ImageView ivHinhDanhBa;


    public DanhBaTinNhanNhanVienHolder(@NonNull View itemView) {
        super(itemView);
        tvHoTen = itemView.findViewById(R.id.tvHoTen);
        tvTinNhan = itemView.findViewById(R.id.tvTinNhan);
        tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
        ivHinhDanhBa = itemView.findViewById(R.id.ivHinhDanhBa);
    }
}
