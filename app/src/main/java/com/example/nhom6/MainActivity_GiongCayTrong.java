package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_GiongCayTrong extends AppCompatActivity {
    RecyclerView recyclerView;
    List<GiongCayTrong> data_GiongCayTrong = new ArrayList<>();
    List<SanPham> data_SP = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_GCT;
    private BottomNavigationView bottomNavigationView;

    TextView tvCayNongNghiep, tvCayCongNghiep, tvCayLamNghiep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giongcaytrong);
        setControl();
        setEvent();
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewGiongCayTrong);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        tvCayNongNghiep = findViewById(R.id.tvCayNongNghiep);
        tvCayCongNghiep = findViewById(R.id.tvCayCongNghiep);
        tvCayLamNghiep = findViewById(R.id.tvCayLamNghiep);

    }

    private void setEvent() {
        bottomNavigationView.getMenu().findItem(R.id.cuahang).setChecked(true);
        database = FirebaseDatabase.getInstance();
        data_GCT = database.getReference("SanPham");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new GiongCayTrong_Adapter(this,data_GiongCayTrong));

        data_GCT.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDL();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDL();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDL();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tvCayNongNghiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCayNongNghiep.setTextColor(Color.BLUE);
                tvCayCongNghiep.setTextColor(Color.BLACK);
                tvCayLamNghiep.setTextColor(Color.BLACK);
                DocDLCayNongNghiep();
            }
        });
        tvCayCongNghiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCayCongNghiep.setTextColor(Color.BLUE);
                tvCayNongNghiep.setTextColor(Color.BLACK);
                tvCayLamNghiep.setTextColor(Color.BLACK);
                DocDLCayCongNghiep();
            }
        });
        tvCayLamNghiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCayCongNghiep.setTextColor(Color.BLACK);
                tvCayNongNghiep.setTextColor(Color.BLACK);
                tvCayLamNghiep.setTextColor(Color.BLUE);
                DocDLCayLamNghiep();
            }
        });
    }
    public void DocDLCayCongNghiep() {
        data_GiongCayTrong.clear();
        data_SP.clear();
        data_GCT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GiongCayTrong.clear();
                data_SP.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (!sanPham.loaiSP.toString().toString().equals("Thuốc")){
                        if (sanPham.loaiSP.toString().toString().equals("Công nghiệp")){
                            data_SP.add(sanPham);
                        }
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                    }
                }

                for (int i = 0; i < data_SP.size(); i=i+2) {
                    GiongCayTrong giongCayTrong = new GiongCayTrong();
                    if (!data_SP.get(i).loaiSP.toString().equals("Thuốc")) {
                        SanPham sanPham = data_SP.get(i);
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                        giongCayTrong.setMaSP1(sanPham.getMaSanPham().trim());
                        giongCayTrong.setTenSP1(sanPham.getTenSP().trim());
                        giongCayTrong.setGia1(sanPham.getGia().trim());
                        giongCayTrong.setHinh1(sanPham.getHinh().trim());

                    }
                    if( i + 1 != data_SP.size()){
                        if (!data_SP.get(i+1).loaiSP.toString().equals("Thuốc")) {
                            SanPham sanPham = data_SP.get(i+1);
//                            Toast.makeText(MainActivity_GiongCayTrong.this, "Giống cây trồng: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                            giongCayTrong.setMaSP2(sanPham.getMaSanPham().trim());
                            giongCayTrong.setTenSP2(sanPham.getTenSP().trim());
                            giongCayTrong.setGia2(sanPham.getGia().trim());giongCayTrong.setHinh2(sanPham.getHinh().trim());
                        }
                    }
                    //data_Thuoc.add(new Thuoc("","ấ","BS32 - Mecin","BSC03 - Combo","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195.000đ","250.000đ","",""));

                    data_GiongCayTrong.add(giongCayTrong);

                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.taikhoan){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.thuoc){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_Thuoc.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.cuahang){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.home){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this, MainActivity_TrangChuKhachHang.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.tuvan){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this, MainActivity_DanhBaTinNhan_KhachHang.class);
                    startActivity(intent);
                    return  true;
                }

                return false;


            }

        });
    }
    public void DocDLCayNongNghiep() {
        data_GiongCayTrong.clear();
        data_SP.clear();
        data_GCT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GiongCayTrong.clear();
                data_SP.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (!sanPham.loaiSP.toString().toString().equals("Thuốc")){
                        if (sanPham.loaiSP.toString().toString().trim().contains("Nông")){
                            data_SP.add(sanPham);
                        }
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                    }
                }

                for (int i = 0; i < data_SP.size(); i=i+2) {
                    GiongCayTrong giongCayTrong = new GiongCayTrong();
                    if (!data_SP.get(i).loaiSP.toString().equals("Thuốc")) {
                        SanPham sanPham = data_SP.get(i);
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                        giongCayTrong.setMaSP1(sanPham.getMaSanPham().trim());
                        giongCayTrong.setTenSP1(sanPham.getTenSP().trim());
                        giongCayTrong.setGia1(sanPham.getGia().trim());
                        giongCayTrong.setHinh1(sanPham.getHinh().trim());

                    }
                    if( i + 1 != data_SP.size()){
                        if (!data_SP.get(i+1).loaiSP.toString().equals("Thuốc")) {
                            SanPham sanPham = data_SP.get(i+1);
//                            Toast.makeText(MainActivity_GiongCayTrong.this, "Giống cây trồng: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                            giongCayTrong.setMaSP2(sanPham.getMaSanPham().trim());
                            giongCayTrong.setTenSP2(sanPham.getTenSP().trim());
                            giongCayTrong.setGia2(sanPham.getGia().trim());
                            giongCayTrong.setHinh2(sanPham.getHinh().trim());
                        }
                    }
                    //data_Thuoc.add(new Thuoc("","ấ","BS32 - Mecin","BSC03 - Combo","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195.000đ","250.000đ","",""));

                    data_GiongCayTrong.add(giongCayTrong);

                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.taikhoan){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.thuoc){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_Thuoc.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.cuahang){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.home){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this, MainActivity_TrangChuKhachHang.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.tuvan){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this, MainActivity_DanhBaTinNhan_KhachHang.class);
                    startActivity(intent);
                    return  true;
                }
                return false;


            }

        });
    }
    public void DocDLCayLamNghiep() {
        data_GiongCayTrong.clear();
        data_SP.clear();
        data_GCT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GiongCayTrong.clear();
                data_SP.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (!sanPham.loaiSP.toString().toString().equals("Thuốc")){
                        if (sanPham.loaiSP.toString().toString().trim().contains("Lâm")){
                            data_SP.add(sanPham);
                        }
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                    }
                }

                for (int i = 0; i < data_SP.size(); i=i+2) {
                    GiongCayTrong giongCayTrong = new GiongCayTrong();
                    if (!data_SP.get(i).loaiSP.toString().equals("Thuốc")) {
                        SanPham sanPham = data_SP.get(i);
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                        giongCayTrong.setMaSP1(sanPham.getMaSanPham().trim());
                        giongCayTrong.setTenSP1(sanPham.getTenSP().trim());
                        giongCayTrong.setGia1(sanPham.getGia().trim());
                        giongCayTrong.setHinh1(sanPham.getHinh().trim());

                    }
                    if( i + 1 != data_SP.size()){
                        if (!data_SP.get(i+1).loaiSP.toString().equals("Thuốc")) {
                            SanPham sanPham = data_SP.get(i+1);
//                            Toast.makeText(MainActivity_GiongCayTrong.this, "Giống cây trồng: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                            giongCayTrong.setMaSP2(sanPham.getMaSanPham().trim());
                            giongCayTrong.setTenSP2(sanPham.getTenSP().trim());
                            giongCayTrong.setGia2(sanPham.getGia().trim());
                            giongCayTrong.setHinh2(sanPham.getHinh().trim());
                        }
                    }
                    //data_Thuoc.add(new Thuoc("","ấ","BS32 - Mecin","BSC03 - Combo","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195.000đ","250.000đ","",""));

                    data_GiongCayTrong.add(giongCayTrong);

                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.taikhoan){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.thuoc){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_Thuoc.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.cuahang){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.home){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this, MainActivity_TrangChuKhachHang.class);
                    startActivity(intent);
                    return  true;
                }
                return false;


            }

        });
    }
    public void DocDL() {
        data_GiongCayTrong.clear();
        data_SP.clear();
        data_GCT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GiongCayTrong.clear();
                data_SP.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (!sanPham.loaiSP.toString().toString().equals("Thuốc")){
                        data_SP.add(sanPham);
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();

                    }
                }

                for (int i = 0; i < data_SP.size(); i=i+2) {
                    GiongCayTrong giongCayTrong = new GiongCayTrong();
                    if (!data_SP.get(i).loaiSP.toString().equals("Thuốc")) {
                        SanPham sanPham = data_SP.get(i);
//                        Toast.makeText(MainActivity_GiongCayTrong.this, "Nông nghiệp: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                        giongCayTrong.setMaSP1(sanPham.getMaSanPham().trim());
                        giongCayTrong.setTenSP1(sanPham.getTenSP().trim());
                        giongCayTrong.setGia1(sanPham.getGia().trim());
                        giongCayTrong.setHinh1(sanPham.getHinh().trim());

                    }
                    if( i + 1 != data_SP.size()){
                        if (!data_SP.get(i+1).loaiSP.toString().equals("Thuốc")) {
                            SanPham sanPham = data_SP.get(i+1);
//                            Toast.makeText(MainActivity_GiongCayTrong.this, "Giống cây trồng: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                            giongCayTrong.setMaSP2(sanPham.getMaSanPham().trim());
                            giongCayTrong.setTenSP2(sanPham.getTenSP().trim());
                            giongCayTrong.setGia2(sanPham.getGia().trim());giongCayTrong.setHinh2(sanPham.getHinh().trim());
                        }
                    }
                    //data_Thuoc.add(new Thuoc("","ấ","BS32 - Mecin","BSC03 - Combo","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195.000đ","250.000đ","",""));

                    data_GiongCayTrong.add(giongCayTrong);

                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.taikhoan){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.thuoc){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_Thuoc.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.cuahang){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this,MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.home){
                    Intent intent = new Intent(MainActivity_GiongCayTrong.this, MainActivity_TrangChuKhachHang.class);
                    startActivity(intent);
                    return  true;
                }
                return false;


            }

        });
    }

    }
