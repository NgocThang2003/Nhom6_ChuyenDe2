package com.example.nhom6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiongCayTrong_Adapter extends RecyclerView.Adapter<GiongCayTrong_Holder> {
    Context context;
    List<GiongCayTrong> data;
    public GiongCayTrong_Adapter(Context context,  List<GiongCayTrong> data) {
        this.context =  context;
        this.data = data;
    }

    @NonNull
    @Override
    public GiongCayTrong_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GiongCayTrong_Holder(LayoutInflater.from(context).inflate(R.layout.item_giongcaytrong,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GiongCayTrong_Holder holder, int position) {

        GiongCayTrong giongCayTrong = data.get(position);

        if (giongCayTrong.hinh1.trim().equals("")){
            holder.ivHinh1.setImageResource(R.drawable.giongngo);
        }
        else {
            try {
                byte[] bytes = chuyenStringSangByte(giongCayTrong.hinh1);
                Bitmap bitmap = chuyenByteSangBitMap(bytes);
                holder.ivHinh1.setImageBitmap(bitmap);
            }
            catch (Exception e){
                holder.ivHinh1.setImageResource(R.drawable.giongngo);
            }
        }
        holder.tvTenSP1.setText(giongCayTrong.tenSP1);
        holder.tvGia1.setText(giongCayTrong.gia1);

        if(!giongCayTrong.tenSP2.trim().equals("tenSP2")){
            if (giongCayTrong.hinh2.trim().equals("")){
                holder.ivHinh2.setImageResource(R.drawable.giongngo);
            }
            else {
                try {
                    byte[] bytes = chuyenStringSangByte(giongCayTrong.hinh2);
                    Bitmap bitmap = chuyenByteSangBitMap(bytes);
                    holder.ivHinh2.setImageBitmap(bitmap);
                }
                catch (Exception e){
                    holder.ivHinh2.setImageResource(R.drawable.giongngo);
                }
            }
            holder.tvTenSP2.setText(giongCayTrong.tenSP2);
            holder.tvGia2.setText(giongCayTrong.gia2);
        }

        else{
            holder.linearLayoutTC.setBackgroundColor(Color.WHITE);
            holder.tvTenSP2.setVisibility(View.GONE);
            holder.ivHinh2.setVisibility(View.GONE);
            holder.tvGia2.setVisibility(View.GONE);
            holder.btnDatMua2.setEnabled(false);
            holder.btnDatMua2.setBackgroundColor(Color.WHITE);
            holder.btnDatMua2.setTextColor(Color.WHITE);

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
