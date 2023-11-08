package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class MainActivity_QuanTriNhanVien extends AppCompatActivity {
    CheckBox chkBanHang, chkThuKho, chkShipper;
    RecyclerView recyclerView;
    List<NhanVien> data_NhanVien = new ArrayList<>();

    EditText edtTimKiem;
    public static Spinner spLoaiNV;
    List<String> data_loaiNhanVien = new ArrayList<>();
    int index = -1;
    ImageView ivHinhNV, ivQuayVe;

    Button btnThem, btnXoa, btnSua;
    FirebaseDatabase database;
    DatabaseReference data_NV;
    DatabaseReference data_LoaiNV;
    ArrayAdapter adapter;

    public static EditText edtTenNV, edtSDT, edtQueQuan, edtNgaySinh, edtGmail, edtCMND, edtTenDangNhap, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_tri_nhan_vien);

        setControl();
        setEvent();
    }

    private void setEvent() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("QuanTriNhanVien");

        database = FirebaseDatabase.getInstance();
        data_NV = database.getReference("NhanVien");
        data_LoaiNV = database.getReference("LoaiNV");

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new NhanVienAdapter(this, data_NhanVien));

        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_loaiNhanVien);
        spLoaiNV.setAdapter(adapter);

        NhanVienAdapter nhanVienAdapter = (NhanVienAdapter) recyclerView.getAdapter();
        nhanVienAdapter.setOnItemClickListenner(new NhanVienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                index = position;
                NhanVien nhanVien = data_NhanVien.get(position);
                edtTenNV.setText(nhanVien.tenNhanVien);
                edtSDT.setText(nhanVien.soDienThoai);
                edtQueQuan.setText(nhanVien.queQuan);
                edtNgaySinh.setText(nhanVien.NgaySinh);
                edtGmail.setText(nhanVien.Gmail);
                edtCMND.setText(nhanVien.CMND);
                edtTenDangNhap.setText(nhanVien.tenDangNhap);
                edtPassword.setText(nhanVien.matKhau);

                if (!nhanVien.getHinh().toString().trim().equals("")) {
                    try {
                        byte[] bytes = chuyenStringSangByte(nhanVien.getHinh());
                        Bitmap bitmap = chuyenByteSangBitMap(bytes);
                        ivHinhNV.setImageBitmap(bitmap);
                        byteArrayHinh = bytes;
                    } catch (Exception e) {
                        byteArrayHinh = new byte[0];
                        ivHinhNV.setImageResource(R.drawable.anhsp_quantri);
                    }

                } else {
                    byteArrayHinh = new byte[0];
                    ivHinhNV.setImageResource(R.drawable.anhsp_quantri);
                }


                kiemLoaiNhanVien(nhanVien);
                EnabelButtonFalse();

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDuLieuNhapVao() == true) {
                    if (spLoaiNV.getSelectedItemPosition() != 0) {

                        NhanVien nhanVien = new NhanVien();
                        String maNV = data_NV.push().getKey();
                        nhanVien.setMaNhanVien(maNV);

                        nhanVien.setTenNhanVien(edtTenNV.getText().toString());
                        nhanVien.setSoDienThoai(edtSDT.getText().toString());
                        nhanVien.setQueQuan(edtQueQuan.getText().toString());

                        nhanVien.setNgaySinh(edtNgaySinh.getText().toString());
                        nhanVien.setGmail(edtGmail.getText().toString());
                        nhanVien.setLoaiNhanVien(spLoaiNV.getSelectedItem().toString());

                        nhanVien.setCMND(edtCMND.getText().toString());
                        nhanVien.setTenDangNhap(edtTenDangNhap.getText().toString());
                        nhanVien.setMatKhau(edtPassword.getText().toString());
                        nhanVien.setQuyen("" + spLoaiNV.getSelectedItemPosition());

                        String hinh = chuyenByteSangChuoi(byteArrayHinh);
                        nhanVien.setHinh(hinh);

                        data_NV.child(maNV).setValue(nhanVien);
                        Toast.makeText(MainActivity_QuanTriNhanVien.this, "Thêm dữ liệu thành công !", Toast.LENGTH_SHORT).show();
                        clearEditText();

                    } else {
                        Toast.makeText(MainActivity_QuanTriNhanVien.this, "Loại nhân viên chưa được chọn", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    String maNV = data_NhanVien.get(index).maNhanVien;
                    data_NV.child(maNV).removeValue();
                    Toast.makeText(MainActivity_QuanTriNhanVien.this, "Xóa dữ liệu thành công !", Toast.LENGTH_SHORT).show();
                    clearEditText();
                    EnabelButtonTrue();
                } else {
                    Toast.makeText(MainActivity_QuanTriNhanVien.this, "Chọn để xóa", Toast.LENGTH_SHORT).show();
                }

            }
        });

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(MainActivity_QuanTriNhanVien.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        edtNgaySinh.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, 2023, 10, 27);
                datePicker.show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    if (spLoaiNV.getSelectedItemPosition() != 0) {
                        String maNV = data_NhanVien.get(index).maNhanVien;

                        data_NV.child(maNV).child("tenNhanVien").setValue(edtTenNV.getText().toString());
                        data_NV.child(maNV).child("soDienThoai").setValue(edtSDT.getText().toString());
                        data_NV.child(maNV).child("queQuan").setValue(edtQueQuan.getText().toString());

                        data_NV.child(maNV).child("ngaySinh").setValue(edtNgaySinh.getText().toString());
                        data_NV.child(maNV).child("gmail").setValue(edtGmail.getText().toString());
                        data_NV.child(maNV).child("loaiNhanVien").setValue(spLoaiNV.getSelectedItem().toString());

                        data_NV.child(maNV).child("cmnd").setValue(edtCMND.getText().toString());
                        data_NV.child(maNV).child("tenDangNhap").setValue(edtTenDangNhap.getText().toString());
                        data_NV.child(maNV).child("matKhau").setValue(edtPassword.getText().toString());

                        data_NV.child(maNV).child("quyen").setValue("" + spLoaiNV.getSelectedItemPosition());

                        String hinh = chuyenByteSangChuoi(byteArrayHinh);
                        data_NV.child(maNV).child("hinh").setValue(hinh);
                        Toast.makeText(MainActivity_QuanTriNhanVien.this, "Sửa dữ liệu thành công!", Toast.LENGTH_SHORT).show();
                        clearEditText();
                        EnabelButtonTrue();

                    } else {
                        Toast.makeText(MainActivity_QuanTriNhanVien.this, "Loại nhân viên chưa được chọn", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity_QuanTriNhanVien.this, "Chọn để sua", Toast.LENGTH_SHORT).show();
                }

            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timKiem();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivHinhNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
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

