package com.example.shipper;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable {
    private int trangthai;

    private String diemnhan;

    private String diaChi;

    private long donGia;

    private List<SanPham> sanpham;

    private String tenKhachhang;

    private String sdtkhachhang;

    private String ghiChu;

    private long thunhap;

    private String time;

    private String idQuan;

    private String shipper;

    private String phoneShipper;

    private String key;

    private String idKhachhang;

    private String idDonHang;

    private String tgHoanThanh;

    private String tgNhanDon;



    public History(String diemnhan, String diaChi, long donGia, String tenKhachhang, String sdtkhachhang, long thunhap,
                   List<SanPham> sanpham, String ghiChu, String time, String idQuan, String shipper, String phoneShipper,
                   String key, int trangthai, String idKhachhang, String idDonHang ) {
        this.diemnhan = diemnhan;
        this.diaChi = diaChi;
        this.donGia = donGia;
        this.tenKhachhang = tenKhachhang;
        this.sdtkhachhang = sdtkhachhang;
        this.thunhap = thunhap;
        this.sanpham = sanpham;
        this.ghiChu = ghiChu;
        this.time = time;
        this.idQuan = idQuan;
        this.shipper = shipper;
        this.phoneShipper = phoneShipper;
        this.key=key;
        this.trangthai=trangthai;
        this.idKhachhang =idKhachhang;
        this.idDonHang=idDonHang;
        this.tgNhanDon=tgNhanDon;
        this.tgHoanThanh=tgHoanThanh;
    }
    public History() {

    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getDiemnhan() {
        return diemnhan;
    }

    public void setDiemnhan(String diemnhan) {
        this.diemnhan = diemnhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public List<SanPham> getSanpham() {
        return sanpham;
    }

    public void setSanpham(List<SanPham> sanpham) {
        this.sanpham = sanpham;
    }

    public String getTenKhachhang() {
        return tenKhachhang;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public String getSdtkhachhang() {
        return sdtkhachhang;
    }

    public void setSdtkhachhang(String sdtkhachhang) {
        this.sdtkhachhang = sdtkhachhang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public long getThunhap() {
        return thunhap;
    }

    public void setThunhap(long thunhap) {
        this.thunhap = thunhap;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(String idQuan) {
        this.idQuan = idQuan;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getPhoneShipper() {
        return phoneShipper;
    }

    public void setPhoneShipper(String phoneShipper) {
        this.phoneShipper = phoneShipper;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIdKhachhang() {
        return idKhachhang;
    }

    public void setIdKhachhang(String idKhachhang) {
        this.idKhachhang = idKhachhang;
    }

    public String getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
    }
    public String getTgHoanThanh() {
        return tgHoanThanh;
    }

    public void setTgHoanThanh(String tgHoanThanh) {
        this.tgHoanThanh = tgHoanThanh;
    }

    public String getTgNhanDon() {
        return tgNhanDon;
    }

    public void setTgNhanDon(String tgNhanDon) {
        this.tgNhanDon = tgNhanDon;
    }
}
