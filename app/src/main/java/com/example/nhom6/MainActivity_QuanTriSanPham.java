package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_QuanTriSanPham extends AppCompatActivity {

    public static EditText edtTenSP, edtChuThich, edtSL, edtKhoiLuong, edtGia, edtMoTa;
    public static Spinner spDonVi, spLoaiSanPham;

    EditText edtTimKiem;
    Button btnThem;

    RecyclerView recyclerViewDanhSachSP;
    List<SanPham> data_SanPham = new ArrayList<>();
    List<SanPham> data_SanPhamBanDau = new ArrayList<>();
    List<String> data_donVi = new ArrayList<>();
    List<String> data_loaiSanPham = new ArrayList<>();

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

        ArrayAdapter adapter_donVi = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_donVi);
        ArrayAdapter adapter_loaiSanPham = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_loaiSanPham);

        spDonVi.setAdapter(adapter_donVi);
        spLoaiSanPham.setAdapter(adapter_loaiSanPham);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSP = edtTenSP.getText().toString();
                String chuThich = edtChuThich.getText().toString();
                String donVi = spDonVi.getSelectedItem().toString();
                data_SanPham.add(new SanPham("Bắp cải xanh", "tươi ngon, bổ dưỡng", "g", "Công nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
                recyclerViewDanhSachSP.getAdapter().notifyDataSetChanged();
            }
        });


    }

    private void KhoiTao() {
        data_SanPham.add(new SanPham("Bắp cải xanh", "tươi ngon, bổ dưỡng", "g", "Công nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
        data_SanPham.add(new SanPham("Dưa hấu đỏ", "Đỏ ít hạt", "g", "Lâm nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));
        data_SanPham.add(new SanPham("Bí ngô", "nảy mầm nhanh", "kg", "Nông nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
        data_SanPham.add(new SanPham("Bí ngô 2", "nảy mầm nhanh", "g", "Lâm nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
        data_SanPham.add(new SanPham("Dưa hấu đỏ 2", "Đỏ ít hạt", "dag", "Nông nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));
        data_SanPham.add(new SanPham("Bắp cải xanh 2", "tươi ngon, bổ dưỡng", "g", "Nông nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
        data_SanPham.add(new SanPham("Bí ngô 3", "nảy mầm nhanh", "dag", "Công nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
        data_SanPham.add(new SanPham("Dưa hấu đỏ 3", "Đỏ ít hạt", "hg", "Lâm nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));


        data_donVi.add("kg");
        data_donVi.add("gam");
        data_donVi.add("dag");
        data_donVi.add("hg");

        data_loaiSanPham.add("Nông nghiệp");
        data_loaiSanPham.add("Lâm nghiệp");
        data_loaiSanPham.add("Công nghiệp");
    }

    private void setControl() {
        recyclerViewDanhSachSP = findViewById(R.id.recyclerViewDanhSachSanPham);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtChuThich = findViewById(R.id.edtChuThich);
        edtSL = findViewById(R.id.edtSL);
        edtKhoiLuong = findViewById(R.id.edtKhoiLuong);
        edtGia = findViewById(R.id.edtGia);
        edtMoTa = findViewById(R.id.edtMoTa);

        spLoaiSanPham = findViewById(R.id.spLoaiSanPham);
        spDonVi = findViewById(R.id.spDonVi);

        edtTimKiem = findViewById(R.id.edtTimKiem);
        btnThem = findViewById(R.id.btnThem);
    }
}