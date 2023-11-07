package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrangChuKhachHang_Adapter extends RecyclerView.Adapter<TrangChuKhachHang_Holder> {
    Context context;
    public static String moTa = "";

    public TrangChuKhachHang_Adapter(Context context, List<TrangChuKhachHang> data) {
        this.context = context;
        this.data = data;
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListenner(OnItemClickListener listenner) {
        mListener = listenner;
    }

    List<TrangChuKhachHang> data;

    @NonNull
    @Override
    public TrangChuKhachHang_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrangChuKhachHang_Holder(LayoutInflater.from(context).inflate(R.layout.item_trangchukhachhang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrangChuKhachHang_Holder holder, int position) {
        TrangChuKhachHang trangChuKhachHang = data.get(position);
        holder.tvMoTa.setText(trangChuKhachHang.tenMoTa);

        if (trangChuKhachHang.tenMoTa.length() > 100) {
            holder.tvMoTa.setText(trangChuKhachHang.tenMoTa.substring(0, 100) + "...");
        }
        moTa = trangChuKhachHang.tenMoTa;
        holder.tvTenKyThuat.setText(trangChuKhachHang.tenKyThuat);

        if (!trangChuKhachHang.getHinh().toString().trim().equals("")) {
            try {
                byte[] bytes = chuyenStringSangByte(trangChuKhachHang.hinh.toString().trim());
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.anhsp_quantri);
            }
        } else {
            holder.ivHinh.setImageResource(R.drawable.anhsp_quantri);
        }


        final int clickedPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    moTa = trangChuKhachHang.tenMoTa;
                    mListener.onItemClick(position);
                }
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
