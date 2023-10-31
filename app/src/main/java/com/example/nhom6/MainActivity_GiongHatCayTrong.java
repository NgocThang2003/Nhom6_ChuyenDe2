package com.example.nhom6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_GiongHatCayTrong extends AppCompatActivity {

    private Button btnCayCN;
    RecyclerView recyclerView;
    List<GiongHatCayTrong> data_GiongCayTrong = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gionghatcaytrong);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new GiongHatCayTrong_Adapter(this, data_GiongCayTrong));

        btnCayCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity_GiaoDienShipper();
            }
        });
    }

    public void openMainActivity_GiaoDienShipper(){
        Intent intent = new Intent(this, MainActivity_GiaoDienShipper.class);
        startActivity(intent);
    }

    private void KhoiTao() {
        data_GiongCayTrong.add(new GiongHatCayTrong("Giống ngô", "Giống Lúa", "20.000 VNĐ", "85.000VND", R.drawable.ngo, R.drawable.lua));
        data_GiongCayTrong.add(new GiongHatCayTrong("Giống ngô", "Giống Lúa", "20.000 VNĐ", "85.000VND", R.drawable.thanhlong, R.drawable.daunanh));
        data_GiongCayTrong.add(new GiongHatCayTrong("Giống ngô", "Giống Lúa", "20.000 VNĐ", "85.000VND", R.drawable.cachua, R.drawable.luamach));

    }

    private void setControl() {
        recyclerView = findViewById(R.id.rcvGiongHatCayTrong);
        btnCayCN = findViewById(R.id.btnCayCN);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gionghatcaytrong, menu);
        return true;
    }

}