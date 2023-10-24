package com.example.nhom6;

public class TrangChuKhachHang {
    String tenKyThuat, tenMoTa;
    int  hinh;

    public TrangChuKhachHang(String tenKyThuat, String tenMoTa, int hinh) {
        this.tenKyThuat = tenKyThuat;
        this.tenMoTa = tenMoTa;
        this.hinh = hinh;
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

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
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
