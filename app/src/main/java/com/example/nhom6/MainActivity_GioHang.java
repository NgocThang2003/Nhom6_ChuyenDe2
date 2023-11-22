package com.example.nhom6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_GioHang extends AppCompatActivity {
    Button btnChonPhuongThucThanhToan,btnChonDiaChi,btnQuayLai;
    TextView tvTongTien;
    CheckBox cbTatCa;
    RecyclerView rcvRecyclerView;
    List<GioHang>data_GioHang=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_GH;
    long tongTienSP;
    List<GioHang> mangMuaHang=new ArrayList<>();


    String maKH = "-NiNrHieKJTJY-rlUhgh";
    String maSP = "-NiT6YxCGEdSMwNTLFFt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
        setcontrol();
        setEvent();
        tinhTongTien();

    }

    private void tinhTongTien() {
        tongTienSP=0;
        for(int i=0;i<mangMuaHang.size();i++){
            tongTienSP=tongTienSP+ (Integer.parseInt(data_GioHang.get(i).getGia())*Integer.parseInt( data_GioHang.get(i).getSoLuong()));
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvTongTien.setText(decimalFormat.format(tongTienSP));
    }

    private void setEvent() {
        maKH = MainActivity_DangNhap.maNguoiDung;
        database=FirebaseDatabase.getInstance();
        data_GH=database.getReference("GioHang");

        rcvRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvRecyclerView.setAdapter(new GioHangAdapter(this,data_GioHang));

        data_GH.addChildEventListener(new ChildEventListener() {
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

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnChonPhuongThucThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_GioHang.this,PhuongThucThanhToan.class);
                startActivity(intent);
            }
        });



    }

    private void DocDL() {
        data_GioHang.clear();
        data_GH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GioHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    GioHang gioHang = item.getValue(GioHang.class);

                    if(maKH.toString().trim().equals(gioHang.maKhachHang.toString().trim())){
                        data_GioHang.add(gioHang);
                    }
                }
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setcontrol() {
        btnQuayLai = findViewById(R.id.btnQuayLai);
        rcvRecyclerView=findViewById(R.id.rcvRecyclerviewGioHang);
        btnChonDiaChi=findViewById(R.id.btnChondiachi);
        btnChonPhuongThucThanhToan=findViewById(R.id.btnTChonPhuongThuc);
        cbTatCa=findViewById(R.id.cbTatCaSP);
        tvTongTien=findViewById(R.id.tvTongCongTien);
    }
}
