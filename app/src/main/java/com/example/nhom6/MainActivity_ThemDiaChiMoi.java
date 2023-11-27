package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ThemDiaChiMoi extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnThem, btnSua, btnXoa;
    List<ThemDiaChiMoi> data_ThemDiaChi = new ArrayList<>();
    EditText edtID, edtTen, edtSDT, edtTinh, edtQuan, edtPhuong, edtSoNha;
    FirebaseDatabase database;
    DatabaseReference data_TDCM;
    ImageView ivQuayVe;

    String maNguoiDung = "-NiNrHieKJTJY-rlUhgh";
//    ArrayList<ThemDiaChiMoi> data_ThemDiaChiMoi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themdiachimoi);
        setControl();
        setEvent();
    }

    private void setEvent() {
        maNguoiDung = MainActivity_DangNhap.maNguoiDung;
        database = FirebaseDatabase.getInstance();
        data_TDCM = database.getReference("ThemDiaChiMoi");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ThemDiaChiMoi_Adapter(this, data_ThemDiaChi));

        ThemDiaChiMoi_Adapter themDiaChiMoi_adapter = (ThemDiaChiMoi_Adapter) recyclerView.getAdapter();
        themDiaChiMoi_adapter.setOnItemClickListenner(new ThemDiaChiMoi_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ThemDiaChiMoi themDiaChiMoi = data_ThemDiaChi.get(position);
                edtID.setText(themDiaChiMoi.getId());
                edtTen.setText(themDiaChiMoi.getTen());
                edtSDT.setText(themDiaChiMoi.getSdt());
                edtTinh.setText(themDiaChiMoi.getTinh());
                edtQuan.setText(themDiaChiMoi.getQuan());
                edtPhuong.setText(themDiaChiMoi.getPhuong());
                edtSoNha.setText(themDiaChiMoi.getSoNha());

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDiaChiMoi themDiaChiMoi = new ThemDiaChiMoi(data_TDCM.push().getKey(), edtTen.getText().toString(), edtSDT.getText().toString(),
                        edtTinh.getText().toString(), edtQuan.getText().toString(), edtPhuong.getText().toString(),
                        edtSoNha.getText().toString(), "");
                themDiaChiMoi.setMaNguoiDung(maNguoiDung);
                data_TDCM.child(themDiaChiMoi.id).setValue(themDiaChiMoi);

                data_TDCM.child(themDiaChiMoi.id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            ThemDiaChiMoi themDiaChiMoi1 = snapshot.getValue(ThemDiaChiMoi.class);
                            edtID.setText(themDiaChiMoi1.getId());
                            edtTen.setText(themDiaChiMoi1.getTen());
                            edtSDT.setText(themDiaChiMoi1.getSdt());
                            edtTinh.setText(themDiaChiMoi1.getTinh());
                            edtQuan.setText(themDiaChiMoi1.getQuan());
                            edtPhuong.setText(themDiaChiMoi1.getPhuong());
                            edtSoNha.setText(themDiaChiMoi1.getSoNha());
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity_ThemDiaChiMoi.this, "Đã thay đổi", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtID.getText().toString().trim().equals("")) {
                    data_TDCM.child(edtID.getText().toString()).child("ten").setValue(edtTen.getText().toString());
                    data_TDCM.child(edtID.getText().toString()).child("sdt").setValue(edtSDT.getText().toString());
                    data_TDCM.child(edtID.getText().toString()).child("tinh").setValue(edtTinh.getText().toString());
                    data_TDCM.child(edtID.getText().toString()).child("quan").setValue(edtQuan.getText().toString());
                    data_TDCM.child(edtID.getText().toString()).child("phuong").setValue(edtPhuong.getText().toString());
                    data_TDCM.child(edtID.getText().toString()).child("soNha").setValue(edtSoNha.getText().toString());
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtID.getText().toString().trim().equals("")) {
                    try {
                        data_TDCM.child(edtID.getText().toString()).removeValue();
                        Toast.makeText(MainActivity_ThemDiaChiMoi.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                        clearEditTextt();
                    } catch (Exception e) {
                    }
                } else {
                    Toast.makeText(MainActivity_ThemDiaChiMoi.this, "Chọn để xóa !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        data_TDCM.addChildEventListener(new ChildEventListener() {
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


    }

    public void DocDL() {
        data_ThemDiaChi.clear();
        data_TDCM.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_ThemDiaChi.clear();
                //Toast.makeText(MainActivity_ThemDiaChiMoi.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ThemDiaChiMoi themDiaChiMoi = item.getValue(ThemDiaChiMoi.class);
                    if (themDiaChiMoi.maNguoiDung.trim().equals(maNguoiDung)) {
                        data_ThemDiaChi.add(themDiaChiMoi);
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewThemDiaChiMoi);
        edtID = findViewById(R.id.edtID);
        edtTen = findViewById(R.id.edtTen);
        edtSDT = findViewById(R.id.edtSDT);
        edtTinh = findViewById(R.id.edtTinh);
        edtQuan = findViewById(R.id.edtQuan);
        edtPhuong = findViewById(R.id.edtPhuong);
        edtSoNha = findViewById(R.id.edtSoNha);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        ivQuayVe = findViewById(R.id.imgQuayVe);
    }

    private void clearEditTextt() {
        edtID.setText("");
        edtTen.setText("");
        edtSDT.setText("");
        edtTinh.setText("");
        edtQuan.setText("");
        edtPhuong.setText("");
    }


}