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

public class DanhBaTinNhanNhanVienAdapter extends RecyclerView.Adapter<DanhBaTinNhanNhanVienHolder> {
    Context context;
    List<TinNhan> data;

    public DanhBaTinNhanNhanVienAdapter(Context context, List<TinNhan> data) {
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
    @NonNull
    @Override
    public DanhBaTinNhanNhanVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DanhBaTinNhanNhanVienHolder(LayoutInflater.from(context).inflate(R.layout.item_danhbatinnhan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DanhBaTinNhanNhanVienHolder holder, int position) {
        TinNhan tinNhanNhanVien = data.get(position);
        holder.tvHoTen.setText(tinNhanNhanVien.hoTenKhachHang);
        holder.tvTinNhan.setText(tinNhanNhanVien.tinNhan);
        holder.tvThoiGian.setText(tinNhanNhanVien.ngay);

        if (!tinNhanNhanVien.getHinhKhachHang().trim().equals("")) {
            try {
                byte[] bytes = chuyenStringSangByte(tinNhanNhanVien.getHinhKhachHang());
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinhDanhBa.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinhDanhBa.setImageResource(R.drawable.anhsp_quantri);
            }
        } else {
            holder.ivHinhDanhBa.setImageResource(R.drawable.anhsp_quantri);
        }

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
