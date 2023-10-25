package com.example.nhom6;

public class ThemDiaChiMoi {
    String diaChi, checkBox;

    public ThemDiaChiMoi(String diaChi, String checkBox) {
        this.diaChi = diaChi;
        this.checkBox = checkBox;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public String toString() {
        return "ThemDiaChiMoi{" +
                "diaChi='" + diaChi + '\'' +
                ", checkBox='" + checkBox + '\'' +
                '}';
    }
}
