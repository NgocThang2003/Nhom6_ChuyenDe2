package com.example.nhom6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class MainActivity_TrangChuChinh extends AppCompatActivity {
    FirebaseDatabase database;
    private BottomNavigationView bottomNavigationView;
    DatabaseReference data_TCKH;
    RecyclerView recyclerView;
    TextView tvTenKyThuat, tvMoTa, tvKyThuatGieoHat;
    ImageView ivHinh;
    Button btnDangNhap;
    List<TrangChuKhachHang> data_TrangChu = new ArrayList<>();
    //ArrayList<TrangChuKhachHang> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchuchinh);
        setControl();
        setEvent();
    }


    private void setControl() {
        ivHinh = findViewById(R.id.ivHinh);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvTenKyThuat = findViewById(R.id.tvTenKyThuat);
        tvKyThuatGieoHat = findViewById(R.id.tvKyThuatGieoHat);
        recyclerView = findViewById(R.id.recyclerviewTrangChu);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btnDangNhap = findViewById(R.id.btnDangNhap);


    }

    private void setEvent() {
//        KhoiTao();
        database = FirebaseDatabase.getInstance();
        data_TCKH = database.getReference("TrangChuKhachHang");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new TrangChuKhachHang_Adapter(this,data_TrangChu));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.taikhoan){
                    Intent intent = new Intent(MainActivity_TrangChuChinh.this,MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.thuoc){
                    Intent intent = new Intent(MainActivity_TrangChuChinh.this,MainActivity_Thuoc.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.cuahang){
                    Intent intent = new Intent(MainActivity_TrangChuChinh.this,MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.home){
                    Intent intent = new Intent(MainActivity_TrangChuChinh.this, MainActivity_TrangChuChinh.class);
                    startActivity(intent);
                    return  true;
                }
                if(item.getItemId() == R.id.tuvan){
                    Intent intent = new Intent(MainActivity_TrangChuChinh.this, MainActivity_DanhBaTinNhan_KhachHang.class);
                    startActivity(intent);
                    return  true;
                }
                return false;
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuChinh.this, MainActivity_DangNhap.class);
                startActivity(intent);
            }
        });

        data_TCKH.addChildEventListener(new ChildEventListener() {
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
        tvKyThuatGieoHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TrangChuChinh.this, MainActivity_KyThuatGieoHat.class);
                startActivity(intent);
            }
        });


    }
    public void DocDL() {
        data_TrangChu.clear();
        data_TCKH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_TrangChu.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    TrangChuKhachHang trangChuKhachHang = item.getValue(TrangChuKhachHang.class);
                    data_TrangChu.add(trangChuKhachHang);
                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    byte[] byteArrayHinh = new byte[0];

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            ivHinh.setImageURI(uri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArrayHinh = stream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // chuyen Byte[] Sang Chuoi
    private String chuyenByteSangChuoi(byte[] byteArray) {
        String base64String = android.util.Base64.encodeToString(byteArray, android.util.Base64.NO_PADDING | android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
        return base64String;
    }

    //chuyen String Sang Byte[]
    private byte[] chuyenStringSangByte(String str) {
        byte[] byteArray = android.util.Base64.decode(str, android.util.Base64.NO_PADDING | android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
        return byteArray;
    }

    //Chuyen byte[] sang bitMap
    private Bitmap chuyenByteSangBitMap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }


}