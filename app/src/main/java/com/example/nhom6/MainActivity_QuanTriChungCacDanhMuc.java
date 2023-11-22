package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

public class MainActivity_QuanTriChungCacDanhMuc extends AppCompatActivity {

    EditText edtTenLoai, edtMaLoai;
    ListView lvLoaiDanhMuc;
    ArrayAdapter adapter;
    RadioButton rdbLoaiSP, rdbLoaiNV, rdbNhomNganh, rdbDonVi;
    Button btnThemLoai, btnXoaLoai, btnSuaLoai;
    TextView tvLoaiDanhMuc, tvDanhSachLoai;

    FirebaseDatabase database;
    DatabaseReference data_loaiSP;
    DatabaseReference data_nhomNganh;
    DatabaseReference data_loaiNV;
    DatabaseReference data_DonVi;

    List<Object> data = new ArrayList<>();
    List<Object> data2 = new ArrayList<>();
    ImageView ivQuayVe;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_tri_chung_cac_danh_muc);

        setControl();
        setEvent();
    }

    private void setEvent() {
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data);
        lvLoaiDanhMuc.setAdapter(adapter);

        edtTenLoai.setHint(rdbLoaiNV.getText().toString().trim());

        database = FirebaseDatabase.getInstance();
        data_loaiSP = database.getReference("LoaiSP");
        data_nhomNganh = database.getReference("NhomNganh");
        data_loaiNV = database.getReference("LoaiNV");
        data_DonVi = database.getReference("DonVi");

        btnThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_QuanTriChungCacDanhMuc.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn thêm dữ liệu ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                if (rdbLoaiSP.isChecked()) {
                                    if (!edtTenLoai.getText().toString().equals("")) {
                                        LoaiSP loaiSP = new LoaiSP();
                                        loaiSP.setMaLoaiSP(data_loaiSP.push().getKey());
                                        loaiSP.setTenLoaiSP(edtTenLoai.getText().toString().trim());
                                        data_loaiSP.child(loaiSP.maLoaiSP).setValue(loaiSP);
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Thêm dữ liệu loại sản phẩm thành công", Toast.LENGTH_SHORT).show();

                                    } else {
                                        edtTenLoai.setError("Dữ liệu không được để trống !");
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Dữ liệu không để trống !", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbNhomNganh.isChecked()) {
                                    if (!edtTenLoai.getText().toString().equals("")) {
                                        NhomNganh nhomNganh = new NhomNganh();
                                        nhomNganh.setMaNhomNganh(data_loaiSP.push().getKey());
                                        nhomNganh.setTenNhomNganh(edtTenLoai.getText().toString().trim());
                                        data_nhomNganh.child(nhomNganh.maNhomNganh).setValue(nhomNganh);
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Thêm dữ liệu nhóm ngành thành công", Toast.LENGTH_SHORT).show();

                                    } else {
                                        edtTenLoai.setError("Dữ liệu không được để trống !");
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Dữ liệu không để trống !", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbLoaiNV.isChecked()) {
                                    if (!edtTenLoai.getText().toString().equals("")) {
                                        LoaiNV loaiNV = new LoaiNV();
                                        loaiNV.setMaNhanVien(data_loaiNV.push().getKey());
                                        loaiNV.setLoaiNhanVien(edtTenLoai.getText().toString().trim());
                                        data_loaiNV.child(loaiNV.maNhanVien).setValue(loaiNV);
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Thêm dữ liệu nhân viên thành công", Toast.LENGTH_SHORT).show();

                                    } else {
                                        edtTenLoai.setError("Dữ liệu không được để trống !");
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Dữ liệu không để trống !", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbDonVi.isChecked()) {
                                    if (!edtTenLoai.getText().toString().equals("")) {
                                        DonVi donVi = new DonVi();
                                        donVi.setMaDonVi(data_DonVi.push().getKey());
                                        donVi.setTenDonVi(edtTenLoai.getText().toString().trim());
                                        data_DonVi.child(donVi.maDonVi).setValue(donVi);
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Thêm dữ liệu loại đơn vị thành công", Toast.LENGTH_SHORT).show();

                                    } else {
                                        edtTenLoai.setError("Dữ liệu không được để trống !");
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Dữ liệu không để trống !", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                edtMaLoai.setText("");
                                edtTenLoai.setText("");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });

        btnXoaLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_QuanTriChungCacDanhMuc.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn xóa dữ liệu ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                if (rdbLoaiSP.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_loaiSP.child(edtMaLoai.getText().toString().trim()).removeValue();
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Xóa dữ liệu loại sản phẩm thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbNhomNganh.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_nhomNganh.child(edtMaLoai.getText().toString().trim()).removeValue();
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Xóa dữ liệu nhóm ngành thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbLoaiNV.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_loaiNV.child(edtMaLoai.getText().toString().trim()).removeValue();
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Xóa dữ liệu loại nhân viên thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbDonVi.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_DonVi.child(edtMaLoai.getText().toString().trim()).removeValue();
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Xóa dữ liệu đơn vị thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để xóa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                edtMaLoai.setText("");
                                edtTenLoai.setText("");
                                index = -1;

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });

        btnSuaLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_QuanTriChungCacDanhMuc.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn sửa dữ liệu ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                if (rdbLoaiSP.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_loaiSP.child(edtMaLoai.getText().toString().trim()).child("tenLoaiSP").setValue(edtTenLoai.getText().toString());
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Sửa dữ liệu loại sản phẩm thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbNhomNganh.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_nhomNganh.child(edtMaLoai.getText().toString().trim()).child("tenNhomNganh").setValue(edtTenLoai.getText().toString());
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Sửa dữ liệu nhóm ngành thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbLoaiNV.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_loaiNV.child(edtMaLoai.getText().toString().trim()).child("loaiNhanVien").setValue(edtTenLoai.getText().toString());
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Sửa dữ liệu loại nhân viên thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                if (rdbDonVi.isChecked()) {
                                    if (index != -1) {
                                        if (!edtMaLoai.getText().toString().trim().equals("")) {
                                            data_DonVi.child(edtMaLoai.getText().toString().trim()).child("tenDonVi").setValue(edtTenLoai.getText().toString());
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Sửa dữ liệu loại đơn vị thành công!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();

                                        }
                                    } else {
                                        Toast.makeText(MainActivity_QuanTriChungCacDanhMuc.this, "Chọn để sửa!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                edtMaLoai.setText("");
                                edtTenLoai.setText("");
                                index = -1;

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();

            }
        });

        lvLoaiDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (rdbLoaiSP.isChecked()) {
                    try {
                        LoaiSP loaiSP = (LoaiSP) data.get(position);
                        edtTenLoai.setText(loaiSP.tenLoaiSP.toString().trim());
                        edtMaLoai.setText(loaiSP.maLoaiSP.toString().trim());
                        index = position;
                    } catch (Exception e) {
                        DocDLLoaiSP();
                    }

                }

                if (rdbNhomNganh.isChecked()) {
                    try {
                        NhomNganh nhomNganh = (NhomNganh) data.get(position);
                        edtTenLoai.setText(nhomNganh.tenNhomNganh.toString().trim());
                        edtMaLoai.setText(nhomNganh.maNhomNganh.toString().trim());
                        index = position;
                    } catch (Exception e) {
                        DocDLNhomNganh();
                    }
                }

                if (rdbLoaiNV.isChecked()) {
                    try {
                        LoaiNV loaiNV = (LoaiNV) data.get(position);
                        edtTenLoai.setText(loaiNV.loaiNhanVien.toString().trim());
                        edtMaLoai.setText(loaiNV.maNhanVien.toString().trim());
                        index = position;
                    } catch (Exception e) {
                        DocDLLoaiNhanVien();
                    }
                }

                if (rdbDonVi.isChecked()) {
                    try {
                        DonVi donVi = (DonVi) data.get(position);
                        edtTenLoai.setText(donVi.tenDonVi.toString().trim());
                        edtMaLoai.setText(donVi.maDonVi.toString().trim());
                        index = position;
                    } catch (Exception e) {
                       DocDuLieuLoaiDonVi();
                    }
                }
            }
        });

        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rdbLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdbLoaiSP.isChecked()) {
                    edtTenLoai.setHint("Tên loại sản phẩm");
                    tvLoaiDanhMuc.setText(rdbLoaiSP.getText().toString());
                    tvDanhSachLoai.setText("Danh sách " + rdbLoaiSP.getText().toString());
                    DocDLLoaiSP();
                    edtMaLoai.setText("");
                    edtTenLoai.setText("");
                }
            }
        });

        rdbNhomNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdbNhomNganh.isChecked()) {
                    edtTenLoai.setHint("Tên nhóm ngành");
                    tvLoaiDanhMuc.setText(rdbNhomNganh.getText().toString());
                    tvDanhSachLoai.setText("Danh sách " + rdbNhomNganh.getText().toString());
                    DocDLNhomNganh();
                    edtMaLoai.setText("");
                    edtTenLoai.setText("");
                }
            }
        });

        rdbDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdbDonVi.isChecked()) {
                    edtTenLoai.setHint("Tên đơn vị");
                    tvLoaiDanhMuc.setText(rdbDonVi.getText().toString());
                    tvDanhSachLoai.setText("Danh sách " + rdbDonVi.getText().toString());
                    edtMaLoai.setText("");
                    edtTenLoai.setText("");
                    DocDuLieuLoaiDonVi();
                }
            }
        });

        rdbLoaiNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdbLoaiNV.isChecked()) {
                    edtTenLoai.setHint("Tên loại nhân viên");
                    tvLoaiDanhMuc.setText(rdbLoaiNV.getText().toString());
                    tvDanhSachLoai.setText("Danh sách " + rdbLoaiNV.getText().toString());
                    DocDLLoaiNhanVien();
                    edtMaLoai.setText("");
                    edtTenLoai.setText("");
                }
            }
        });


        data_nhomNganh.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbNhomNganh.isChecked()) {
                    DocDLNhomNganh();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbNhomNganh.isChecked()) {
                    DocDLNhomNganh();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (rdbNhomNganh.isChecked()) {
                    DocDLNhomNganh();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        data_loaiSP.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbLoaiSP.isChecked()) {
                    DocDLLoaiSP();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbLoaiSP.isChecked()) {
                    DocDLLoaiSP();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (rdbLoaiSP.isChecked()) {
                    DocDLLoaiSP();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        data_loaiNV.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbLoaiNV.isChecked()) {
                    DocDLLoaiNhanVien();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbLoaiNV.isChecked()) {
                    DocDLLoaiNhanVien();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (rdbLoaiNV.isChecked()) {
                    DocDLLoaiNhanVien();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        data_DonVi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbDonVi.isChecked()) {
                    DocDuLieuLoaiDonVi();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (rdbDonVi.isChecked()) {
                    DocDuLieuLoaiDonVi();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (rdbDonVi.isChecked()) {
                    DocDuLieuLoaiDonVi();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        edtTenLoai = findViewById(R.id.edtTenLoai);
        edtMaLoai = findViewById(R.id.edtMaLoai);

        btnThemLoai = findViewById(R.id.btnThemLoai);
        btnSuaLoai = findViewById(R.id.btnSuaLoai);
        btnXoaLoai = findViewById(R.id.btnXoaLoai);

        rdbLoaiSP = findViewById(R.id.rdbLoaiSP);
        rdbNhomNganh = findViewById(R.id.rdbNhomNganh);
        rdbDonVi = findViewById(R.id.rdbDonVi);
        rdbLoaiNV = findViewById(R.id.rdbLoaiNV);

        tvLoaiDanhMuc = findViewById(R.id.tvLoaiDanhMuc);
        tvDanhSachLoai = findViewById(R.id.tvDanhSanh);

        lvLoaiDanhMuc = findViewById(R.id.lvLoaiDanhMuc);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }

    public void DocDLLoaiSP() {
        data.clear();
        data_loaiSP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    LoaiSP loaiSP = item.getValue(LoaiSP.class);
                    data.add(loaiSP);
                }
                tvDanhSachLoai.setText("Danh sách " + rdbLoaiSP.getText().toString().trim());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void DocDLLoaiNhanVien() {
        data.clear();
        data_loaiNV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {

                    LoaiNV loaiNV = item.getValue(LoaiNV.class);
                    data.add(loaiNV);
                }
                tvDanhSachLoai.setText("Danh sách " + rdbLoaiNV.getText().toString().trim());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDuLieuLoaiDonVi() {
        data.clear();
        data_DonVi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {

                    DonVi donVi = item.getValue(DonVi.class);
                    data.add(donVi);
                }
                tvDanhSachLoai.setText("Danh sách " + rdbDonVi.getText().toString().trim());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void DocDLNhomNganh() {
        data.clear();
        data_nhomNganh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    NhomNganh nhomNganh = item.getValue(NhomNganh.class);
                    data.add(nhomNganh);
                }
                tvDanhSachLoai.setText("Danh sách " + rdbNhomNganh.getText().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}