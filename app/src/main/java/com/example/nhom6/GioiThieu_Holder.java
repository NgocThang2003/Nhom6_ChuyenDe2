package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GioiThieu_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh1, ivHinh2;
    TextView tvTen1, tvTen2;
    public GioiThieu_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh1 = itemView.findViewById(R.id.ivHinh1);
        ivHinh2 = itemView.findViewById(R.id.ivHinh2);
        tvTen1 = itemView.findViewById(R.id.tvTen1);
        tvTen2 = itemView.findViewById(R.id.tvTen2);



    }
}
