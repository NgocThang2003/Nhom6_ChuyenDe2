package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity_TaiKhoan extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    TextView tvXemTatCa, tvLienHe;

    ImageView ivLienHe, ivLienHe2,ivDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taikhoan);

        setControl();
        setEvent();
    }

    private void setEvent() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.taikhoan) {
                    Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    return true;
                }
                if (item.getItemId() == R.id.thuoc) {
                    Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_Thuoc.class);
                    startActivity(intent);
                    return true;
                }
                if (item.getItemId() == R.id.cuahang) {
                    Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    return true;
                }
                if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_TrangChuKhachHang.class);
                    startActivity(intent);
                    return true;
                }
                if (item.getItemId() == R.id.tuvan) {
                    Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_DanhBaTinNhan_KhachHang.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        tvXemTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_DonHang.class);
                startActivity(intent);
            }
        });
        tvLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_LienHe.class);
                startActivity(intent);
            }
        });

        ivLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_LienHe.class);
                startActivity(intent);
            }
        });
        ivLienHe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_LienHe.class);
                startActivity(intent);
            }
        });
        ivDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_TaiKhoan.this,MainActivity_TatCaDanhGia.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        tvXemTatCa = findViewById(R.id.tvXemTatCa);
        tvLienHe = findViewById(R.id.tvLienHe);

        ivLienHe = findViewById(R.id.ivLienHe);
        ivLienHe2 = findViewById(R.id.ivLienHe2);
        ivDanhGia = findViewById(R.id.ivDanhGiaTK);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_taikhoan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.menuGioHang == item.getItemId()){
            Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_GioHang.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void chucnang(View view) {

        if (view.getId() == R.id.ivHoSo) {
            Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_HoSoNguoiDung.class);
            startActivity(intent);
        }

    }

    public void chucnangdiachi(View view) {

        if (view.getId() == R.id.ivDiaChi) {
            Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_DiaChiGiaoHang.class);
            startActivity(intent);
        }

    }

    public void chucnanggioithieu(View view) {

        if (view.getId() == R.id.ivGioiThieu) {
            Intent intent = new Intent(MainActivity_TaiKhoan.this, MainActivity_GioiThieu.class);
            startActivity(intent);
        }

    }
}
