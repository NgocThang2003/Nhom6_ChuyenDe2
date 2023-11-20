package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_DonHangDangGiao_ThuKho extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference data_KDHCXN;
    List<DonHang> data_DonHang = new ArrayList<>();

    TextView tvTieuDe;
    ImageView ivQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khodonhangchoxacnhan);

        setControl();
        setEvent();
    }

    private void setEvent() {
        tvTieuDe.setText("Đơn hàng đang giao");
        database = FirebaseDatabase.getInstance();
        data_KDHCXN = database.getReference("DonHang");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new KhoDonHangDangGiao_Adapter(this, data_DonHang));

        data_KDHCXN.addChildEventListener(new ChildEventListener() {
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
        data_KDHCXN.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);
                    if (donHang.trangThai.toString().trim().equals("Đang giao hàng")) {
                        data_DonHang.add(donHang);
                    }
//                    data_DonHang.add(donHang);
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
        tvTieuDe = findViewById(R.id.tvTieuDe);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }
}