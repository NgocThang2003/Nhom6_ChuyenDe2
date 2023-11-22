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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity_ShipperDanhSachDonHang extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference data_DSDH;
    List<DonHang> data_DonHang = new ArrayList<>();
    ImageView ivQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipperdanhsachdonhang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_DSDH = database.getReference("DonHang");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new ShipperDanhSachDonHang_Adapter(this,data_DonHang));

        data_DSDH.addChildEventListener(new ChildEventListener() {
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
        data_DonHang.clear();
        data_DSDH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);
                    if(donHang.trangThai.toString().trim().equals("Đang đóng gói")){

                            data_DonHang.add(donHang);

                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    Collections.sort(data_DonHang, new Comparator<DonHang>() {
                        @Override
                        public int compare(DonHang donHang1, DonHang donHang2) {
                            Date date1 = null;
                            Date date2 = null;
                            try {
                                date1 = dateFormat.parse(donHang1.getNgay().trim());
                                date2 = dateFormat.parse(donHang2.getNgay().trim());
                            } catch (Exception e) {
                                return 0;
                            }
                            return date1.compareTo(date2)*-1;
                        }
                    });

                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewDonHang);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }
}