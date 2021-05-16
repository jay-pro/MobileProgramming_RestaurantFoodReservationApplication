package com.example.restaurantfoodreservationapplication.Class;

public class Nhan_Vien {
    public String ID;
    public String MaCV;
    public String HoTen;
    public String GioiTinh;
    public String CMND;
    public String SDT;
    public String DiaChi;
    public double Luong;

    public Nhan_Vien(String ID, String maCV, String hoTen, String gioiTinh, String CMND, String SDT, String diaChi, double luong) {
        this.ID = ID;
        MaCV = maCV;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        this.CMND = CMND;
        this.SDT = SDT;
        DiaChi = diaChi;
        Luong = luong;
    }

    public Nhan_Vien() {
    }
}
