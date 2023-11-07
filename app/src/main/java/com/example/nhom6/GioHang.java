package com.example.nhom6;

public class GioHang {
    String maGioHang, maKhachHang, maSanPham, tenSanPham, moTa,gia,khoiLuong, soLuong,donVi,hinh ;

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
                ", tenSanPham='" + tenSanPham + '\'' +
                ", moTa='" + moTa + '\'' +
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    public GioHang(String maGioHang, String tenSanPham, String moTa, String gia, String khoiLuong, String soLuong, String donVi, String hinh) {
        this.maGioHang = maGioHang;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.gia = gia;
        this.khoiLuong = khoiLuong;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.hinh = hinh;
    }
}
