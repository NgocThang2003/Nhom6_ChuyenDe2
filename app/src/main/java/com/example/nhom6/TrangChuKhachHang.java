package com.example.nhom6;

public class TrangChuKhachHang {
    String tenKyThuat, tenMoTa, nhomNganh;
    String maKyThuat, hinh;

    public TrangChuKhachHang() {
    }

    public TrangChuKhachHang(String maKyThuat, String tenKyThuat, String tenMoTa, String hinh, String nhomNganh) {
        this.tenKyThuat = tenKyThuat;
        this.tenMoTa = tenMoTa;
        this.hinh = hinh;
        this.nhomNganh = nhomNganh;
        this.maKyThuat = maKyThuat;
    }

    public String getNhomNganh() {
        return nhomNganh;
    }

    public void setNhomNganh(String nhomNganh) {
        this.nhomNganh = nhomNganh;
    }

    public String getMaKyThuat() {
        return maKyThuat;
    }

    public void setMaKyThuat(String maKyThuat) {
        this.maKyThuat = maKyThuat;
    }

    public String getTenKyThuat() {
        return tenKyThuat;
    }

    public void setTenKyThuat(String tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public String getTenMoTa() {
        return tenMoTa;
    }

    public void setTenMoTa(String tenMoTa) {
        this.tenMoTa = tenMoTa;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    @Override
    public String toString() {
        return "TrangChuKhachHang{" +
                "tenKyThuat='" + tenKyThuat + '\'' +
                ", tenMoTa='" + tenMoTa + '\'' +
                ", hinh=" + hinh +
                '}';
    }
}
