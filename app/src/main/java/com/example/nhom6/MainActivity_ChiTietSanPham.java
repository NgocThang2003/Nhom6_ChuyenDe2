package com.example.nhom6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity_ChiTietSanPham extends AppCompatActivity {
    TextView tvTenSP, tvChuThich, tvKhoiLuong, tvSoLuong, tvMoTa, tvGia, tvID;
    Button btnDatHang, btnThemVaoGH, btnCong, btnTru;
    ImageView imgHinh;
    FirebaseDatabase database;
    DatabaseReference data_ChiTiet;
    DatabaseReference data_DonHang;
    DatabaseReference data_GioHang;
    List<SanPham> data_CT = new ArrayList<>();

    String maSP = "-NiT6YxCGEdSMwNTLFFt";
    String maKH = "-NiNrHieKJTJY-rlUhgh";
    String tenKH = "Le Hong Thuy";

    TextView tvDiaChi, tvPhuongThuc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitet_sanpham);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_ChiTiet = database.getReference("SanPham");
        data_DonHang = database.getReference("DonHang");
        data_GioHang = database.getReference("GioHang");
        data_ChiTiet.addChildEventListener(new ChildEventListener() {
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

        //nhấn vao tăng số lượng
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvSoLuong.getText().toString().trim()) + 1 <= Integer.parseInt(data_CT.get(0).soLuong)) {
                    tvSoLuong.setText(Integer.parseInt(tvSoLuong.getText().toString().trim()) + 1 + "");
                } else {
                    Toast.makeText(MainActivity_ChiTietSanPham.this, "Số lượng không được đặt lớn hơn trong kho hàng", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Nhấn vào giảm số lượng
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvSoLuong.getText().toString().trim()) - 1 > 0) {
                    tvSoLuong.setText(Integer.parseInt(tvSoLuong.getText().toString().trim()) - 1 + "");
                } else {
                    Toast.makeText(MainActivity_ChiTietSanPham.this, "Số lượng không được nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //nhấn vào đặt hàng
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                this.maDonHang = maDonHang;
//                this.maKhachHang = maKhachHang;
//                this.tenKhachHang = tenKhachHang;

//                this.soLuong = soLuong;
//                this.diaChi = diaChi;
//                this.trangThai = trangThai;

//                this.maSanPham = maSanPham;
//                this.tenSanPham = tenSanPham;
//                this.maShipper = maShipper;

//                this.tenShipper = tenShipper;
//                this.phuongThucThanhToan = phuongThucThanhToan;
//                this.thongTinVanChuyen = thongTinVanChuyen;

//                this.nhanVienDuyetHang = nhanVienDuyetHang;
//                this.ngay = ngay;
//                this.lyDoHuyDon = lyDoHuyDon;
                Date currentTime = Calendar.getInstance().getTime();

                String msg = ", " + currentTime.getDate() + " Tháng " + currentTime.getMonth();


                SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                String currentDateandTime = sdf.format(new Date());

//                sdf = new SimpleDateFormat("HH::mm");

                DonHang donHang = new DonHang();
                donHang.setMaDonHang(data_DonHang.push().getKey());
                donHang.setHinh(data_CT.get(0).getHinh());
                donHang.setMaKhachHang(maKH);
                donHang.setTenKhachHang(tenKH);

                donHang.setSoLuong(tvSoLuong.getText().toString().trim());
                donHang.setDiaChi(tvDiaChi.getText().toString().trim());
                donHang.setTrangThai("Đang chờ xác nhận");

                donHang.setMaSanPham(maSP);
                donHang.setTenSanPham(data_CT.get(0).tenSP.trim());
                donHang.setMaShipper("");

                donHang.setTenShipper("");
                donHang.setPhuongThucThanhToan(tvPhuongThuc.getText().toString().trim());
                donHang.setThongTinVanChuyen("Đang chờ xác nhận");

                donHang.setNhanVienDuyetHang("");
                donHang.setNgay(""+msg);
                donHang.setLyDoHuyDon("");

                data_DonHang.child(donHang.maDonHang).setValue(donHang);



            }
        });

        //Nhấn để thêm vào giỏ hàng
        btnThemVaoGH.setOnClickListener(new View.OnClickListener() {
//            maGioHang, maKhachHang, maSanPham, tenSP, chuThich,gia,khoiLuong, soLuong,donVi,hinh
            @Override
            public void onClick(View v) {
                GioHang gioHang=new GioHang();
                String maGH=data_GioHang.push().getKey();
                gioHang.setMaGioHang(maGH);
                gioHang.setTenSP(data_CT.get(0).tenSP);
                gioHang.setMaKhachHang(maKH);
                gioHang.setMaSanPham(maSP);
                gioHang.setChuThich(data_CT.get(0).getChuThich());
                gioHang.setGia(data_CT.get(0).getGia());
                gioHang.setKhoiLuong(data_CT.get(0).getKhoiLuong());
                gioHang.setSoLuong(tvSoLuong.getText().toString().trim());
                gioHang.setDonVi(data_CT.get(0).getDonVi());
                gioHang.setHinh(data_CT.get(0).getHinh());
                data_GioHang.child(maGH).setValue(gioHang);
                Intent intent=new Intent(MainActivity_ChiTietSanPham.this,MainActivity_GioHang.class);
                startActivity(intent);
            }
        });

    }

    private void DocDL() {
        data_CT.clear();
        data_ChiTiet.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_CT.clear();
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (maSP.toString().trim().equals(sanPham.maSanPham.toString().trim())) {
                        data_CT.add(sanPham);
                        tvID.setText(sanPham.getMaSanPham().toString().trim());
                        tvTenSP.setText(sanPham.getTenSP().toString().trim());
                        tvChuThich.setText(sanPham.getChuThich().toString().trim());
                        tvKhoiLuong.setText(sanPham.getKhoiLuong().toString().trim());
                        tvMoTa.setText(sanPham.getMoTa().toString().trim());
                        if (!sanPham.getHinh().trim().equals("")) {
                            try {
                                byte[] bytes = chuyenStringSangByte(sanPham.getHinh());
                                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                                imgHinh.setImageBitmap(bitmap);
                            } catch (Exception e) {
                                imgHinh.setImageResource(R.drawable.ic_launcher_background);
                            }
                        } else {
                            imgHinh.setImageResource(R.drawable.ic_launcher_background);
                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    private void setControl() {
        tvTenSP = findViewById(R.id.tvTenSPChiTiet);
        tvChuThich = findViewById(R.id.tvChuThich);
        tvKhoiLuong = findViewById(R.id.tvKhoiLuongCT);
        tvSoLuong = findViewById(R.id.tvSoLuongCT);
        tvMoTa = findViewById(R.id.tvMoTaCT);
        tvGia = findViewById(R.id.tvGiaCT);
        btnDatHang = findViewById(R.id.btnDatHang);
        btnThemVaoGH = findViewById(R.id.btnThemVaoGH);
        imgHinh = findViewById(R.id.ivHinhChiTiet);
        tvID = findViewById(R.id.edtIDCT);
        btnCong = findViewById(R.id.btnCongCT);
        btnTru = findViewById(R.id.btnTruCT);

        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvPhuongThuc = findViewById(R.id.tvPhuongThuc);
    }
}
