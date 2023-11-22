package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThongKeApdater extends RecyclerView.Adapter<ThongKeHolder> {

    Context context;
    List<ThongKe> data;

    public ThongKeApdater(Context context, List<ThongKe> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ThongKeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThongKeHolder(LayoutInflater.from(context).inflate(R.layout.item_thongke_sanpham, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeHolder holder, int position) {
        ThongKe thongKe = data.get(position);

        holder.tvTen.setText(thongKe.getTenSanPham());
        holder.tvGia.setText("đ" + thongKe.getGia());
        holder.tvChuThich.setText(thongKe.chuThich);
        holder.tvSoLuong.setText("Sô lượng: " + thongKe.getSoLuong());
        if (thongKe.hinh.trim().equals("")) {
            holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
        } else {
            try {
                byte[] bytes = chuyenStringSangByte(thongKe.hinh);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
            }
        }

        if (thongKe.daBan.trim().equals("")) {
            holder.tvDaBan.setText("Đã bán: " + 0);
        } else {
            holder.tvDaBan.setText("Đã bán: " + thongKe.daBan);
        }

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
