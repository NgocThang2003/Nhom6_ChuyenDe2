package com.example.nhom6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity_GioHang extends AppCompatActivity {
    ImageView ivChonPhuongThucThanhToan, ivChonDiaChi;
    Button btnQuayLai, btnThanhToan;
    TextView tvTongTien, tvTamTinh;
    public static TextView tvDiaChiGiaoHang;
    CheckBox cbTatCa;
    public static RecyclerView rcvRecyclerView;
    List<GioHang> data_GioHang = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_GH;
    DatabaseReference data_TK;
    DatabaseReference data_DonHang;

    String maKH = "-NiNrHieKJTJY-rlUhgh";
    String maSP = "-NiT6YxCGEdSMwNTLFFt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
        setcontrol();
        setEvent();
    }


    private void setEvent() {
        tvDiaChiGiaoHang.setText(ChonDiaChi_Adapter.diaChi);
        maKH = MainActivity_DangNhap.maNguoiDung;
        database = FirebaseDatabase.getInstance();
        data_GH = database.getReference("GioHang");
        data_DonHang = database.getReference("DonHang");
        data_TK = database.getReference("DangKy");
        DocDLLucDau();

        rcvRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvRecyclerView.setAdapter(new GioHangAdapter(this, data_GioHang));

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
        ivChonPhuongThucThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_GioHang.this, PhuongThucThanhToan.class);
//                startActivity(intent);
            }
        });

        ivChonDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_GioHang.this, MainActivity_ChonDiaChiGiaoHang.class);
                startActivity(intent);
            }
        });

