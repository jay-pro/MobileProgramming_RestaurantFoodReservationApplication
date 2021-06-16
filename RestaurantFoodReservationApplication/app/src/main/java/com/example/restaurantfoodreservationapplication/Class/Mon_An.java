package com.example.restaurantfoodreservationapplication.Class;

public class Mon_An {

    double GiaMon;
    String MaDM;
    String TenMon;
    String Purl;

    public Mon_An() { }
    public Mon_An(double giaMon, String maDM, String tenMon, String purl) {
        GiaMon = giaMon;
        MaDM = maDM;
        TenMon = tenMon;
        Purl = purl;
    }
    public double getGiaMon() {
        return GiaMon;
    }

    public void setGiaMon(double giaMon) {
        GiaMon = giaMon;
    }

    public String getMaDM() {
        return MaDM;
    }

    public void setMaDM(String maDM) {
        MaDM = maDM;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getPurl() {
        return Purl;
    }

    public void setPurl(String purl) {
        Purl = purl;
    }
}