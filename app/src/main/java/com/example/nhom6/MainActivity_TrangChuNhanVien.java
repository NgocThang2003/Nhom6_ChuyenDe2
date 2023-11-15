package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity_TrangChuNhanVien extends AppCompatActivity {
    ImageView ivTuVan, ivKyThuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchunhanvien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ivTuVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuNhanVien.this, MainActivity_DanhBaTinNhan_NhanVien.class);
                startActivity(intent);
            }
        });
        ivKyThuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuNhanVien.this, MainActivity_QuanTriKyThuat.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        ivTuVan = findViewById(R.id.ivTuVan);
        ivKyThuat = findViewById(R.id.ivQuanTriKyThuat);
    }
}