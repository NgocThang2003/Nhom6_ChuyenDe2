package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity_ThongKeSanPham extends AppCompatActivity {

    RecyclerView recyclerviewThongKe;
    List<ThongKe> data = new ArrayList<>();
    List<SanPham> data_SP = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference data_SanPham;
    DatabaseReference data_DonHang;

    ImageView ivBanItNhat, ivBanNhieuNhat, ivSoLuongLonNhat, ivSoLuongItNhat;
    TextView tvBanItNhat, tvBanNhieuNhat, tvSoLuongLonNhat, tvSoLuongItNhat;

    ImageView ivQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_ke_san_pham);

        setControl();
        setEvnet();


    }

    private void setEvnet() {
        ivSoLuongItNhat.setImageResource(R.drawable.bolocmt);
        database = FirebaseDatabase.getInstance();
        data_SanPham = database.getReference("SanPham");
        data_DonHang = database.getReference("DonHang");


        recyclerviewThongKe.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerviewThongKe.setAdapter(new ThongKeApdater(this, data));

        data_SanPham.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDuLieuSanPham();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDuLieuSanPham();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDuLieuSanPham();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tvBanNhieuNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBanItNhat.setImageResource(R.drawable.bolocmt);
                ivBanNhieuNhat.setImageResource(R.drawable.bolocden);
                ivSoLuongItNhat.setImageResource(R.drawable.bolocmt);
                ivSoLuongLonNhat.setImageResource(R.drawable.bolocmt);
                DocDuLieuBanNhieuNhat();
            }
        });
        tvBanItNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBanItNhat.setImageResource(R.drawable.bolocden);
                ivBanNhieuNhat.setImageResource(R.drawable.bolocmt);
                ivSoLuongItNhat.setImageResource(R.drawable.bolocmt);
                ivSoLuongLonNhat.setImageResource(R.drawable.bolocmt);
                DocDuLieuBanItNhat();
            }
        });

        tvSoLuongItNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBanItNhat.setImageResource(R.drawable.bolocmt);
                ivBanNhieuNhat.setImageResource(R.drawable.bolocmt);
                ivSoLuongItNhat.setImageResource(R.drawable.bolocden);
                ivSoLuongLonNhat.setImageResource(R.drawable.bolocmt);
                DocDuLieuSanPhamSanPhamSoLuongItNhat();
            }
        });

        tvSoLuongLonNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBanItNhat.setImageResource(R.drawable.bolocmt);
                ivBanNhieuNhat.setImageResource(R.drawable.bolocmt);
                ivSoLuongItNhat.setImageResource(R.drawable.bolocmt);
                ivSoLuongLonNhat.setImageResource(R.drawable.bolocden);
                DocDuLieuSanPhamSanPhamSoLuongLonNhat();
            }
        });
        ivQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        recyclerviewThongKe = findViewById(R.id.recyclerviewThongKe);

        ivBanItNhat = findViewById(R.id.ivBanItNhat);
        ivBanNhieuNhat = findViewById(R.id.ivBanNhieuNhat);
        ivSoLuongLonNhat = findViewById(R.id.ivSoLuongLonNhat);
        ivSoLuongItNhat = findViewById(R.id.ivSoLuongItNhat);

        tvBanItNhat = findViewById(R.id.tvBanItNhat);
        tvBanNhieuNhat = findViewById(R.id.tvBanNhieuNhat);
        tvSoLuongItNhat = findViewById(R.id.tvSoLuongItNhat);
        tvSoLuongLonNhat = findViewById(R.id.tvSoLuongLonNhat);
        ivQuayVe = findViewById(R.id.ivQuayVe);
    }

    public void DocDuLieuSanPham() {
        data.clear();
        data_SanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data.add(new ThongKe("" + sanPham.maSanPham, "" + sanPham.tenSP, "" + sanPham.chuThich, "" + sanPham.gia, "" + sanPham.soLuong, ""+0, "" + sanPham.hinh));
                }

                data_DonHang.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (int i = 0; i < data.size(); i++) {
                            int dem = 0;
                            //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                            for (DataSnapshot item : snapshot.getChildren()) {
                                DonHang donHang = item.getValue(DonHang.class);
                                if (donHang.trangThai.trim().equals("Đã giao hàng")) {
                                    if (donHang.maSanPham.trim().equals(data.get(i).maSanPham)) {
                                        int soLuong = Integer.parseInt(donHang.soLuong);
                                        dem = dem + soLuong;
                                    }
                                }

                            }
                            data.get(i).setDaBan("" + dem);
                        }
                        recyclerviewThongKe.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                recyclerviewThongKe.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void DocDuLieuSanPhamSanPhamSoLuongItNhat() {
        data.clear();
        data_SanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data.add(new ThongKe("" + sanPham.maSanPham, "" + sanPham.tenSP, "" + sanPham.chuThich, "" + sanPham.gia, "" + sanPham.soLuong, ""+0, "" + sanPham.hinh));
                }

                data_DonHang.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (int i = 0; i < data.size(); i++) {
                            int dem = 0;
                            //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                            for (DataSnapshot item : snapshot.getChildren()) {
                                DonHang donHang = item.getValue(DonHang.class);
                                if (donHang.trangThai.trim().equals("Đã giao hàng")) {
                                    if (donHang.maSanPham.trim().equals(data.get(i).maSanPham)) {
                                        int soLuong = Integer.parseInt(donHang.soLuong);
                                        dem = dem + soLuong;
                                    }
                                }

                            }
                            data.get(i).setDaBan("" + dem);
                        }
                        Collections.sort(data, new Comparator<ThongKe>() {
                            @Override
                            public int compare(ThongKe thongKe1, ThongKe thongKe2) {
                                int soLuong1 = 0;
                                int soLuong2 = 0;
                                try {
                                    soLuong1 = Integer.parseInt(thongKe1.soLuong.trim());
                                    soLuong2 = Integer.parseInt(thongKe2.soLuong.trim());
                                } catch (Exception e) {
                                    return 0;
                                }
                                return soLuong1 - soLuong2;
                            }
                        });

                        recyclerviewThongKe.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                recyclerviewThongKe.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDuLieuSanPhamSanPhamSoLuongLonNhat() {
        data.clear();
        data_SanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data.add(new ThongKe("" + sanPham.maSanPham, "" + sanPham.tenSP, "" + sanPham.chuThich, "" + sanPham.gia, "" + sanPham.soLuong, "" + 0, "" + sanPham.hinh));
                }

                data_DonHang.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (int i = 0; i < data.size(); i++) {
                            int dem = 0;
                            //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                            for (DataSnapshot item : snapshot.getChildren()) {
                                DonHang donHang = item.getValue(DonHang.class);
                                if (donHang.trangThai.trim().equals("Đã giao hàng")) {
                                    if (donHang.maSanPham.trim().equals(data.get(i).maSanPham)) {
                                        int soLuong = Integer.parseInt(donHang.soLuong);
                                        dem = dem + soLuong;
                                    }
                                }

                            }
                            data.get(i).setDaBan("" + dem);
                        }
                        Collections.sort(data, new Comparator<ThongKe>() {
                            @Override
                            public int compare(ThongKe thongKe1, ThongKe thongKe2) {
                                int soLuong1 = 0;
                                int soLuong2 = 0;
                                try {
                                    soLuong1 = Integer.parseInt(thongKe1.soLuong.trim());
                                    soLuong2 = Integer.parseInt(thongKe2.soLuong.trim());
                                } catch (Exception e) {
                                    return 0;
                                }
                                return (soLuong1 - soLuong2) * -1;
                            }
                        });

                        recyclerviewThongKe.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                recyclerviewThongKe.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDuLieuBanItNhat() {
        data.clear();
        data_SanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data.add(new ThongKe("" + sanPham.maSanPham, "" + sanPham.tenSP, "" + sanPham.chuThich, "" + sanPham.gia, "" + sanPham.soLuong, "" + 0, "" + sanPham.hinh));
                }

                data_DonHang.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (int i = 0; i < data.size(); i++) {
                            int dem = 0;
                            //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                            for (DataSnapshot item : snapshot.getChildren()) {
                                DonHang donHang = item.getValue(DonHang.class);
                                if (donHang.trangThai.trim().equals("Đã giao hàng")) {
                                    if (donHang.maSanPham.trim().equals(data.get(i).maSanPham)) {
                                        int soLuong = Integer.parseInt(donHang.soLuong);
                                        dem = dem + soLuong;
                                    }
                                }

                            }
                            data.get(i).setDaBan("" + dem);
                        }
                        Collections.sort(data, new Comparator<ThongKe>() {
                            @Override
                            public int compare(ThongKe thongKe1, ThongKe thongKe2) {
                                int daBan1 = 0;
                                int daBan2 = 0;
                                try {
                                    daBan1 = Integer.parseInt(thongKe1.daBan.trim());
                                    daBan2 = Integer.parseInt(thongKe2.daBan.trim());
                                } catch (Exception e) {
                                    return 0;
                                }
                                return (daBan1 - daBan2) * 1;
                            }
                        });

                        recyclerviewThongKe.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                recyclerviewThongKe.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void DocDuLieuBanNhieuNhat() {
        data.clear();
        data_SanPham.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    data.add(new ThongKe("" + sanPham.maSanPham, "" + sanPham.tenSP, "" + sanPham.chuThich, "" + sanPham.gia, "" + sanPham.soLuong, "" + 0, "" + sanPham.hinh));
                }

                data_DonHang.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (int i = 0; i < data.size(); i++) {
                            int dem = 0;
                            //Toast.makeText(MainActivity_QuanTriKyThuat.this, "thay đổi", Toast.LENGTH_SHORT).show();
                            for (DataSnapshot item : snapshot.getChildren()) {
                                DonHang donHang = item.getValue(DonHang.class);
                                if (donHang.trangThai.trim().equals("Đã giao hàng")) {
                                    if (donHang.maSanPham.trim().equals(data.get(i).maSanPham)) {
                                        int soLuong = Integer.parseInt(donHang.soLuong);
                                        dem = dem + soLuong;
                                    }
                                }

                            }
                            data.get(i).setDaBan("" + dem);
                        }
                        Collections.sort(data, new Comparator<ThongKe>() {
                            @Override
                            public int compare(ThongKe thongKe1, ThongKe thongKe2) {
                                int daBan1 = 0;
                                int daBan2 = 0;
                                try {
                                    daBan1 = Integer.parseInt(thongKe1.daBan.trim());
                                    daBan2 = Integer.parseInt(thongKe2.daBan.trim());
                                } catch (Exception e) {
                                    return 0;
                                }
                                return (daBan1 - daBan2) * -1;
                            }
                        });

                        recyclerviewThongKe.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                recyclerviewThongKe.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}