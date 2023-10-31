package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class MainActivity_QuanTriSanPham extends AppCompatActivity {

    public static EditText edtTenSP, edtChuThich, edtSL, edtKhoiLuong, edtGia, edtMoTa;
    public static Spinner spDonVi, spLoaiSanPham;

    EditText edtTimKiem;
    Button btnThem, btnXoa, btnSua;
    int index = -1;

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
        EnabelButtonTrue();
        KhoiTao();
        recyclerViewDanhSachSP.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDanhSachSP.setAdapter(new SanPhamAdapter(this, data_SanPham));

        ArrayAdapter adapter_donVi = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_donVi);
        ArrayAdapter adapter_loaiSanPham = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_loaiSanPham);

        spDonVi.setAdapter(adapter_donVi);
        spLoaiSanPham.setAdapter(adapter_loaiSanPham);
        SanPhamAdapter sanPhamAdapter = (SanPhamAdapter) recyclerViewDanhSachSP.getAdapter();
        sanPhamAdapter.setOnItemClickListenner(new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EnabelButtonFalse();
                SanPham sanPham = data_SanPham.get(position);

                edtTenSP.setText(sanPham.tenSP);
                edtChuThich.setText(sanPham.chuThich);
                edtSL.setText("" + sanPham.soLuong);
                edtKhoiLuong.setText("" + sanPham.khoiLuong);
                edtGia.setText("" + sanPham.gia);
                edtMoTa.setText("" + sanPham.moTa);
                spDonVi.setSelection(Integer.parseInt(sanPham.donVi));
                spLoaiSanPham.setSelection(Integer.parseInt(sanPham.loaiSP));

                index = position;

                //kiemTraDonVi(sanPham);
                //kiemTraLoaiSanPham(sanPham);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDieuKien() == true) {
                    if (spLoaiSanPham.getSelectedItemPosition() != 0) {
                        if (spDonVi.getSelectedItemPosition() != 0) {
                            themSanPham();
                        } else {
                            Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn đơn vị !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn loại sản phẩm !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    xoaSanPham();
                } else {
                    Toast.makeText(MainActivity_QuanTriSanPham.this, "Chọn để xóa sản phẩm !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    if (kiemTraDieuKien() == true) {
                        if (spLoaiSanPham.getSelectedItemPosition() != 0) {
                            if (spDonVi.getSelectedItemPosition() != 0) {
                                suaSanPham();
                            } else {
                                Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn đơn vị !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity_QuanTriSanPham.this, "Bạn chưa chọn loại sản phẩm !", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity_QuanTriSanPham.this, "Chọn để sửa sản phẩm !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timSanPham();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean kiemTraDieuKien() {
        boolean kiemTra = true;
        if (!edtTenSP.getText().toString().trim().equals("")) {
            if (!edtChuThich.getText().toString().trim().equals("")) {
                if (!edtSL.getText().toString().trim().equals("")) {
                    if (!edtKhoiLuong.getText().toString().trim().equals("")) {
                        if (!edtGia.getText().toString().trim().equals("")) {
                            if (!edtMoTa.getText().toString().trim().equals("")) {

                            } else {
                                edtMoTa.setError("Không được để trống !");
                                kiemTra = false;
                            }
                        } else {
                            edtGia.setError("Không được để trống !");
                            kiemTra = false;
                        }
                    } else {
                        edtKhoiLuong.setError("Không được để trống !");
                        kiemTra = false;
                    }
                } else {
                    edtSL.setError("Không được để trống !");
                    kiemTra = false;
                }
            } else {
                edtChuThich.setError("Không được để trống !");
                kiemTra = false;
            }
        } else {
            edtTenSP.setError("Không được để trống !");
            kiemTra = false;
        }
        return kiemTra;
    }


    private void kiemTraLoaiSanPham(SanPham sanPham) {
        if (sanPham.loaiSP.equals("Nông nghiệp")) {
            spLoaiSanPham.setSelection(1);
        } else if (sanPham.loaiSP.equals("Lâm nghiệp")) {
            spLoaiSanPham.setSelection(3);
        } else if (sanPham.loaiSP.equals("Công nghiệp")) {
            spLoaiSanPham.setSelection(2);
        }
    }

    private void kiemTraDonVi(SanPham sanPham) {
        if (sanPham.donVi.equals("kq")) {
            spDonVi.setSelection(1);
        } else if (sanPham.donVi.equals("hg")) {
            spDonVi.setSelection(2);
        } else if (sanPham.donVi.equals("dag")) {
            spDonVi.setSelection(3);
        } else if (sanPham.donVi.equals("g")) {
            spDonVi.setSelection(4);
        }
    }

    private void KhoiTao() {
        getDataNhomNganh();
        getDonVi();
        getSanPhamCayTrong();
//        data_SanPham.add(new SanPham("Bắp cải xanh", "tươi ngon, bổ dưỡng", "g", "Công nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
//        data_SanPham.add(new SanPham("Dưa hấu đỏ", "Đỏ ít hạt", "g", "Lâm nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));
//        data_SanPham.add(new SanPham("Bí ngô", "nảy mầm nhanh", "kg", "Nông nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
//        data_SanPham.add(new SanPham("Bí ngô 2", "nảy mầm nhanh", "g", "Lâm nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
//        data_SanPham.add(new SanPham("Dưa hấu đỏ 2", "Đỏ ít hạt", "dag", "Nông nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));
//        data_SanPham.add(new SanPham("Bắp cải xanh 2", "tươi ngon, bổ dưỡng", "g", "Nông nghiệp", "aaa", "" + R.drawable.anhsp_quantri, 4, 100, 35000));
//        data_SanPham.add(new SanPham("Bí ngô 3", "nảy mầm nhanh", "dag", "Công nghiệp", "linh động", "" + R.drawable.anhsp_quantri, 4, 100, 45000));
//        data_SanPham.add(new SanPham("Dưa hấu đỏ 3", "Đỏ ít hạt", "hg", "Lâm nghiệp", "nhan chóng", "" + R.drawable.anhsp_quantri, 4, 100, 30000));

        data_loaiSanPham.add("Bank");
        data_donVi.add("Bank");
//        data_donVi.add("kg");
//        data_donVi.add("gam");
//        data_donVi.add("dag");
//        data_donVi.add("hg");

//        data_loaiSanPham.add("Nông nghiệp");
//        data_loaiSanPham.add("Lâm nghiệp");
//        data_loaiSanPham.add("Công nghiệp");
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
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
    }

    String host = "192.168.137.33:80";

    private void getDataNhomNganh() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/getNhomNganh.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String nhomNganh = String.valueOf(jsonObject.getString("NhomNganh"));
                        data_loaiSanPham.add(nhomNganh);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void getDonVi() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/getDonVi.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String donVi = jsonObject.getString("DonVi").trim();
                        data_donVi.add(donVi);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void getSanPhamCayTrong() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/nhom6/getSanPhamCayTrong.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id_SanPham = jsonObject.getString("id_SanPham").trim();
                        String tenSanPham = jsonObject.getString("tenSanPham").trim();
                        String chuThich = jsonObject.getString("ChuThich").trim();
                        int soLuong = jsonObject.getInt("SoLuong");
                        int id_DonVi = jsonObject.getInt("id_DonVi");
                        int khoiLuong = jsonObject.getInt("KhoiLuong");
                        int gia = jsonObject.getInt("Gia");
                        String id_LoaiSanPham = jsonObject.getString("id_LoaiSanPham").trim();
                        String moTa = jsonObject.getString("moTa").trim();
                        String hinh = jsonObject.getString("Hinh").trim();

                        SanPham sanPham = new SanPham("" + id_SanPham, tenSanPham, chuThich, "" + id_DonVi, id_LoaiSanPham, moTa, hinh, soLuong, khoiLuong, gia);
                        data_SanPham.add(sanPham);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void timSanPham() {
        data_SanPham.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/nhom6/timSanPham.php?tenSanPham=" + edtTimKiem.getText().toString().trim().replace(" ", "+") + "";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id_SanPham = jsonObject.getString("id_SanPham").trim();
                        String tenSanPham = jsonObject.getString("tenSanPham").trim();
                        String chuThich = jsonObject.getString("ChuThich").trim();
                        int soLuong = jsonObject.getInt("SoLuong");
                        int id_DonVi = jsonObject.getInt("id_DonVi");
                        int khoiLuong = jsonObject.getInt("KhoiLuong");
                        int gia = jsonObject.getInt("Gia");
                        String id_LoaiSanPham = jsonObject.getString("id_LoaiSanPham").trim();
                        String moTa = jsonObject.getString("moTa").trim();
                        String hinh = jsonObject.getString("Hinh").trim();

                        SanPham sanPham = new SanPham("" + id_SanPham, tenSanPham, chuThich, "" + id_DonVi, id_LoaiSanPham, moTa, hinh, soLuong, khoiLuong, gia);
                        data_SanPham.add(sanPham);
                        recyclerViewDanhSachSP.getAdapter().notifyDataSetChanged();

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void hienThiLaiDanhSach() {
        data_SanPham.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/nhom6/getSanPhamCayTrong.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                data_SanPham.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id_SanPham = jsonObject.getString("id_SanPham").trim();
                        String tenSanPham = jsonObject.getString("tenSanPham").trim();
                        String chuThich = jsonObject.getString("ChuThich").trim();
                        int soLuong = jsonObject.getInt("SoLuong");
                        int id_DonVi = jsonObject.getInt("id_DonVi");
                        int khoiLuong = jsonObject.getInt("KhoiLuong");
                        int gia = jsonObject.getInt("Gia");
                        String id_LoaiSanPham = jsonObject.getString("id_LoaiSanPham").trim();
                        String moTa = jsonObject.getString("moTa").trim();
                        String hinh = jsonObject.getString("Hinh").trim();

                        SanPham sanPham = new SanPham("" + id_SanPham, tenSanPham, chuThich, "" + id_DonVi, id_LoaiSanPham, moTa, hinh, soLuong, khoiLuong, gia);
                        data_SanPham.add(sanPham);
                        recyclerViewDanhSachSP.getAdapter().notifyDataSetChanged();


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void themSanPham() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/nhom6/themSanPham.php?" +
                "tenSanPham=" + edtTenSP.getText().toString().trim().replace(" ", "+") + "" +
                "&ChuThich=" + edtChuThich.getText().toString().trim().replace(" ", "+") + "" +
                "&SoLuong=" + edtSL.getText().toString().trim() + "" +
                "&id_DonVi=" + spDonVi.getSelectedItemPosition() + "" +
                "&KhoiLuong=" + edtKhoiLuong.getText().toString().trim() + "" +
                "&Gia=" + edtKhoiLuong.getText().toString().trim() + "" +
                "&id_LoaiSanPham=" + spLoaiSanPham.getSelectedItemPosition() + "" +
                "&moTa=" + edtMoTa.getText().toString().trim().replace(" ", "+") + "" +
                "&Hinh=0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, response, Toast.LENGTH_SHORT).show();
                hienThiLaiDanhSach();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void xoaSanPham() {
//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/nhom6/xoaSanPham.php?id_SanPham=" + data_SanPham.get(index).maSanPham.trim() + "";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, response, Toast.LENGTH_SHORT).show();
                hienThiLaiDanhSach();
                EnabelButtonTrue();
                clearEditText();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void suaSanPham() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/nhom6/suaSanPham.php?" +
                "id_SanPham=" + data_SanPham.get(index).maSanPham.trim() + "" +
                "&tenSanPham=" + edtTenSP.getText().toString().trim().replace(" ", "+") + "" +
                "&ChuThich=" + edtChuThich.getText().toString().trim().replace(" ", "+") + "" +
                "&SoLuong=" + edtSL.getText().toString().trim() + "" +
                "&KhoiLuong=" + edtKhoiLuong.getText().toString().trim() + "" +
                "&id_DonVi=" + spDonVi.getSelectedItemPosition() + "" +
                "&Gia=" + edtKhoiLuong.getText().toString().trim() + "" +
                "&id_LoaiSanPham=" + spLoaiSanPham.getSelectedItemPosition() + "" +
                "&moTa=" + edtMoTa.getText().toString().trim().replace(" ", "+") + "" +
                "&Hinh=0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriSanPham.this, response, Toast.LENGTH_SHORT).show();
                hienThiLaiDanhSach();
                EnabelButtonTrue();
                clearEditText();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void clearEditText() {
        edtTenSP.setText("");
        edtChuThich.setText("");
        edtSL.setText("");
        edtKhoiLuong.setText("");
        edtGia.setText("");
        edtMoTa.setText("");
        spLoaiSanPham.setSelection(0);
        spDonVi.setSelection(0);
    }

    private void EnabelButtonTrue() {
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
    }

    private void EnabelButtonFalse() {
        btnThem.setEnabled(false);
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }
}