package com.example.nhom6;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonHangHolder extends RecyclerView.ViewHolder {
    TextView tvTenSP,tvMoTa,tvGia, tvSoLuong, tvNgay, tvTrangThai, tvGiaDH, tvDiaChi,tvLyDo;

    //Button btnHuyDongHang,btnDanhGia;

    ImageView ivHinh;
    Button btnDonHang;
    public DonHangHolder(@NonNull View itemView) {
        super(itemView);
        tvTenSP=itemView.findViewById(R.id.tvTenSPDonHang);
        tvMoTa=itemView.findViewById(R.id.tvMoTaDonhang);
        tvGia=itemView.findViewById(R.id.tvGiaDonHang);
        tvSoLuong =itemView.findViewById(R.id.tvSoLuongDonHang);
        tvNgay =itemView.findViewById(R.id.tvNgayDonHang);
        tvTrangThai =itemView.findViewById(R.id.tvTrangThai);
        tvGiaDH =itemView.findViewById(R.id.tvTongGiaDonHang);
        tvDiaChi =itemView.findViewById(R.id.tvDiaChiDonHang);
        tvLyDo =itemView.findViewById(R.id.tvLyDo);

        ivHinh =itemView.findViewById(R.id.ivHinhDonHang);
        btnDonHang = itemView.findViewById(R.id.btndonHang);



    }
}
