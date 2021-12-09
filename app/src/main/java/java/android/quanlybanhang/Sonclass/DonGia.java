package java.android.quanlybanhang.Sonclass;

import java.io.Serializable;

public class DonGia implements Serializable {

    private long giaBan;

    private String tenDonGia;

    public DonGia() {
    }

    public DonGia(long giaBan,  String tenDonGia) {

        this.giaBan = giaBan;

        this.tenDonGia = tenDonGia;
    }


    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }



    public String getTenDonGia() {
        return tenDonGia;
    }

    public void setTenDonGia(String tenDonGia) {
        this.tenDonGia = tenDonGia;
    }
}
