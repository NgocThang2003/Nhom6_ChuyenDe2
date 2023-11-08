package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Thuoc extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Thuoc> data_Thuoc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuoc);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new Thuoc_Adapter(this,data_Thuoc));
    }

    private void KhoiTao() {
        data_Thuoc.add(new Thuoc("BS32 - Mecin","BSC03 - Combo","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195.000đ","250.000đ",R.drawable.thuoc1,R.drawable.thuoc2));
        data_Thuoc.add(new Thuoc("BSC01 - Combo","BS25 - Insect","Dinh dưỡng , dưỡng cây, dưỡng rễ, phục hồi rễ","Trừ nhện đỏ, rầy, rệp. con trùng chích hút","250.000đ","220.000đ",R.drawable.thuoc3,R.drawable.thuoc4));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewThuoc);
    }
}