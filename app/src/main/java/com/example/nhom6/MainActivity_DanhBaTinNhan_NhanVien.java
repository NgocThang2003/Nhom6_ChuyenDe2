package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_DanhBaTinNhan extends AppCompatActivity {

    RecyclerView recyclerViewDanhBa;
    List<TinNhan> data_danhba = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_ba_tin_nhan_nhanvien);

        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerViewDanhBa.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerViewDanhBa.setAdapter(new DanhBaTinNhanNhanVienAdapter(this, data_danhba));
    }

    private void KhoiTao() {
        data_danhba.add(new TinNhan("0001","abc","Tran xuan quy","aff","Tran xuan dung","abc --- dddd","2023/11/11 13:45","1","",""));
        data_danhba.add(new TinNhan("0001","abc","Tran xuan quy","aff","Tran xuan dung","abc --- dddd","2023/11/11 13:45","1","",""));
    }

    private void setControl() {
        recyclerViewDanhBa = findViewById(R.id.recyclerviewDanhBa);
    }
}