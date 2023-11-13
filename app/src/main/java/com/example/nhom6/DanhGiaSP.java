package com.example.nhom6;

public class DanhGiaSP {
    String tenSP,moTa,gia,soLuong,hinh;

    public DanhGiaSP(String tenSP, String moTa, String gia, String soLuong, String hinh) {
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinh = hinh;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getGia() {
        return gia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    @Override
    public String toString() {
        return "DanhGiaSP{" +
                "tenSP='" + tenSP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", gia='" + gia + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", hinh='" + hinh + '\'' +
                '}';
    }
}
