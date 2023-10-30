package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ThuocChoCayTrong extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ThuocChoCayTrong> data_Thuocchocaytrong = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuocchocaytrong);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ThuocChoCayTrong_Adapter(this, data_Thuocchocaytrong));
    }


    private void KhoiTao() {
       data_Thuocchocaytrong.add(new ThuocChoCayTrong("Thuoc","Abc", "Uống", "Ăn", "195.000đ", "100000VND",R.drawable.thuoc1,R.drawable.thuoc2));
       data_Thuocchocaytrong.add(new ThuocChoCayTrong("Thuoc","Abc", "Uống", "Ăn", "250.000đ", "100000VND",R.drawable.thuoc3,R.drawable.thuoc4));
       data_Thuocchocaytrong.add(new ThuocChoCayTrong("Thuoc","Abc", "Uống", "Ăn", "100000VND", "100000VND",R.drawable.ngo,R.drawable.lua));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewThuocchocaytrong);
    }
}