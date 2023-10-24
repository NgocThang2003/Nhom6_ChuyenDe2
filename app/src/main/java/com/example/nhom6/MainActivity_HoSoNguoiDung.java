package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity_HoSoNguoiDung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosonguoidung);
    }

    public void chucnang(View view) {
        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_HoSoNguoiDung.this,MainActivity.class);
            startActivity(intent);
        }
    }
}