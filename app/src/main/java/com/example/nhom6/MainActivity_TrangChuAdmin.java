package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity_TrangChuAdmin extends AppCompatActivity {

    ImageView ivQuanLySanPham, ivQuanLyNhanVien, ivQuanLyDoanhMuc, ivThongKeSanPham, ivThongKeDoanhThu, ivQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu_admin);

        setControl();
        setEvent();
    }

    private void setEvent() {

        ivQuanLySanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuAdmin.this, MainActivity_QuanTriSanPham.class);
                startActivity(intent);
            }
        });

        ivThongKeSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuAdmin.this, MainActivity_ThongKeSanPham.class);
                startActivity(intent);
            }
        });
        ivQuanLyNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuAdmin.this, MainActivity_QuanTriNhanVien.class);
                startActivity(intent);
            }
        });

        ivQuanLyDoanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuAdmin.this, MainActivity_QuanTriChungCacDanhMuc.class);
                startActivity(intent);
            }
        });

        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        ivQuanLyDoanhMuc = findViewById(R.id.ivQuanLyChungCacDanhMuc);
        ivQuanLySanPham = findViewById(R.id.ivQuanLySanPham);
        ivQuanLyNhanVien = findViewById(R.id.ivQuanLyNhanVien);

        ivThongKeDoanhThu = findViewById(R.id.ivThongKeDoanhThu);
        ivThongKeSanPham = findViewById(R.id.ivThongKeSanPham);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }
}