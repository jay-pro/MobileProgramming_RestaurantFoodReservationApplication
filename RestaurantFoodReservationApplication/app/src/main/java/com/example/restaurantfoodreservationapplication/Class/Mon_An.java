package com.example.restaurantfoodreservationapplication.Class;

public class Mon_An {

    String Url;
    double GiaMon;
    String MaDM;
    String TenMon;

    public Mon_An() { }

    public Mon_An(double giaMon, String maDM, String tenMon, String url) {
        GiaMon = giaMon;
        MaDM = maDM;
        TenMon = tenMon;
        Url=url;
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


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}