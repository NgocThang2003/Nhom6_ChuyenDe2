package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_TrangChuKhachHang extends AppCompatActivity {

    RecyclerView recyclerView;
    List<TrangChuKhachHang> data_TrangChu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchukhachhang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new TrangChuKhachHang_Adapter(this,data_TrangChu));
    }

    private void KhoiTao() {
        data_TrangChu.add(new TrangChuKhachHang("Kỹ thuật trồng Giổi xanh", "Hạt giống được thu hái từ các cây giống từ 20 tuổi trở lên, có thân thẳng đẹp, tán đều, phân cành cao", R.drawable.kythuat1));
        data_TrangChu.add(new TrangChuKhachHang("Kỹ thuật gieo hạt giống Lim Xanh:", "Lim xanh là một loài cây gỗ có giá trị kinh tế cao, phân bố ở các vùng đồi núi có độ cao dưới 700m so với ....", R.drawable.kythuat2));
        data_TrangChu.add(new TrangChuKhachHang("Kỹ thuật gieo ươm cây keo lai:", "Keo lai là một loài cây lâm nghiệp phổ biến, có khả năng chịu hạn và sinh trưởng nhanh. Hạt được ...", R.drawable.kythuat3));
        data_TrangChu.add(new TrangChuKhachHang("Kỹ thuật gieo ươm cây keo lai:", "Keo lai là một loài cây lâm nghiệp phổ biến, có khả năng chịu hạn và sinh trưởng nhanh. Hạt được ...", R.drawable.kythuat3));
        data_TrangChu.add(new TrangChuKhachHang("Kỹ thuật gieo ươm cây keo lai:", "Keo lai là một loài cây lâm nghiệp phổ biến, có khả năng chịu hạn và sinh trưởng nhanh. Hạt được ...", R.drawable.kythuat3));
        data_TrangChu.add(new TrangChuKhachHang("Kỹ thuật gieo ươm cây keo lai:", "Keo lai là một loài cây lâm nghiệp phổ biến, có khả năng chịu hạn và sinh trưởng nhanh. Hạt được ...", R.drawable.kythuat3));

    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewTrangChu);
    }
    public void chucnang(View view) {

        if(view.getId()==R.id.tvKyThuatGieoHat){
            Intent intent = new Intent(MainActivity_TrangChuKhachHang.this,MainActivity_KyThuatGieoHat.class);
            startActivity(intent);
        }
    }
}