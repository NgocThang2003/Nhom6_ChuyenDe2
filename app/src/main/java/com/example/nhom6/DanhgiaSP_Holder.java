package com.example.nhom6;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhgiaSP_Holder extends RecyclerView.ViewHolder {
    ImageView ivHinh,ivSao1,ivSao2,ivSao3,ivSao4,ivSao5;
    TextView tvTen,tvMoTa,tvGia,tvSoLuong,tvDiaChi,tvMotaShop,tvSua,tvNgay,tvTrangThaiSao, tvBinhLuanDanhGia;
    EditText edtBinhLuanDanhGia;
    Button btnDanhGia;

    public DanhgiaSP_Holder(@NonNull View itemView) {
        super(itemView);
        ivHinh=itemView.findViewById(R.id.ivHinhDanhGia);
        tvTen=itemView.findViewById(R.id.tvTenSPDG);
        tvMoTa=itemView.findViewById(R.id.tvTenMoTaDG);
        tvGia=itemView.findViewById(R.id.tvGiaDG);
        tvSoLuong=itemView.findViewById(R.id.tvSoLuongDG);
        tvDiaChi=itemView.findViewById(R.id.tvDiaChiDanhGia);
        edtBinhLuanDanhGia =itemView.findViewById(R.id.edtBinhLuanDanhGia);
        tvMotaShop=itemView.findViewById(R.id.tvMoTaDanhGia);
        tvSua=itemView.findViewById(R.id.tvSuaDG);
        btnDanhGia=itemView.findViewById(R.id.btnDanhGia_chua);
        tvNgay=itemView.findViewById(R.id.tvNgayDanhGia);
        ivSao1=itemView.findViewById(R.id.ivSao1);
        ivSao2=itemView.findViewById(R.id.ivSao2);
        ivSao3=itemView.findViewById(R.id.ivSao3);
        ivSao4=itemView.findViewById(R.id.ivSao4);
        ivSao5=itemView.findViewById(R.id.ivSao5);
        tvTrangThaiSao=itemView.findViewById(R.id.tvTrangThaiSao);
        tvBinhLuanDanhGia=itemView.findViewById(R.id.tvBinhLuanDanhGia);
    }
}
