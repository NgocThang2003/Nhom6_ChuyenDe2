package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class MainActivity_DanhBaTinNhan_NhanVien extends AppCompatActivity {

    RecyclerView recyclerViewDanhBa;
    List<TinNhan> data_danhba = new ArrayList<>();
    List<TinNhan> data_tinNhan = new ArrayList<>();
    EditText edtTimKiem;
    ImageView ivQuayVe;

    FirebaseDatabase database;
    //    DatabaseReference data_TinNhanNhanVien;
//    DatabaseReference data_TinNhanKhachHang;

    DatabaseReference data_KhachHang;
    DatabaseReference data_TinNhanKhachHang;
    DatabaseReference data_TinNhanNhanVien;
    public static String maNV = "-NiYKq6L8MCZJK4XuYou";
    public static String maKH = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_ba_tin_nhan_nhanvien);

        setControl();
        setEvent();
    }

    private void setEvent() {
        //KhoiTao();
        maNV = MainActivity_DangNhap.maNguoiDung;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data_TinNhanKhachHang = database.getReference("TinNhanKhachHang");
        data_TinNhanNhanVien = database.getReference("TinNhanNhanVien");
        data_KhachHang = database.getReference("DangKy");

        recyclerViewDanhBa.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDanhBa.setAdapter(new DanhBaTinNhanNhanVienAdapter(this, data_danhba));

        DanhBaTinNhanNhanVienAdapter adapter = (DanhBaTinNhanNhanVienAdapter) recyclerViewDanhBa.getAdapter();
        adapter.setOnItemClickListenner(new DanhBaTinNhanNhanVienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity_DanhBaTinNhan_NhanVien.this, MainActivity_tinnhan_nhanvien.class);
                MainActivity_tinnhan_nhanvien.maNV = maNV;
                MainActivity_tinnhan_nhanvien.maKH = data_danhba.get(position).maKhachHang;
                startActivity(intent);
            }
        });
        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        data_KhachHang.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKH();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKH();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLKH();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        data_TinNhanKhachHang.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKH();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKH();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLKH();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        data_TinNhanNhanVien.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKH();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKH();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLKH();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (edtTimKiem.getText().toString().trim().equals("")) {
                    DocDLKH();
                } else {
                    TimDLKH();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void DocDLKH() {
        data_danhba.clear();
        data_KhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_danhba.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);

                    data_danhba.add(new TinNhan("", "", "", "" + taiKhoan.maNguoiDung, "" + taiKhoan.getHoten()
                            , "", "", "0", "", "" + taiKhoan.getHinh()));

                    //Toast.makeText(MainActivity_DanhBaTinNhan_NhanVien.this, "" + taiKhoan.maNguoiDung, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        data_tinNhan.clear();
        ArrayList<TinNhan> tinNhan1 = new ArrayList<>();
        ArrayList<TinNhan> tinNhan2 = new ArrayList<>();

        data_TinNhanKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.trim().equals(maNV.trim())) {
                        data_tinNhan.add(tinNhan);
                    }
                }

                DocTinNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        data_TinNhanNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.trim().equals(maNV.trim())) {
                        data_tinNhan.add(tinNhan);
                        tinNhan2.add(tinNhan);
                        dem = dem + 1;
                    }

                }
                DocTinNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void TimDLKH() {
        data_danhba.clear();
        data_KhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_danhba.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);
                    if (taiKhoan.getHoten().toString().trim().toLowerCase().contains(edtTimKiem.getText().toString().trim().toLowerCase())) {
                        data_danhba.add(new TinNhan("", "", "", "" + taiKhoan.maNguoiDung, "" + taiKhoan.getHoten()
                                , "", "", "0", "", "" + taiKhoan.getHinh()));
                    }
                    //Toast.makeText(MainActivity_DanhBaTinNhan_NhanVien.this, "" + taiKhoan.maNguoiDung, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        data_tinNhan.clear();
        ArrayList<TinNhan> tinNhan1 = new ArrayList<>();
        ArrayList<TinNhan> tinNhan2 = new ArrayList<>();

        data_TinNhanKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.trim().equals(maNV.trim())) {
                        data_tinNhan.add(tinNhan);
                    }
                }
                DocTinNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        data_TinNhanNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.trim().equals(maNV.trim())) {
                        data_tinNhan.add(tinNhan);
                        tinNhan2.add(tinNhan);

                    }
                }

                DocTinNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void DocTinNhanNhanVien() {
        data_TinNhanNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.trim().equals(maNV.trim())) {
                        data_tinNhan.add(tinNhan);
                    }
                }
                DocTinNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void DocTinNhanKhachHang() {
        data_TinNhanKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.trim().equals(maNV.trim())) {
                        data_tinNhan.add(tinNhan);
                    }
                }

                DocTinNhan();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static int dem = 0;

    private void DocTinNhan() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        Collections.sort(data_tinNhan, new Comparator<TinNhan>() {
            @Override
            public int compare(TinNhan tinNhan1, TinNhan tinNhan2) {
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = dateFormat.parse(tinNhan1.getNgay().trim());
                    date2 = dateFormat.parse(tinNhan2.getNgay().trim());
                } catch (Exception e) {

                }
                return date1.compareTo(date2);
            }
        });

        for (int i = data_danhba.size() - 1; i >= 0; i--) {
            for (int j = data_tinNhan.size() - 1; j >= 0; j--) {
                if (data_danhba.get(i).maKhachHang.trim().equals(data_tinNhan.get(j).maKhachHang.trim())) {
                    data_danhba.get(i).setTinNhan(data_tinNhan.get(j).tinNhan);
                    data_danhba.get(i).setNgay(data_tinNhan.get(j).ngay);
                    break;
                }
            }
        }
        Collections.sort(data_danhba, new Comparator<TinNhan>() {
            @Override
            public int compare(TinNhan tinNhan1, TinNhan tinNhan2) {
                Date date1 = null;
                Date date2 = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    date1 = dateFormat.parse(tinNhan1.getNgay());
                    //Toast.makeText(MainActivity_DanhBaTinNhan_NhanVien.this, "" + date2, Toast.LENGTH_SHORT).show();
                    date2 = dateFormat.parse(tinNhan2.getNgay());
                } catch (Exception e) {
                    return (tinNhan1.getNgay().compareTo(tinNhan2.getNgay())) * -1;
                }
                return date1.compareTo(date2);
            }
        });
        recyclerViewDanhBa.getAdapter().notifyDataSetChanged();
    }

    private void KhoiTao() {
        data_danhba.add(new TinNhan("0001", "abc", "Tran xuan quy", "aff", "Tran xuan dung", "abc --- dddd", "2023/11/11 13:45", "1", "", ""));
        data_danhba.add(new TinNhan("0001", "abc", "Tran xuan quy", "aff", "Tran xuan dung", "abc --- dddd", "2023/11/11 13:45", "1", "", ""));
    }

    private void setControl() {
        recyclerViewDanhBa = findViewById(R.id.recyclerviewDanhBa);

        edtTimKiem = findViewById(R.id.edtTimKiem);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }
}