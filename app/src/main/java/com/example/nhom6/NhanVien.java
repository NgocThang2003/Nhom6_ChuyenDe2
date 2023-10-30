package com.example.nhom6;
public class NhanVien {
    String maNhanVien;
            String tenNhanVien, soDienThoai, queQuan, NgaySinh, Gmail, loaiNhanVien, CMND, tenDangNhap, matKhau, hinh;

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
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

    public NhanVien(String maNhanVien, String tenNhanVien, String soDienThoai, String queQuan, String ngaySinh, String gmail, String loaiNhanVien, String CMND, String tenDangNhap, String matKhau, String hinh) {
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
}
