package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ChonDiaChiGiaoHang extends AppCompatActivity {

    RecyclerView recyclerviewDiaChi;
    List<ThemDiaChiMoi> data_ThemDiaChi = new ArrayList<>();
    String maNguoiDung = "-NiNrHieKJTJY-rlUhgh";
    public static String diaChi = "";
    ImageView ivQuayVe;
    Button btnThemDiaChiMoi;
    FirebaseDatabase database;
    DatabaseReference data_TDCM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chon_dia_chi_giao_hang);

        setControl();
        setEvent();
    }

    private void setEvent() {
        maNguoiDung = MainActivity_DangNhap.maNguoiDung;
        database = FirebaseDatabase.getInstance();
        data_TDCM = database.getReference("ThemDiaChiMoi");
        recyclerviewDiaChi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerviewDiaChi.setAdapter(new ChonDiaChi_Adapter(this, data_ThemDiaChi));

        ChonDiaChi_Adapter chonDiaChiAdapter = (ChonDiaChi_Adapter) recyclerviewDiaChi.getAdapter();
        chonDiaChiAdapter.setOnItemClickListenner(new ChonDiaChi_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ThemDiaChiMoi themDiaChiMoi = data_ThemDiaChi.get(position);
                ChonDiaChi_Adapter.diaChi = "Họ tên: " + themDiaChiMoi.ten + " - SDT: " + themDiaChiMoi.sdt + ", Địa chỉ: " + themDiaChiMoi.soNha + ", " + themDiaChiMoi.tinh + ", " + themDiaChiMoi.quan + ", " + themDiaChiMoi.phuong;
                diaChi = "Họ tên: " + themDiaChiMoi.ten + " - SDT: " + themDiaChiMoi.sdt + ", " + themDiaChiMoi.soNha + ", " + themDiaChiMoi.tinh + ", " + themDiaChiMoi.quan + ", " + themDiaChiMoi.phuong;
//                Intent intent = new Intent(context,MainActivity_ChiTietSanPham.class);
                try {

                    MainActivity_ChiTietSanPham.tvDiaChi.setText(chonDiaChiAdapter.diaChi);
                    MainActivity_GioHang.tvDiaChiGiaoHang.setText(diaChi);
                } catch (Exception e) {

                }

                onBackPressed();
            }
        });
        data_TDCM.addChildEventListener(new ChildEventListener() {
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

        btnThemDiaChiMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_ChonDiaChiGiaoHang.this, MainActivity_ThemDiaChiMoi.class);
                startActivity(intent);
            }
        });
    }

    public void DocDL() {
        data_ThemDiaChi.clear();
        data_TDCM.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_ThemDiaChi.clear();
                //Toast.makeText(MainActivity_ChonDiaChiGiaoHang.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ThemDiaChiMoi themDiaChiMoi = item.getValue(ThemDiaChiMoi.class);
                    if (themDiaChiMoi.maNguoiDung.trim().equals(maNguoiDung.trim())) {
                        data_ThemDiaChi.add(themDiaChiMoi);
                    }
                }
                recyclerviewDiaChi.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        recyclerviewDiaChi = findViewById(R.id.recyclerviewDiaChi);
        ivQuayVe = findViewById(R.id.ivQuayVe);
        btnThemDiaChiMoi = findViewById(R.id.btnThemDiaChiMoi);
    }
}