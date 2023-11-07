package com.example.nhom6;
public class NhanVien {
    String quyen;

    public NhanVien(String quyen, String maNhanVien, String tenNhanVien, String soDienThoai, String queQuan, String ngaySinh, String gmail, String loaiNhanVien, String CMND, String tenDangNhap, String matKhau, String hinh) {
        this.quyen = quyen;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.queQuan = queQuan;
        NgaySinh = ngaySinh;
        Gmail = gmail;
        this.loaiNhanVien = loaiNhanVien;
        this.CMND = CMND;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hinh = hinh;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "quyen='" + quyen + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", NgaySinh='" + NgaySinh + '\'' +
                ", Gmail='" + Gmail + '\'' +
                ", loaiNhanVien='" + loaiNhanVien + '\'' +
                ", CMND='" + CMND + '\'' +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hinh='" + hinh + '\'' +
                '}';
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public NhanVien() {
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    String maNhanVien;
    String tenNhanVien;
    String soDienThoai;
    String queQuan;
    String NgaySinh;
    String Gmail;
    String loaiNhanVien;
    String CMND;
    String tenDangNhap;
    String matKhau;
    String hinh;


}
