package com.example.restaurantfoodreservationapplication.Class;

public class Chi_Tiet_Don_Dat {
    private String MaBan;
    private String TenMon;
    private double DonGia;
    private int SoLuong;
    public Chi_Tiet_Don_Dat() { }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String maBan) {
        MaBan = maBan;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public Chi_Tiet_Don_Dat(String maBan, String tenMon, double donGia, int soLuong) {
        MaBan = maBan;
        TenMon = tenMon;
        DonGia = donGia;
        SoLuong = soLuong;
    }




/*  public String MaDonDat;
        public String MaMon;
        public int  SoLuong;

        public Chi_Tiet_Don_Dat(String maDonDat, String maMon, int soLuong) {
            MaDonDat = maDonDat;
            MaMon = maMon;
            SoLuong = soLuong;
        }

        public Chi_Tiet_Don_Dat() {
        }*/




}
