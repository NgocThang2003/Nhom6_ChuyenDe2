package com.example.nhom6;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_TatCaDanhGia extends AppCompatActivity {
    RecyclerView recyclerviewDanhgiaSP;
    FirebaseDatabase database;

    DatabaseReference data_DanhGia;
    List<DonHang> data_DG = new ArrayList<>();
    public static String maKH = "-NiNrHieKJTJY-rlUhgh";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhgiasp_tatca);


        setControl();
        setEvent();
    }

    private void setEvent() {
        maKH = MainActivity_DangNhap.maNguoiDung;
        database = FirebaseDatabase.getInstance();
        data_DanhGia = database.getReference("DonHang");
        recyclerviewDanhgiaSP.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerviewDanhgiaSP.setAdapter(new DanhGiaSP_Adappter(this,data_DG));
    }

    private void setControl() {
        recyclerviewDanhgiaSP = findViewById(R.id.recyclerviewDanhgiaSP);
    }

    private void DocDL() {
        data_DG.clear();
        data_DanhGia.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DG.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        if(donHang.trangThai.trim().equals("Đã giao hàng")) {

                            data_DG.add(donHang);
                        }
                    }
                }
                recyclerviewDanhgiaSP.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
