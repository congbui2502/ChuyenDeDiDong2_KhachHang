package com.example.shipper;

public class RatingModel {
    private String comment;
    private String date;
    private String numberrating;
    private String tenkhachhang;
    private String idDonHang;

    public RatingModel() {
    }

    public RatingModel(String comment, String date, String numberrating, String tenkhachhang, String idDonHang) {
        this.comment = comment;
        this.date = date;
        this.numberrating = numberrating;
        this.tenkhachhang = tenkhachhang;
        this.idDonHang = idDonHang;
    }

    public RatingModel(String comment, String date, String numberrating, String tenkhachhang) {
        this.comment = comment;
        this.date = date;
        this.numberrating = numberrating;
        this.tenkhachhang = tenkhachhang;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumberrating() {
        return numberrating;
    }

    public void setNumberrating(String numberrating) {
        this.numberrating = numberrating;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }
}
