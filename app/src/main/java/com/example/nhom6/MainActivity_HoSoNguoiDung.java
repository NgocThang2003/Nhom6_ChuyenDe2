package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_HoSoNguoiDung extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference data_HSND;
    EditText edtTen, edtSDT, edtEmail;
    ImageView ivHinh;
    Button btnLuu;
    ImageView ivQuayVe;
    List<TaiKhoan> data_HoSo= new ArrayList<>();
    String maKH = "-NiNrHieKJTJY-rlUhgh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosonguoidung);
        setControl();
        setEvent();
    }

    private void setControl() {
        ivHinh = findViewById(R.id.ivHinh);
        edtTen = findViewById(R.id.edtTen);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        btnLuu = findViewById(R.id.btnLuu);
        ivQuayVe = findViewById(R.id.imgQuayVe);
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_HSND = database.getReference("DangKy");

        data_HSND.addChildEventListener(new ChildEventListener() {
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
        ivHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!maKH.toString().trim().equals("")){
                    data_HSND.child(maKH.toString()).child("hoten").setValue(edtTen.getText().toString());
                    data_HSND.child(maKH.toString()).child("sdt").setValue(edtSDT.getText().toString());
                    data_HSND.child(maKH.toString()).child("email").setValue(edtEmail.getText().toString());
                    String hinh = chuyenByteSangChuoi(byteArrayHinh);
                    data_HSND.child(maKH.toString()).child("hinh").setValue(hinh);
                    Toast.makeText(MainActivity_HoSoNguoiDung.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public void DocDL() {
        data_HoSo.clear();
        data_HSND.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_HoSo.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);
                    if (maKH.toString().trim().equals(taiKhoan.maNguoiDung.trim())) {
                        edtTen.setText(taiKhoan.hoten);
                        edtSDT.setText(taiKhoan.sdt);
                        edtEmail.setText(taiKhoan.Email);
                        if(taiKhoan.getHinh().trim().equals("")){
                            ivHinh.setImageResource(R.drawable.use);
                        }
                        else {
                            try {
                                byte[] bytes = chuyenStringSangByte(taiKhoan.getHinh());
                                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                                ivHinh.setImageBitmap(bitmap);
                            }
                            catch (Exception e){
                                ivHinh.setImageResource(R.drawable.use);
                            }
                        }
                    }
                    data_HoSo.add(taiKhoan);
                    //Toast.makeText(MainActivity_TrangChuKhachHang.this, "thay đổi"+trangChuKhachHang.tenKyThuat, Toast.LENGTH_SHORT).show();
                }
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