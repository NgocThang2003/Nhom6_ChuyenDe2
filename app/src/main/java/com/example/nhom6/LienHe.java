package com.example.nhom6;

import androidx.appcompat.app.AppCompatActivity;

public class LienHe {
    String id,ten,sdt,email,congTy,gopY;

    public LienHe() {
    }

    public LienHe(String id, String ten, String sdt, String email, String congTy, String gopY) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.congTy = congTy;
        this.gopY = gopY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCongTy() {
        return congTy;
    }

    public void setCongTy(String congTy) {
        this.congTy = congTy;
    }

    public String getGopY() {
        return gopY;
    }

    public void setGopY(String gopY) {
        this.gopY = gopY;
    }
}
