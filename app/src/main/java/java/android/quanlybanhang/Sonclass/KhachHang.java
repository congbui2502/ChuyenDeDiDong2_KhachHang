package java.android.quanlybanhang.Sonclass;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private String nameKhachHang;
    private String emailKhachHang;
    private String dateKhachHang;
    private String sdtKhachHang;
    private String idKhachhang;
    private String imgAvatar;

    public KhachHang() {
    }

    public KhachHang(String nameKhachHang, String emailKhachHang, String dateKhachHang, String sdtKhachHang, String idKhachhang) {
        this.nameKhachHang = nameKhachHang;
        this.emailKhachHang = emailKhachHang;
        this.dateKhachHang = dateKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.idKhachhang = idKhachhang;
    }

    public KhachHang(String nameKhachHang, String emailKhachHang, String dateKhachHang, String sdtKhachHang) {
        this.nameKhachHang = nameKhachHang;
        this.emailKhachHang = emailKhachHang;
        this.dateKhachHang = dateKhachHang;
        this.sdtKhachHang = sdtKhachHang;
    }

    public KhachHang(String nameKhachHang, String emailKhachHang, String dateKhachHang,
                     String sdtKhachHang, String idKhachhang, String imgAvatar) {
        this.nameKhachHang = nameKhachHang;
        this.emailKhachHang = emailKhachHang;
        this.dateKhachHang = dateKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.idKhachhang = idKhachhang;
        this.imgAvatar = imgAvatar;
    }

    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getIdKhachhang() {
        return idKhachhang;
    }

    public void setIdKhachhang(String idKhachhang) {
        this.idKhachhang = idKhachhang;
    }

    public String getNameKhachHang() {
        return nameKhachHang;
    }

    public void setNameKhachHang(String nameKhachHang) {
        this.nameKhachHang = nameKhachHang;
    }

    public String getEmailKhachHang() {
        return emailKhachHang;
    }

    public void setEmailKhachHang(String emailKhachHang) {
        this.emailKhachHang = emailKhachHang;
    }

    public String getDateKhachHang() {
        return dateKhachHang;
    }

    public void setDateKhachHang(String dateKhachHang) {
        this.dateKhachHang = dateKhachHang;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }
}
