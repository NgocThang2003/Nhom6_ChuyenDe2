package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ThemDiaChiMoi extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ThemDiaChiMoi> data_ThemDiaChi = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themdiachimoi);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new ThemDiaChiMoi_Adapter(this,data_ThemDiaChi));
    }

    private void KhoiTao() {
        data_ThemDiaChi.add(new ThemDiaChiMoi("53, Võ Văn Ngân, Linh Chiểu, Thủ Đức", ""));
        data_ThemDiaChi.add(new ThemDiaChiMoi("54, Võ Văn Ngân, Linh Chiểu, Thủ Đức", ""));
        data_ThemDiaChi.add(new ThemDiaChiMoi("55, Võ Văn Ngân, Linh Chiểu, Thủ Đức", ""));
        data_ThemDiaChi.add(new ThemDiaChiMoi("56, Võ Văn Ngân, Linh Chiểu, Thủ Đức", ""));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewThemDiaChiMoi);
    }
}