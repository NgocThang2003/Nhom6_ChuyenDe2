package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_QuanTriKyThuat extends AppCompatActivity {


    EditText edtTenKyThuat, edtMoTa;
    Spinner spNhomNganh;

    int index = -1;

    Button btnThem, btnXoa, btnSua;
    List<String> data_nhomNganh = new ArrayList<>();

    RecyclerView recyclerView;
    ArrayList<TrangChuKhachHang> data_kyThuaTrongCay = new ArrayList<>();

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
        recyclerView.setAdapter(new TrangChuKhachHang_Adapter(this, data_kyThuaTrongCay));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_nhomNganh);
        spNhomNganh.setAdapter(adapter);

        TrangChuKhachHang_Adapter trangChuKhachHangAdapter = (TrangChuKhachHang_Adapter) recyclerView.getAdapter();
        trangChuKhachHangAdapter.setOnItemClickListenner(new NhanVienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                TrangChuKhachHang trangChuKhachHang = data_kyThuaTrongCay.get(position);
                edtTenKyThuat.setText(trangChuKhachHang.tenKyThuat);
                edtMoTa.setText(trangChuKhachHang.tenMoTa);
                index = position;
                kiemTraNhomNganh(trangChuKhachHang);
                //hienThiLaiDanhSach();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themKyThuat();
                hienThiLaiDanhSach();
                hienThiLaiDanhSach();

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    xoaKyThuat();
                    hienThiLaiDanhSach();
                    hienThiLaiDanhSach();

                } else {
                    Toast.makeText(MainActivity_QuanTriKyThuat.this, "Chọn để xóa dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    suaKyThuat();
                    hienThiLaiDanhSach();
                    hienThiLaiDanhSach();

                } else {
                    Toast.makeText(MainActivity_QuanTriKyThuat.this, "Chọn để sửa dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void kiemTraNhomNganh(TrangChuKhachHang trangChuKhachHang) {
        if (trangChuKhachHang.nhomNganh.equals("Nông nghiệp")) {
            spNhomNganh.setSelection(1);
        } else if (trangChuKhachHang.nhomNganh.trim().equals("Công nghiệp")) {
            spNhomNganh.setSelection(2);
        } else if (trangChuKhachHang.nhomNganh.trim().equals("Lâm nghiệp")) {
            spNhomNganh.setSelection(3);
        }
    }

    private void KhoiTao() {
        data_nhomNganh.clear();
        getDataNhomNganh();
        getDataQuanTriKyThuat();
//        data.add(new TrangChuKhachHang("Kỹ thuật trồng Giổi xanh:", "Hạt giống được thu hái từ các cây giống từ 20 tuổi trở lên, có thân thẳng đẹp, tán đều, phân cành cao", R.drawable.anhsp_quantri, "Nông nghiệp"));
//        data.add(new TrangChuKhachHang("Kỹ thuật gieo hạt giống Lim Xanh::", "Lim xanh là một loài cây gỗ có giá trị kinh tế cao, phân bố ở các vùng đồi núi có độ cao dưới 700m so với ", R.drawable.anhsp_quantri, "Công nghiệp"));
//        data.add(new TrangChuKhachHang("Kỹ thuật gieo ươm cây keo lai:", "Keo lai là một loài cây lâm nghiệp phổ biến, có khả năng chịu hạn và sinh trưởng nhanh. Hạt được", R.drawable.anhsp_quantri, "Lâm nghiệp"));


        data_nhomNganh.add("Bank");

    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewDanhSachKyThuatCayTrong);
        edtTenKyThuat = findViewById(R.id.edtTenKyThuat);
        edtMoTa = findViewById(R.id.edtMoTa);

        spNhomNganh = findViewById(R.id.spNhomNganh);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);


    }

    private void getDataNhomNganh() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113:80/Nhom6/getNhomNganh.php";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        String nhomNganh = String.valueOf(jsonObject.getString("NhomNganh"));
                        data_nhomNganh.add(nhomNganh);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriKyThuat.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void getDataQuanTriKyThuat() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113/Nhom6/getKyThuatTrongCay.php";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);


                        String tenKyThuat = jsonObject.getString("tenKyThuat");
                        String nhomNganh = jsonObject.getString("NhomNganh");
                        String moTa = jsonObject.getString("MoTa");
                        String hinh = jsonObject.getString("Hinh");
                        int id_kyThuat = jsonObject.getInt("id_KyThuat");

                        TrangChuKhachHang trangChuKhachHang = new TrangChuKhachHang(id_kyThuat, tenKyThuat, moTa, R.drawable.anhsp_quantri, nhomNganh);
                        data_kyThuaTrongCay.add(trangChuKhachHang);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriKyThuat.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }


    private void themKyThuat() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113/Nhom6/themKyThuat.php?" +
                "tenKyThuat=" + edtTenKyThuat.getText().toString().trim().replace(" ", "+") + "" +
                "&id_NhomNganh=" + (spNhomNganh.getSelectedItemPosition()) + "" +
                "&MoTa=" + edtMoTa.getText().toString().trim().replace(" ", "+") + "" +
                "&Hinh=0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriKyThuat.this, response, Toast.LENGTH_SHORT).show();
                index = -1;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void xoaKyThuat() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113/Nhom6/xoaKyThuat.php?" +
                "id_KyThuat=" + data_kyThuaTrongCay.get(index).maKyThuat + "";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriKyThuat.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void suaKyThuat() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113/Nhom6/suaKyThuat.php?" +
                "id_KyThuat=" + data_kyThuaTrongCay.get(index).maKyThuat + "" +
                "&tenKyThuat=" + edtTenKyThuat.getText().toString().trim().replace(" ", "+") + "" +
                "&id_NhomNganh=" + (spNhomNganh.getSelectedItemPosition()) + "" +
                "&MoTa=" + edtMoTa.getText().toString().trim().replace(" ", "+") + "" +
                "&Hinh=0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriKyThuat.this, response, Toast.LENGTH_SHORT).show();
                index = -1;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void hienThiLaiDanhSach() {
        data_kyThuaTrongCay.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.113/Nhom6/getKyThuatTrongCay.php";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                data_kyThuaTrongCay.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);


                        String tenKyThuat = jsonObject.getString("tenKyThuat");
                        String nhomNganh = jsonObject.getString("NhomNganh");
                        String moTa = jsonObject.getString("MoTa");
                        //String hinh = jsonObject.getString("Hinh");
                        int id_kyThuat = jsonObject.getInt("id_KyThuat");

                        TrangChuKhachHang trangChuKhachHang = new TrangChuKhachHang(id_kyThuat, tenKyThuat, moTa, R.drawable.anhsp_quantri, nhomNganh);
                        data_kyThuaTrongCay.add(trangChuKhachHang);
                        recyclerView.getAdapter().notifyDataSetChanged();

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriKyThuat.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

}