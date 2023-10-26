package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_QuanTriKyThuat extends AppCompatActivity {

    EditText edtTenKyThuat, edtMoTa;
    Spinner spNhomNganh;
    List<String> data_nhomNganh = new ArrayList<>();

    RecyclerView recyclerView;
    List<TrangChuKhachHang> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_tri_ky_thuat);

        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TrangChuKhachHang_Adapter(this, data));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_nhomNganh);
        spNhomNganh.setAdapter(adapter);

        TrangChuKhachHang_Adapter trangChuKhachHangAdapter = (TrangChuKhachHang_Adapter) recyclerView.getAdapter();
        trangChuKhachHangAdapter.setOnItemClickListenner(new NhanVienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TrangChuKhachHang trangChuKhachHang = data.get(position);
                edtTenKyThuat.setText(trangChuKhachHang.tenKyThuat);
                edtMoTa.setText(trangChuKhachHang.tenMoTa);

                kiemTraNhomNganh(trangChuKhachHang);

            }
        });

    }

    private void kiemTraNhomNganh(TrangChuKhachHang trangChuKhachHang) {
        if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
            spNhomNganh.setSelection(0);
        } else if (trangChuKhachHang.nhomNganh.trim().equals("Công nghiệp")) {
            spNhomNganh.setSelection(1);
        } else if (trangChuKhachHang.nhomNganh.trim().equals("Lâm nghiệp")) {
            spNhomNganh.setSelection(2);
        }
    }

    private void KhoiTao() {
        data.add(new TrangChuKhachHang("Kỹ thuật trồng Giổi xanh:", "Hạt giống được thu hái từ các cây giống từ 20 tuổi trở lên, có thân thẳng đẹp, tán đều, phân cành cao", R.drawable.anhsp_quantri, "Nông nghiệp"));
        data.add(new TrangChuKhachHang("Kỹ thuật gieo hạt giống Lim Xanh::", "Lim xanh là một loài cây gỗ có giá trị kinh tế cao, phân bố ở các vùng đồi núi có độ cao dưới 700m so với ", R.drawable.anhsp_quantri, "Công nghiệp"));
        data.add(new TrangChuKhachHang("Kỹ thuật gieo ươm cây keo lai:", "Keo lai là một loài cây lâm nghiệp phổ biến, có khả năng chịu hạn và sinh trưởng nhanh. Hạt được", R.drawable.anhsp_quantri, "Lâm nghiệp"));

        data_nhomNganh.add("Nông nghiệp");
        data_nhomNganh.add("Công nghiệp");
        data_nhomNganh.add("Lâm nghiệp");
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewDanhSachKyThuatCayTrong);
        edtTenKyThuat = findViewById(R.id.edtTenKyThuat);
        edtMoTa = findViewById(R.id.edtMoTa);

        spNhomNganh = findViewById(R.id.spNhomNganh);

    }
}