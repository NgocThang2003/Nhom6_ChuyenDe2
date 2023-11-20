package com.example.nhom6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PhanHoiSanPhamAdapter extends RecyclerView.Adapter<PhanHoiSanPhamHolder> {
    Context context;
    List<DonHang> data;

    FirebaseDatabase database;
    DatabaseReference data_DH;

    public PhanHoiSanPhamAdapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PhanHoiSanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhanHoiSanPhamHolder(LayoutInflater.from(context).inflate(R.layout.item_phanhoidanhgia, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhanHoiSanPhamHolder holder, int position) {
        database = FirebaseDatabase.getInstance();
        data_DH = database.getReference("DonHang");
        DonHang donHang = data.get(position);
        holder.tvTenKH.setText(donHang.tenKhachHang);
        holder.tvSDT.setText(donHang.sDT);
        holder.tvTenSP.setText(donHang.tenSanPham);
        holder.tvMoTa.setText(donHang.moTa);
        holder.tvGia.setText(donHang.gia);
        holder.tvSoLuong.setText(donHang.soLuong);
        holder.tvDiaChi.setText(donHang.diaChi);
        holder.tvNgay.setText(donHang.ngay);
        holder.tvTrangThai.setText(donHang.trangThai);
        int sL = Integer.parseInt(donHang.soLuong);
        int gia = Integer.parseInt(donHang.gia);
        holder.tvThanhTien.setText("đ" + sL * gia);
        holder.tvDanhGia.setText("Đánh giá sản phẩm: " + donHang.danhGia);

        if (donHang.hinh.trim().equals("")) {
            holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
        } else {
            try {
                byte[] bytes = chuyenStringSangByte(donHang.hinh);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);

            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
            }
        }

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(holder.edtPhanHoi.getWindowToken(), 0);

        holder.tvGui.setVisibility(View.GONE);
        holder.tvGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_DH.child(donHang.maDonHang).child("phanHoi").setValue(holder.edtPhanHoi.getText().toString().trim());
                imm.hideSoftInputFromWindow(holder.edtPhanHoi.getWindowToken(), 0);
                holder.edtPhanHoi.setCursorVisible(false);
                holder.tvGui.setVisibility(View.GONE);
            }
        });

        holder.edtPhanHoi.setText(donHang.phanHoi.trim());
        holder.edtPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvGui.setVisibility(View.VISIBLE);
                holder.edtPhanHoi.setCursorVisible(true);
            }
        });

        holder.edtPhanHoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (holder.edtPhanHoi.getText().toString().trim().equals("")) {
                    holder.tvGui.setVisibility(View.GONE);
                } else {
                    holder.edtPhanHoi.setCursorVisible(true);
                    holder.tvGui.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
}
