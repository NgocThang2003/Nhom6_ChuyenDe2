package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ThuocChoCayTrong_Holder extends RecyclerView.ViewHolder {
    ImageView imgHinh1, imgHinh2;
    TextView txtTenSP1, txtTenSP2, txtMoTa1, txtMoTa2, txtGiaSP1, txtGiaSP2;
    public ThuocChoCayTrong_Holder(@NonNull View itemView) {
        super(itemView);
        imgHinh1 = itemView.findViewById(R.id.imgHinh1);
        imgHinh2 = itemView.findViewById(R.id.imgHinh2);
        txtTenSP1 = itemView.findViewById(R.id.txtTenSP1);
        txtTenSP2 = itemView.findViewById(R.id.txtTenSP2);
        txtMoTa1 = itemView.findViewById(R.id.txtMoTa1);
        txtMoTa2 = itemView.findViewById(R.id.txtMoTa2);
        txtGiaSP1 = itemView.findViewById(R.id.txtGiaSP1);
        txtGiaSP2 = itemView.findViewById(R.id.txtGiaSP2);
    }
}
