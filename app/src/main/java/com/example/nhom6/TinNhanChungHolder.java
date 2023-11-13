package com.example.nhom6;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class TinNhanChungHolder extends RecyclerView.ViewHolder {

    TextView tvThoiGian,tvTinNhan1, tvTinNhan2;
    LinearLayout linearLayoutTinNhan;
    ImageView ivHinhTinNhan1, ivHinhTinNhan2;
    public TinNhanChungHolder(@NonNull View itemView) {
        super(itemView);

        tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
        tvTinNhan1 = itemView.findViewById(R.id.tvTinNhan1);
        tvTinNhan2 = itemView.findViewById(R.id.tvTinNhan2);

        ivHinhTinNhan1 = itemView.findViewById(R.id.ivHinhTinNhan1);
        ivHinhTinNhan2 = itemView.findViewById(R.id.ivHinhTinNhan2);
        linearLayoutTinNhan = itemView.findViewById(R.id.linearLayoutTinNhan);

    }
}
