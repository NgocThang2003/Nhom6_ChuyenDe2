package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class MainActivity_QuanTriSanPham extends AppCompatActivity {

    public static EditText edtTenSP, edtChuThich, edtSL, edtKhoiLuong, edtGia, edtMoTa;
    public static Spinner spDonVi, spLoaiSanPham;
    ImageView ivHinhSP;

    EditText edtTimKiem;
    Button btnThem, btnXoa, btnSua;
    int index = -1;

    RecyclerView recyclerViewDanhSachSP;
    List<SanPham> data_SanPham = new ArrayList<>();
    List<SanPham> data_SanPhamBanDau = new ArrayList<>();
    List<String> data_donVi = new ArrayList<>();
    List<String> data_loaiSanPham = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference data_SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quantrisanpham);

        setControl();
        setEvent();
    }

    private void setEvent() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("QuanTriSanPham");


        database = FirebaseDatabase.getInstance();
        data_SP = database.getReference("SanPham");
        EnabelButtonTrue();
        KhoiTao();
        recyclerViewDanhSachSP.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDanhSachSP.setAdapter(new SanPhamAdapter(this, data_SanPham));

        ArrayAdapter adapter_donVi = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_donVi);
        ArrayAdapter adapter_loaiSanPham = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_loaiSanPham);

        spDonVi.setAdapter(adapter_donVi);
        spLoaiSanPham.setAdapter(adapter_loaiSanPham);
        SanPhamAdapter sanPhamAdapter = (SanPhamAdapter) recyclerViewDanhSachSP.getAdapter();
        sanPhamAdapter.setOnItemClickListenner(new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EnabelButtonFalse();
                SanPham sanPham = data_SanPham.get(position);

                edtTenSP.setText(SanPhamAdapter.tenSP);
                edtChuThich.setText(SanPhamAdapter.chuThich);
                edtSL.setText("" + sanPham.soLuong);
                edtKhoiLuong.setText("" + sanPham.khoiLuong);
                edtGia.setText("" + sanPham.gia);
                edtMoTa.setText("" + sanPham.moTa);

                index = position;

                kiemTraDonVi(sanPham);
                kiemTraLoaiSanPham(sanPham);

                if (!sanPham.getHinh().toString().trim().equals("")) {
                    try {
                        byte[] bytes = chuyenStringSangByte(sanPham.getHinh());
                        Bitmap bitmap = chuyenByteSangBitMap(bytes);
                        ivHinhSP.setImageBitmap(bitmap);
                        byteArrayHinh = bytes;
                    } catch (Exception e) {
                        ivHinhSP.setImageResource(R.drawable.anhsp_quantri);
                        byteArrayHinh = new byte[0];
                    }
                } else {
                    ivHinhSP.setImageResource(R.drawable.anhsp_quantri);
                    byteArrayHinh = new byte[0];
                }
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDieuKien() == true) {
                    if (spLoaiSanPham.getSelectedItemPosition() != 0) {
                        if (spDonVi.getSelectedItemPosition() != 0) {
                            SanPham sanPham = new SanPham();

                            String maSP = data_SP.push().getKey();
                            sanPham.setMaSanPham(maSP);

                            sanPham.setTenSP(edtTenSP.getText().toString().trim());
                            sanPham.setChuThich(edtChuThich.getText().toString().trim());
                            sanPham.setSoLuong(edtSL.getText().toString().trim());

                            sanPham.setDonVi(spDonVi.getSelectedItem().toString().trim());
                            sanPham.setKhoiLuong(edtKhoiLuong.getText().toString().trim());
                            sanPham.setGia(edtGia.getText().toString().trim());

                            sanPham.setLoaiSP(spLoaiSanPham.getSelectedItem().toString().trim());
                            sanPham.setMoTa(edtMoTa.getText().toString().trim());

                            String hinh = chuyenByteSangChuoi(byteArrayHinh);
                            sanPham.setHinh(hinh);

                            data_SP.child(maSP).setValue(sanPham);
                            Toast.makeText(MainActivity_QuanTriSanPham.this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                            clearEditText();


                        } else {
                            Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn đơn vị !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn loại sản phẩm !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index != -1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_QuanTriSanPham.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                    builder.setMessage("Bạn có muốn xóa dữ liệu này không ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // START THE GAME!
                                    String maSanPham = data_SanPham.get(index).maSanPham;
                                    data_SP.child(maSanPham).removeValue();
                                    clearEditText();
                                    EnabelButtonTrue();
                                    Toast.makeText(MainActivity_QuanTriSanPham.this, "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create().show();


                } else {
                    Toast.makeText(MainActivity_QuanTriSanPham.this, "Chọn để xóa sản phẩm !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    if (kiemTraDieuKien() == true) {
                        if (spLoaiSanPham.getSelectedItemPosition() != 0) {
                            if (spDonVi.getSelectedItemPosition() != 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_QuanTriSanPham.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                                builder.setMessage("Bạn có muốn sửa dữ liệu này không ?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // START THE GAME!
//                                                String maSanPham, tenSP, chuThich, donVi, loaiSP, moTa, hinh;
//                                                String soLuong, khoiLuong, gia;

                                                String maSanPham = data_SanPham.get(index).maSanPham;

                                                data_SP.child(maSanPham).child("tenSP").setValue(edtTenSP.getText().toString());
                                                data_SP.child(maSanPham).child("chuThich").setValue(edtChuThich.getText().toString());
                                                data_SP.child(maSanPham).child("donVi").setValue(spDonVi.getSelectedItem().toString());

                                                data_SP.child(maSanPham).child("loaiSP").setValue(spLoaiSanPham.getSelectedItem().toString());
                                                data_SP.child(maSanPham).child("moTa").setValue(edtMoTa.getText().toString());
                                                data_SP.child(maSanPham).child("soLuong").setValue(edtSL.getText().toString());

                                                data_SP.child(maSanPham).child("khoiLuong").setValue(edtKhoiLuong.getText().toString());
                                                data_SP.child(maSanPham).child("gia").setValue(edtGia.getText().toString());

                                                String hinh = chuyenByteSangChuoi(byteArrayHinh);
                                                data_SP.child(maSanPham).child("hinh").setValue(hinh);


                                                clearEditText();
                                                EnabelButtonTrue();
                                                Toast.makeText(MainActivity_QuanTriSanPham.this, "Sửa dữ liệu thành công !", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // User cancelled the dialog
                                            }
                                        });
                                // Create the AlertDialog object and return it
                                builder.create().show();

                            } else {
                                Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn đơn vị !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn loại sản phẩm !", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity_QuanTriSanPham.this, "Chọn để sửa sản phẩm !", Toast.LENGTH_SHORT).show();
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

        data_SP.addChildEventListener(new ChildEventListener() {
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

        ivHinhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    byte[] byteArrayHinh = new byte[0];

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            ivHinhSP.setImageURI(uri);
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

    public void DocDL() {
        data_SanPham.clear();
        data_SP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_SanPham.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data_SanPham.add(sanPham);
                }
                edtTimKiem.setText("");
                recyclerViewDanhSachSP.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private boolean kiemTraDieuKien() {
        boolean kiemTra = true;
        if (!edtTenSP.getText().toString().trim().equals("")) {
            if (!edtChuThich.getText().toString().trim().equals("")) {
                if (!edtSL.getText().toString().trim().equals("")) {
                    if (!edtKhoiLuong.getText().toString().trim().equals("")) {
                        if (!edtGia.getText().toString().trim().equals("")) {
                            if (!edtMoTa.getText().toString().trim().equals("")) {

                            } else {
                                edtMoTa.setError("Không được để trống !");
                                kiemTra = false;
                            }
                        } else {
                            edtGia.setError("Không được để trống !");
                            kiemTra = false;
                        }
                    } else {
                        edtKhoiLuong.setError("Không được để trống !");
                        kiemTra = false;
                    }
                } else {
                    edtSL.setError("Không được để trống !");
                    kiemTra = false;
                }
            } else {
                edtChuThich.setError("Không được để trống !");
                kiemTra = false;
            }
        } else {
            edtTenSP.setError("Không được để trống !");
            kiemTra = false;
        }
        return kiemTra;
    }


    private void kiemTraLoaiSanPham(SanPham sanPham) {
        for (int i = 0; i < data_loaiSanPham.size(); i++) {
            if (sanPham.getLoaiSP().toString().trim().equals(data_loaiSanPham.get(i).toString().trim())) {
                spLoaiSanPham.setSelection(i);
            }
        }
    }

    private void kiemTraDonVi(SanPham sanPham) {
        for (int i = 0; i < data_donVi.size(); i++) {
            if (sanPham.getDonVi().toString().trim().equals(data_donVi.get(i).toString().trim())) {
                spDonVi.setSelection(i);
            }
        }
    }

    private void KhoiTao() {

//        data_SanPham.add(new SanPham("Bắp cải xanh", "tươi ngon, bổ dưỡng", "g", "Công nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
//        data_SanPham.add(new SanPham("Dưa hấu đỏ", "Đỏ ít hạt", "g", "Lâm nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));
//        data_SanPham.add(new SanPham("Bí ngô", "nảy mầm nhanh", "kg", "Nông nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
//        data_SanPham.add(new SanPham("Bí ngô 2", "nảy mầm nhanh", "g", "Lâm nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
//        data_SanPham.add(new SanPham("Dưa hấu đỏ 2", "Đỏ ít hạt", "dag", "Nông nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));
//        data_SanPham.add(new SanPham("Bắp cải xanh 2", "tươi ngon, bổ dưỡng", "g", "Nông nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
//        data_SanPham.add(new SanPham("Bí ngô 3", "nảy mầm nhanh", "dag", "Công nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
//        data_SanPham.add(new SanPham("Dưa hấu đỏ 3", "Đỏ ít hạt", "hg", "Lâm nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));

        data_loaiSanPham.add("Bank");
        data_loaiSanPham.add("Nông nghiệp");
        data_loaiSanPham.add("Lâm nghiệp");
        data_loaiSanPham.add("Công nghiệp");
        data_loaiSanPham.add("Thuốc");

        data_donVi.add("Bank");
        data_donVi.add("kg");
        data_donVi.add("hg");
        data_donVi.add("dag");
        data_donVi.add("g");

        data_donVi.add("lít");
        data_donVi.add("decilit");
        data_donVi.add("centilit");
        data_donVi.add("mililit");
//        data_donVi.add("kg");
//        data_donVi.add("gam");
//        data_donVi.add("dag");
//        data_donVi.add("hg");

//        data_loaiSanPham.add("Nông nghiệp");
//        data_loaiSanPham.add("Lâm nghiệp");
//        data_loaiSanPham.add("Công nghiệp");
    }

    private void setControl() {
        recyclerViewDanhSachSP = findViewById(R.id.recyclerViewDanhSachSanPham);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtChuThich = findViewById(R.id.edtChuThich);
        edtSL = findViewById(R.id.edtSL);
        edtKhoiLuong = findViewById(R.id.edtKhoiLuong);
        edtGia = findViewById(R.id.edtGia);
        edtMoTa = findViewById(R.id.edtMoTa);

        spLoaiSanPham = findViewById(R.id.spLoaiSanPham);
        spDonVi = findViewById(R.id.spDonVi);

        edtTimKiem = findViewById(R.id.edtTimKiem);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

        ivHinhSP = findViewById(R.id.ivHinhSP);
    }


    private void clearEditText() {
        edtTenSP.setText("");
        edtChuThich.setText("");
        edtSL.setText("");
        edtKhoiLuong.setText("");
        edtGia.setText("");
        edtMoTa.setText("");
        spLoaiSanPham.setSelection(0);
        spDonVi.setSelection(0);
        ivHinhSP.setImageResource(R.drawable.anhsp_quantri);
        byteArrayHinh = new byte[0];
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


    public void timKiem() {
        data_SanPham.clear();
        data_SP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_SanPham.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (sanPham.getTenSP().contains(edtTimKiem.getText().toString())) {
                        data_SanPham.add(sanPham);
                    }
                }
                recyclerViewDanhSachSP.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}