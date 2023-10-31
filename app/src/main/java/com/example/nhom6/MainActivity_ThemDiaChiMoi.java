package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

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

    public void chucnang(View view) {

        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_ThemDiaChiMoi.this,MainActivity_DiaChiGiaoHang.class);
            startActivity(intent);
        }
    }
//    private void getDataList() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String url = "http://192.168.2.130/API/getDataQTSP.php";
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//
//
//
//
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }
}