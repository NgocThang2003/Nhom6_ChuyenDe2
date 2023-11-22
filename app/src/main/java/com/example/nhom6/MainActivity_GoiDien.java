package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity_GoiDien extends AppCompatActivity {
    EditText edtGoiDien;
    ImageButton ivHinh;
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_goidien);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ivHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edtGoiDien.getText().toString()));
                if (ActivityCompat.checkSelfPermission(MainActivity_GoiDien.this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity_GoiDien.this, new
                            String[]{android.Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(intent);

            }
        });
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        edtGoiDien = findViewById(R.id.edtGoiDien);
        ivHinh = findViewById(R.id.ivHinh);
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }
}