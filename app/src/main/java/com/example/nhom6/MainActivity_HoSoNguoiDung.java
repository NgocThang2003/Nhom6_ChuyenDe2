package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_HoSoNguoiDung extends AppCompatActivity {

    EditText edtTen, edtSDT, edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosonguoidung);

        setControl();
        setEvent();

    }

    private void setEvent() {
        getDataList();
    }

    private void setControl() {
        edtTen = findViewById(R.id.edtTen);
        edtSDT = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);

    }

    public void chucnang(View view) {

        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_HoSoNguoiDung.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void getDataList() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.131:8088/API/getDataTaiKhoan.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

//                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(0);

                       edtTen.setText(jsonObject.getString("Ten"));
                       edtEmail.setText( jsonObject.getString("Email"));
                       edtSDT.setText( jsonObject.getString("Sdt"));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}