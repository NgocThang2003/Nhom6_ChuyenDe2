package com.example.nhom6;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class MainActivity_DangKy extends AppCompatActivity {
    EditText edtTen,edtSDT,edtEmail,edtMatKhau,edtNhapLaiMK;
    Button btnXacNhan;
    CheckBox checkBoxHienMatKhau;
    TextView tvLoi,tvLogin;
    FirebaseDatabase database;
    DatabaseReference data_TaiKhoan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdangky);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database=FirebaseDatabase.getInstance();
        data_TaiKhoan=database.getReference("DangKy");
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDuLieuNhapVao() == true) {
                    TaiKhoan taiKhoan = new TaiKhoan();

                    taiKhoan.setMaNguoiDung(data_TaiKhoan.push().getKey());
                    taiKhoan.setHoten(edtTen.getText().toString());
                    taiKhoan.setSdt(edtSDT.getText().toString());
                    taiKhoan.setEmail(edtEmail.getText().toString());
                    taiKhoan.setPassword(edtMatKhau.getText().toString());
                    taiKhoan.setHinh("");
                    taiKhoan.setQuyen(4);
                    //
                    data_TaiKhoan.child(taiKhoan.maNguoiDung).setValue(taiKhoan);

                    //


                    Toast.makeText(MainActivity_DangKy.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity_DangKy.this, MainActivity_DangNhap.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(MainActivity_DangKy.this, "Đăng ký không thành công !", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        Hien thi mat khau
        checkBoxHienMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxHienMatKhau.isChecked() == true) {
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);
                    edtNhapLaiMK.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtNhapLaiMK.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_DangKy.this, MainActivity_DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private boolean kiemTraDuLieuNhapVao() {
        boolean kiemTra = true;
        if (!edtTen.getText().toString().trim().equals("")) {


            if (!edtEmail.getText().toString().trim().equals("")) {
                if (!edtSDT.getText().toString().trim().equals("")) {

                    if (!edtMatKhau.getText().toString().trim().equals("")) {
                        if (!edtMatKhau.getText().toString().trim().equals(edtNhapLaiMK.getText().toString().trim())) {
                            tvLoi.setText("Mật khẩu nhập lại không đúng !");
                            return false;
                        }

                    } else {
                        kiemTra = false;
                        edtMatKhau.setError("Dữ liệu nhập vào đang để trống !");
                    }

                } else {
                    kiemTra = false;
                    edtEmail.setError("Dữ liệu nhập vào đang để trống !");
                }
            }else {
                kiemTra=false;
                edtSDT.setError("Dữ liệu nhập vào đang để trống !");
            }

        } else {
            kiemTra = false;
            edtTen.setError("Dữ liệu nhập vào đang để trống !");
        }

        return kiemTra;
    }



//    private boolean kiemTraHopLe() {
//        if (edtTen.getText().toString().trim().equals("")) {
//            tvLoi.setText("Tên đăng nhập đang để trống !");
//            return false;
//        } else if (!edtMatKhau.getText().toString().trim().equals("")) {
//            if (!edtMatKhau.getText().toString().trim().equals(edtNhapLaiMK.getText().toString().trim())) {
//                tvLoi.setText("Mật khẩu nhập lại không đúng !");
//                return false;
//            }
//        } else if (edtMatKhau.getText().toString().trim().equals("")) {
//            tvLoi.setText("Mật khẩu không được để trống !");
//        }
//
//        return true;
//    }


    private void setControl() {
        edtTen = findViewById(R.id.edtTenDK);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNhapLaiMK = findViewById(R.id.edtNhapLaiMK);
        btnXacNhan = findViewById(R.id.btnXacNhanDK);
        checkBoxHienMatKhau = findViewById(R.id.cbcheckboxHienMK);
        tvLoi = findViewById(R.id.tvError);
        tvLogin = findViewById(R.id.tvLogIn);
    }
}
