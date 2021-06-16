package com.example.restaurantfoodreservationapplication.Class;

public class Chi_Tiet_HD {
    String Url;
   // String MaBan;
    String TenMon;
    int SoLuong;
    double ThanhTien;

    public Chi_Tiet_HD() {
    }

    public Chi_Tiet_HD(String tenMon, int soLuong, double thanhTien, String url) {
        //MaBan = maBan;
        TenMon = tenMon;
        SoLuong = soLuong;
        ThanhTien = thanhTien;
        Url = url;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

/*    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String maBan) {
        MaBan = maBan;
    }*/

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }
}
