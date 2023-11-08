package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_GiongCayTrong extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvCayNongNghiep, tvCayCongNghiep, tvCayLamNghiep;
    List<SanPham> data_GiongCayTrong = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_GCT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giongcaytrong);
        setControl();
        setEvent();
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewGiongCayTrong);
        tvCayNongNghiep = findViewById(R.id.tvCayNongNghiep);
        tvCayCongNghiep = findViewById(R.id.tvCayCongNghiep);
        tvCayLamNghiep = findViewById(R.id.tvCayLamNghiep);
    }

    private void setEvent() {
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

    }
    public void DocDL() {
        data_GiongCayTrong.clear();
        data_GCT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GiongCayTrong.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data_GiongCayTrong.add(sanPham);
                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}