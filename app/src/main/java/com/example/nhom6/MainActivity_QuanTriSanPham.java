package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_QuanTriSanPham extends AppCompatActivity {

    RecyclerView recyclerViewDanhSachSP;
    List<SanPham> data_SanPham = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quantrisanpham);

        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerViewDanhSachSP.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDanhSachSP.setAdapter(new SanPhamAdapter(this, data_SanPham));

    }

    private void KhoiTao() {
        data_SanPham.add(new SanPham("Bắp cải xanh", "tươi ngon, bổ dưỡng", "gam", "Nông nghiệp", "aaa", "" + R.drawable.anhsp, 4, 100, 35000));
        data_SanPham.add(new SanPham("Dưa hấu đỏ", "Đỏ ít hạt", "kg", "Nông nghiệp", "nhan chóng", "" + R.drawable.anhsp, 4, 100, 30000));
        data_SanPham.add(new SanPham("Bí ngô", "nảy mầm nhanh", "lạng", "Nông nghiệp", "linh động", "" + R.drawable.anhsp, 4, 100, 45000));
        data_SanPham.add(new SanPham("Bí ngô 2", "nảy mầm nhanh", "lạng", "Nông nghiệp", "linh động", "" + R.drawable.anhsp, 4, 100, 45000));
        data_SanPham.add(new SanPham("Dưa hấu đỏ 2", "Đỏ ít hạt", "kg", "Nông nghiệp", "nhan chóng", "" + R.drawable.anhsp, 4, 100, 30000));
        data_SanPham.add(new SanPham("Bắp cải xanh 2", "tươi ngon, bổ dưỡng", "gam", "Nông nghiệp", "aaa", "" + R.drawable.anhsp, 4, 100, 35000));
        data_SanPham.add(new SanPham("Bí ngô 3", "nảy mầm nhanh", "lạng", "Nông nghiệp", "linh động", "" + R.drawable.anhsp, 4, 100, 45000));
        data_SanPham.add(new SanPham("Dưa hấu đỏ 3", "Đỏ ít hạt", "kg", "Nông nghiệp", "nhan chóng", "" + R.drawable.anhsp, 4, 100, 30000));
    }

    private void setControl() {
        recyclerViewDanhSachSP = findViewById(R.id.recyclerViewDanhSachSanPham);
    }
}