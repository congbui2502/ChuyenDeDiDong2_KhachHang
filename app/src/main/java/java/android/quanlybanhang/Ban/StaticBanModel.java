package java.android.quanlybanhang.Ban;

public class StaticBanModel {






    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getGioDaOder() {
        return gioDaOder;
    }
    public String getTenban() {
        return tenban;
    }

    public String getTrangthai() {
        return trangthai;
    }

    private   String tenban;

    public StaticBanModel(String tenban, String trangthai, String tenNhanVien, String gioDaOder) {
        this.tenban = "";
        this.trangthai = "";
        this.tenNhanVien = "";
        this.gioDaOder = "";
    }

    public StaticBanModel() {
    }

    private   String trangthai;

    public StaticBanModel(String tenban, String trangthai) {
        this.tenban = tenban;
        this.trangthai = trangthai;
    }

    private  String tenNhanVien;
    private  String gioDaOder;


}
