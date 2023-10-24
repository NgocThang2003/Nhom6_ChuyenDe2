package com.example.nhom6;

public class KyThuatGieoHat {
    String tenKyThuat, moTa;

    public KyThuatGieoHat(String tenKyThuat, String moTa) {
        this.tenKyThuat = tenKyThuat;
        this.moTa = moTa;
    }

    public String getTenKyThuat() {
        return tenKyThuat;
    }

    public void setTenKyThuat(String tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "KyThuatGieoHat{" +
                "tenKyThuat='" + tenKyThuat + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
