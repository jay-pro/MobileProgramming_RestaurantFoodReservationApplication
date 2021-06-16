package com.example.restaurantfoodreservationapplication.Class;

public class Mon_An {

<<<<<<< HEAD
    String Url;
    double GiaMon;
    String MaDM;
    String TenMon;

    public Mon_An() { }

=======
    private double GiaMon;
    private String MaDM;
    private String TenMon;
    private String Url;

    public Mon_An() { }
>>>>>>> Nhi-QLmon
    public Mon_An(double giaMon, String maDM, String tenMon, String url) {
        GiaMon = giaMon;
        MaDM = maDM;
        TenMon = tenMon;
<<<<<<< HEAD
        Url=url;
=======
        Url = url;
>>>>>>> Nhi-QLmon
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

<<<<<<< HEAD

    public String getUrl() {
        return Url;
=======
    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
>>>>>>> Nhi-QLmon
    }

    public void setUrl(String url) {
        Url = url;
    }

}