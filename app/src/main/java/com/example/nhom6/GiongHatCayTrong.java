package com.example.nhom6;

public class GiongHatCayTrong {


    public GiongHatCayTrong(String tenSP1, String tenSP2, String giaSP1, String giaSP2, int hinh1, int hinh2) {
        this.tenSP1 = tenSP1;
        this.tenSP2 = tenSP2;
        this.giaSP1 = giaSP1;
        this.giaSP2 = giaSP2;
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

    public String getGiaSP1() {
        return giaSP1;
    }

    public void setGiaSP1(String giaSP1) {
        this.giaSP1 = giaSP1;
    }

    public String getGiaSP2() {
        return giaSP2;
    }

    public void setGiaSP2(String giaSP2) {
        this.giaSP2 = giaSP2;
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
        return "GiongHatCayTrong{" +
                "tenSP1='" + tenSP1 + '\'' +
                ", tenSP2='" + tenSP2 + '\'' +
                ", giaSP1='" + giaSP1 + '\'' +
                ", giaSP2='" + giaSP2 + '\'' +
                ", hinh1=" + hinh1 +
                ", hinh2=" + hinh2 +
                '}';
    }

    String tenSP1, tenSP2, giaSP1, giaSP2;
    int hinh1, hinh2;
}
