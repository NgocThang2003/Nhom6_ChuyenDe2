package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_KyThuatGieoHat extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference data_KTGH;
    RecyclerView recyclerView;
    TextView tvTenKyThuat, tvMoTa;

    ImageView ivHinh;
    RadioButton rdbNongNghiep, rdbCongNgiep, rdbLamNgiep;
    List<TrangChuKhachHang> data_KyThuatGieoHat = new ArrayList<>();
    //ArrayList<TrangChuKhachHang> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kythuatgieohat);
        setControl();
        setEvent();
    }

    private void setControl() {
        ivHinh = findViewById(R.id.ivHinh);
        rdbNongNghiep = findViewById(R.id.radio_a);
        rdbCongNgiep = findViewById(R.id.radio_b);
        rdbLamNgiep = findViewById(R.id.radio_c);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvTenKyThuat = findViewById(R.id.tvTenKyThuat);
        recyclerView = findViewById(R.id.recyclerviewKyThuatGieoHat);
    }

    private void setEvent() {
        rdbNongNghiep.setChecked(false);
        rdbCongNgiep.setChecked(false);
        rdbLamNgiep.setChecked(false);
//        KhoiTao();
        database = FirebaseDatabase.getInstance();
        data_KTGH = database.getReference("TrangChuKhachHang");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new TrangChuKhachHang_Adapter(this,data_KyThuatGieoHat));

        data_KTGH.addChildEventListener(new ChildEventListener() {
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

        rdbNongNghiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdbNongNghiep.isChecked()){
                    PhanLoaiKyThuat();
                }

            }
        });
        rdbCongNgiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhanLoaiKyThuat();
            }
        });
        rdbLamNgiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhanLoaiKyThuat();
            }
        });
    }
    public void PhanLoaiKyThuat() {
        data_KyThuatGieoHat.clear();
        data_KTGH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_KyThuatGieoHat.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    TrangChuKhachHang trangChuKhachHang = item.getValue(TrangChuKhachHang.class);
                    if(rdbNongNghiep.isChecked()){
                        if(trangChuKhachHang.getNhomNganh().equals("Nông nghiệp")){
                            data_KyThuatGieoHat.add(trangChuKhachHang);
                        }
                    }
                    if(rdbCongNgiep.isChecked()){
                        if(trangChuKhachHang.getNhomNganh().equals("Công nghiệp")){
                            data_KyThuatGieoHat.add(trangChuKhachHang);
                        }
                    }
                    if(rdbLamNgiep.isChecked()){
                        if(trangChuKhachHang.getNhomNganh().equals("Lâm nghiệp")){
                            data_KyThuatGieoHat.add(trangChuKhachHang);
                        }
                    }

                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }

                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void DocDL() {
        data_KyThuatGieoHat.clear();
        data_KTGH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_KyThuatGieoHat.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    TrangChuKhachHang trangChuKhachHang = item.getValue(TrangChuKhachHang.class);
                    data_KyThuatGieoHat.add(trangChuKhachHang);
                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    byte[] byteArrayHinh = new byte[0];

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            ivHinh.setImageURI(uri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArrayHinh = stream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // chuyen Byte[] Sang Chuoi
    private String chuyenByteSangChuoi(byte[] byteArray) {
        String base64String = android.util.Base64.encodeToString(byteArray, android.util.Base64.NO_PADDING | android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
        return base64String;
    }

    //chuyen String Sang Byte[]
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