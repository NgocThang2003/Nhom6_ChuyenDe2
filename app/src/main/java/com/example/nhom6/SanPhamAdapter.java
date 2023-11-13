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

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamHolder> {
    Context context;
    List<SanPham> data;

    public static String chuThich = "";
    public static String tenSP = "";


    public SanPhamAdapter(Context context, List<SanPham> data) {
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
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SanPhamHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_sanpham, parent, false));
    }

    public static int count = 0;

    @Override
    public void onBindViewHolder(@NonNull SanPhamHolder holder, int position) {

        SanPham sanPham = data.get(position);

        if (!sanPham.getHinh().toString().trim().equals("")) {
            try {
                byte[] bytes = chuyenStringSangByte(sanPham.getHinh());
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.anhsp_quantri);
            }
        } else {
            holder.ivHinh.setImageResource(R.drawable.anhsp_quantri);
        }

        holder.tvTenSP.setText(sanPham.tenSP.trim());

        if (sanPham.tenSP.toString().trim().length() > 10) {
            holder.tvTenSP.setText(sanPham.tenSP.trim().substring(0, 10) + "...");
        }

        holder.tvChuThich.setText(sanPham.chuThich.trim());
        if (sanPham.chuThich.toString().trim().length() > 10) {
            holder.tvChuThich.setText(sanPham.chuThich.trim().substring(0, 10) + "...");
        }


        holder.tvLoaiSP.setText(sanPham.loaiSP.trim());

        holder.tvDonVi.setText(sanPham.donVi.trim());
        holder.tvKhoiLuong.setText(sanPham.khoiLuong.trim());
        holder.tvGia.setText(sanPham.gia.trim());
        holder.tvSoLuong.setText(sanPham.soLuong.trim());

        SanPham finalSanPham = sanPham;
//        holder.ivHinh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setOnClick(finalSanPham);
//            }
//        });
        count = count + 1;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    chuThich = sanPham.chuThich;
                    tenSP = sanPham.tenSP;
                    mListener.onItemClick(position);
                }
            }
        });

    }

    private void setOnClick(SanPham sanPham) {
        MainActivity_QuanTriSanPham.edtTenSP.setText(sanPham.tenSP);
        MainActivity_QuanTriSanPham.edtChuThich.setText(sanPham.chuThich);
        MainActivity_QuanTriSanPham.edtSL.setText("" + sanPham.soLuong);
        MainActivity_QuanTriSanPham.edtKhoiLuong.setText("" + sanPham.khoiLuong);
        MainActivity_QuanTriSanPham.edtGia.setText("" + sanPham.gia);
        MainActivity_QuanTriSanPham.edtMoTa.setText("" + sanPham.moTa);

        kiemTraDonVi(sanPham);
        kiemTraLoaiSanPham(sanPham);
    }

    private void kiemTraLoaiSanPham(SanPham sanPham) {
        if (sanPham.loaiSP.equals("Nông nghiệp")) {
            MainActivity_QuanTriSanPham.spLoaiSanPham.setSelection(0);
        } else if (sanPham.loaiSP.equals("Lâm nghiệp")) {
            MainActivity_QuanTriSanPham.spLoaiSanPham.setSelection(1);
        } else if (sanPham.loaiSP.equals("Công nghiệp")) {
            MainActivity_QuanTriSanPham.spLoaiSanPham.setSelection(2);
        }

    }

    private void kiemTraDonVi(SanPham sanPham) {
        if (sanPham.donVi.equals("kq")) {
            MainActivity_QuanTriSanPham.spDonVi.setSelection(0);
        } else if (sanPham.donVi.equals("hg")) {
            MainActivity_QuanTriSanPham.spDonVi.setSelection(1);
        } else if (sanPham.donVi.equals("dag")) {
            MainActivity_QuanTriSanPham.spDonVi.setSelection(2);
        } else if (sanPham.donVi.equals("g")) {
            MainActivity_QuanTriSanPham.spDonVi.setSelection(3);
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
