package com.example.nhom6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_DanhGia extends AppCompatActivity {
    ImageView ivHinh,ivQuayLai,ivSao1,ivSao2,ivSao3,ivSao4,ivSao5;
    TextView tvTen,tvMoTa,tvGia,tvSoLuong,tvDiaChi,tvNgay,tvTrangThaiSao;
    EditText edtBinhLuan;
    Button btnGui;
    FirebaseDatabase database;
    DatabaseReference data_DanhGia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_chuadanhgia);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database=FirebaseDatabase.getInstance();
        data_DanhGia = database.getReference("DonHang");
        DonHang donHang = (DonHang) getIntent().getSerializableExtra("don hang");
        tvTen.setText(donHang.tenSanPham);
        tvMoTa.setText(donHang.moTa);
        tvGia.setText(donHang.gia);
        tvSoLuong.setText(donHang.soLuong);
        tvDiaChi.setText(donHang.diaChi);
        tvNgay.setText(donHang.ngay);
        if (!donHang.getHinh().toString().trim().equals("")) {
            try {
                byte[] hinh = chuyenStringSangByte(donHang.getHinh());
                Bitmap bitmap = chuyenByteSangBitMap(hinh);
                ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                ivHinh.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            ivHinh.setImageResource(R.drawable.ic_launcher_background);
        }
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_DanhGia.child(donHang.maDonHang).child("danhGia").setValue(edtBinhLuan.getText().toString().trim());
                //data_DanhGia.child(donHang.maDonHang).child("phanHoi").setValue("");
              Intent intent = new Intent(MainActivity_DanhGia.this, MainActivity_TatCaDanhGia.class);
               startActivity(intent);
            }
        });
        ivSao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSao1.setImageResource(R.drawable.star);
                ivSao2.setImageResource(R.drawable.saoxam);
                ivSao3.setImageResource(R.drawable.saoxam);
                ivSao4.setImageResource(R.drawable.saoxam);
                ivSao5.setImageResource(R.drawable.saoxam);
                tvTrangThaiSao.setText("Tệ");
                data_DanhGia.child(donHang.maDonHang).child("soSao").setValue("1");
            }
        });
        ivSao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSao1.setImageResource(R.drawable.star);
                ivSao2.setImageResource(R.drawable.star);
                ivSao3.setImageResource(R.drawable.saoxam);
                ivSao4.setImageResource(R.drawable.saoxam);
                ivSao5.setImageResource(R.drawable.saoxam);
                tvTrangThaiSao.setText("Không hài lòng");
                data_DanhGia.child(donHang.maDonHang).child("soSao").setValue("2");
            }
        });
        ivSao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSao1.setImageResource(R.drawable.star);
                ivSao2.setImageResource(R.drawable.star);
                ivSao3.setImageResource(R.drawable.star);
                ivSao4.setImageResource(R.drawable.saoxam);
                ivSao5.setImageResource(R.drawable.saoxam);
                tvTrangThaiSao.setText("Bình thường");
                data_DanhGia.child(donHang.maDonHang).child("soSao").setValue("3");
            }
        });
        ivSao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSao1.setImageResource(R.drawable.star);
                ivSao2.setImageResource(R.drawable.star);
                ivSao3.setImageResource(R.drawable.star);
                ivSao4.setImageResource(R.drawable.star);
                ivSao5.setImageResource(R.drawable.saoxam);
                tvTrangThaiSao.setText("Hài lòng");
                data_DanhGia.child(donHang.maDonHang).child("soSao").setValue("4");
            }
        });
        ivSao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSao1.setImageResource(R.drawable.star);
                ivSao2.setImageResource(R.drawable.star);
                ivSao3.setImageResource(R.drawable.star);
                ivSao4.setImageResource(R.drawable.star);
                ivSao5.setImageResource(R.drawable.star);
                tvTrangThaiSao.setText("Tuyệt vời");
                data_DanhGia.child(donHang.maDonHang).child("soSao").setValue("5");
            }
        });
        ivQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_DanhGia.this,MainActivity_DonHang.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        ivHinh=findViewById(R.id.ivHinhChuaDanhGia);
        ivQuayLai=findViewById(R.id.ivQuayLaiChuaDG);
        tvTen=findViewById(R.id.tvTenSPchuaDG);
        tvMoTa=findViewById(R.id.tvTenMoTachuaDG);
        tvGia=findViewById(R.id.tvGiachuaDG);
        tvSoLuong=findViewById(R.id.tvSoLuongchuaDG);
        tvDiaChi=findViewById(R.id.tvDiaChiChuaDanhGia);
        edtBinhLuan=findViewById(R.id.edtChiaSeBinhLuan);
        btnGui=findViewById(R.id.btnGuiDG);
        tvNgay=findViewById(R.id.tvNgayChuaDanhGia);
        ivSao1=findViewById(R.id.ivSaoDG1);
        ivSao2=findViewById(R.id.ivSaoDG2);
        ivSao3=findViewById(R.id.ivSaoDG3);
        ivSao4=findViewById(R.id.ivSaoDG4);
        ivSao5=findViewById(R.id.ivSaoDG5);
        tvTrangThaiSao=findViewById(R.id.tvtrangThaiSoSao);
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
