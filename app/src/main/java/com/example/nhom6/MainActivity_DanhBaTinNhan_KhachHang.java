package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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

public class MainActivity_DanhBaTinNhan_KhachHang extends AppCompatActivity {
    RecyclerView recyclerViewDanhBa;
    EditText edtTimKiem;

    List<TinNhan> data_danhba = new ArrayList<>();
    List<TinNhan> data_tinNhan = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference data_NhanVien;
    DatabaseReference data_TinNhanNhanVien;
    DatabaseReference data_TinNhanKhachHang;

    //    public static String maNV = "";
    public static String maKH = "-NicfEfjZkm0OfST0xMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_ba_tin_nhan_khach_hang);

        setControl();
        setEvent();
    }

    private void setEvent() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data_TinNhanNhanVien = database.getReference("TinNhanNhanVien");
        data_TinNhanKhachHang = database.getReference("TinNhanKhachHang");
        data_NhanVien = database.getReference("NhanVien");

        recyclerViewDanhBa.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDanhBa.setAdapter(new DanhBaTinNhanKhachHangAdapter(this, data_danhba));


        DanhBaTinNhanKhachHangAdapter adapter = (DanhBaTinNhanKhachHangAdapter) recyclerViewDanhBa.getAdapter();
        adapter.setOnItemClickListenner(new DanhBaTinNhanKhachHangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity_DanhBaTinNhan_KhachHang.this, MainActivity_tinnhan_khachhang.class);
                MainActivity_tinnhan_khachhang.maKH = maKH;
                MainActivity_tinnhan_khachhang.maNV = data_danhba.get(position).maNhanVien;
                startActivity(intent);
            }
        });


        data_NhanVien.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNV();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNV();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLNV();
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
                DocDLNV();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNV();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLNV();
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
                DocDLNV();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNV();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLNV();
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
                    DocDLNV();
                } else {
                    TimDLNV();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void DocDLTinNhan1() {
        data_TinNhanKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                data_TN1.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maKhachHang.toString().trim().equals(maKH)) {
                        data_TN1.add(tinNhan);
                        for (int i = data_danhba.size() - 1; i >= 0; i--) {
                            if (tinNhan.maNhanVien.toString().trim().equals(data_danhba.get(i).maNhanVien.trim())) {
                                data_danhba.get(i).setTinNhan(tinNhan.tinNhan);
                                data_danhba.get(i).setNgay(tinNhan.ngay);
                                break;
                            }
                        }

                    }
                }

                data_TinNhanNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot item : snapshot.getChildren()) {
                            TinNhan tinNhan = item.getValue(TinNhan.class);
                            if (tinNhan.maKhachHang.toString().trim().equals(maKH)) {
                                for (int i = 0; i < data_danhba.size(); i++) {
                                    if (tinNhan.maNhanVien.toString().trim().equals(data_danhba.get(i).maNhanVien.trim())) {
                                        Date date1 = null;
                                        Date date2 = null;
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                                        try {
                                            date1 = dateFormat.parse(tinNhan.getNgay().trim());
                                            date2 = dateFormat.parse(data_danhba.get(i).getNgay().trim());


                                            if (date1.compareTo(date2) > 0) {
                                                data_danhba.get(i).setTinNhan(tinNhan.tinNhan);
                                                data_danhba.get(i).setNgay(tinNhan.ngay);
                                                recyclerViewDanhBa.getAdapter().notifyDataSetChanged();
                                                break;
                                            }

                                        } catch (Exception e) {

                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                recyclerViewDanhBa.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void DocDLTinNhan2() {
        data_TinNhanNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maKhachHang.toString().trim().equals(maKH)) {
                        for (int i = data_danhba.size() - 1; i >= 0; i--) {
                            if (tinNhan.maNhanVien.toString().trim().equals(data_danhba.get(i).maNhanVien.trim())) {
                                data_danhba.get(i).setTinNhan(tinNhan.tinNhan);
                                data_danhba.get(i).setNgay(tinNhan.ngay);
                                break;
                            }
                        }
                    }
                }

                data_TinNhanKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot item : snapshot.getChildren()) {
                            TinNhan tinNhan = item.getValue(TinNhan.class);
                            if (tinNhan.maKhachHang.toString().trim().equals(maKH)) {
                                for (int i = data_danhba.size() - 1; i >= 0; i--) {
                                    if (tinNhan.maNhanVien.toString().trim().equals(data_danhba.get(i).maNhanVien.trim())) {

                                        Date date1;
                                        Date date2;
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

                                        try {
                                            date1 = dateFormat.parse(tinNhan.getNgay().trim());
                                            date2 = dateFormat.parse(data_danhba.get(i).getNgay().trim());


                                            if (date1.compareTo(date2) > 0) {
                                                data_danhba.get(i).setTinNhan(tinNhan.tinNhan);
                                                data_danhba.get(i).setNgay(tinNhan.ngay);
                                                recyclerViewDanhBa.getAdapter().notifyDataSetChanged();
                                                break;
                                            }

                                        } catch (Exception e) {

                                        }
                                        break;
                                    }
                                }
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void DocDLTinNhan() {
        data_tinNhan.clear();
        DocDLTinNhan1();
        DocDLTinNhan2();
    }

    private void DocDLNV() {
        //SapXepTinNhan();
        data_danhba.clear();

        data_NhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_danhba.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien = item.getValue(NhanVien.class);
                    TinNhan tinNhan = new TinNhan("", "" + nhanVien.maNhanVien, "" + nhanVien.getTenNhanVien(), "" + maKH, ""
                            , "", "", "0", "" + nhanVien.getHinh(), "");
                    data_danhba.add(tinNhan);
                }

                DocDLTinNhan();
                Collections.sort(data_danhba, new Comparator<TinNhan>() {
                    @Override
                    public int compare(TinNhan tinNhan1, TinNhan tinNhan2) {
                        Date date1 = null;
                        Date date2 = null;
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                            date1 = dateFormat.parse(tinNhan1.getNgay());
                            date2 = dateFormat.parse(tinNhan2.getNgay());
                        } catch (Exception e) {
                            return (tinNhan1.getNgay().compareTo(tinNhan2.getNgay())) * -1;
                        }
                        return date1.compareTo(date2);
                    }
                });

                recyclerViewDanhBa.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    List<TinNhan> data_TN1 = new ArrayList<>();
    List<TinNhan> data_TN2 = new ArrayList<>();

    private void TimDLNV() {
        //SapXepTinNhan();
        data_danhba.clear();

        data_NhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_danhba.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien = item.getValue(NhanVien.class);
                    if (nhanVien.getTenNhanVien().toString().trim().toLowerCase().contains(edtTimKiem.getText().toString().trim().toLowerCase())) {
                        TinNhan tinNhan = new TinNhan("", "" + nhanVien.maNhanVien, "" + nhanVien.getTenNhanVien(), "" + maKH, ""
                                , "", "", "0", "" + nhanVien.getHinh(), "");
                        data_danhba.add(tinNhan);
                    }
                }

                DocDLTinNhan();
                Collections.sort(data_danhba, new Comparator<TinNhan>() {
                    @Override
                    public int compare(TinNhan tinNhan1, TinNhan tinNhan2) {
                        Date date1 = null;
                        Date date2 = null;
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                            date1 = dateFormat.parse(tinNhan1.getNgay());
                            date2 = dateFormat.parse(tinNhan2.getNgay());
                        } catch (Exception e) {
                            return (tinNhan1.getNgay().compareTo(tinNhan2.getNgay())) * -1;
                        }
                        return date1.compareTo(date2);
                    }
                });

                recyclerViewDanhBa.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setControl() {
        recyclerViewDanhBa = findViewById(R.id.recyclerviewDanhBa);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }
}