package com.example.nhom6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_LyDoHuyDon extends AppCompatActivity {
    TextView tvTenKhachHang,tvTenSP,tvMoTa,tvGia,tvSoLuong,tvNgay,tvDiaChi,tvSDT;
    EditText edtLyDoHuyDon;
    Button btnGui;
    ImageView ivHinh, ivQuayVe;

    FirebaseDatabase database;
    public static DatabaseReference data_DH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_lydohuydon_khachhang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_DH = database.getReference("DonHang");
        btnGui.setVisibility(View.GONE);
        // Sử dụng Serializable
        Intent intent = getIntent();
        DonHang myObject = (DonHang) intent.getSerializableExtra("don hang");

        tvTenKhachHang.setText(myObject.tenKhachHang);
        tvTenSP.setText(myObject.tenSanPham);
        tvMoTa.setText(myObject.moTa);
        tvGia.setText(myObject.gia);
        tvSoLuong.setText(myObject.soLuong);
        tvNgay.setText(myObject.ngay);
        tvDiaChi.setText(myObject.diaChi);
        tvSDT.setText(myObject.sDT);
        if (!myObject.getHinh().toString().trim().equals("")) {
            try {
                byte[] hinh = chuyenStringSangByte(myObject.getHinh());
                Bitmap bitmap = chuyenByteSangBitMap(hinh);
                ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                ivHinh.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            ivHinh.setImageResource(R.drawable.ic_launcher_background);
        }

        edtLyDoHuyDon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtLyDoHuyDon.getText().toString().equals("")){
                    btnGui.setVisibility(View.GONE);
                }
                else {
                    btnGui.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_DH.child(myObject.maDonHang).child("lyDoHuyDon").setValue(edtLyDoHuyDon.getText().toString().trim());
                data_DH.child(myObject.maDonHang).child("trangThai").setValue("Đã huỷ");
                Intent intent = new Intent(MainActivity_LyDoHuyDon.this, MainActivity_DonHang.class);
                startActivity(intent);
            }
        });
        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        tvTenKhachHang=findViewById(R.id.tvTenKH);
        tvTenSP=findViewById(R.id.tvTenSpHuyDon);
        tvMoTa=findViewById(R.id.tvMoTaHuyDon);
        tvGia=findViewById(R.id.tvGiaHuyDon);
        tvSoLuong=findViewById(R.id.tvSoLuongHuyDon);
        tvNgay=findViewById(R.id.tvNgayHuydon);
        tvDiaChi=findViewById(R.id.tvDiaChiHuyDon);
        tvSDT=findViewById(R.id.tvSDTHuyDon);
        edtLyDoHuyDon=findViewById(R.id.edtLyDoHuyDon);
        btnGui=findViewById(R.id.btnGuiHuy);
        ivHinh=findViewById(R.id.ivHinhHuy);
        ivQuayVe=findViewById(R.id.ivQuayVe);
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
