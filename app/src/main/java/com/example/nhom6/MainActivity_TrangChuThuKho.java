package com.example.nhom6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity_TrangChuThuKho extends AppCompatActivity {

    ImageView ivDanhSachDonHang, ivDonHangDaDongGoi, ivDonHangDaDuocShipperLayHang, ivDonHangDaGiao, ivDonHangBiHuy, ivDangXuat, ivKhoLienHe;

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
        ivDonHangDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_KhoDonDaGiaoHang.class);
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

        ivKhoLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuThuKho.this, MainActivity_DanhBaTinNhan_NhanVien.class);
                startActivity(intent);
            }
        });

        ivDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_TrangChuThuKho.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn đăng xuất khỏi tài khoản này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(MainActivity_TrangChuThuKho.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(MainActivity_TrangChuThuKho.this,MainActivity_DangNhap.class));
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
        ivDanhSachDonHang = findViewById(R.id.ivDanhSachDonHang);
        ivDonHangDaDongGoi = findViewById(R.id.ivDonHangDaDongGoi);
        ivDonHangDaDuocShipperLayHang = findViewById(R.id.ivDonHangDaDuocShipperLayHang);

        ivDonHangDaGiao = findViewById(R.id.ivDonHangDaGiao);
        ivDonHangBiHuy = findViewById(R.id.ivDonHangBiHuy);
        ivDangXuat = findViewById(R.id.ivDangXuat);
        ivKhoLienHe = findViewById(R.id.ivKhoLienHe);
    }
}