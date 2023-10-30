package com.example.nhom6;

public class ThuocChoCayTrong {
    String tenSP1 , tenSP2, moTa1, moTa2, gia1, gia2;
    int hinh1, hinh2;

    public ThuocChoCayTrong(String tenSP1, String tenSP2, String moTa1, String moTa2, String gia1, String gia2, int hinh1, int hinh2) {
        this.tenSP1 = tenSP1;
        this.tenSP2 = tenSP2;
        this.moTa1 = moTa1;
        this.moTa2 = moTa2;
        this.gia1 = gia1;
        this.gia2 = gia2;
        this.hinh1 = hinh1;
        this.hinh2 = hinh2;
    }

    public String getTenSP1() {
        return tenSP1;
    }

    public void setTenSP1(String tenSP1) {
        this.tenSP1 = tenSP1;
    }

    public String getTenSP2() {
        return tenSP2;
    }

    public void setTenSP2(String tenSP2) {
        this.tenSP2 = tenSP2;
    }

    public String getMoTa1() {
        return moTa1;
    }

    public void setMoTa1(String moTa1) {
        this.moTa1 = moTa1;
    }

    public String getMoTa2() {
        return moTa2;
    }

    public void setMoTa2(String moTa2) {
        this.moTa2 = moTa2;
    }

    public String getGia1() {
        return gia1;
    }

    public void setGia1(String gia1) {
        this.gia1 = gia1;
    }

    public String getGia2() {
        return gia2;
    }

    public void setGia2(String gia2) {
        this.gia2 = gia2;
    }

    public int getHinh1() {
        return hinh1;
    }

    public void setHinh1(int hinh1) {
        this.hinh1 = hinh1;
    }

    public int getHinh2() {
        return hinh2;
    }

    public void setHinh2(int hinh2) {
        this.hinh2 = hinh2;
    }

    @Override
    public String toString() {
        return "ThuocChoCayTrong{" +
                "tenSP1='" + tenSP1 + '\'' +
                ", tenSP2='" + tenSP2 + '\'' +
                ", moTa1='" + moTa1 + '\'' +
                ", moTa2='" + moTa2 + '\'' +
                ", gia1='" + gia1 + '\'' +
                ", gia2='" + gia2 + '\'' +
                ", hinh1=" + hinh1 +
                ", hinh2=" + hinh2 +
                '}';
    }
}
