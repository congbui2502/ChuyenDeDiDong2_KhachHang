package java.android.quanlybanhang.Sonclass;

import java.io.Serializable;

public class DonGia implements Serializable {
    private boolean check;
    private long giaBan;
    private long giachung;
    private  String id;
    private  String tenDonGia;

    public DonGia(boolean check, long giaBan, long giachung, String id, String tenDonGia) {
        this.check = check;
        this.giaBan = giaBan;
        this.giachung = giachung;
        this.id = id;
        this.tenDonGia = tenDonGia;
    }

    public DonGia() {
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public long getGiachung() {
        return giachung;
    }

    public void setGiachung(long giachung) {
        this.giachung = giachung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDonGia() {
        return tenDonGia;
    }

    public void setTenDonGia(String tenDonGia) {
        this.tenDonGia = tenDonGia;
    }
}
