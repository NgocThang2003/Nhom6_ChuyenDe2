package com.example.nhom6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangHolder> {

    Context context;
    List<GioHang> data = new ArrayList<>();
    List<SanPham> dataSP = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference data_GioHang;
    DatabaseReference data_SanPham;

    public GioHangAdapter(Context context, List<GioHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GioHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GioHangHolder(LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangHolder holder, int position) {
        database = FirebaseDatabase.getInstance();
        data_GioHang = database.getReference("GioHang");
        data_SanPham = database.getReference("SanPham");

        GioHang gioHang = data.get(position);

        holder.tvTenSP.setText(gioHang.getTenSP());
        holder.tvChuThich.setText(gioHang.getChuThich());
        if (gioHang.getChuThich().toString().trim().length() > 10) {
            holder.tvChuThich.setText(gioHang.getChuThich().substring(0, 10) + "...");
        }

        holder.tvKhoiLuong.setText(gioHang.getKhoiLuong());
        holder.tvDonVi.setText(gioHang.getDonVi());
        NumberFormat numberFormatDefault = NumberFormat.getInstance();
        holder.tvGia.setText("đ" + numberFormatDefault.format(Integer.parseInt(gioHang.getGia())));
        holder.tvSoLuong.setText(gioHang.getSoLuong());
        if (!gioHang.getHinh().toString().trim().equals("")) {
            try {
                byte[] hinh = chuyenStringSangByte(gioHang.getHinh());
                Bitmap bitmap = chuyenByteSangBitMap(hinh);
                holder.ivHinh.setImageBitmap(bitmap);
            } catch (Exception e) {
                holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
        }
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                builder.setMessage("Bạn có muốn xóa sản phẩm này không ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                data_GioHang.child(gioHang.maGioHang).removeValue();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create().show();
            }
        });
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Integer.parseInt(holder.tvSoLuong.getText().toString().trim()) + 1 <= Integer.parseInt(dataSP.get(0).soLuong)) {
//                    holder.tvSoLuong.setText(Integer.parseInt(holder.tvSoLuong.getText().toString().trim()) + 1 + "");
//                } else {
//                    Toast.makeText(context, "Số lượng không được đặt lớn hơn trong kho hàng", Toast.LENGTH_SHORT).show();
//                }
                //holder.tvSoLuong.setText(Integer.parseInt(holder.tvSoLuong.getText().toString().trim()) + 1 + "");
                data_GioHang.child(gioHang.maGioHang).child("soLuong").setValue(Integer.parseInt(holder.tvSoLuong.getText().toString().trim()) + 1 + "");
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.tvSoLuong.getText().toString().trim()) - 1 > 0) {
                    data_GioHang.child(gioHang.maGioHang).child("soLuong").setValue(Integer.parseInt(holder.tvSoLuong.getText().toString().trim()) - 1 + "");
                } else {
                    Toast.makeText(context, "Số lượng không được nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.cbTungSP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    data_GioHang.child(gioHang.maGioHang).child("daChon").setValue("1");
                } else {
                    data_GioHang.child(gioHang.maGioHang).child("daChon").setValue("0");
                }
            }
        });

        if (data.get(position).daChon.trim().equals("1")) {
            holder.cbTungSP.setChecked(true);
        } else {
            holder.cbTungSP.setChecked(false);
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
