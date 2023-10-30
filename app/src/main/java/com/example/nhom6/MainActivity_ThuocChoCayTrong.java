package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ThuocChoCayTrong extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ThuocChoCayTrong> data_Thuochocaytrong = new ArrayList<>();
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
        recyclerView.setAdapter(new ThuocChoCayTrong_Adapter(this, data_Thuochocaytrong));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.rcvGiongHatCayTrong);
    }

    private void KhoiTao() {
       data_Thuochocaytrong.add(new ThuocChoCayTrong("BS32 - Mecin","BSC03 - Cobo siêu kích rễ","Nứt thân,xù mũ, chảy nhựa, chảy gôm","Kích rễ, dưỡng cây xanh lá, phục hồi bộ rễ","195000", "250000", R.drawable.thuoc1, R.drawable.thuoc2));
       data_Thuochocaytrong.add(new ThuocChoCayTrong("BSC01 - Cobo siêu dưỡng cây","BS25 - Insect","Dinh dưỡng , dưỡng cây, dưỡng rễ, phục hồi rễ","Trừ nhện đỏ, rầy, rệp. con trùng chích hút","195000", "250000", R.drawable.thuoc3, R.drawable.thuoc4));


    }
}