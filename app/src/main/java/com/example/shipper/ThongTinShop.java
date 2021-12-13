package com.example.shipper;

import java.io.Serializable;

public class ThongTinShop implements Serializable {

    private String name;
    private String soNha;
    private String phuongXa;
    private String quanHuyen;
    private String tinhThanhPho;


    public ThongTinShop(String name, String soNha, String phuongXa, String quanHuyen, String tinhThanhPho) {
        this.name = name;
        this.soNha = soNha;
        this.phuongXa = phuongXa;
        this.quanHuyen = quanHuyen;
        this.tinhThanhPho = tinhThanhPho;
    }

    public String getShopAddress()
    {
        return soNha+" , "+phuongXa+" , "+quanHuyen+" , "+tinhThanhPho;
    }

    public ThongTinShop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getTinhThanhPho() {
        return tinhThanhPho;
    }

    public void setTinhThanhPho(String tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }
}
