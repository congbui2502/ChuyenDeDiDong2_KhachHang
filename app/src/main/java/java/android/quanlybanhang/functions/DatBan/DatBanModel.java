package java.android.quanlybanhang.functions.DatBan;

public class DatBanModel {
    private  String tenban;
    private String id_ngaydat;
    private String giodat;
    private  String gioketthuc;
    private String id_bk;
    private String ngaydat;
    private String ngayhientai;
    private String sodienthoai;
    private String sotiendadattruoc;
    private String tenkhachhang;
    private  String trangthai;


    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    private boolean up = false;

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public DatBanModel(String id_ngaydat, String giodat, String gioketthuc, String id_bk, String ngaydat, String ngayhientai, String sodienthoai, String sotiendadattruoc, String tenkhachhang,String tenban) {

        this.id_ngaydat = id_ngaydat;
        this.giodat = giodat;
        this.gioketthuc = gioketthuc;
        this.id_bk = id_bk;
        this.ngaydat = ngaydat;
        this.ngayhientai = ngayhientai;
        this.sodienthoai = sodienthoai;
        this.sotiendadattruoc = sotiendadattruoc;
        this.tenkhachhang = tenkhachhang;
        this.tenban = tenban;
    }

    public DatBanModel(String tenban, String id_ngaydat, String giodat, String gioketthuc, String id_bk, String ngaydat, String ngayhientai, String sodienthoai, String sotiendadattruoc, String tenkhachhang, String trangthai) {
        this.tenban = tenban;
        this.id_ngaydat = id_ngaydat;
        this.giodat = giodat;
        this.gioketthuc = gioketthuc;
        this.id_bk = id_bk;
        this.ngaydat = ngaydat;
        this.ngayhientai = ngayhientai;
        this.sodienthoai = sodienthoai;
        this.sotiendadattruoc = sotiendadattruoc;
        this.tenkhachhang = tenkhachhang;
        this.trangthai = trangthai;
    }

    public String getGioketthuc() {
        return gioketthuc;
    }

    public void setGioketthuc(String gioketthuc) {
        this.gioketthuc = gioketthuc;
    }


    public String getId_ngaydat() {
        return id_ngaydat;
    }

    public void setId_ngaydat(String id_ngaydat) {
        this.id_ngaydat = id_ngaydat;
    }

    public String getGiodat() {
        return giodat;
    }

    public void setGiodat(String giodat) {
        this.giodat = giodat;
    }

    public String getId_bk() {
        return id_bk;
    }

    public void setId_bk(String id_bk) {
        this.id_bk = id_bk;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getNgayhientai() {
        return ngayhientai;
    }

    public void setNgayhientai(String ngayhientai) {
        this.ngayhientai = ngayhientai;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getSotiendadattruoc() {
        return sotiendadattruoc;
    }

    public void setSotiendadattruoc(String sotiendadattruoc) {
        this.sotiendadattruoc = sotiendadattruoc;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
