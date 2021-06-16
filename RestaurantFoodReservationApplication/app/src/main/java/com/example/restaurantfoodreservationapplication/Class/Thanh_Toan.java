package com.example.restaurantfoodreservationapplication.Class;

public class Thanh_Toan {
    double TongTien;
    String NgayThanhToan;
    String MaBan;
    public Thanh_Toan(){};

    public Thanh_Toan(double tongTien, String ngayThanhToan, String maBan) {
        TongTien = tongTien;
        NgayThanhToan = ngayThanhToan;
        MaBan = maBan;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double tongTien) {
        TongTien = tongTien;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        NgayThanhToan = ngayThanhToan;
    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String maBan) {
        MaBan = maBan;
    }
}
