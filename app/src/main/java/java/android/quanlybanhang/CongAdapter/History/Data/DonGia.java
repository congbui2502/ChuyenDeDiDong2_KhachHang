package java.android.quanlybanhang.CongAdapter.History.Data;

import java.io.Serializable;

public class DonGia implements Serializable {
    private Double giaBan;
    private String tenDonGia;

    public DonGia() {
    }

    public DonGia(Double giaBan, String tenDonGia) {
        this.giaBan = giaBan;
        this.tenDonGia = tenDonGia;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public String getTenDonGia() {
        return tenDonGia;
    }

    public void setTenDonGia(String tenDonGia) {
        this.tenDonGia = tenDonGia;
    }
}
