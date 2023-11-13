package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_GioiThieu extends AppCompatActivity {
    RecyclerView recyclerView;
    List<GioiThieu> data_GioiThieu = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gioithieu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new GioiThieu_Adapter(this,data_GioiThieu));
    }

    private void KhoiTao() {
        data_GioiThieu.add(new GioiThieu("Tư vấn kỹ thuật, giải pháp, cách trồng","Sản phẩm sinh học hiệu quả - an toàn",R.drawable.gioithieu1, R.drawable.gioithieu2));
        data_GioiThieu.add(new GioiThieu("Mua dễ dàng - giao hàng nhanh chóng","Nông nghiệp thông minh 4.0",R.drawable.gioithieu3, R.drawable.gioithieu4));
        data_GioiThieu.add(new GioiThieu("Thời gian làm việc 24/7","Chốt mọi đơn của khách hàng",R.drawable.gioithieu5, R.drawable.gioithieu6));

    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewGioiThieu);
    }

    public void chucnang(View view) {

        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_GioiThieu.this,MainActivity_TaiKhoan.class);
            startActivity(intent);
        }
    }
}