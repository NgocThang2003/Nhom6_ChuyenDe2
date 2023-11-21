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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_GopYTuKhachHang_BanHang extends AppCompatActivity {

    TextView tvTieuDe;
    ImageView ivQuayVe;
    RecyclerView recyclerviewGopY;

    FirebaseDatabase database;
    DatabaseReference data_LienHe;
    List<LienHe> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khodonhangchoxacnhan);

        setControl();
        setEvent();
    }

    private void setEvent() {
        tvTieuDe.setText("Góp ý từ khách hàng");
        database = FirebaseDatabase.getInstance();
        data_LienHe = database.getReference("LienHe");

        recyclerviewGopY.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerviewGopY.setAdapter(new LienHeAdapter(this, data));

        data_LienHe.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLLienHe();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLLienHe();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLLienHe();
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

    private void DocDLLienHe() {
        data.clear();
        data_LienHe.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    LienHe lienHe = item.getValue(LienHe.class);
                    data.add(lienHe);
                }

                recyclerviewGopY.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setControl() {
        tvTieuDe = findViewById(R.id.tvTieuDe);
        ivQuayVe = findViewById(R.id.ivQuayVe);
        recyclerviewGopY = findViewById(R.id.recyclerviewDonHang);
    }
}