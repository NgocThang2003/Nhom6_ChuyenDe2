package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class MainActivity_GiongCayTrong extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvCayNongNghiep, tvCayCongNghiep, tvCayLamNghiep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giongcaytrong);
        setControl();
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewGiongCayTrong);
        tvCayNongNghiep = findViewById(R.id.tvCayNongNghiep);
        tvCayCongNghiep = findViewById(R.id.tvCayCongNghiep);
        tvCayLamNghiep = findViewById(R.id.tvCayLamNghiep);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_giongcaytrong, menu);
        return true;
    }
}