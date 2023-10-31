package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_HoSoNguoiDung extends AppCompatActivity {

    EditText edtTen, edtSDT, edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosonguoidung);
    }

    public void chucnang(View view) {

        if(view.getId()==R.id.imgQuayVe){
            Intent intent = new Intent(MainActivity_HoSoNguoiDung.this,MainActivity_TaiKhoan.class);
            startActivity(intent);
        }
    }

}