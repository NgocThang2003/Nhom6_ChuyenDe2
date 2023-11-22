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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity_PhanHoiDanhGiaKhachHang_BanHang extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference data_DH;
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
        tvTieuDe.setText("Phản hồi đánh giá khách hàng");
        database = FirebaseDatabase.getInstance();
        data_DH= database.getReference("DonHang");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new PhanHoiSanPhamAdapter(this,data_DonHang));

        data_DH.addChildEventListener(new ChildEventListener() {
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
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);
                    if(donHang.trangThai.trim().equals("Đã giao hàng")){
                        if(!donHang.danhGia.equals("")){
                            data_DonHang.add(donHang);
                        }
                    }
                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
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