package com.example.restaurantfoodreservationapplication.ui.quanly_mon;

public class QUANLY_MONModel {
    String tenmon,purl;
    String giamon;
    QUANLY_MONModel()
    {

    }
    public QUANLY_MONModel(String tenmon, String giamon, String purl) {
        this.tenmon = tenmon;
        this.giamon = giamon;
        this.purl = purl;
    }

    public String getTenmon() { return tenmon; }
    public void setTenmon(String tenmon) { this.tenmon = tenmon; }

    public String getGiamon() { return giamon; }
    public void setGiamon(String giamon) { this.giamon = giamon; }

    public String getPurl() { return purl; }
    public void setPurl(String purl) { this.purl = purl; }
}
