package com.example.nhom6;

public class GioHang {
    String maGioHang, maKhachHang, maSanPham, tenSP, chuThich, gia, khoiLuong, soLuong, donVi, hinh, daChon;

    public String getDaChon() {
        return daChon;
    }

    public void setDaChon(String daChon) {
        this.daChon = daChon;
    }

    public GioHang(String maGioHang, String maKhachHang, String maSanPham, String tenSanPham, String moTa, String gia, String khoiLuong, String soLuong, String donVi, String hinh) {
        this.maGioHang = maGioHang;
        this.maKhachHang = maKhachHang;
        this.maSanPham = maSanPham;
        this.tenSP = tenSanPham;
        this.chuThich = moTa;
        this.gia = gia;
        this.khoiLuong = khoiLuong;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.hinh = hinh;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "maGioHang='" + maGioHang + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                ", maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSP + '\'' +
                ", moTa='" + chuThich + '\'' +
                ", gia='" + gia + '\'' +
                ", khoiLuong='" + khoiLuong + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", donVi='" + donVi + '\'' +
                ", hinh='" + hinh + '\'' +
                '}';
    }

    public String getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        this.maGioHang = maGioHang;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSanPham) {
        this.tenSP = tenSanPham;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public GioHang() {
    }


}
