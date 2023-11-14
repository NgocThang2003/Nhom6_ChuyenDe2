package com.example.nhom6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_DonHang extends AppCompatActivity {
    public static RecyclerView rcvRecyclerView;
    public static RecyclerView rcvRecyclerViewDanhMuc;

    public static List<DonHang> data_DonHang = new ArrayList<>();
    public static List<DanhMuc> data_DanhMuc = new ArrayList<>();

    Button btnQuayLai;
    FirebaseDatabase database;
    public static DatabaseReference data_DH;
    public static String maKH = "-NiNrHieKJTJY-rlUhgh";
    public static String maSP = "-NiT6YxCGEdSMwNTLFFt";

    //    String maDH= "-NiimFmlfigqCqiJsSoe";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang_tatca);
        setControl();
        setEvent();
    }

    private void setEvent() {
        maKH = MainActivity_DangNhap.maNguoiDung;
        KhoiTao();
        database = FirebaseDatabase.getInstance();
        data_DH = database.getReference("DonHang");
        rcvRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvRecyclerView.setAdapter(new DonHangAdapter(this, data_DonHang));

        rcvRecyclerViewDanhMuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvRecyclerViewDanhMuc.setAdapter(new DanhMucAdapter(this, data_DanhMuc));

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

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void KhoiTao() {
        data_DanhMuc.clear();
        data_DanhMuc.add(new DanhMuc("Tất cả", "1", "Chờ xác nhận", "0", "Đang đóng gói", "0"));
        data_DanhMuc.add(new DanhMuc("Đang giao hàng", "0", "Đã nhận hàng", "0", "Đã huỷ", "0"));
    }

    public static void DocDL() {
        data_DonHang.clear();
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        data_DonHang.add(donHang);
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void DocDLDonHangDangChoXacNhan() {
        data_DonHang.clear();
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        if (donHang.trangThai.toString().trim().equals("Đang chờ xác nhận".trim())) {
                            data_DonHang.add(donHang);
                        }
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void DocDLDonHangDangDongGoi() {
        data_DonHang.clear();
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        if (donHang.trangThai.toString().trim().equals("Đang đóng gói".trim())) {
                            data_DonHang.add(donHang);
                        }
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void DocDLDonHangDangGiaoHang() {
        data_DonHang.clear();
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        if (donHang.trangThai.toString().trim().equals("Đang giao hàng".trim())) {
                            data_DonHang.add(donHang);
                        }
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void DocDLDonHangDaGiaoHang() {
        data_DonHang.clear();
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        if (donHang.trangThai.toString().trim().equals("Đã giao hàng".trim())) {
                            data_DonHang.add(donHang);
                        }
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void DocDLDonHangDaHuy() {
        data_DonHang.clear();
        data_DH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_DonHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    DonHang donHang = item.getValue(DonHang.class);

                    if (maKH.toString().trim().equals(donHang.maKhachHang.toString().trim())) {
                        if (donHang.trangThai.toString().trim().equals("Đã huỷ".trim())) {
                            data_DonHang.add(donHang);
                        }
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setControl() {
        rcvRecyclerView = findViewById(R.id.recyclerviewDonHang);
        rcvRecyclerViewDanhMuc = findViewById(R.id.recyclerviewDanhMuc);

        btnQuayLai = findViewById(R.id.btnQuayLai);

    }
}
