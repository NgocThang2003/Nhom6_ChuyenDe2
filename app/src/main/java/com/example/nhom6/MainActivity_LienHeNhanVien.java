package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity_LienHeNhanVien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lienhenhanvien);
    }
    public void chucnang(View view) {

        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_LienHeNhanVien.this,MainActivity_TaiKhoan.class);
            startActivity(intent);
        }
    }
}