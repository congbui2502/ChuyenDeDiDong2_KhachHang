package com.example.shipper;

import java.io.Serializable;
import java.util.List;

public class DonHang implements Serializable {
    private int trangthai;

    private String diemnhan;

    private String diemgiao;

    private long tonggia;

   private List<SanPham> dsChon;

   private String tenKhachHang;

   private String sdtkhachhang;

    public DonHang() {
    }

    public DonHang(int trangthai, String diemnhan, String diemgiao, long tonggia, List<SanPham> dsChon, String tenKhachHang,String sdtkhachhang) {
        this.trangthai = trangthai;
        this.diemnhan = diemnhan;
        this.diemgiao = diemgiao;
        this.tonggia=tonggia;
        this.dsChon=dsChon;
        this.tenKhachHang=tenKhachHang;
        this.sdtkhachhang=sdtkhachhang;
    }

    public List<SanPham> getDsChon() {
        return dsChon;
    }

    public void setDsChon(List<SanPham> dsChon) {
        this.dsChon = dsChon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdtkhachhang() {
        return sdtkhachhang;
    }

    public void setSdtkhachhang(String sdtkhachhang) {
        this.sdtkhachhang = sdtkhachhang;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public long getTonggia() {
        return tonggia;
    }

    public void setTonggia(long tonggia) {
        this.tonggia = tonggia;
    }

    public String getDiemnhan() {
        return diemnhan;
    }

    public void setDiemnhan(String diemnhan) {
        this.diemnhan = diemnhan;
    }

    public String getDiemgiao() {
        return diemgiao;
    }

    public void setDiemgiao(String diemgiao) {
        this.diemgiao = diemgiao;
    }

}
