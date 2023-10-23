package com.example.nhom6;

public class SanPham {
    String tenSP, chuThich, donVi, loaiSP, moTa, hinh;
    int soLuong, khoiLuong, gia;

    public SanPham(String tenSP, String chuThich, String donVi, String loaiSP, String moTa, String hinh, int soLuong, int khoiLuong, int gia) {
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

    @Override
    public String toString() {
        return "SanPham{" +
                "tenSP='" + tenSP + '\'' +
                ", chuThich='" + chuThich + '\'' +
                ", donVi='" + donVi + '\'' +
                ", loaiSP='" + loaiSP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", hinh='" + hinh + '\'' +
                ", soLuong=" + soLuong +
                ", khoiLuong=" + khoiLuong +
                ", gia=" + gia +
                '}';
    }
}
