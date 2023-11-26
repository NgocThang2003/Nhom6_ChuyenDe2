package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Thuoc extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Thuoc> data_Thuoc = new ArrayList<>();
    List<SanPham> data_SP = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_SanPham;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuoc);
        setControl();
        setEvent();
    }

    private void setEvent() {
        bottomNavigationView.getMenu().findItem(R.id.thuoc).setChecked(true);
        database = FirebaseDatabase.getInstance();
        data_SanPham = database.getReference("SanPham");

        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new Thuoc_Adapter(this, data_Thuoc));

        data_SanPham.addChildEventListener(new ChildEventListener() {
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

    private void KhoiTao() {
        //data_Thuoc.add(new Thuoc("","âs","BSC01 - Combo","BS25 - Insect","Dinh dưỡng , dưỡng cây, dưỡng rễ, phục hồi rễ","Trừ nhện đỏ, rầy, rệp. con trùng chích hút","250.000đ","220.000đ","",""));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewThuoc);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }


    public void DocDL() {
        data_Thuoc.clear();
        data_SP.clear();
        data_SanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_Thuoc.clear();
                data_SP.clear();

                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (sanPham.loaiSP.toString().toString().equals("Thuốc")) {
                        data_SP.add(sanPham);
                    }
                }

                for (int i = 0; i < data_SP.size(); i = i + 2) {
                    Thuoc thuoc = new Thuoc();
                    if (data_SP.get(i).loaiSP.toString().equals("Thuốc")) {
                        SanPham sanPham = data_SP.get(i);
                        //Toast.makeText(MainActivity_Thuoc.this, "Thuốc: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                        thuoc.setMaSP1(sanPham.getMaSanPham().trim());
                        thuoc.setTenSP1(sanPham.getTenSP().trim());
                        thuoc.setMoTa1(sanPham.getMoTa().trim());
                        thuoc.setGia1(sanPham.getGia().trim());
                        thuoc.setHinh1(sanPham.getHinh().trim());

                    }
                    if (i + 1 != data_SP.size()) {
                        if (data_SP.get(i + 1).loaiSP.toString().equals("Thuốc")) {
                            SanPham sanPham = data_SP.get(i + 1);
                            //Toast.makeText(MainActivity_Thuoc.this, "Thuốc: "+sanPham.tenSP, Toast.LENGTH_SHORT).show();
                            thuoc.setMaSP2(sanPham.getMaSanPham().trim());
                            thuoc.setTenSP2(sanPham.getMoTa().trim());
                            thuoc.setMoTa2(sanPham.getTenSP().trim());
                            thuoc.setGia2(sanPham.getGia().trim());
                            thuoc.setHinh2(sanPham.getHinh().trim());
                        }
                    }
                    //data_Thuoc.add(new Thuoc("","ấ","BS32 - Mecin","BSC03 - Combo","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195.000đ","250.000đ","",""));

                    data_Thuoc.add(thuoc);

                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.taikhoan) {
                    Intent intent = new Intent(MainActivity_Thuoc.this, MainActivity_TaiKhoan.class);
                    startActivity(intent);
                    //return true;
                }
//                if (item.getItemId() == R.id.thuoc) {
//                    Intent intent = new Intent(MainActivity_Thuoc.this, MainActivity_Thuoc.class);
//                    startActivity(intent);
//                    //return true;
//                }
                if (item.getItemId() == R.id.cuahang) {
                    Intent intent = new Intent(MainActivity_Thuoc.this, MainActivity_GiongCayTrong.class);
                    startActivity(intent);
                    //return true;
                }
                if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(MainActivity_Thuoc.this, MainActivity_TrangChuKhachHang.class);
                    startActivity(intent);
                    //return true;
                }
                if (item.getItemId() == R.id.tuvan) {
                    Intent intent = new Intent(MainActivity_Thuoc.this, MainActivity_DanhBaTinNhan_KhachHang.class);
                    startActivity(intent);
                    //return true;
                }
                return false;
            }
        });
    }
}