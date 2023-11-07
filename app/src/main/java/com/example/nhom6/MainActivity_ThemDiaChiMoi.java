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
    Button btnThem;
    List<ThemDiaChiMoi> data_ThemDiaChi = new ArrayList<>();
    EditText edtID, edtTen, edtSDT, edtTinh, edtQuan, edtPhuong, edtSoNha;
    FirebaseDatabase database;
    DatabaseReference data_TDCM;
//    ArrayList<ThemDiaChiMoi> data_ThemDiaChiMoi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themdiachimoi);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_TDCM = database.getReference("ThemDiaChiMoi");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new ThemDiaChiMoi_Adapter(this,data_ThemDiaChi));

        ThemDiaChiMoi_Adapter themDiaChiMoi_adapter = (ThemDiaChiMoi_Adapter) recyclerView.getAdapter();
        themDiaChiMoi_adapter.setOnItemClickListenner(new ThemDiaChiMoi_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ThemDiaChiMoi themDiaChiMoi = data_ThemDiaChi.get(position);
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



    }
    public void DocDL() {
        data_ThemDiaChi.clear();
        data_TDCM.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_ThemDiaChi.clear();
                Toast.makeText(MainActivity_ThemDiaChiMoi.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ThemDiaChiMoi themDiaChiMoi = item.getValue(ThemDiaChiMoi.class);
                    data_ThemDiaChi.add(themDiaChiMoi);
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
    }



}