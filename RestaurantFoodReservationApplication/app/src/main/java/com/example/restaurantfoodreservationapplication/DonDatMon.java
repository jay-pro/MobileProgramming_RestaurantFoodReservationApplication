package com.example.restaurantfoodreservationapplication;

import java.util.Date;

public class DonDatMon {
    private String MaDonDat;
    private  String MaBan;
    private int Hinh;
    private  String MaMon;
    private String ThoiGian;
    private float TongGia;

    public DonDatMon(String maDonDat, String maBan, int hinh, String maMon, String thoiGian, float tongGia) {
        MaDonDat = maDonDat;
        MaBan = maBan;
        Hinh = hinh;
        MaMon = maMon;
        ThoiGian = thoiGian;
        TongGia = tongGia;
    }

    public String getMaDonDat() {
        return MaDonDat;
    }

    public void setMaDonDat(String maDonDat) {
        MaDonDat = maDonDat;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String maBan) {
        MaBan = maBan;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }

    public float getTongGia() {
        return TongGia;
    }

    public void setTongGia(float tongGia) {
        TongGia = tongGia;
    }
}
