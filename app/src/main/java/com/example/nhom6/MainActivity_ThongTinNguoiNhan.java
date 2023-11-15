package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MainActivity_ThongTinNguoiNhan extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference data_TTNN;
    List<TaiKhoan> data_ThongTinNguoiNhan = new ArrayList<>();
    ImageView ivQuayVe;
    public static TextView tvTen, tvSDT, tvEmail, tvDiemDen;

    public static String maNguoiDung = "-NiNrHieKJTJY-rlUhgh";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_thongtinnguoinhan);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_TTNN = database.getReference("DangKy");
        String diaChi = getIntent().getStringExtra("Dia Chi");
        tvDiemDen.setText(diaChi);

        data_TTNN.addChildEventListener(new ChildEventListener() {
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
        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void DocDL() {
        data_ThongTinNguoiNhan.clear();
        data_TTNN.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_ThongTinNguoiNhan.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);
                    if (maNguoiDung.toString().trim().equals(taiKhoan.maNguoiDung)) {
                        tvTen.setText(taiKhoan.hoten);
                        tvSDT.setText(taiKhoan.sdt);
                        tvEmail.setText(taiKhoan.Email);
                    }
                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void setControl() {
        tvTen = findViewById(R.id.tvTen);
        tvSDT = findViewById(R.id.tvSDT);
        tvEmail = findViewById(R.id.tvEmail);
        tvDiemDen = findViewById(R.id.tvDiemDen);
        ivQuayVe = findViewById(R.id.ivQuayVe);

    }

}