//        data_LoaiNV.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                DocDLLoaiNhanVien();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                DocDLLoaiNhanVien();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                DocDLLoaiNhanVien();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        chkBanHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                phanLoaiNhanVien();
            }
        });

        chkThuKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                phanLoaiNhanVien();
            }
        });

        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditText();
                EnabelButtonTrue();

                Intent intent = new Intent(MainActivity_QuanTriNhanVien.this, MainActivity_TrangChuAdmin.class);
                startActivity(intent);
            }
        });

        chkShipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                phanLoaiNhanVien();
            }
        });
    }


    private void clearEditText() {
        edtTenNV.setText("");
        edtSDT.setText("");
        edtQueQuan.setText("");
        edtNgaySinh.setText("");
        edtGmail.setText("");
        edtCMND.setText("");
        edtTenDangNhap.setText("");
        edtPassword.setText("");
        spLoaiNV.setSelection(0);

        byteArrayHinh = new byte[0];
        ivHinhNV.setImageResource(R.drawable.anhsp_quantri);
    }

    private void EnabelButtonTrue() {
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
    }

    private void EnabelButtonFalse() {
        btnThem.setEnabled(false);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }

    byte[] byteArrayHinh = new byte[0];

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            ivHinhNV.setImageURI(uri);
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

    private boolean kiemTraDuLieuNhapVao() {
        if (!edtTenNV.getText().toString().trim().equals("")) {
            if (!edtSDT.getText().toString().trim().equals("")) {
                if (!edtQueQuan.getText().toString().trim().equals("")) {
                    if (!edtNgaySinh.getText().toString().equals(null)) {
                        if (!edtGmail.getText().toString().trim().equals("")) {
                            if (!edtCMND.getText().toString().trim().equals("")) {
                                if (!edtTenDangNhap.getText().toString().trim().equals("")) {
                                    if (!edtPassword.getText().toString().trim().equals("")) {


                                    } else {
                                        edtPassword.setError("Password đang để trống ! ");
                                        return false;
                                    }
                                } else {
                                    edtTenDangNhap.setError("Tên đăng nhập đang để trống ! ");
                                    return false;
                                }
                            } else {
                                edtCMND.setError("CMND đang để trống ! ");
                                return false;
                            }
                        } else {
                            edtGmail.setError("Gmail đang để trống ! ");
                            return false;
                        }
                    } else {
                        edtNgaySinh.setError("Ngày sinh đang để trống ! ");
                        return false;
                    }
                } else {
                    edtQueQuan.setError("Quê quán đang để trống ! ");
                    return false;
                }
            } else {
                edtSDT.setError("Số điện thoại đang để trống ! ");
                return false;
            }
        } else {
            edtTenNV.setError("Tên nhân viên đang để trống ! ");
            return false;
        }
        return true;
    }

    private void kiemLoaiNhanVien(NhanVien nhanVien) {
        for (int i = 0; i < data_loaiNhanVien.size(); i++) {
           if(nhanVien.getLoaiNhanVien().toString().trim().equals(data_loaiNhanVien.get(i).toString().trim())){
               spLoaiNV.setSelection(i);
           }
        }
    }


    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewDanhSachNhanVien);
        spLoaiNV = findViewById(R.id.spLoaiNhanVien);

        edtTenNV = findViewById(R.id.edtTenNV);
        edtSDT = findViewById(R.id.edtSDT);
        edtQueQuan = findViewById(R.id.edtQueQuan);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtGmail = findViewById(R.id.edtGmail);
        edtCMND = findViewById(R.id.edtCMND);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtPassword = findViewById(R.id.edtMatKhau);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

        edtTimKiem = findViewById(R.id.edtTimKiem);

        ivHinhNV = findViewById(R.id.ivHinhNV);
        ivQuayVe = findViewById(R.id.ivQuayVe);

        chkShipper = findViewById(R.id.checkBoxShipper);
        chkBanHang = findViewById(R.id.checkBoxBanHang);
        chkThuKho = findViewById(R.id.checkBoxThuKho);
    }

    public void timKiem() {
        data_NhanVien.clear();
        data_NV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_NhanVien.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien = item.getValue(NhanVien.class);
                    if (nhanVien.getTenNhanVien().toLowerCase().contains(edtTimKiem.getText().toString().toLowerCase())) {
                        data_NhanVien.add(nhanVien);
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDL() {
        data_NhanVien.clear();
        data_NV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_NhanVien.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien = item.getValue(NhanVien.class);
                    data_NhanVien.add(nhanVien);
                }
                edtTimKiem.setText("");
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDLLoaiNhanVien() {
        data_loaiNhanVien.clear();
        data_LoaiNV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    data_loaiNhanVien.clear();
                    data_loaiNhanVien.add("Nhóm ngành");
                    //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        LoaiNV loaiNV = item.getValue(LoaiNV.class);
                        data_loaiNhanVien.add(loaiNV.loaiNhanVien);
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

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


    public void phanLoaiNhanVien() {
        data_NhanVien.clear();
        data_NV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_NhanVien.clear();
                //Toast.makeText(MainActivity_QuanTriNhanVien.this, "Hello", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhanVien nhanVien = item.getValue(NhanVien.class);

                    data_loaiNhanVien.add("Bán hàng");
                    data_loaiNhanVien.add("Thủ kho");
                    data_loaiNhanVien.add("Shipper");

                    if (chkBanHang.isChecked()) {
                        if (nhanVien.loaiNhanVien.equals("Bán hàng")) {
                            data_NhanVien.add(nhanVien);
                        }
                        if (chkThuKho.isChecked()) {
                            if (nhanVien.loaiNhanVien.equals("Thủ kho")) {
                                data_NhanVien.add(nhanVien);
                            }
                            if (chkShipper.isChecked()) {
                                if (nhanVien.loaiNhanVien.equals("Shipper")) {
                                    data_NhanVien.add(nhanVien);
                                }
                            }
                        } else if (chkShipper.isChecked()) {
                            if (nhanVien.loaiNhanVien.equals("Shipper")) {
                                data_NhanVien.add(nhanVien);
                            }
                            if (chkThuKho.isChecked()) {
                                if (nhanVien.loaiNhanVien.equals("Thủ kho")) {
                                    data_NhanVien.add(nhanVien);
                                }
                            }
                        }
                    } else if (chkThuKho.isChecked()) {
                        if (nhanVien.loaiNhanVien.equals("Thủ kho")) {
                            data_NhanVien.add(nhanVien);
                        }
                        if (chkBanHang.isChecked()) {
                            if (nhanVien.loaiNhanVien.equals("Bán hàng")) {
                                data_NhanVien.add(nhanVien);
                            }
                            if (chkShipper.isChecked()) {
                                if (nhanVien.loaiNhanVien.equals("Shipper")) {
                                    data_NhanVien.add(nhanVien);
                                }
                            }

                        } else if (chkShipper.isChecked()) {
                            if (nhanVien.loaiNhanVien.equals("Shipper")) {
                                data_NhanVien.add(nhanVien);
                            }
                            if (chkBanHang.isChecked()) {
                                if (nhanVien.loaiNhanVien.equals("Bán hàng")) {
                                    data_NhanVien.add(nhanVien);
                                }
                            }
                        }
                    } else if (chkShipper.isChecked()) {
                        if (nhanVien.loaiNhanVien.equals("Shipper")) {
                            data_NhanVien.add(nhanVien);
                        }

                        if (chkBanHang.isChecked()) {
                            if (nhanVien.loaiNhanVien.equals("Bán hàng")) {
                                data_NhanVien.add(nhanVien);
                            }

                            if (chkThuKho.isChecked()) {
                                if (nhanVien.loaiNhanVien.equals("Thủ kho")) {
                                    data_NhanVien.add(nhanVien);
                                }
                            }

                        } else if (chkThuKho.isChecked()) {
                            if (nhanVien.loaiNhanVien.equals("Thủ kho")) {
                                data_NhanVien.add(nhanVien);
                            }

                            if (chkBanHang.isChecked()) {
                                if (nhanVien.loaiNhanVien.equals("Bán hàng")) {
                                    data_NhanVien.add(nhanVien);
                                }
                            }
                        }
                    } else {
                        if (nhanVien.loaiNhanVien.equals("Bán hàng")) {
                            data_NhanVien.add(nhanVien);
                        }
                        if (nhanVien.loaiNhanVien.equals("Thủ kho")) {
                            data_NhanVien.add(nhanVien);
                        }
                        if (nhanVien.loaiNhanVien.equals("Shipper")) {
                            data_NhanVien.add(nhanVien);
                        }
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}