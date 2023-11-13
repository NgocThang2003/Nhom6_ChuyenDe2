package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangHolder> {

    Context context;
    List<GioHang> data = new ArrayList<>();

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
       GioHang gioHang = data.get(position);

       holder.tvTenSP.setText(gioHang.getTenSP());
        holder.tvChuThich.setText(gioHang.getChuThich());
       if(gioHang.getChuThich().toString().trim().length()>10){
           holder.tvChuThich.setText(gioHang.getChuThich().substring(0,10)+"...");
       }


       holder.tvKhoiLuong.setText(gioHang.getKhoiLuong());
       holder.tvDonVi.setText(gioHang.getDonVi());
       holder.tvGia.setText(gioHang.getGia());
       holder.tvSoLuong.setText(gioHang.getSoLuong());
       if(!gioHang.getHinh().toString().trim().equals("")){
           try{
               byte[] hinh=chuyenStringSangByte(gioHang.getHinh());
               Bitmap bitmap=chuyenByteSangBitMap(hinh);
               holder.ivHinh.setImageBitmap(bitmap);
           }catch (Exception e){
               holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
           }
       }else {
           holder.ivHinh.setImageResource(R.drawable.ic_launcher_background);
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
