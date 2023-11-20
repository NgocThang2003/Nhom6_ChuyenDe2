package com.example.nhom6;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhanHoiSanPhamHolder extends RecyclerView.ViewHolder {
    TextView tvTenKH,tvSDT,tvTenSP,tvGia,tvMoTa,tvSoLuong,tvTrangThai,tvThanhTien,tvDanhGia,tvGui,tvDiaChi,tvNgay;
    ImageView ivHinh;
    EditText edtPhanHoi;
    public PhanHoiSanPhamHolder(@NonNull View itemView) {
        super(itemView);

        tvTenKH = itemView.findViewById(R.id.tvTenKH);
        tvSDT = itemView.findViewById(R.id.tvSDT);
        tvTenSP = itemView.findViewById(R.id.tvTenSP);
        tvGia = itemView.findViewById(R.id.tvGia);
        tvMoTa = itemView.findViewById(R.id.tvMoTa);
        tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
        tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
        tvDanhGia = itemView.findViewById(R.id.tvDanhGia);
        edtPhanHoi = itemView.findViewById(R.id.edtPhanHoi);
        tvGui = itemView.findViewById(R.id.tvGui);
        tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
        tvNgay = itemView.findViewById(R.id.tvNgay);

        ivHinh = itemView.findViewById(R.id.ivHinh);
    }
}
