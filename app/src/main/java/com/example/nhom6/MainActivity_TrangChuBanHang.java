package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity_TrangChuBanHang extends AppCompatActivity {

    ImageView ivQuanTriKyThuat, ivTuVan, ivDonHangBiHuy,ivGopY,ivDanhSachDonHang,ivPhanHoiDanhGia;
    LinearLayout linearLayoutDangXuat;
    Button btnDangXuat;
    ImageButton ivGoiDien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu_ban_hang);

        setControl();
        setEvent();
    }

    private void setEvent() {
        ivQuanTriKyThuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_QuanTriKyThuat.class);
                startActivity(intent);
            }
        });

        ivTuVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_DanhBaTinNhan_NhanVien.class);
                startActivity(intent);
            }
        });
        ivDonHangBiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_KhoDonHangDaHuy.class);
                startActivity(intent);
            }
        });

        ivGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_GopYTuKhachHang_BanHang.class);
                startActivity(intent);
            }
        });

        ivDanhSachDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_DanhSachDonHang_BanHang.class);

                startActivity(intent);
            }
        });

        ivPhanHoiDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_PhanHoiDanhGiaKhachHang_BanHang.class);
                startActivity(intent);
            }
        });


        linearLayoutDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_TrangChuBanHang.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn đăng xuất khỏi tài khoản này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(MainActivity_TrangChuBanHang.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });
        ivGoiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuBanHang.this, MainActivity_GoiDien.class);
                startActivity(intent);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_TrangChuBanHang.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn đăng xuất khỏi tài khoản này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(MainActivity_TrangChuBanHang.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });
    }

    private void setControl() {
        ivQuanTriKyThuat = findViewById(R.id.ivQuanTriKyThuat);
        ivTuVan = findViewById(R.id.ivTuVan);
        ivDonHangBiHuy = findViewById(R.id.ivDonHangBiHuy);
        ivGopY = findViewById(R.id.ivGopY);
        ivDanhSachDonHang = findViewById(R.id.ivDanhSachDonHang);
        linearLayoutDangXuat = findViewById(R.id.linearLayoutDangXuat);
        ivPhanHoiDanhGia = findViewById(R.id.ivPhanHoiDanhGia);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        ivGoiDien = findViewById(R.id.ivGoiDien);
    }
}