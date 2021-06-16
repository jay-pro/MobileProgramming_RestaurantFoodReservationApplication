package com.example.restaurantfoodreservationapplication.Class;

public class Nhan_Vien {
    private String ID;
    private String ChucVu;
    private String HoTen;
    private String GioiTinh;
    private String CMND;
    private String SDT;
    private String DiaChi;
    private double Luong;
    private String HinhAnh;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double luong) {
        Luong = luong;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public Nhan_Vien(String ID, String chucVu, String hoTen, String gioiTinh, String CMND, String SDT, String diaChi, double luong, String hinhAnh) {
        this.ID = ID;
        ChucVu = chucVu;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        this.CMND = CMND;
        this.SDT = SDT;
        DiaChi = diaChi;
        Luong = luong;
        HinhAnh = hinhAnh;
    }

    public Nhan_Vien() {
    }
}
