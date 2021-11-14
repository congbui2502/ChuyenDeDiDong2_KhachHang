package com.example.shipper;

public class Shipper {
    private String nameShipper;
    private String dateShipper;
    private String emailShipper;
    private String phoneShipper;

    public Shipper() {

    }

    public Shipper(String nameShipper, String dateShipper, String emailShipper, String phoneShipper) {
        this.nameShipper = nameShipper;
        this.dateShipper = dateShipper;
        this.emailShipper = emailShipper;
        this.phoneShipper = phoneShipper;
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
}
