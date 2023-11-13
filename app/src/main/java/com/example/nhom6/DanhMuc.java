package com.example.nhom6;

public class DanhMuc {
    String tenDangMuc1, dangChon1, tenDangMuc2, dangChon2, tenDangMuc3, dangChon3;

    public DanhMuc(String tenDangMuc1, String dangChon1, String tenDangMuc2, String dangChon2, String tenDangMuc3, String dangChon3) {
        this.tenDangMuc1 = tenDangMuc1;
        this.dangChon1 = dangChon1;
        this.tenDangMuc2 = tenDangMuc2;
        this.dangChon2 = dangChon2;
        this.tenDangMuc3 = tenDangMuc3;
        this.dangChon3 = dangChon3;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
                "tenDangMuc1='" + tenDangMuc1 + '\'' +
                ", dangChon1='" + dangChon1 + '\'' +
                ", tenDangMuc2='" + tenDangMuc2 + '\'' +
                ", dangChon2='" + dangChon2 + '\'' +
                ", tenDangMuc3='" + tenDangMuc3 + '\'' +
                ", dangChon3='" + dangChon3 + '\'' +
                '}';
    }

    public String getTenDangMuc1() {
        return tenDangMuc1;
    }

    public void setTenDangMuc1(String tenDangMuc1) {
        this.tenDangMuc1 = tenDangMuc1;
    }

    public String getDangChon1() {
        return dangChon1;
    }

    public void setDangChon1(String dangChon1) {
        this.dangChon1 = dangChon1;
    }

    public String getTenDangMuc2() {
        return tenDangMuc2;
    }

    public void setTenDangMuc2(String tenDangMuc2) {
        this.tenDangMuc2 = tenDangMuc2;
    }

    public String getDangChon2() {
        return dangChon2;
    }

    public void setDangChon2(String dangChon2) {
        this.dangChon2 = dangChon2;
    }

    public String getTenDangMuc3() {
        return tenDangMuc3;
    }

    public void setTenDangMuc3(String tenDangMuc3) {
        this.tenDangMuc3 = tenDangMuc3;
    }

    public String getDangChon3() {
        return dangChon3;
    }

    public void setDangChon3(String dangChon3) {
        this.dangChon3 = dangChon3;
    }
}
