package java.android.quanlybanhang.DatBan.History.Data;


import java.io.Serializable;
import java.util.ArrayList;

public class SanPham implements Serializable {
    private Boolean addToCart;
    private String chitiet;
    private Double giaBan;
    private Double giaNhap;
    private String imgProduct;
    private String nameProduct;
    private String nhomsanpham;
    private int soluong;
    private String loai;
    private String yeuCau;
    private Double giaProudct;
    private ArrayList<DonGia> donGia;

    public SanPham() {
    }

    public SanPham(Boolean addToCart, String chitiet, Double giaBan, Double giaNhap, String imgProduct, String nameProduct, String nhomsanpham, int soluong, String loai, String yeuCau) {
        this.addToCart = addToCart;
        this.chitiet = chitiet;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.imgProduct = imgProduct;
        this.nameProduct = nameProduct;
        this.nhomsanpham = nhomsanpham;
        this.soluong = soluong;
        this.loai = loai;
        this.yeuCau = yeuCau;
        this.giaProudct = giaBan;
    }

    public ArrayList<DonGia> getDonGia() {
        return donGia;
    }

    public void setDonGia(ArrayList<DonGia> donGia) {
        this.donGia = donGia;
    }

    public Boolean getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(Boolean addToCart) {
        this.addToCart = addToCart;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }

    public Double getGiaProudct() {
        return giaProudct;
    }

    public void setGiaProudct(Double giaProudct) {
        this.giaProudct = giaProudct;
    }
}

