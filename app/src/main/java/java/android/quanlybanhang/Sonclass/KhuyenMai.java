package java.android.quanlybanhang.Sonclass;

public class KhuyenMai {

    private String idCuahang;
    private int phanTramKhuyenMai;
    private int giaDeDuocKhuyenMai;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private int loaiKhuyenmai;


    public KhuyenMai(String idCuahang, int phanTramKhuyenMai, int giaDeDuocKhuyenMai, String thoiGianBatDau, String thoiGianKetThuc) {
        this.idCuahang = idCuahang;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public KhuyenMai(String idCuahang, int phanTramKhuyenMai, int giaDeDuocKhuyenMai, String thoiGianBatDau, String thoiGianKetThuc, int loaiKhuyenmai) {
        this.idCuahang = idCuahang;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.loaiKhuyenmai = loaiKhuyenmai;
    }

    public KhuyenMai() {
    }

    public KhuyenMai(String idCuahang, int phanTramKhuyenMai, int giaDeDuocKhuyenMai) {
        this.idCuahang = idCuahang;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
    }

    public KhuyenMai(int loaiKhuyenmai,int phanTramKhuyenMai, int giaDeDuocKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
        this.loaiKhuyenmai = loaiKhuyenmai;
    }

    public KhuyenMai(int phanTramKhuyenMai, int giaDeDuocKhuyenMai ) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;

    }

    public int getLoaiKhuyenmai() {
        return loaiKhuyenmai;
    }

    public void setLoaiKhuyenmai(int loaiKhuyenmai) {
        this.loaiKhuyenmai = loaiKhuyenmai;
    }

    public String getIdCuahang() {
        return idCuahang;
    }

    public void setIdCuahang(String idCuahang) {
        this.idCuahang = idCuahang;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public int getPhanTramKhuyenMai() {
        return phanTramKhuyenMai;
    }

    public void setPhanTramKhuyenMai(int phanTramKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }

    public int getGiaDeDuocKhuyenMai() {
        return giaDeDuocKhuyenMai;
    }

    public void setGiaDeDuocKhuyenMai(int giaDeDuocKhuyenMai) {
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
    }



}
