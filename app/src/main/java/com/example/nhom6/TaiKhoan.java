package com.example.nhom6;

public class TaiKhoan {
    Integer quyen;
    String maNguoiDung, hinh,diaChi;
    String Email, password, hoten, sdt;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer quyen, String maNguoiDung, String hinh, String diaChi, String email, String password, String hoten, String sdt) {
        this.quyen = quyen;
        this.maNguoiDung = maNguoiDung;
        this.hinh = hinh;
        this.diaChi = diaChi;
        Email = email;
        this.password = password;
        this.hoten = hoten;
        this.sdt = sdt;
    }

    public Integer getQuyen() {
        return quyen;
    }

    public void setQuyen(Integer quyen) {
        this.quyen = quyen;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
