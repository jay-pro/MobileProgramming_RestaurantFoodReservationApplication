package com.example.restaurantfoodreservationapplication.Class;

public class Ban_An {
    private String MaBan;
    private String TenBan;
    private int SoCho;
    public Ban_An(){

    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String maBan) {
        MaBan = maBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }

    public int getSoCho() {
        return SoCho;
    }

    public void setSoCho(int soCho) {
        SoCho = soCho;
    }

    public Ban_An(String maBan, String tenBan, int soCho) {
        MaBan = maBan;
        TenBan = tenBan;
        SoCho = soCho;
    }
}
