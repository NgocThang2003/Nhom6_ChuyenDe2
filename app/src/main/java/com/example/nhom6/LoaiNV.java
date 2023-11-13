package com.example.nhom6;

public class LoaiNV {
    String maNhanVien,loaiNhanVien;

    public LoaiNV(String maNhanVien, String loaiNhanVien) {
        this.maNhanVien = maNhanVien;
        this.loaiNhanVien = loaiNhanVien;
    }

    public LoaiNV() {
    }

    @Override
    public String toString() {
        return "LoaiNV{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", loaiNhanVien='" + loaiNhanVien + '\'' +
                '}';
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }
}
