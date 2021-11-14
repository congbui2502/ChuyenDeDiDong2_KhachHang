package com.example.shipper;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String chitiet;
    private long giaBan;
    private long giaNhap;
    private  String nameProduct;
    private String imgProduct;
    private String nhomsanpham;
    private int soluong;
    private boolean isAddToCart;

    public SanPham() {
    }

    public SanPham(String imgProduct, String chitiet, long giaBan, long giaNhap, String nameProduct, String nhomsanpham, int soluong,boolean isAddToCart) {
        this.chitiet = chitiet;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.nameProduct = nameProduct;
        this.nhomsanpham = nhomsanpham;
        this.soluong = soluong;
        this.imgProduct = imgProduct;
        this.isAddToCart=false;
    }





    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(long giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNhomsanpham() {
        return nhomsanpham;
    }

    public void setNhomsanpham(String nhomsanpham) {
        this.nhomsanpham = nhomsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }


    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }

}
