package com.example.nhom6;

public class DonHang {

    //gia, moTa ,sDT
    String  hinh, maDonHang, maKhachHang, tenKhachHang, soLuong, diaChi, trangThai, maSanPham, tenSanPham, maShipper, tenShipper, phuongThucThanhToan, thongTinVanChuyen, nhanVienDuyetHang, ngay, lyDoHuyDon, gia, moTa ,sDT;




    public DonHang() {
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

    public String getMaShipper() {
        return maShipper;
    }

    public void setMaShipper(String maShipper) {
        this.maShipper = maShipper;
    }

    public String getTenShipper() {
        return tenShipper;
    }

    public void setTenShipper(String tenShipper) {
        this.tenShipper = tenShipper;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getThongTinVanChuyen() {
        return thongTinVanChuyen;
    }

    public void setThongTinVanChuyen(String thongTinVanChuyen) {
        this.thongTinVanChuyen = thongTinVanChuyen;
    }

    public String getNhanVienDuyetHang() {
        return nhanVienDuyetHang;
    }

    public void setNhanVienDuyetHang(String nhanVienDuyetHang) {
        this.nhanVienDuyetHang = nhanVienDuyetHang;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLyDoHuyDon() {
        return lyDoHuyDon;
    }

    public void setLyDoHuyDon(String lyDoHuyDon) {
        this.lyDoHuyDon = lyDoHuyDon;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public DonHang(String hinh, String maDonHang, String maKhachHang, String tenKhachHang, String soLuong, String diaChi, String trangThai, String maSanPham, String tenSanPham, String maShipper, String tenShipper, String phuongThucThanhToan, String thongTinVanChuyen, String nhanVienDuyetHang, String ngay, String lyDoHuyDon) {
        this.hinh = hinh;
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soLuong = soLuong;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maShipper = maShipper;
        this.tenShipper = tenShipper;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.thongTinVanChuyen = thongTinVanChuyen;
        this.nhanVienDuyetHang = nhanVienDuyetHang;
        this.ngay = ngay;
        this.lyDoHuyDon = lyDoHuyDon;

    }

    @Override
    public String toString() {
        return "DonHang{" +
                "hinh='" + hinh + '\'' +
                ", maDonHang='" + maDonHang + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", maShipper='" + maShipper + '\'' +
                ", tenShipper='" + tenShipper + '\'' +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", thongTinVanChuyen='" + thongTinVanChuyen + '\'' +
                ", nhanVienDuyetHang='" + nhanVienDuyetHang + '\'' +
                ", ngay='" + ngay + '\'' +
                ", lyDoHuyDon='" + lyDoHuyDon + '\'' +
                '}';
    }

}
