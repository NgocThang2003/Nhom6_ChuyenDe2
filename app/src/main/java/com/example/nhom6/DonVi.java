package com.example.nhom6;

public class DonVi {
    String maDonVi, tenDonVi;

    @Override
    public String toString() {
        return "DonVi{" +
                "maDonVi='" + maDonVi + '\'' +
                ", tenDonVi='" + tenDonVi + '\'' +
                '}';
    }

    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public DonVi() {
    }

    public DonVi(String maDonVi, String tenDonVi) {
        this.maDonVi = maDonVi;
        this.tenDonVi = tenDonVi;
    }
}
