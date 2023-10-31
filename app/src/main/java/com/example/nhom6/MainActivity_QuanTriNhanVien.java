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
import android.widget.TextView;
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

public class MainActivity_QuanTriNhanVien extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NhanVien> data_NhanVien = new ArrayList<>();

    EditText edtTimKiem;
    public static Spinner spLoaiNhanVien;
    List<String> data_loaiNhanVien = new ArrayList<>();
    int index = -1;

    Button btnThem, btnXoa, btnSua;

    public static EditText edtTenNV, edtSDT, edtQueQuan, edtNgaySinh, edtGmail, edtCMND, edtTenDangNhap, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quan_tri_nhan_vien);

        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new NhanVienAdapter(this, data_NhanVien));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data_loaiNhanVien);
        spLoaiNhanVien.setAdapter(adapter);

        NhanVienAdapter nhanVienAdapter = (NhanVienAdapter) recyclerView.getAdapter();
        nhanVienAdapter.setOnItemClickListenner(new NhanVienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                index = position;

                NhanVien nhanVien = data_NhanVien.get(position);
                edtTenNV.setText(nhanVien.tenNhanVien);
                edtSDT.setText(nhanVien.soDienThoai);
                edtQueQuan.setText(nhanVien.queQuan);
                edtNgaySinh.setText(nhanVien.NgaySinh);
                edtGmail.setText(nhanVien.Gmail);
                edtCMND.setText(nhanVien.CMND);
                edtTenDangNhap.setText(nhanVien.tenDangNhap);
                edtPassword.setText(nhanVien.matKhau);

                kiemLoaiNhanVien(nhanVien);
                EnabelButtonFalse();
                hienThiLaiDanhSach();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDuLieuNhapVao() == true) {
                    if (spLoaiNhanVien.getSelectedItemPosition() != 0) {
                        themNhanVienMoi();
                    } else {
                        Toast.makeText(MainActivity_QuanTriNhanVien.this, "Loại nhân viên chưa được chọn", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    xoaNhanVien();
                } else {
                    Toast.makeText(MainActivity_QuanTriNhanVien.this, "Chọn để xóa", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != -1) {
                    if (spLoaiNhanVien.getSelectedItemPosition() != 0) {
                        suaNhanVien();
                    } else {
                        Toast.makeText(MainActivity_QuanTriNhanVien.this, "Loại nhân viên chưa được chọn", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity_QuanTriNhanVien.this, "Chọn để sua", Toast.LENGTH_SHORT).show();
                }

            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timNhanVien();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void clearEditText() {
        edtTenNV.setText("");
        edtSDT.setText("");
        edtQueQuan.setText("");
        edtNgaySinh.setText("");
        edtGmail.setText("");
        edtCMND.setText("");
        edtTenDangNhap.setText("");
        edtPassword.setText("");
        spLoaiNhanVien.setSelection(0);
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


    private boolean kiemTraDuLieuNhapVao() {
        if (!edtTenNV.getText().toString().trim().equals("")) {
            if (!edtSDT.getText().toString().trim().equals("")) {
                if (!edtQueQuan.getText().toString().trim().equals("")) {
                    if (!edtNgaySinh.getText().toString().equals(null)) {
                        if (!edtGmail.getText().toString().trim().equals("")) {
                            if (!edtCMND.getText().toString().trim().equals("")) {
                                if (!edtTenDangNhap.getText().toString().trim().equals("")) {
                                    if (!edtPassword.getText().toString().trim().equals("")) {

                                    } else {
                                        edtPassword.setError("Password đang để trống ! ");
                                        return false;
                                    }
                                } else {
                                    edtTenDangNhap.setError("Tên đăng nhập đang để trống ! ");
                                    return false;
                                }
                            } else {
                                edtCMND.setError("CMND đang để trống ! ");
                                return false;
                            }
                        } else {
                            edtGmail.setError("Gmail đang để trống ! ");
                            return false;
                        }
                    } else {
                        edtNgaySinh.setError("Ngày sinh đang để trống ! ");
                        return false;
                    }
                } else {
                    edtQueQuan.setError("Quê quán đang để trống ! ");
                    return false;
                }
            } else {
                edtSDT.setError("Số điện thoại đang để trống ! ");
                return false;
            }
        } else {
            edtTenNV.setError("Tên nhân viên đang để trống ! ");
            return false;
        }
        return true;
    }

    private void kiemLoaiNhanVien(NhanVien nhanVien) {
        if (nhanVien.loaiNhanVien.trim().equals("Shipper")) {
            spLoaiNhanVien.setSelection(1);
        }
        if (nhanVien.loaiNhanVien.trim().equals("Bán hàng")) {
            spLoaiNhanVien.setSelection(2);
        }
        if (nhanVien.loaiNhanVien.trim().equals("Thủ kho")) {
            spLoaiNhanVien.setSelection(3);
        }
    }

    private void KhoiTao() {
        getLoaiNhanVien();
        getNhanVien();
//        data_NhanVien.add(new NhanVien("1", "My Kieu Oanh", "098989889", "HaNoi", "2023-24-1", "aaaaa@gmail.com", "Shipper", "09898989890", "myKieuaaaaa", "123456789", "" + R.drawable.anhsp_quantri));
//        data_NhanVien.add(new NhanVien("2", "My  Oanh", "098989889", "HaNoi", "2023-24-1", "aaaaa@gmail.com", "Bán hàng", "09898989890", "myKieuaaaaa", "123456789", "" + R.drawable.anhsp_quantri));
//        data_NhanVien.add(new NhanVien("3", "My Kieu", "098989889", "HaNoi", "2023-24-1", "aaaaa@gmail.com", "Thủ kho", "09898989890", "myKieuaaaaa", "123456789", "" + R.drawable.anhsp_quantri));
//        data_NhanVien.add(new NhanVien("4", "My Oanh Oanh", "098989889", "HaNoi", "2023-24-1", "aaaaa@gmail.com", "Shipper", "09898989890", "myKieuaaaaa", "123456789", "" + R.drawable.anhsp_quantri));

        data_loaiNhanVien.add("Bank");
//        data_loaiNhanVien.add("Bán hàng");
//        data_loaiNhanVien.add("Thủ kho");

    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewDanhSachNhanVien);
        spLoaiNhanVien = findViewById(R.id.spLoaiNhanVien);

        edtTenNV = findViewById(R.id.edtTenNV);
        edtSDT = findViewById(R.id.edtSDT);
        edtQueQuan = findViewById(R.id.edtQueQuan);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtGmail = findViewById(R.id.edtGmail);
        edtCMND = findViewById(R.id.edtCMND);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtPassword = findViewById(R.id.edtMatKhau);

        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

        edtTimKiem = findViewById(R.id.edtTimKiem);


    }

    String host = "192.168.137.33:80";

    private void getLoaiNhanVien() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/getLoaiNhanVien.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        String loaiNhanVien = jsonObject.getString("LoaiNhanVien");
                        data_loaiNhanVien.add(loaiNhanVien);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void getNhanVien() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/getNhanVien.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        String id_NhanVien = jsonObject.getString("id_NhanVien");
                        String tenNhanVien = jsonObject.getString("tenNhanVien");
                        String sDT = jsonObject.getString("Sdt");
                        String queQuan = jsonObject.getString("QueQuan");
                        String ngaySinh = jsonObject.getString("NgaySinh");
                        String gmail = jsonObject.getString("Email");
                        String cMND = jsonObject.getString("Cmnd");
                        String loaiNhanVien = jsonObject.getString("LoaiNhanVien");
                        String id_Username = jsonObject.getString("id_Username");
                        String hinh = jsonObject.getString("Hinh");
                        String password = jsonObject.getString("Password");

                        NhanVien nhanVien = new NhanVien(id_NhanVien, tenNhanVien, sDT, queQuan, ngaySinh, gmail, loaiNhanVien, cMND, id_Username, password, hinh);
                        data_NhanVien.add(nhanVien);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void themTaiKhoan() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/themTaiKhoanNhanVien.php?" +
                "id_Username=" + edtTenDangNhap.getText().toString().replace(" ", "+") + "" +
                "&Password=" + edtPassword.getText().toString().replace(" ", "+") + "" +
                "&Quyen=" + spLoaiNhanVien.getSelectedItemPosition() + "";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, response, Toast.LENGTH_SHORT).show();
                hienThiLaiDanhSach();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void themNhanVienMoi() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/themNhanVien.php?" +
                "tenNhanVien=" + edtTenDangNhap.getText().toString().replace(" ", "+") + "" +
                "&Sdt=" + edtSDT.getText().toString().replace(" ", "+") + "" +
                "&QueQuan=" + edtQueQuan.getText().toString().replace(" ", "+") + "" +
                "&NgaySinh=" + edtNgaySinh.getText().toString().replace(" ", "+") + "" +
                "&Email=" + edtGmail.getText().toString().replace(" ", "+") + "" +
                "&id_loaiNV=" + spLoaiNhanVien.getSelectedItemPosition() + "" +
                "&Cmnd=" + edtCMND.getText().toString().replace(" ", "+") + "" +
                "&id_Username=" + edtTenDangNhap.getText().toString().replace(" ", "+") + "" +
                "&Hinh=0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, response, Toast.LENGTH_SHORT).show();
                themTaiKhoan();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void xoaNhanVien() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/xoaNhanVien.php?" +
                "id_NhanVien=" + data_NhanVien.get(index).maNhanVien.trim().replace(" ", "+") + "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, response, Toast.LENGTH_SHORT).show();
                xoaTaiKhoan();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void xoaTaiKhoan() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/xoaTaiKhoanNhanVien.php?" +
                "id_Username=" + data_NhanVien.get(index).tenDangNhap.trim().replace(" ", "+") + "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, response, Toast.LENGTH_SHORT).show();
                EnabelButtonTrue();
                clearEditText();
                hienThiLaiDanhSach();
                hienThiLaiDanhSach();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void hienThiLaiDanhSach() {
        data_NhanVien.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/getNhanVien.php";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                data_NhanVien.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id_NhanVien = jsonObject.getString("id_NhanVien");
                        String tenNhanVien = jsonObject.getString("tenNhanVien");
                        String sDT = jsonObject.getString("Sdt");
                        String queQuan = jsonObject.getString("QueQuan");
                        String ngaySinh = jsonObject.getString("NgaySinh");
                        String gmail = jsonObject.getString("Email");
                        String cMND = jsonObject.getString("Cmnd");
                        String loaiNhanVien = jsonObject.getString("LoaiNhanVien");
                        String id_Username = jsonObject.getString("id_Username");
                        String hinh = jsonObject.getString("Hinh");
                        String password = jsonObject.getString("Password");

                        NhanVien nhanVien = new NhanVien(id_NhanVien, tenNhanVien, sDT, queQuan, ngaySinh, gmail, loaiNhanVien, cMND, id_Username, password, hinh);
                        data_NhanVien.add(nhanVien);
                        recyclerView.getAdapter().notifyDataSetChanged();


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void timNhanVien() {
        data_NhanVien.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/timNhanVien.php?tenNhanVien="+edtTimKiem.getText().toString().trim().replace(" ","+")+"";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                data_NhanVien.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id_NhanVien = jsonObject.getString("id_NhanVien");
                        String tenNhanVien = jsonObject.getString("tenNhanVien");
                        String sDT = jsonObject.getString("Sdt");
                        String queQuan = jsonObject.getString("QueQuan");
                        String ngaySinh = jsonObject.getString("NgaySinh");
                        String gmail = jsonObject.getString("Email");
                        String cMND = jsonObject.getString("Cmnd");
                        String loaiNhanVien = jsonObject.getString("LoaiNhanVien");
                        String id_Username = jsonObject.getString("id_Username");
                        String hinh = jsonObject.getString("Hinh");
                        String password = jsonObject.getString("Password");

                        NhanVien nhanVien = new NhanVien(id_NhanVien, tenNhanVien, sDT, queQuan, ngaySinh, gmail, loaiNhanVien, cMND, id_Username, password, hinh);
                        data_NhanVien.add(nhanVien);
                        recyclerView.getAdapter().notifyDataSetChanged();


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onErrorResponse: ", error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void suaNhanVien() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/suaNhanVien.php?" +
                "id_NhanVien=" + data_NhanVien.get(index).maNhanVien.trim().replace(" ", "+") + "" +
                "&tenNhanVien=" + edtTenNV.getText().toString().trim().replace(" ", "+") + "" +
                "&Sdt=" + edtSDT.getText().toString().trim().replace(" ", "+") + "" +
                "&QueQuan=" + edtQueQuan.getText().toString().trim().replace(" ", "+") + "" +
                "&NgaySinh=" + edtNgaySinh.getText().toString().trim().replace(" ", "+") + "" +
                "&Email=" + edtGmail.getText().toString().trim().replace(" ", "+") + "" +
                "&id_loaiNV=" + spLoaiNhanVien.getSelectedItemPosition() + "" +
                "&Cmnd=" + edtCMND.getText().toString().trim().replace(" ", "+") + "" +
                "&id_Username=" + edtTenDangNhap.getText().toString().trim().replace(" ", "+") + "" +
                "&Hinh=0";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, response, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity_QuanTriNhanVien.this, edtTenDangNhap.getText().toString(), Toast.LENGTH_SHORT).show();

                suaTaiKhoan();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void suaTaiKhoan() {

//        Date currentTime = Calendar.getInstance().getTime();
//        String dateNow = currentTime.getYear() + "-" + currentTime.getMonth() + "-" + currentTime.getDate();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://" + host + "/Nhom6/suaTaiKhoanNhanVien.php?id_Username="+data_NhanVien.get(index).tenDangNhap.trim().replace(" ","+")+"" +
                "&id_UsernameMoi=" + edtTenDangNhap.getText().toString().trim() + "" +
                "&Password="+edtPassword.getText().toString().trim().replace(" ","+")+"&" +
                "Quyen="+spLoaiNhanVien.getSelectedItemPosition()+"";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity_QuanTriNhanVien.this, response, Toast.LENGTH_SHORT).show();
                EnabelButtonTrue();
                clearEditText();
                hienThiLaiDanhSach();
                hienThiLaiDanhSach();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}