package com.example.nhom6;

public class ThongKe {
    String maSanPham, tenSanPham, chuThich, gia, soLuong, daBan, hinh;

    @Override
    public String toString() {
        return "ThongKe{" +
                "maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", chuThich='" + chuThich + '\'' +
                ", gia='" + gia + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", daBan='" + daBan + '\'' +
                ", hinh='" + hinh + '\'' +
                '}';
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
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

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDaBan() {
        return daBan;
    }

    public void setDaBan(String daBan) {
        this.daBan = daBan;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public ThongKe(String maSanPham, String tenSanPham, String chuThich, String gia, String soLuong, String daBan, String hinh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.chuThich = chuThich;
        this.gia = gia;
        this.soLuong = soLuong;
        this.daBan = daBan;
        this.hinh = hinh;
    }
}
