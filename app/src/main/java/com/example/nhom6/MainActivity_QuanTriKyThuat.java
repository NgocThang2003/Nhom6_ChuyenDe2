package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

public class MainActivity_QuanTriKyThuat extends AppCompatActivity {

    CheckBox chkCN, chkNN, chkLN;
    EditText edtTenKyThuat, edtMoTa, edtTimKiem;
    Spinner spNhomNganh;

    int index = -1;
    ImageView ivHinh;

    Button btnThem, btnXoa, btnSua;
    List<String> data_nhomNganh = new ArrayList<>();

    RecyclerView recyclerView;
    ArrayList<TrangChuKhachHang> data_kyThuaTrongCay = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference data_KyThuat;
    DatabaseReference data_NhomNganh;
    String maKT = "";

    EditText edtNhomNganh;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_tri_ky_thuat);

        setControl();
        setEvent();
    }

    private void setEvent() {

        KhoiTao();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("TrangChuKhachHang");


        database = FirebaseDatabase.getInstance();
        data_KyThuat = database.getReference("TrangChuKhachHang");
        data_NhomNganh = database.getReference("NhomNganh");


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TrangChuKhachHang_Adapter(this, data_kyThuaTrongCay));

         adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_nhomNganh);
        spNhomNganh.setAdapter(adapter);

        TrangChuKhachHang_Adapter trangChuKhachHangAdapter = (TrangChuKhachHang_Adapter) recyclerView.getAdapter();

        trangChuKhachHangAdapter.setOnItemClickListenner(new TrangChuKhachHang_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TrangChuKhachHang trangChuKhachHang = data_kyThuaTrongCay.get(position);
                edtTenKyThuat.setText(trangChuKhachHang.tenKyThuat);
                edtMoTa.setText(TrangChuKhachHang_Adapter.moTa);
                index = position;
                maKT = trangChuKhachHang.maKyThuat;
                if (!trangChuKhachHang.getHinh().toString().trim().equals("")) {
                    try {
                        byte[] bytes = chuyenStringSangByte(trangChuKhachHang.getHinh());
                        Bitmap bitmap = chuyenByteSangBitMap(bytes);
                        ivHinh.setImageBitmap(bitmap);
                        byteArrayHinh = bytes;
                    } catch (Exception e) {
                        ivHinh.setImageResource(R.drawable.anhsp_quantri);
                        byteArrayHinh = new byte[0];
                    }
                } else {
                    ivHinh.setImageResource(R.drawable.anhsp_quantri);
                    byteArrayHinh = new byte[0];
                }

                kiemTraNhomNganh(trangChuKhachHang);
                EnabelButtonFalse();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDieuKien() == true) {
                    if (spNhomNganh.getSelectedItemPosition() != 0) {
                        TrangChuKhachHang trangChuKhachHang = new TrangChuKhachHang();

                        trangChuKhachHang.setMaKyThuat(data_KyThuat.push().getKey());
                        trangChuKhachHang.setTenKyThuat(edtTenKyThuat.getText().toString().trim());
                        trangChuKhachHang.setTenMoTa(edtMoTa.getText().toString().trim());
                        trangChuKhachHang.setNhomNganh(spNhomNganh.getSelectedItem().toString());

                        String hinh = chuyenByteSangChuoi(byteArrayHinh);
                        trangChuKhachHang.setHinh(hinh);

                        data_KyThuat.child(trangChuKhachHang.maKyThuat).setValue(trangChuKhachHang);
                        clearEditText();
                        EnabelButtonTrue();
                    } else {
                        Toast.makeText(MainActivity_QuanTriKyThuat.this, "Bạn chưa chọn nhóm ngành !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    String maKyThuat = data_kyThuaTrongCay.get(index).maKyThuat;
                    data_KyThuat.child(maKyThuat).removeValue();
                    clearEditText();
                    EnabelButtonFalse();

                } else {
                    Toast.makeText(MainActivity_QuanTriKyThuat.this, "Chọn để xóa dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    if (kiemTraDieuKien() == true) {
                        if (spNhomNganh.getSelectedItemPosition() != 0) {
                            String maKyThuat = data_kyThuaTrongCay.get(index).maKyThuat;
                            data_KyThuat.child(maKyThuat).child("tenKyThuat").setValue(edtTenKyThuat.getText().toString());
                            data_KyThuat.child(maKyThuat).child("tenMoTa").setValue(edtMoTa.getText().toString());
                            data_KyThuat.child(maKyThuat).child("nhomNganh").setValue(spNhomNganh.getSelectedItem().toString());


                            String hinh = chuyenByteSangChuoi(byteArrayHinh);
                            data_KyThuat.child(maKyThuat).child("hinh").setValue(hinh);

//                            String tenKyThuat, tenMoTa, nhomNganh;
//                            String maKyThuat, hinh;
                            clearEditText();
                            EnabelButtonFalse();

                        } else {
                            Toast.makeText(MainActivity_QuanTriKyThuat.this, "Bạn chưa chọn nhóm ngành !", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity_QuanTriKyThuat.this, "Chọn để sửa dữ liệu", Toast.LENGTH_SHORT).show();
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

        data_KyThuat.addChildEventListener(new ChildEventListener() {
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

        data_NhomNganh.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNhomNganh();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLNhomNganh();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLNhomNganh();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ivHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        chkNN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                phanLoaiNhomNganh();
            }
        });

        chkCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                phanLoaiNhomNganh();
            }
        });

        chkLN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                phanLoaiNhomNganh();
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

    public void phanLoaiNhomNganh() {
        data_kyThuaTrongCay.clear();
        data_KyThuat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_kyThuaTrongCay.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "Hello", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TrangChuKhachHang trangChuKhachHang = item.getValue(TrangChuKhachHang.class);

                    if (chkNN.isChecked()) {
                        if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
                            data_kyThuaTrongCay.add(trangChuKhachHang);
                        }
                        if (chkCN.isChecked()) {
                            if (trangChuKhachHang.nhomNganh.equals("Công nghiệp")) {
                                data_kyThuaTrongCay.add(trangChuKhachHang);
                            }
                            if (chkLN.isChecked()) {
                                if (trangChuKhachHang.nhomNganh.equals("Lâm nghiệp")) {
                                    data_kyThuaTrongCay.add(trangChuKhachHang);
                                }
                            }
                        } else if (chkLN.isChecked()) {
                            if (trangChuKhachHang.nhomNganh.equals("Lâm nghiệp")) {
                                data_kyThuaTrongCay.add(trangChuKhachHang);
                            }
                            if (chkCN.isChecked()) {
                                if (trangChuKhachHang.nhomNganh.equals("Công nghiệp")) {
                                    data_kyThuaTrongCay.add(trangChuKhachHang);
                                }
                            }
                        }
                    } else if (chkCN.isChecked()) {
                        if (trangChuKhachHang.nhomNganh.equals("Công nghiệp")) {
                            data_kyThuaTrongCay.add(trangChuKhachHang);
                        }
                        if (chkNN.isChecked()) {
                            if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
                                data_kyThuaTrongCay.add(trangChuKhachHang);
                            }
                            if (chkLN.isChecked()) {
                                if (trangChuKhachHang.nhomNganh.equals("Lâm nghiệp")) {
                                    data_kyThuaTrongCay.add(trangChuKhachHang);
                                }
                            }

                        } else if (chkLN.isChecked()) {
                            if (trangChuKhachHang.nhomNganh.equals("Lâm nghiệp")) {
                                data_kyThuaTrongCay.add(trangChuKhachHang);
                            }
                            if (chkNN.isChecked()) {
                                if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
                                    data_kyThuaTrongCay.add(trangChuKhachHang);
                                }
                            }
                        }
                    } else if (chkLN.isChecked()) {
                        if (trangChuKhachHang.nhomNganh.equals("Lâm nghiệp")) {
                            data_kyThuaTrongCay.add(trangChuKhachHang);
                        }

                        if (chkNN.isChecked()) {
                            if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
                                data_kyThuaTrongCay.add(trangChuKhachHang);
                            }

                            if (chkCN.isChecked()) {
                                if (trangChuKhachHang.nhomNganh.equals("Công nghiệp")) {
                                    data_kyThuaTrongCay.add(trangChuKhachHang);
                                }
                            }

                        } else if (chkCN.isChecked()) {
                            if (trangChuKhachHang.nhomNganh.equals("Công nghiệp")) {
                                data_kyThuaTrongCay.add(trangChuKhachHang);
                            }

                            if (chkNN.isChecked()) {
                                if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
                                    data_kyThuaTrongCay.add(trangChuKhachHang);
                                }
                            }
                        }
                    } else {
                        if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
                            data_kyThuaTrongCay.add(trangChuKhachHang);
                        }
                        if (trangChuKhachHang.nhomNganh.equals("Công nghiệp")) {
                            data_kyThuaTrongCay.add(trangChuKhachHang);
                        }
                        if (trangChuKhachHang.nhomNganh.equals("Lâm nghiệp")) {
                            data_kyThuaTrongCay.add(trangChuKhachHang);
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

    public void timKiem() {
        data_kyThuaTrongCay.clear();
        data_KyThuat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_kyThuaTrongCay.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TrangChuKhachHang trangChuKhachHang = item.getValue(TrangChuKhachHang.class);
                    if (trangChuKhachHang.getTenKyThuat().contains(edtTimKiem.getText().toString())) {
                        data_kyThuaTrongCay.add(trangChuKhachHang);
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
        data_kyThuaTrongCay.clear();
        data_KyThuat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_kyThuaTrongCay.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TrangChuKhachHang trangChuKhachHang = item.getValue(TrangChuKhachHang.class);
                    data_kyThuaTrongCay.add(trangChuKhachHang);
                }
                edtTimKiem.setText("");
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void DocDLNhomNganh() {
        //data_nhomNganh.clear();
        data_NhomNganh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_nhomNganh.clear();
                data_nhomNganh.add("Bank");
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhomNganh nhomNganh = item.getValue(NhomNganh.class);
                    data_nhomNganh.add(nhomNganh.tenNhomNganh.toString().trim());
                }
                adapter.notifyDataSetChanged();
                //edtTimKiem.setText("");
                //recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void KhoiTao() {
        //edtNhomNganh.setText(spNhomNganh.getSelectedItem().toString());
        //data_nhomNganh.add("Bank");
        try{
            DocDLNhomNganh();
        }
        catch (Exception e){

        }

//        data_nhomNganh.add("Nông nghiệp");
//        data_nhomNganh.add("Công nghiệp");
//        data_nhomNganh.add("Lâm nghiệp");
    }

    private void kiemTraNhomNganh(TrangChuKhachHang trangChuKhachHang) {
//        if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
//            spNhomNganh.setSelection(1);
//        } else if (trangChuKhachHang.nhomNganh.trim().equals("Công nghiệp")) {
//            spNhomNganh.setSelection(2);
//        } else if (trangChuKhachHang.nhomNganh.trim().equals("Lâm nghiệp")) {
//            spNhomNganh.setSelection(3);
//        }

        for (int i = 0; i < data_nhomNganh.size(); i++) {
            if (trangChuKhachHang.nhomNganh.equals(data_nhomNganh.get(i).toString().trim())) {
                spNhomNganh.setSelection(i);
            }
        }
    }


    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewDanhSachKyThuatCayTrong);
        edtTenKyThuat = findViewById(R.id.edtTenKyThuat);
        edtMoTa = findViewById(R.id.edtMoTa);
        edtTimKiem = findViewById(R.id.edtTimKiem);

        spNhomNganh = findViewById(R.id.spNhomNganh);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

        ivHinh = findViewById(R.id.ivHinh);
        chkCN = findViewById(R.id.checkBoxCongNghiep);
        chkNN = findViewById(R.id.checkBoxNongNghiep);
        chkLN = findViewById(R.id.checkBoxLamNghep);

        edtNhomNganh = findViewById(R.id.edtNhomNganh);

    }

    private boolean kiemTraDieuKien() {
        boolean kiemTra = true;
        if (!edtTenKyThuat.getText().toString().equals("")) {
            if (!edtMoTa.getText().toString().equals("")) {

            } else {
                edtMoTa.setError("Dữ liệu không được để trống !");
                kiemTra = false;
            }
        } else {
            edtTenKyThuat.setError("Dữ liệu không được để trống !");
            kiemTra = false;
        }

        return kiemTra;
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


    private void clearEditText() {
        edtTenKyThuat.setText("");
        edtMoTa.setText("");
        edtTimKiem.setText(null);
        spNhomNganh.setSelection(0);
        ivHinh.setImageResource(R.drawable.anhsp_quantri);
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

}