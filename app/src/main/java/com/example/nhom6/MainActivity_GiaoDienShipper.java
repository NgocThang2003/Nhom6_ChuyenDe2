package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity_GiaoDienShipper extends AppCompatActivity {
    ImageView imgDonmoi, imgLienhe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaodienshipper);
        setControl();
        setEvent();



    }

    private void setEvent() {
        imgDonmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity_GiaoDienShipper.this,"Màn hình đơn mới", Toast.LENGTH_LONG).show();
            }
        });

        imgLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity_GiaoDienShipper.this,"Màn hình liên hệ khách hàng", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setControl() {
        imgDonmoi = findViewById(R.id.imgDonmoi);
        imgLienhe = findViewById(R.id.imgLienhe);
    }
}