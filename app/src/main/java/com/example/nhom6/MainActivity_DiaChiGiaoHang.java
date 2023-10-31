package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity_DiaChiGiaoHang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diachigaohang);
    }
    public void chucnang(View view) {

        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_DiaChiGiaoHang.this,MainActivity.class);
            startActivity(intent);
        }

    }
    public void chucnangthemdiachimoi(View view) {

        if(view.getId()==R.id.btnThemDiaChiMoi){
            Intent intent = new Intent(MainActivity_DiaChiGiaoHang.this,MainActivity_ThemDiaChiMoi.class);
            startActivity(intent);
        }
    }

}