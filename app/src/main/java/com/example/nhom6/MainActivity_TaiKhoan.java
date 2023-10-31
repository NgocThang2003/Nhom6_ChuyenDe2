package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;


public class MainActivity_TaiKhoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taikhoan);
        
        setControl();
        setEvent();
    }

    private void setEvent() {
    }

    private void setControl() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_taikhoan, menu);
        return true;
    }
    public void chucnang(View view) {

        if(view.getId()==R.id.ivHoSo){
            Intent intent = new Intent(MainActivity_TaiKhoan.this,MainActivity_HoSoNguoiDung.class);
            startActivity(intent);
        }

    }
    public void chucnangdiachi(View view) {

        if(view.getId()==R.id.ivDiaChi){
            Intent intent = new Intent(MainActivity_TaiKhoan.this,MainActivity_DiaChiGiaoHang.class);
            startActivity(intent);
        }

    }
    public void chucnanggioithieu(View view) {

        if(view.getId()==R.id.ivGioiThieu){
            Intent intent = new Intent(MainActivity_TaiKhoan.this,MainActivity_GioiThieu.class);
            startActivity(intent);
        }

    }
    public void chucnanglienhe(View view) {

        if(view.getId()==R.id.ivLienHe){
            Intent intent = new Intent(MainActivity_TaiKhoan.this,MainActivity_LienHeNhanVien.class);
            startActivity(intent);
        }

    }



}
