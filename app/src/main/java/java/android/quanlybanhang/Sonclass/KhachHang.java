package java.android.quanlybanhang.Sonclass;

public class KhachHang {
    private String nameKhachHang;
    private String emailKhachHang;
    private String dateKhachHang;
    private String sdtKhachHang;



    public KhachHang() {
    }

    public KhachHang(String nameKhachHang, String emailKhachHang, String dateKhachHang, String sdtKhachHang) {
        this.nameKhachHang = nameKhachHang;
        this.emailKhachHang = emailKhachHang;
        this.dateKhachHang = dateKhachHang;
        this.sdtKhachHang = sdtKhachHang;
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
