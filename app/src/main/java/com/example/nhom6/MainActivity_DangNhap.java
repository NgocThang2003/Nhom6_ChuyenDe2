package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_DangNhap extends AppCompatActivity {
    EditText edtTen, edtMatKhau;
    RadioButton rdAdmin, rdNhanVien, rdKhachHang, rdThuKho, rdShipper;
    TextView tvRegister;
    CheckBox cbHienMK;
    Button btnxacNhan;
    List<TaiKhoan> data_taiKhoan = new ArrayList<>();
    List<NhanVien> data_NhanVien = new ArrayList<>();
    public static TaiKhoan dangNhap = new TaiKhoan();
    public static int index = -1;
    FirebaseDatabase database;
    DatabaseReference data_TK;
    DatabaseReference data_NV;
    public static String maNguoiDung;
    public static String tenShipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdangnhap);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_TK = database.getReference("DangKy");
        data_NV = database.getReference("NhanVien");
        btnxacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemTraDangNhap();
            }
        });
//        Chuyển sang màn hình đăng ký
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_DangNhap.this, MainActivity_DangKy.class);
                startActivity(intent);
            }
        });
//        Hiện mật khẩu
        cbHienMK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbHienMK.isChecked() == true) {
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });
        data_TK.addChildEventListener(new ChildEventListener() {
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

        data_NV.addChildEventListener(new ChildEventListener() {
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

    }

    private void DocDL() {
        data_taiKhoan.clear();
        data_TK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_taiKhoan.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);
                    data_taiKhoan.add(taiKhoan);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        data_NhanVien.clear();  
        data_NV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_NhanVien.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien = item.getValue(NhanVien.class);
                    data_NhanVien.add(nhanVien);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void kiemTraDangNhap() {
        boolean kiemTra = false;
        for (int i = 0; i < data_taiKhoan.size(); i++) {
            if (edtTen.getText().toString().trim().equals(data_taiKhoan.get(i).Email)) {
                if (edtMatKhau.getText().toString().trim().equals(data_taiKhoan.get(i).password)) {
                    if (data_taiKhoan.get(i).getQuyen() == 0 && rdAdmin.isChecked() == true) {
                        kiemTra = true;
                        index = i;
                        //kiểm tra tài khoản nếu đúng thì sẽ vào admin trang chủ
                        Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity_DangNhap.this, TrangChu_Admin.class);
//                        startActivity(intent);
                    }
                    if (data_taiKhoan.get(i).getQuyen() == 4 && rdKhachHang.isChecked() == true) {
                        kiemTra = true;
                        index = i;
                        maNguoiDung =data_taiKhoan.get(i).maNguoiDung;
                        Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity_DangNhap.this, Trangchu_KhachHang.class);
//                        startActivity(intent);
                    }

                }
            }
        }

        for (int i = 0; i < data_NhanVien.size(); i++) {
            //Toast.makeText(this, ""+data_NhanVien.get(i).Gmail, Toast.LENGTH_SHORT).show();
            if (edtTen.getText().toString().trim().equals(data_NhanVien.get(i).Gmail.toString().trim())) {
                if (edtMatKhau.getText().toString().trim().equals(data_NhanVien.get(i).matKhau.toString().trim())) {

                    if (data_NhanVien.get(i).quyen.toString().trim().equals("1") && rdNhanVien.isChecked() == true) {
                        kiemTra = true;
                        index = i;
                        Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity_DangNhap.this, Trangchu_NhanVien.class);
//                        startActivity(intent);
                    }
                    if (data_NhanVien.get(i).quyen.equals("2") && rdThuKho.isChecked() == true) {
                        kiemTra = true;
                        index = i;
                        Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity_DangNhap.this, Trangchu_ThuKho.class);
//                        startActivity(intent);
                    }
                    if (data_NhanVien.get(i).quyen.equals("3") && rdShipper.isChecked() == true) {
                        kiemTra = true;
                        index = i;
                        maNguoiDung = data_NhanVien.get(i).maNhanVien;
                        tenShipper = data_NhanVien.get(i).tenNhanVien;
                        Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity_DangNhap.this, MainActivity_TrangChuShipper.class);
                        startActivity(intent);
                    }

                }
            }

        }

        if (kiemTra == false) {
            Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void setControl() {
        edtTen = findViewById(R.id.edtTenDN);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        rdAdmin = findViewById(R.id.rdAdmin);
        rdNhanVien = findViewById(R.id.rdNhanVien);
        rdKhachHang = findViewById(R.id.rdKhachHang);
        rdThuKho = findViewById(R.id.rdThuKho);
        rdShipper = findViewById(R.id.rdShipper);
        tvRegister = findViewById(R.id.tvRegister);
        cbHienMK = findViewById(R.id.cbHienMK);
        btnxacNhan = findViewById(R.id.btnDangNhap);
    }
}
