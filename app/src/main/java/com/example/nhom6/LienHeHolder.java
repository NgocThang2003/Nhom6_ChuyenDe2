package com.example.nhom6;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LienHeHolder extends RecyclerView.ViewHolder {
    TextView tvTen,tvNgay,tvSDT, tvGmail, tvCongTy, tvGopY;
    public LienHeHolder(@NonNull View itemView) {
        super(itemView);
        tvTen = itemView.findViewById(R.id.tvTen);
        tvNgay = itemView.findViewById(R.id.tvNgay);
        tvSDT = itemView.findViewById(R.id.tvSDT);
        tvGmail = itemView.findViewById(R.id.tvGmail);
        tvCongTy = itemView.findViewById(R.id.tvCongTy);
        tvGopY = itemView.findViewById(R.id.tvGopY);
    }
}
