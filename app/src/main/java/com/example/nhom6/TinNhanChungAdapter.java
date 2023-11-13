package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TinNhanChungAdapter extends RecyclerView.Adapter<TinNhanChungHolder> {
    Context context;
    List<TinNhan> data;

    @NonNull
    @Override
    public TinNhanChungHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TinNhanChungHolder(LayoutInflater.from(context).inflate(R.layout.item_tinnhan_chung, parent, false));
    }

    public TinNhanChungAdapter(Context context, List<TinNhan> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull TinNhanChungHolder holder, int position) {

        TinNhan tinNhan = data.get(position);

        holder.tvThoiGian.setText(tinNhan.getNgay().trim());

        if (tinNhan.maNhanVien.toString().trim().equals(MainActivity_tinnhan_nhanvien.maNV.trim())) {
            holder.tvTinNhan2.setVisibility(View.VISIBLE);
            holder.ivHinhTinNhan2.setVisibility(View.VISIBLE);
            holder.tvTinNhan1.setVisibility(View.GONE);
            holder.ivHinhTinNhan1.setVisibility(View.GONE);

            holder.tvTinNhan2.setText(tinNhan.getTinNhan().trim());
            holder.linearLayoutTinNhan.setGravity(Gravity.END);

            if (!tinNhan.getHinhNhanVien().trim().equals("")) {
                try {
                    byte[] bytes = chuyenStringSangByte(tinNhan.getHinhNhanVien());
                    Bitmap bitmap = chuyenByteSangBitMap(bytes);
                    holder.ivHinhTinNhan2.setImageBitmap(bitmap);
                } catch (Exception e) {
                    holder.ivHinhTinNhan2.setImageResource(R.drawable.ic_launcher_background);
                }
            } else {
                holder.ivHinhTinNhan2.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            holder.tvTinNhan1.setVisibility(View.VISIBLE);
            holder.ivHinhTinNhan1.setVisibility(View.VISIBLE);

            holder.tvTinNhan2.setVisibility(View.GONE);
            holder.ivHinhTinNhan2.setVisibility(View.GONE);

            holder.tvTinNhan1.setText(tinNhan.getTinNhan().trim());
            holder.linearLayoutTinNhan.setGravity(View.TEXT_ALIGNMENT_TEXT_END);
            if (!tinNhan.getHinhKhachHang().trim().equals("")) {
                try {
                    byte[] bytes = chuyenStringSangByte(tinNhan.getHinhKhachHang());
                    Bitmap bitmap = chuyenByteSangBitMap(bytes);
                    holder.ivHinhTinNhan1.setImageBitmap(bitmap);
                } catch (Exception e) {
                    holder.ivHinhTinNhan1.setImageResource(R.drawable.anhsp_quantri);
                }
            } else {
                holder.ivHinhTinNhan1.setImageResource(R.drawable.anhsp_quantri);
            }
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
