package com.example.nhom6;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GiongCayTrong_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh1, ivHinh2;
    TextView tvTenSP1, tvTenSP2, tvGia1, tvGia2;
    Button btnDatMua2;
    LinearLayout linearLayoutTC;

    Button btn2 ;
    public GiongCayTrong_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh1 = itemView.findViewById(R.id.ivHinh1);
        ivHinh2 = itemView.findViewById(R.id.ivHinh2);
        tvTenSP1 = itemView.findViewById(R.id.tvTenSP1);
        tvTenSP2 = itemView.findViewById(R.id.tvTenSP2);
        tvGia1 = itemView.findViewById(R.id.tvGia1);
        tvGia2 = itemView.findViewById(R.id.tvGia2);
        linearLayoutTC = itemView.findViewById(R.id.linearLayoutTrangChu);

        btnDatMua2 =itemView.findViewById(R.id.btnDatMua2);
    }
}
