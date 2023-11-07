package com.example.nhom6;

public class SanPham {
    String maSanPham, tenSP, chuThich, donVi, loaiSP, moTa, hinh;
    String soLuong, khoiLuong, gia;

    @Override
    public String toString() {
        return "SanPham{" +
                "maSanPham='" + maSanPham + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", chuThich='" + chuThich + '\'' +
                ", donVi='" + donVi + '\'' +
                ", loaiSP='" + loaiSP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", hinh='" + hinh + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", khoiLuong='" + khoiLuong + '\'' +
                ", gia='" + gia + '\'' +
                '}';
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSP, String chuThich, String donVi, String loaiSP, String moTa, String hinh, String soLuong, String khoiLuong, String gia) {
        this.maSanPham = maSanPham;
        this.tenSP = tenSP;
        this.chuThich = chuThich;
        this.donVi = donVi;
        this.loaiSP = loaiSP;
        this.moTa = moTa;
        this.hinh = hinh;
        this.soLuong = soLuong;
        this.khoiLuong = khoiLuong;
        this.gia = gia;
    }
}
