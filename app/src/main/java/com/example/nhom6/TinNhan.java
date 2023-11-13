package com.example.nhom6;

public class TinNhan {
    String maTinNhan, maNhanVien, hoTenNhanVien, maKhachHang, hoTenKhachHang, tinNhan, ngay, daDoc, hinhNhanVien, hinhKhachHang;

    @Override
    public String toString() {
        return "TinNhan{" +
                "maTinNhan='" + maTinNhan + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", hoTenNhanVien='" + hoTenNhanVien + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                ", hoTenKhachHang='" + hoTenKhachHang + '\'' +
                ", tinNhan='" + tinNhan + '\'' +
                ", ngay='" + ngay + '\'' +
                ", daDoc='" + daDoc + '\'' +
                ", hinhNhanVien='" + hinhNhanVien + '\'' +
                ", hinhKhachHang='" + hinhKhachHang + '\'' +
                '}';
    }

    public String getMaTinNhan() {
        return maTinNhan;
    }

    public void setMaTinNhan(String maTinNhan) {
        this.maTinNhan = maTinNhan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKhachHang() {
        return hoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public String getTinNhan() {
        return tinNhan;
    }

    public void setTinNhan(String tinNhan) {
        this.tinNhan = tinNhan;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getDaDoc() {
        return daDoc;
    }

    public void setDaDoc(String daDoc) {
        this.daDoc = daDoc;
    }

    public String getHinhNhanVien() {
        return hinhNhanVien;
    }

    public void setHinhNhanVien(String hinhNhanVien) {
        this.hinhNhanVien = hinhNhanVien;
    }

    public String getHinhKhachHang() {
        return hinhKhachHang;
    }

    public void setHinhKhachHang(String hinhKhachHang) {
        this.hinhKhachHang = hinhKhachHang;
    }

    public TinNhan() {
    }

    public TinNhan(String maTinNhan, String maNhanVien, String hoTenNhanVien, String maKhachHang, String hoTenKhachHang, String tinNhan, String ngay, String daDoc, String hinhNhanVien, String hinhKhachHang) {
        this.maTinNhan = maTinNhan;
        this.maNhanVien = maNhanVien;
        this.hoTenNhanVien = hoTenNhanVien;
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
        this.tinNhan = tinNhan;
        this.ngay = ngay;
        this.daDoc = daDoc;
        this.hinhNhanVien = hinhNhanVien;
        this.hinhKhachHang = hinhKhachHang;
    }
}
