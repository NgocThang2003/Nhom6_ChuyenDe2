package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity_TrangChuThuKho extends AppCompatActivity {

    ImageView ivDanhSachDonHang, ivDonHangDaDongGoi, ivDonHangDaDuocShipperLayHang, ivDonHangDaGiao, ivDonHangBiHuy, ivDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchuthukho);

        setControl();
        setEvent();
    }

    private void setEvent() {

        ivDonHangDaDongGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_KhoDonHangDangDongGoi.class);
                startActivity(intent);
            }
        });

        ivDanhSachDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_KhoDonHangChoXacNhan.class);
                startActivity(intent);
            }
        });

        ivDonHangBiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_KhoDonHangDaHuy.class);
                startActivity(intent);
            }
        });

        ivDonHangDaDuocShipperLayHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_DonHangDangGiao_ThuKho.class);
                startActivity(intent);
            }
        });

        ivDonHangDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_KhoDonDaGiaoHang.class);
                startActivity(intent);
            }
        });

        ivDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        ivDanhSachDonHang = findViewById(R.id.ivDanhSachDonHang);
        ivDonHangDaDongGoi = findViewById(R.id.ivDonHangDaDongGoi);
        ivDonHangDaDuocShipperLayHang = findViewById(R.id.ivDonHangDaDuocShipperLayHang);

        ivDonHangDaGiao = findViewById(R.id.ivDonHangDaGiao);
        ivDonHangBiHuy = findViewById(R.id.ivDonHangBiHuy);
        ivDangXuat = findViewById(R.id.ivDangXuat);
    }
}