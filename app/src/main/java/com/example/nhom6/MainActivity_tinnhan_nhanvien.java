package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity_tinnhan_nhanvien extends AppCompatActivity {
    RecyclerView recyclerviewTinNhan;
    List<TinNhan> data_tinNhan = new ArrayList<>();

    public static List<NhanVien> dataNhanVien = new ArrayList<>();
    public static List<TaiKhoan> dataKhachHang = new ArrayList<>();

    public static String maNV = "-NiYKq6L8MCZJK4XuYou";
    public  static String maKH = "-NicfEfjZkm0OfST0xMS";


    EditText edtTinNhan;
    TextView tvGui, tvTenKhachHang;
    ImageView ivHinhKhachHang, ivQuayVe;

    FirebaseDatabase database;
    DatabaseReference data_TinNhanNhanVien;
    DatabaseReference data_TinNhanKhachHang;
    DatabaseReference data_NhanVien;
    DatabaseReference data_KhachHang;

    ImageView ivQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tinnhan_nhanvien);

        setControl();
        setEvent();
    }

    private void setEvent() {
        tvGui.setVisibility(View.GONE);
        KhoiTao();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data_TinNhanNhanVien = database.getReference("TinNhanNhanVien");
        data_TinNhanKhachHang = database.getReference("TinNhanKhachHang");

        data_NhanVien = database.getReference("NhanVien");
        data_KhachHang = database.getReference("DangKy");

        //data_KhachHang = database.getReference("DangKy");


//        recyclerviewTinNhan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerviewTinNhan.setAdapter(new TinNhanChungAdapter(this, data_tinNhan));


        // Tạo LinearLayoutManager và thiết lập reverseLayout và stackFromEnd
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerviewTinNhan.setLayoutManager(layoutManager);
        TinNhanChungAdapter adapter = new TinNhanChungAdapter(this, data_tinNhan);
        recyclerviewTinNhan.setAdapter(adapter);

        edtTinNhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtTinNhan.getText().toString().trim().equals("")) {
                    tvGui.setVisibility(View.GONE);
                } else {
                    tvGui.setVisibility(View.VISIBLE);
                }
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

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        tvGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtTinNhan.getText().toString().trim().equals("")) {
                    Date currentTime = Calendar.getInstance().getTime();

                    String msg = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String currentDateandTime = sdf.format(new Date());
                    TinNhan tinNhan = new TinNhan("" + data_TinNhanNhanVien.push().getKey(), "" + maNV, "" + dataNhanVien.get(0).getTenNhanVien(), "" + maKH, ""+dataKhachHang.get(0).hoten, "" + edtTinNhan.getText().toString().trim(), "" + currentDateandTime, "0", "" + dataNhanVien.get(0).getHinh(), ""+dataKhachHang.get(0).hinh);
                    data_TinNhanNhanVien.child(tinNhan.maTinNhan).setValue(tinNhan);

                    inputMethodManager.hideSoftInputFromWindow(edtTinNhan.getWindowToken(), 0);
                    edtTinNhan.setText("");

                    //recyclerviewTinNhan.getAdapter().notifyDataSetChanged();

                }
            }
        });

        data_TinNhanNhanVien.addChildEventListener(new ChildEventListener() {
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

        data_NhanVien.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNhanVien();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNhanVien();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLNhanVien();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        data_KhachHang.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKhachHang();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLKhachHang();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLKhachHang();
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
    List<TinNhan> data_TN1 = new ArrayList<>();
    List<TinNhan> data_TN2 = new ArrayList<>();

    private void DocDL() {
        data_tinNhan.clear();
        data_TN2.clear();
        data_TN1.clear();
        data_TinNhanNhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                data_TN1.clear();

//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.toString().trim().equals(maNV)) {
                        if (tinNhan.maKhachHang.toString().trim().equals(maKH)) {
                            data_TN1.add(tinNhan);
                        }
                    }
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

                data_tinNhan.addAll(data_TN1);
                data_tinNhan.addAll(data_TN2);

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
                recyclerviewTinNhan.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        data_TinNhanKhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_tinNhan.clear();
                data_TN2.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TinNhan tinNhan = item.getValue(TinNhan.class);
                    if (tinNhan.maNhanVien.toString().trim().equals(maNV)) {
                        if (tinNhan.maKhachHang.toString().trim().equals(maKH)) {
                            TinNhan tinNhan1 = new TinNhan();
                            //maTinNhan, maNhanVien, hoTenNhanVien, maKhachHang, hoTenKhachHang, tinNhan, ngay, daDoc, hinhNhanVien, hinhKhachHang
                            tinNhan1.setMaTinNhan(tinNhan.maTinNhan);
                            tinNhan1.setMaNhanVien(tinNhan.maKhachHang.trim());
                            tinNhan1.setHoTenNhanVien(tinNhan.hoTenKhachHang);
                            tinNhan1.setMaKhachHang(tinNhan.maNhanVien.trim());
                            tinNhan1.setHoTenKhachHang(tinNhan.hoTenNhanVien);
                            tinNhan1.setTinNhan(tinNhan.tinNhan);
                            tinNhan1.setNgay(tinNhan.ngay);
                            tinNhan1.setDaDoc(tinNhan.daDoc);
                            tinNhan1.setHinhNhanVien(tinNhan.hinhNhanVien);
                            tinNhan1.setHinhKhachHang(tinNhan.hinhKhachHang);

                            data_TN2.add(tinNhan1);
                        }
                    }
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

                data_tinNhan.addAll(data_TN1);
                data_tinNhan.addAll(data_TN2);

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
                recyclerviewTinNhan.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }


    public void DocDLNhanVien() {
        dataNhanVien.clear();
        data_NhanVien.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataNhanVien.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien1 = item.getValue(NhanVien.class);
                    if (nhanVien1.maNhanVien.toString().trim().equals(maNV.trim())) {
                        //Toast.makeText(MainActivity_tinnhan_nhanvien.this, ""+nhanVien1.maNhanVien, Toast.LENGTH_SHORT).show();
                        dataNhanVien.add(nhanVien1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDLKhachHang() {
        dataKhachHang.clear();
        data_KhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataKhachHang.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);
                    if (taiKhoan.maNguoiDung.toString().trim().equals(maKH.trim())) {
                        //Toast.makeText(MainActivity_tinnhan_nhanvien.this, ""+nhanVien1.maNhanVien, Toast.LENGTH_SHORT).show();
                        dataKhachHang.add(taiKhoan);
                        tvTenKhachHang.setText(""+dataKhachHang.get(0).hoten);

                        if(!taiKhoan.hinh.trim().equals("")){
                            try {
                                byte[] bytes = chuyenStringSangByte(taiKhoan.hinh);
                                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                                ivHinhKhachHang.setImageBitmap(bitmap);
                            }
                            catch (Exception e){
                                ivHinhKhachHang.setImageResource(R.drawable.anhsp_quantri);
                            }
                        }
                        else{
                            ivHinhKhachHang.setImageResource(R.drawable.anhsp_quantri);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void KhoiTao() {

    }

    private void setControl() {
        recyclerviewTinNhan = findViewById(R.id.recyclerviewTinNhan);

        edtTinNhan = findViewById(R.id.edtTinNhan);
        tvGui = findViewById(R.id.tvGui);
        ivQuayVe = findViewById(R.id.ivQuayVe);

        tvTenKhachHang = findViewById(R.id.tvTenKhachHang);
        ivHinhKhachHang = findViewById(R.id.ivHinhKhachHang);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }

    private byte[] chuyenStringSangByte(String str) {
        byte[] byteArray = android.util.Base64.decode(str, android.util.Base64.NO_PADDING | android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
        return byteArray;
    }

    //Chuyen byte[] sang bitMap
    private Bitmap chuyenByteSangBitMap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }
}