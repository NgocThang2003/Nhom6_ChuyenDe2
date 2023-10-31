package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_KyThuatGieoHat extends AppCompatActivity {
    RecyclerView recyclerView;
    List<KyThuatGieoHat> data_KyThuat = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kythuatgieohat);
        setControl();
        setEvent();

    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new KyThuatGieoHat_Adapter(this,data_KyThuat));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerviewKyThuatGieoHat);
    }

    private void KhoiTao() {
        data_KyThuat.add(new KyThuatGieoHat("• Chuẩn bị chất trồng", "Chất trồng sau khi trộn đều, chúng ta cho vào chậu hoặc khay uơm. Tưới đẫm chất trồng.\n" +
                "Phun thuốc trừ nấm lên mặt chất trồng (bước này rất quan trọng). Tốt nhất phun liên tục 2-3 lần để thuốc thấm xuống sâu hơn."));
        data_KyThuat.add(new KyThuatGieoHat("• Ủ hạt giống", "Sau khi ngâm hạt giống cây trồng, tiến hành ủ hạt (tùy loại hạt, có loại cần ủ vài tiếng, 1 hoặc nhiều ngày), cũng có loại hạt không cần ngâm ủ."));
        data_KyThuat.add(new KyThuatGieoHat("• Ủ hạt giống", "Sau khi ngâm hạt giống cây trồng, tiến hành ủ hạt (tùy loại hạt, có loại cần ủ vài tiếng, 1 hoặc nhiều ngày), cũng có loại hạt không cần ngâm ủ."));
        data_KyThuat.add(new KyThuatGieoHat("• Ủ hạt giống", "Sau khi ngâm hạt giống cây trồng, tiến hành ủ hạt (tùy loại hạt, có loại cần ủ vài tiếng, 1 hoặc nhiều ngày), cũng có loại hạt không cần ngâm ủ."));
        data_KyThuat.add(new KyThuatGieoHat("• Ủ hạt giống", "Sau khi ngâm hạt giống cây trồng, tiến hành ủ hạt (tùy loại hạt, có loại cần ủ vài tiếng, 1 hoặc nhiều ngày), cũng có loại hạt không cần ngâm ủ."));
        data_KyThuat.add(new KyThuatGieoHat("• Ủ hạt giống", "Sau khi ngâm hạt giống cây trồng, tiến hành ủ hạt (tùy loại hạt, có loại cần ủ vài tiếng, 1 hoặc nhiều ngày), cũng có loại hạt không cần ngâm ủ."));
        data_KyThuat.add(new KyThuatGieoHat("• Gieo hạt", "Nguyên tắc gieo hạt là chôn hạt với độ sâu bằng 2-3 lần đường kính của hạt. Đối với các loại hạt rất nhỏ, thì chúng ta gieo trực tiếp trên mặt đất ẩm, sau đó phun suơng cho hạt bám vào chất trồng là được."));
    }
}