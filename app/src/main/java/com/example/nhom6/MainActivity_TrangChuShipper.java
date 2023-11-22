package com.example.nhom6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity_TrangChuShipper extends AppCompatActivity {
    ImageView ivDonHangCuaBan, ivDanhSachDonHang, ivDonHangDaHuy, ivDonHangDangGiao, ivLienLac;
    LinearLayout linearLayout;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu_shipper);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ivDonHangCuaBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuShipper.this, MainActivity_ShipperDonHangCuaBan.class);
                startActivity(intent);
            }
        });
        ivDanhSachDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuShipper.this,MainActivity_ShipperDanhSachDonHang.class);
                startActivity(intent);
            }
        });
        ivDonHangDaHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuShipper.this,MainActivity_ShipperDonHangDaHuy.class);
                startActivity(intent);
            }
        });
        ivDonHangDangGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuShipper.this,MainActivity_ShipperDonHangDangGiao.class);
                startActivity(intent);
            }
        });
        ivLienLac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuShipper.this, MainActivity_DanhBaTinNhan_NhanVien.class);
                MainActivity_DanhBaTinNhan_NhanVien.maNV = MainActivity_DangNhap.maNguoiDung;
                startActivity(intent);

            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_TrangChuShipper.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn đăng xuất khỏi tài khoản này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(MainActivity_TrangChuShipper.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity_TrangChuShipper.this,MainActivity_DangNhap.class));
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
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_TrangChuShipper.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn đăng xuất khỏi tài khoản này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(MainActivity_TrangChuShipper.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity_TrangChuShipper.this,MainActivity_DangNhap.class));
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
        ivDonHangCuaBan = findViewById(R.id.ivDonHangCuaBan);
        ivDanhSachDonHang = findViewById(R.id.ivDanhSachDonHang);
        ivDonHangDaHuy = findViewById(R.id.ivDonHangDaHuy);
        ivDonHangDangGiao = findViewById(R.id.ivDonHangDangGiao);
        ivLienLac = findViewById(R.id.ivLienLac);
        linearLayout = findViewById(R.id.LinearLayoutDangXuat);
        btnDangXuat = findViewById(R.id.btnDangXuat);

    }

}