package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity_DiaChiGiaoHang extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ThemDiaChiMoi> data_ThemDiaChi = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_TDCM;
    ImageView ivQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diachigaohang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_TDCM = database.getReference("ThemDiaChiMoi");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ThemDiaChiMoi_Adapter(this, data_ThemDiaChi));

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
                Intent intent = new Intent(MainActivity_DiaChiGiaoHang.this, MainActivity_TaiKhoan.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewThemDiaChiMoi);
        ivQuayVe = findViewById(R.id.imgQuayVe);
    }


    public void DocDL() {
        data_ThemDiaChi.clear();
        data_TDCM.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_ThemDiaChi.clear();
                //Toast.makeText(MainActivity_DiaChiGiaoHang.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ThemDiaChiMoi themDiaChiMoi = item.getValue(ThemDiaChiMoi.class);
                    if (themDiaChiMoi.maNguoiDung.trim().equals(MainActivity_DangNhap.maNguoiDung)) {
                        data_ThemDiaChi.add(themDiaChiMoi);
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void chucnangthemdiachimoi(View view) {

        if (view.getId() == R.id.btnThemDiaChiMoi) {
            Intent intent = new Intent(MainActivity_DiaChiGiaoHang.this, MainActivity_ThemDiaChiMoi.class);
            startActivity(intent);
        }
    }

}