package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SanPhamHolder extends RecyclerView.ViewHolder {

    ImageView ivHinh1;
    ImageView ivHinh2, ivHinh3;
    TextView tvSP1;
    TextView tvSP2, tvSP3;

    public SanPhamHolder(@NonNull View itemView) {
        super(itemView);
        ivHinh1 = itemView.findViewById(R.id.ivHinh1);
        ivHinh2 = itemView.findViewById(R.id.ivHinh2);
        ivHinh3 = itemView.findViewById(R.id.ivHinh3);

        tvSP1 = itemView.findViewById(R.id.tvTenSP1);
        tvSP2 = itemView.findViewById(R.id.tvTenSP2);
        tvSP3 = itemView.findViewById(R.id.tvTenSP3);

    }
}
