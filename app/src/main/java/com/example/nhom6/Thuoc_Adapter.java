package com.example.nhom6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Thuoc_Adapter extends RecyclerView.Adapter<Thuoc_Holder> {
    Context context;
    List<Thuoc> data;

    public Thuoc_Adapter(Context context, List<Thuoc> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Thuoc_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Thuoc_Holder(LayoutInflater.from(context).inflate(R.layout.item_thuoc, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Thuoc_Holder holder, int position) {
        Thuoc thuoc = data.get(position);
        if (thuoc.hinh1.trim().equals("")) {
            holder.ivHinh1.setImageResource(R.drawable.giongngo);
        } else {
            try {
                byte[] bytes = chuyenStringSangByte(thuoc.hinh1);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh1.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh1.setImageResource(R.drawable.giongngo);
            }
        }


        holder.tvTenSP1.setText(thuoc.tenSP1);
        holder.tvMoTa1.setText(thuoc.moTa1);
        if (thuoc.moTa1.trim().length() > 20) {
            holder.tvMoTa1.setText(thuoc.moTa1.substring(0, 20) + "...");
        }
        holder.tvGia1.setText(thuoc.gia1);

        if (!thuoc.tenSP2.trim().equals("tenSP2")) {
            if (thuoc.hinh2.trim().equals("")) {
                holder.ivHinh2.setImageResource(R.drawable.giongngo);
            } else {
                try {
                    byte[] bytes = chuyenStringSangByte(thuoc.hinh2);
                    Bitmap bitmap = chuyenByteSangBitMap(bytes);
                    holder.ivHinh2.setImageBitmap(bitmap);
                } catch (Exception e) {
                    holder.ivHinh2.setImageResource(R.drawable.giongngo);
                }
            }
            holder.tvTenSP2.setText(thuoc.tenSP2);
            holder.tvMoTa2.setText(thuoc.moTa2);


            if (thuoc.moTa2.trim().length() > 20) {
                holder.tvMoTa2.setText(thuoc.moTa2.substring(0, 20) + "...");
            }
            holder.tvGia2.setText(thuoc.gia2);
        } else {
            holder.tvTenSP2.setVisibility(View.GONE);
            holder.ivHinh2.setVisibility(View.GONE);
            holder.tvMoTa2.setVisibility(View.GONE);
            holder.tvMoTa2.setVisibility(View.GONE);
            holder.tvMoTa2.setVisibility(View.GONE);
            holder.tvGia2.setVisibility(View.GONE);
            holder.btnMua2.setEnabled(false);
            holder.btnMua2.setBackgroundColor(Color.WHITE);
            holder.btnMua2.setTextColor(Color.WHITE);

        }

        holder.btnMua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ChiTietSanPham.class);
                MainActivity_ChiTietSanPham.maSP = thuoc.maSP1;
                context.startActivity(intent);
            }
        });

        holder.btnMua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ChiTietSanPham.class);
                MainActivity_ChiTietSanPham.maSP = thuoc.maSP2;
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
}
