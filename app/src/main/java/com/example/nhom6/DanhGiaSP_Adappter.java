package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;

import java.text.NumberFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DanhGiaSP_Adappter extends RecyclerView.Adapter<DanhgiaSP_Holder> {
    Context context;
    FirebaseDatabase database;
    DatabaseReference data_DonHang;

    public DanhGiaSP_Adappter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    List<DonHang> data;


    @NonNull
    @Override
    public DanhgiaSP_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DanhgiaSP_Holder(LayoutInflater.from(context).inflate(R.layout.item_danhgia, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DanhgiaSP_Holder holder, int position) {
        DonHang donHang = data.get(position);
        database = FirebaseDatabase.getInstance();
        data_DonHang = database.getReference("DonHang");

        holder.tvTen.setText(donHang.tenSanPham);
        holder.tvMoTa.setText(donHang.moTa);
        holder.tvDiaChi.setText(donHang.diaChi);
        NumberFormat numberFormatDefault = NumberFormat.getInstance();
        holder.tvGia.setText("Giá: đ" + numberFormatDefault.format(Integer.parseInt(donHang.gia.trim())));
        holder.tvSoLuong.setText("x" + donHang.soLuong);
        holder.edtBinhLuanDanhGia.setText(donHang.danhGia);
        holder.tvMotaShop.setText(donHang.phanHoi);
        holder.tvNgay.setText(donHang.ngay);
        if (!donHang.getHinh().toString().trim().equals("")) {
            try {
                byte[] hinh = chuyenStringSangByte(donHang.getHinh());
                Bitmap bitmap = chuyenByteSangBitMap(hinh);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
        }
        holder.tvSua.setVisibility(View.GONE);
        holder.edtBinhLuanDanhGia.setCursorVisible(false);
        holder.edtBinhLuanDanhGia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (holder.edtBinhLuanDanhGia.getText().toString().trim().equals("")) {
                    holder.tvSua.setVisibility(View.GONE);

                } else {
                    holder.tvSua.setVisibility(View.VISIBLE);
                    holder.edtBinhLuanDanhGia.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        holder.tvSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvSua.setVisibility(View.GONE);
                holder.edtBinhLuanDanhGia.setCursorVisible(false);
                imm.hideSoftInputFromWindow(holder.edtBinhLuanDanhGia.getWindowToken(), 0);
                data_DonHang.child(donHang.maDonHang).child("danhGia").setValue(holder.edtBinhLuanDanhGia.getText().toString().trim());
            }
        });

        holder.edtBinhLuanDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.edtBinhLuanDanhGia.setCursorVisible(true);
            }
        });

        if (donHang.soSao.trim().equals("")) {
            holder.ivSao1.setImageResource(R.drawable.saoxam);
            holder.ivSao2.setImageResource(R.drawable.saoxam);
            holder.ivSao3.setImageResource(R.drawable.saoxam);
            holder.ivSao4.setImageResource(R.drawable.saoxam);
            holder.ivSao5.setImageResource(R.drawable.saoxam);
            holder.tvTrangThaiSao.setText("Không");
        }
        if (donHang.soSao.trim().equals("1")) {
            holder.ivSao1.setImageResource(R.drawable.star);
            holder.ivSao2.setImageResource(R.drawable.saoxam);
            holder.ivSao3.setImageResource(R.drawable.saoxam);
            holder.ivSao4.setImageResource(R.drawable.saoxam);
            holder.ivSao5.setImageResource(R.drawable.saoxam);
            holder.tvTrangThaiSao.setText("Tệ");
        }
        if (donHang.soSao.trim().equals("2")) {
            holder.ivSao1.setImageResource(R.drawable.star);
            holder.ivSao2.setImageResource(R.drawable.star);
            holder.ivSao3.setImageResource(R.drawable.saoxam);
            holder.ivSao4.setImageResource(R.drawable.saoxam);
            holder.ivSao5.setImageResource(R.drawable.saoxam);
            holder.tvTrangThaiSao.setText("Không hài lòng");
        }
        if (donHang.soSao.trim().equals("3")) {
            holder.ivSao1.setImageResource(R.drawable.star);
            holder.ivSao2.setImageResource(R.drawable.star);
            holder.ivSao3.setImageResource(R.drawable.star);
            holder.ivSao4.setImageResource(R.drawable.saoxam);
            holder.ivSao5.setImageResource(R.drawable.saoxam);
            holder.tvTrangThaiSao.setText("Bình thường");
        }
        if (donHang.soSao.trim().equals("4")) {
            holder.ivSao1.setImageResource(R.drawable.star);
            holder.ivSao2.setImageResource(R.drawable.star);
            holder.ivSao3.setImageResource(R.drawable.star);
            holder.ivSao4.setImageResource(R.drawable.star);
            holder.ivSao5.setImageResource(R.drawable.saoxam);
            holder.tvTrangThaiSao.setText("Hài lòng");
        }
        if (donHang.soSao.trim().equals("5")) {
            holder.ivSao1.setImageResource(R.drawable.star);
            holder.ivSao2.setImageResource(R.drawable.star);
            holder.ivSao3.setImageResource(R.drawable.star);
            holder.ivSao4.setImageResource(R.drawable.star);
            holder.ivSao5.setImageResource(R.drawable.star);
            holder.tvTrangThaiSao.setText("Tuyệt vời");
        }


        holder.ivSao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivSao1.setImageResource(R.drawable.star);
                holder.ivSao2.setImageResource(R.drawable.saoxam);
                holder.ivSao3.setImageResource(R.drawable.saoxam);
                holder.ivSao4.setImageResource(R.drawable.saoxam);
                holder.ivSao5.setImageResource(R.drawable.saoxam);
                holder.tvTrangThaiSao.setText("Tệ");
                data_DonHang.child(donHang.maDonHang).child("soSao").setValue("1");

            }
        });
        holder.ivSao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivSao1.setImageResource(R.drawable.star);
                holder.ivSao2.setImageResource(R.drawable.star);
                holder.ivSao3.setImageResource(R.drawable.saoxam);
                holder.ivSao4.setImageResource(R.drawable.saoxam);
                holder.ivSao5.setImageResource(R.drawable.saoxam);
                holder.tvTrangThaiSao.setText("Không hài lòng");
                data_DonHang.child(donHang.maDonHang).child("soSao").setValue("2");

            }
        });
        holder.ivSao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivSao1.setImageResource(R.drawable.star);
                holder.ivSao2.setImageResource(R.drawable.star);
                holder.ivSao3.setImageResource(R.drawable.star);
                holder.ivSao4.setImageResource(R.drawable.saoxam);
                holder.ivSao5.setImageResource(R.drawable.saoxam);
                holder.tvTrangThaiSao.setText("Bình thường");
                data_DonHang.child(donHang.maDonHang).child("soSao").setValue("3");

            }
        });
        holder.ivSao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivSao1.setImageResource(R.drawable.star);
                holder.ivSao2.setImageResource(R.drawable.star);
                holder.ivSao3.setImageResource(R.drawable.star);
                holder.ivSao4.setImageResource(R.drawable.star);
                holder.ivSao5.setImageResource(R.drawable.saoxam);
                holder.tvTrangThaiSao.setText("Hài lòng");
                data_DonHang.child(donHang.maDonHang).child("soSao").setValue("4");

            }
        });
        holder.ivSao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivSao1.setImageResource(R.drawable.star);
                holder.ivSao2.setImageResource(R.drawable.star);
                holder.ivSao3.setImageResource(R.drawable.star);
                holder.ivSao4.setImageResource(R.drawable.star);
                holder.ivSao5.setImageResource(R.drawable.star);
                holder.tvTrangThaiSao.setText("Tuyệt vời");
                data_DonHang.child(donHang.maDonHang).child("soSao").setValue("5");

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