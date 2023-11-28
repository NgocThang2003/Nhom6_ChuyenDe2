package com.example.nhom6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhoDonHangChoXacNhan_Adapter extends RecyclerView.Adapter<KhoDonHangChoXacNhan_Holder> {
    public static Context context;
    List<DonHang> data;

    FirebaseDatabase database;
    DatabaseReference data_DH;
    public static DatabaseReference data_SP;
    public static List<SanPham> dataSanPham = new ArrayList<>();

    public KhoDonHangChoXacNhan_Adapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    private SanPhamAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListenner(SanPhamAdapter.OnItemClickListener listenner) {
        mListener = listenner;
    }

    @NonNull
    @Override
    public KhoDonHangChoXacNhan_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KhoDonHangChoXacNhan_Holder(LayoutInflater.from(context).inflate(R.layout.item_khodonhangchoxacnhan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KhoDonHangChoXacNhan_Holder holder, int position) {
        DonHang donHang = data.get(position);
        database = FirebaseDatabase.getInstance();
        data_DH = database.getReference("DonHang");
        data_SP = database.getReference("SanPham");

        data_SP.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDL(donHang, holder);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DocDL(donHang, holder);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                DocDL(donHang, holder);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    DocDL(donHang, holder);
                }
            }
        });


        holder.tvTenKH.setText(donHang.tenKhachHang);
        if (donHang.hinh.trim().equals("")) {
            holder.ivHinh.setImageResource(R.drawable.giongngo);
        } else {
            try {
                byte[] bytes = chuyenStringSangByte(donHang.hinh);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.giongngo);
            }
        }
        //Toast.makeText(context, ""+dataSanPham.size(), Toast.LENGTH_SHORT).show();
        holder.tvDiaChi.setText(donHang.diaChi);
        holder.tvTenSP.setText(donHang.tenSanPham);
        holder.tvMoTa.setText(donHang.moTa);
        holder.tvSDT.setText(donHang.sDT);
        holder.tvGia.setText(donHang.gia);
        holder.tvSoLuong.setText(donHang.soLuong);
        holder.tvNgay.setText(donHang.ngay);
        holder.tvTrangThai.setText(donHang.trangThai);
        //holder.tvTonKho.setText(dataSanPham.get(0).soLuong);

        int gia = Integer.parseInt(donHang.gia.trim());
        int soLuong = Integer.parseInt(donHang.soLuong.trim());


        holder.tvThanhTien.setText("đ" + gia * soLuong);

        holder.btnTinNhan.setVisibility(View.VISIBLE);
        holder.btnGoiDien.setVisibility(View.VISIBLE);
        //Toast.makeText(context, ""+dataSanPham.size(), Toast.LENGTH_SHORT).show();

        holder.btnTinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_tinnhan_nhanvien.class);
                MainActivity_tinnhan_nhanvien.maNV = MainActivity_DangNhap.maNguoiDung;
                MainActivity_tinnhan_nhanvien.maKH = donHang.maKhachHang;
                context.startActivity(intent);
            }
        });

        holder.btnGoiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + donHang.sDT.toString()));
                if (ActivityCompat.checkSelfPermission(context,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new
                            String[]{android.Manifest.permission.CALL_PHONE},1);
                    return;
                }
                context.startActivity(intent);

            }
        });

        if (donHang.maShipper.trim().equals("")) {
            holder.tvMaShipper.setVisibility(View.GONE);
        } else {
            holder.tvMaShipper.setText("Mã shipper: " + donHang.maShipper + "Tên shipper: " + donHang.tenShipper);
        }


        holder.btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn sửa đơn hàng này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                                Toast.makeText(context, "Sửa dữ liệu thành công", Toast.LENGTH_SHORT).show();

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                                String currentDateandTime = sdf.format(new Date());
                                int soLuongHienTai = Integer.parseInt(holder.tvTonKho.getText().toString().trim());
                                int soLuongSanPhamDonHang = Integer.parseInt(donHang.soLuong.trim());
                                int soLuongConLai = soLuongHienTai - soLuongSanPhamDonHang;

                                if (soLuongConLai < 0) {
                                    soLuongConLai = 0;
                                }

                                data_DH.child(donHang.maDonHang).child("trangThai").setValue("Đang đóng gói");
                                data_SP.child(donHang.maSanPham).child("soLuong").setValue("" + soLuongConLai);
                                data_DH.child(donHang.maDonHang).child("thongTinVanChuyen").setValue(donHang.thongTinVanChuyen + "\nĐang đóng gói " + currentDateandTime);
                                data_DH.child(donHang.maDonHang).child("nhanVienDuyetHang").setValue(donHang.nhanVienDuyetHang + " \nĐang đóng gói - Mã nhân viên: " + MainActivity_DangNhap.maNguoiDung + " " + currentDateandTime);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                //Toast.makeText(context, "" + dataSanPham.size(), Toast.LENGTH_SHORT).show();

                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();


            }
        });

        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_LyDoHuyDon_ThuKho.class);
                intent.putExtra("donHang_ThuKho", donHang);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private byte[] chuyenStringSangByte(String str) {
        byte[] byteArray = android.util.Base64.decode(str, android.util.Base64.NO_PADDING | android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
        return byteArray;
    }

    //Chuyen byte[] sang bitMap
    private Bitmap chuyenByteSangBitMap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }

    public static void DocDL(DonHang donHang, KhoDonHangChoXacNhan_Holder holder) {
        dataSanPham.clear();
        data_SP.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSanPham.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    SanPham sanPham = item.getValue(SanPham.class);
                    if (donHang.maSanPham.trim().equals(sanPham.maSanPham)) {
                        dataSanPham.add(sanPham);
                        holder.tvTonKho.setText("" + sanPham.soLuong);

                        int tonKho = Integer.parseInt(holder.tvTonKho.getText().toString().trim());
                        int soLuongSP = Integer.parseInt(donHang.soLuong.trim());
                        if (tonKho < soLuongSP) {
                            holder.linearLayoutDonHang.setBackgroundColor(Color.rgb(252, 237, 237));
                        } else {
                            holder.linearLayoutDonHang.setBackgroundColor(Color.WHITE);
                        }
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
