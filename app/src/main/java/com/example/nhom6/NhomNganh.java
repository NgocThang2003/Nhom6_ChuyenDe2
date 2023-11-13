package com.example.nhom6;

public class NhomNganh {
    String maNhomNganh, tenNhomNganh;

    public String getMaNhomNganh() {
        return maNhomNganh;
    }

    @Override
    public String toString() {
        return "NhomNganh{" +
                "maNhomNganh='" + maNhomNganh + '\'' +
                ", tenNhomNganh='" + tenNhomNganh + '\'' +
                '}';
    }

    public void setMaNhomNganh(String maNhomNganh) {
        this.maNhomNganh = maNhomNganh;
    }

    public String getTenNhomNganh() {
        return tenNhomNganh;
    }

    public void setTenNhomNganh(String tenNhomNganh) {
        this.tenNhomNganh = tenNhomNganh;
    }

    public NhomNganh() {
    }

    public NhomNganh(String maNhomNganh, String tenNhomNganh) {
        this.maNhomNganh = maNhomNganh;
        this.tenNhomNganh = tenNhomNganh;
    }
}
