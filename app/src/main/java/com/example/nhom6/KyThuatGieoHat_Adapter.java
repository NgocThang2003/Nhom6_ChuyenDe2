package com.example.nhom6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KyThuatGieoHat_Adapter extends RecyclerView.Adapter<KyThuatGieoHat_Holder> {
    Context context;
    List<KyThuatGieoHat> data;
    public KyThuatGieoHat_Adapter(Context context, List<KyThuatGieoHat> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public KyThuatGieoHat_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KyThuatGieoHat_Holder(LayoutInflater.from(context).inflate(R.layout.item_kythuatgieohat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull KyThuatGieoHat_Holder holder, int position) {
        KyThuatGieoHat kyThuatGieoHat = data.get(position);
        holder.tvMoTa.setText(kyThuatGieoHat.moTa);
        holder.tvTenKyThuat.setText(kyThuatGieoHat.tenKyThuat);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
