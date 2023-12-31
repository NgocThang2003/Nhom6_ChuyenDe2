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

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienHolder> {
    Context context;
    List<NhanVien> data_NV;

    public NhanVienAdapter(Context context, List<NhanVien> data_NV) {
        this.context = context;
        this.data_NV = data_NV;
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListenner(OnItemClickListener listenner) {
        mListener = listenner;
    }

    @NonNull
    @Override
    public NhanVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NhanVienHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_nhanvien, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienHolder holder, int position) {
        NhanVien nhanVien = data_NV.get(position);

        if (!nhanVien.getHinh().toString().trim().equals("")) {
            try {
                byte[] bytes = chuyenStringSangByte(nhanVien.getHinh());
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);


            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.img_nhanvien);
            }
        } else {
            holder.ivHinh.setImageResource(R.drawable.img_nhanvien);
        }


        holder.tvTenNV.setText(nhanVien.tenNhanVien);
        holder.tvSDT.setText(nhanVien.soDienThoai);
        holder.tvSTT.setText(nhanVien.maNhanVien);
        holder.tvNgaySinh.setText(nhanVien.NgaySinh);

        final int clickedPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_NV.size();
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
