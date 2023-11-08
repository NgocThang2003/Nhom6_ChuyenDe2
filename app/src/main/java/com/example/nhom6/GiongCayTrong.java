package com.example.nhom6;

public class GiongCayTrong {
    String hinh1, hinh2, maSP1, maSP2, tenSP1, tenSP2, gia1, gia2;

    public GiongCayTrong() {
        this.hinh1 = "";
        this.hinh2 = "";
        this.maSP1 = "maSP1";
        this.maSP2 = "maSP2";
        this.tenSP1 = "tenSP1";
        this.tenSP2 = "tenSP2";
        this.gia1 = "gia1";
        this.gia2 = "gia2";
    }

    public GiongCayTrong(String hinh1, String hinh2, String maSP1, String maSP2, String tenSP1, String tenSP2, String gia1, String gia2) {
        this.hinh1 = hinh1;
        this.hinh2 = hinh2;
        this.maSP1 = maSP1;
        this.maSP2 = maSP2;
        this.tenSP1 = tenSP1;
        this.tenSP2 = tenSP2;
        this.gia1 = gia1;
        this.gia2 = gia2;
    }

    public String getHinh1() {
        return hinh1;
    }

    public void setHinh1(String hinh1) {
        this.hinh1 = hinh1;
    }

    public String getHinh2() {
        return hinh2;
    }

    public void setHinh2(String hinh2) {
        this.hinh2 = hinh2;
    }

    public String getMaSP1() {
        return maSP1;
    }

    public void setMaSP1(String maSP1) {
        this.maSP1 = maSP1;
    }

    public String getMaSP2() {
        return maSP2;
    }

    public void setMaSP2(String maSP2) {
        this.maSP2 = maSP2;
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

    @Override
    public String toString() {
        return "GiongCayTrong{" +
                "hinh1='" + hinh1 + '\'' +
                ", hinh2='" + hinh2 + '\'' +
                ", maSP1='" + maSP1 + '\'' +
                ", maSP2='" + maSP2 + '\'' +
                ", tenSP1='" + tenSP1 + '\'' +
                ", tenSP2='" + tenSP2 + '\'' +
                ", gia1='" + gia1 + '\'' +
                ", gia2='" + gia2 + '\'' +
                '}';
    }
}
