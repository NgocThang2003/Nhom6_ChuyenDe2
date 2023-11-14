package com.example.nhom6;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KhoDonHangChoXacNhan_Holder extends RecyclerView.ViewHolder {
    Button btnGoiDien, btnTinNhan, btnXacNhanDonHang, btnHuy;
    ImageView ivHinh;
    TextView tvTenKH, tvDiaChi, tvTenSP, tvMoTa, tvSDT, tvGia, tvSoLuong, tvNgay, tvTrangThai, tvThanhTien,tvLyDoHuyDon, tvTonKho;
    public KhoDonHangChoXacNhan_Holder(@NonNull View itemView) {
        super(itemView);
        btnGoiDien = itemView.findViewById(R.id.btnGoiDien);
        btnTinNhan = itemView.findViewById(R.id.btnNhanTin);
        btnXacNhanDonHang = itemView.findViewById(R.id.btnXacNhanDonHang);
        btnHuy = itemView.findViewById(R.id.btnHuyDonHang);

        tvTenKH = itemView.findViewById(R.id.tvTenKH);
        tvMoTa = itemView.findViewById(R.id.tvMoTa);
        ivHinh = itemView.findViewById(R.id.ivHinh);
        tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
        tvTenSP = itemView.findViewById(R.id.tvTenSP);
        tvSDT = itemView.findViewById(R.id.tvSDT);
        tvGia = itemView.findViewById(R.id.tvGia);
        tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        tvNgay = itemView.findViewById(R.id.tvNgay);
        tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
        tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
        tvLyDoHuyDon = itemView.findViewById(R.id.tvLyDoHuyDon);
        tvTonKho = itemView.findViewById(R.id.tvTonKho);
    }
}
