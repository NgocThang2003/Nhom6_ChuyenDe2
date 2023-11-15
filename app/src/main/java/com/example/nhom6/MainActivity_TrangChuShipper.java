package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity_TrangChuShipper extends AppCompatActivity {
    ImageView ivDonHangCuaBan, ivDanhSachDonHang, ivDonHangDaHuy, ivDonHangDangGiao;

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
    }

    private void setControl() {
        ivDonHangCuaBan = findViewById(R.id.ivDonHangCuaBan);
        ivDanhSachDonHang = findViewById(R.id.ivDanhSachDonHang);
        ivDonHangDaHuy = findViewById(R.id.ivDonHangDaHuy);
        ivDonHangDangGiao = findViewById(R.id.ivDonHangDangGiao);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_shipper, menu);
//        return true;
//    }


}