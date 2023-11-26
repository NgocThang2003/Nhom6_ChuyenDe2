package com.example.nhom6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangHolder> {
    Context context;
    List<DonHang> data = new ArrayList<>();

    public DonHangAdapter(Context context, List<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DonHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonHangHolder(LayoutInflater.from(context).inflate(R.layout.item_donhang_tatca, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangHolder holder, int position) {

        DonHang donHang = data.get(position);
        holder.tvTenSP.setText(donHang.getTenSanPham());
        holder.tvSoLuong.setText(donHang.getSoLuong());
        holder.tvDiaChi.setText(donHang.getDiaChi());
        holder.tvTrangThai.setText(donHang.getTrangThai());
        holder.tvNgay.setText(donHang.getNgay());
        NumberFormat numberFormatDefault = NumberFormat.getInstance();
        holder.tvGia.setText("Giá: đ" + numberFormatDefault.format(Integer.parseInt(donHang.getGia().trim())));

        int sL = Integer.parseInt(donHang.getSoLuong().toString().trim());
        int gia = Integer.parseInt(donHang.getGia().toString().trim());
        holder.tvGiaDH.setText("đ" + numberFormatDefault.format(sL * gia));


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


        if (donHang.trangThai.equals("Đang đóng gói")) {
            holder.btnDonHang.setVisibility(View.GONE);
            holder.tvLyDo.setText("");
        }

        if (donHang.trangThai.equals("Đã đóng gói")) {
            holder.btnDonHang.setVisibility(View.GONE);
            holder.tvLyDo.setText("");
        }

        if (donHang.trangThai.equals("Đang giao hàng")) {
            holder.btnDonHang.setVisibility(View.GONE);
            holder.tvLyDo.setText("");
        }

        if (donHang.trangThai.equals("Đang chờ xác nhận")) {
            holder.btnDonHang.setVisibility(View.VISIBLE);
            holder.btnDonHang.setText("Huỷ");
            holder.tvLyDo.setText("");
            holder.btnDonHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity_LyDoHuyDon.class);
                    intent.putExtra("don hang", donHang);
                    context.startActivity(intent);
                }
            });
        }
        if (donHang.trangThai.equals("Đã huỷ")) {
            holder.btnDonHang.setVisibility(View.GONE);
            holder.tvLyDo.setText("lý do huỷ đon: " + donHang.lyDoHuyDon);


        }
        if (donHang.trangThai.equals("Đã giao hàng")) {
            if (donHang.danhGia.trim().equals("")) {
                holder.btnDonHang.setVisibility(View.VISIBLE);
                holder.btnDonHang.setText("Đánh giá");
                holder.tvLyDo.setText("");
                holder.btnDonHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MainActivity_DanhGia.class);
                        intent.putExtra("don hang", donHang);
                        context.startActivity(intent);
                    }
                });
            } else {
                holder.tvTrangThai.setText("Đã đánh giá");
                holder.btnDonHang.setVisibility(View.VISIBLE);
                holder.btnDonHang.setText("Xem đánh giá");
                holder.tvLyDo.setText("");

                holder.btnDonHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MainActivity_TatCaDanhGia.class);
                        //intent.putExtra("don hang", donHang);
                        context.startActivity(intent);
                    }
                });
            }


        }

    }

    @Override
    public int getItemCount() {
        return data.size();
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
}
