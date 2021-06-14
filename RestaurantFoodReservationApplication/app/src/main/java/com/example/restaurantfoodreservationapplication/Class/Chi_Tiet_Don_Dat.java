package com.example.restaurantfoodreservationapplication.Class;

public class Chi_Tiet_Don_Dat {
    private double DonGia;
    private String MaBan;
    private int SoLuong;
    private String TenMon;

    public Chi_Tiet_Don_Dat() { }
    public Chi_Tiet_Don_Dat(double donGia, String maBan, int soLuong, String tenMon) {
        DonGia = donGia;
        MaBan = maBan;
        SoLuong = soLuong;
        TenMon = tenMon;
    }


    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String maBan) {
        MaBan = maBan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }
}