//        cbTatCa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked == true) {
//                    ChonTatCa();
//                } else {
//                    DocDLLucDau();
//                }
//            }
//        });

        cbTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTatCa.isChecked() == true) {
                    ChonTatCa();
                } else {
                    DocDLLucDau();
                }
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean kiemTra = false;
                for (int i = 0; i < data_GioHang.size(); i++) {
                    if (data_GioHang.get(i).daChon.trim().equals("1")) {
                        kiemTra = true;
                    } else {

                    }
                }
                if (kiemTra == true) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_GioHang.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                    builder.setMessage("Bạn đồng ý mua những sản phẩm này ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(MainActivity_GioHang.this, "Thanh toán thành công !", Toast.LENGTH_SHORT).show();
                                    for (int i = 0; i < data_GioHang.size(); i++) {
                                        if (data_GioHang.get(i).daChon.trim().equals("1")) {
                                            Date currentTime = Calendar.getInstance().getTime();

                                            String msg = ", " + currentTime.getDate() + " Tháng " + currentTime.getMonth();


                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                                            String currentDateandTime = dateFormat.format(new Date());


                                            DonHang donHang = new DonHang();
                                            donHang.setMaDonHang(data_DonHang.push().getKey());
                                            donHang.setHinh(data_GioHang.get(i).getHinh());
                                            donHang.setMaKhachHang(maKH);
                                            donHang.setTenKhachHang(taiKhoan2.hoten);

                                            donHang.setSoLuong(data_GioHang.get(i).soLuong.trim());
                                            donHang.setDiaChi(tvDiaChiGiaoHang.getText().toString().trim());
                                            donHang.setTrangThai("Đang chờ xác nhận");

                                            donHang.setMaSanPham(maSP);
                                            donHang.setTenSanPham(data_GioHang.get(i).tenSP.trim());
                                            donHang.setMaShipper("");

                                            donHang.setTenShipper("");
                                            donHang.setPhuongThucThanhToan("Bằng tiền mặt");
                                            donHang.setThongTinVanChuyen("Đang chờ xác nhận  " + currentDateandTime);

                                            donHang.setNhanVienDuyetHang("");
                                            donHang.setNgay(currentDateandTime);
                                            donHang.setLyDoHuyDon("");

                                            donHang.setGia(data_GioHang.get(i).gia);
                                            donHang.setMoTa(data_GioHang.get(i).chuThich);
                                            donHang.setsDT(taiKhoan2.getSdt());

                                            //String danhGia, phanHoi, soSao, thuTien, luotThich;
                                            donHang.setDanhGia("");
                                            donHang.setPhanHoi("");
                                            donHang.setSoSao("");
                                            donHang.setThuTien("");
                                            donHang.setLuotThich("");

                                            data_DonHang.child(donHang.maDonHang).setValue(donHang);

                                            data_GH.child(data_GioHang.get(i).maGioHang).removeValue();
                                        }
                                    }
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
                else {
                    Toast.makeText(MainActivity_GioHang.this, "Chưa chọn sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                }
            }

        });




        data_TK.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLTK();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDLTK();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDLTK();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void DocDLLucDau() {
        data_GioHang.clear();
        data_GH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GioHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    GioHang gioHang = item.getValue(GioHang.class);
                    if (maKH.toString().trim().equals(gioHang.maKhachHang.toString().trim())) {
                        data_GH.child(gioHang.maGioHang).child("daChon").setValue("0");
                        //data_GioHang.add(gioHang);
                    }
                }
                //rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void ChonTatCa() {
        data_GioHang.clear();
        data_GH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GioHang.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    GioHang gioHang = item.getValue(GioHang.class);
                    if (maKH.toString().trim().equals(gioHang.maKhachHang.toString().trim())) {
                        data_GH.child(gioHang.maGioHang).child("daChon").setValue("1");
                        //data_GioHang.add(gioHang);
                    }
                }
                //rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void DocDL() {
        data_GioHang.clear();
        data_GH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data_GioHang.clear();
                int tongTien = 0;
                boolean check = true;
                for (DataSnapshot item : snapshot.getChildren()) {
                    GioHang gioHang = item.getValue(GioHang.class);
                    if (maKH.toString().trim().equals(gioHang.maKhachHang.toString().trim())) {
                        //data_GH.child(gioHang.maGioHang).child("daChon").setValue("0");
                        data_GioHang.add(gioHang);
                        if (gioHang.daChon.trim().equals("1")) {

                            int soLuong = Integer.parseInt(gioHang.soLuong.trim());
                            int gia = Integer.parseInt(gioHang.gia.trim());
                            tongTien = soLuong * gia + tongTien;
                        } else {
                            check = false;
                            cbTatCa.setChecked(false);
                        }
                    }
                }
                if (check == true) {
                    cbTatCa.setChecked(true);
                }
                NumberFormat numberFormatDefault = NumberFormat.getInstance();

                tvTongTien.setText(numberFormatDefault.format(tongTien) + "đ");
                tvTamTinh.setText(numberFormatDefault.format(tongTien));
                rcvRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    TaiKhoan taiKhoan2 = new TaiKhoan();

    private void DocDLTK() {
        data_TK.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(MainActivity_DangNhap.this, "thay đổi", Toast.LENGTH_SHORT).show();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TaiKhoan taiKhoan = item.getValue(TaiKhoan.class);
                    if (taiKhoan.maNguoiDung.toString().trim().equals(maKH.toString().trim())) {
                        taiKhoan2 = taiKhoan;
                        //Toast.makeText(MainActivity_ChiTietSanPham.this, ""+taiKhoan.maNguoiDung, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setcontrol() {
        btnQuayLai = findViewById(R.id.btnQuayLai);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        rcvRecyclerView = findViewById(R.id.rcvRecyclerviewGioHang);
        ivChonDiaChi = findViewById(R.id.ivChondiachi);
        ivChonPhuongThucThanhToan = findViewById(R.id.ivTChonPhuongThuc);
        cbTatCa = findViewById(R.id.cbTatCaSP);
        tvTongTien = findViewById(R.id.tvTongCongTien);
        tvDiaChiGiaoHang = findViewById(R.id.tvDiaChiGiaoHang);
        tvTamTinh = findViewById(R.id.tvTamTinh);
    }
}
