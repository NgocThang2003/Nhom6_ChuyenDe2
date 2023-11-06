package com.example.nhom6;

public class ThemDiaChiMoi {
    String id,ten, sdt, tinh, quan, phuong, soNha, check;

    public ThemDiaChiMoi() {
    }


    public ThemDiaChiMoi(String id, String ten, String sdt, String tinh, String quan, String phuong, String soNha, String check) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.tinh = tinh;
        this.quan = quan;
        this.phuong = phuong;
        this.soNha = soNha;
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "ThemDiaChiMoi{" +
                "id='" + id + '\'' +
                ", ten='" + ten + '\'' +
                ", sdt='" + sdt + '\'' +
                ", tinh='" + tinh + '\'' +
                ", quan='" + quan + '\'' +
                ", phuong='" + phuong + '\'' +
                ", soNha='" + soNha + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}
