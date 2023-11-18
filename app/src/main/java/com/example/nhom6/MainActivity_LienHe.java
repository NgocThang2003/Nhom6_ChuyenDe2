package com.example.nhom6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity_LienHe extends AppCompatActivity {
    EditText edtID, edtHoTen, edtSDT, edtEmail, edtCongTy, edtGopY;
    Button btnGuiThongTin, btnQuayLai;
    FirebaseDatabase database;
    DatabaseReference data_LienHe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lienhevoichungtoi);
        setControl();
        setEvent();
    }

    private void setEvent() {
        database = FirebaseDatabase.getInstance();
        data_LienHe = database.getReference("LienHe");
        btnGuiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraDuLieu() == true) {
                    LienHe lienHe = new LienHe();
                    lienHe.setId(data_LienHe.push().getKey());
                    lienHe.setTen(edtHoTen.getText().toString());
                    lienHe.setSdt(edtSDT.getText().toString());
                    lienHe.setEmail(edtEmail.getText().toString());
                    lienHe.setCongTy(edtCongTy.getText().toString());
                    lienHe.setGopY(edtGopY.getText().toString());

                    //lấy ngày hiện tại
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String currentDateandTime = sdf.format(new Date());
                    lienHe.setDate(currentDateandTime);

                    data_LienHe.child(lienHe.id).setValue(lienHe);
                    Toast.makeText(MainActivity_LienHe.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private boolean kiemTraDuLieu() {
        if (edtHoTen.getText().toString().trim().equals("")) {
            edtHoTen.setError("Không được để trống !");
            return false;
        } else if (edtSDT.getText().toString().trim().equals("")) {
            edtSDT.setError("Không được để trống !");
            return false;
        } else if (edtGopY.getText().toString().trim().equals("")) {
            edtGopY.setError("Không được để trống !");
            return false;
        }
        return true;
    }

    private void setControl() {
        edtHoTen = findViewById(R.id.edtHoTenLienHe);
        edtSDT = findViewById(R.id.edtSDTLienHe);
        edtEmail = findViewById(R.id.edtEmailLienHe);
        edtCongTy = findViewById(R.id.edtCongTy);
        edtGopY = findViewById(R.id.edtGopY);
        btnGuiThongTin = findViewById(R.id.btnGuiThongTin);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        edtID = findViewById(R.id.edtID);


    }
}
