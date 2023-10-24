package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamHolder> {
    Context context;
    List<SanPham> data;

    public SanPhamAdapter(Context context, List<SanPham> data) {
        this.context = context;
        this.data = data;
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

        holder.ivHinh.setImageResource(Integer.parseInt(sanPham.hinh.trim()));
        holder.tvTenSP.setText(sanPham.tenSP.trim());
        holder.tvChuThich.setText(sanPham.chuThich.trim());
        holder.tvLoaiSP.setText(sanPham.loaiSP.trim());

        SanPham finalSanPham = sanPham;
        holder.ivHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClick(finalSanPham);
            }
        });
        count = count + 1;

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


}
