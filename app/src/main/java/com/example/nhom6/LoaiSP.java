package com.example.nhom6;

public class LoaiSP {
    String maLoaiSP, tenLoaiSP;

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    @Override
    public String toString() {
        return "LoaiSP{" +
                "maLoaiSP='" + maLoaiSP + '\'' +
                ", tenLoaiSP='" + tenLoaiSP + '\'' +
                '}';
    }

    public LoaiSP() {
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public LoaiSP(String maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }
}
