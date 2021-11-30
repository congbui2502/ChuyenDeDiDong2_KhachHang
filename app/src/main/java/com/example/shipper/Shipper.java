package com.example.shipper;

import android.widget.ImageView;

public class Shipper {
    private String nameShipper;
    private String dateShipper;
    private String emailShipper;
    private String phoneShipper;
    private String bienSoXe;
    private String soCMT;
    private String imgAvatar;

    public Shipper(String nameShipper, String dateShipper, String emailShipper, String phoneShipper, String bienSoXe, String soCMT, String imgAvatar) {
        this.nameShipper = nameShipper;
        this.dateShipper = dateShipper;
        this.emailShipper = emailShipper;
        this.phoneShipper = phoneShipper;
        this.bienSoXe = bienSoXe;
        this.soCMT = soCMT;
        this.imgAvatar = imgAvatar;
    }

    public Shipper() {

    }

    public String getNameShipper() {
        return nameShipper;
    }

    public void setNameShipper(String nameShipper) {
        this.nameShipper = nameShipper;
    }

    public String getDateShipper() {
        return dateShipper;
    }

    public void setDateShipper(String dateShipper) {
        this.dateShipper = dateShipper;
    }

    public String getEmailShipper() {
        return emailShipper;
    }

    public void setEmailShipper(String emailShipper) {
        this.emailShipper = emailShipper;
    }

    public String getPhoneShipper() {
        return phoneShipper;
    }

    public void setPhoneShipper(String phoneShipper) {
        this.phoneShipper = phoneShipper;
    }
    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public String getSoCMT() {
        return soCMT;
    }

    public void setSoCMT(String soCMT) {
        this.soCMT = soCMT;
    }

    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }
}
