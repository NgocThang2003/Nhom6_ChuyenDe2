package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_DonHangChoXacNhan_BanHang extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DonHangChoXacNhan_BanHang> data_DonHangChoXacNhan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donhangchoxacnhan_banhang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new DonHangChoXacNhan_BanHang_Adapter(this,data_DonHangChoXacNhan));
    }

    private void KhoiTao() {
        data_DonHangChoXacNhan.add(new DonHangChoXacNhan_BanHang("Khách Hàng A","","53 Võ Văn Ngân, Linh Đông","Giống hạt dưa lê","Hạt dưa gang sinh trưởng nhanh","0989 xxx xxx","đ170.000","x1","13-09-2023 13:30","Đang chờ xác nhận","đ250.000"));
        data_DonHangChoXacNhan.add(new DonHangChoXacNhan_BanHang("Khách Hàng A","","53 Võ Văn Ngân, Linh Đông","Giống hạt dưa lê","Hạt dưa gang sinh trưởng nhanh","0989 xxx xxx","đ170.000","x1","13-09-2023 13:30","Đang chờ xác nhận","đ250.000"));
        data_DonHangChoXacNhan.add(new DonHangChoXacNhan_BanHang("Khách Hàng A","","53 Võ Văn Ngân, Linh Đông","Giống hạt dưa lê","Hạt dưa gang sinh trưởng nhanh","0989 xxx xxx","đ170.000","x1","13-09-2023 13:30","Đang chờ xác nhận","đ250.000"));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewDonHang);
    }
}