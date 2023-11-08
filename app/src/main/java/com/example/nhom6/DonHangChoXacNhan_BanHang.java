package com.example.nhom6;

public class DonHangChoXacNhan_BanHang {
    String tenKH, hinh, diaChi, tenSP, moTa, sdt, gia, soLuong, ngay, trangThai, thanhTien;

    public DonHangChoXacNhan_BanHang() {
    }

    public DonHangChoXacNhan_BanHang(String tenKH, String hinh, String diaChi, String tenSP, String moTa, String sdt, String gia, String soLuong, String ngay, String trangThai, String thanhTien) {
        this.tenKH = tenKH;
        this.hinh = hinh;
        this.diaChi = diaChi;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.sdt = sdt;
        this.gia = gia;
        this.soLuong = soLuong;
        this.ngay = ngay;
        this.trangThai = trangThai;
        this.thanhTien = thanhTien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "DonHangChoXacNhan_BanHang{" +
                "tenKH='" + tenKH + '\'' +
                ", hinh='" + hinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", sdt='" + sdt + '\'' +
                ", gia='" + gia + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", ngay='" + ngay + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", thanhTien='" + thanhTien + '\'' +
                '}';
    }
}
