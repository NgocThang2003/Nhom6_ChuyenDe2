package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_PhuongThucThanhToan extends AppCompatActivity {
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phuongthucthanhtoan);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }
}