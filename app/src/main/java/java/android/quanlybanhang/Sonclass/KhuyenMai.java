package java.android.quanlybanhang.Sonclass;

public class KhuyenMai {

    private String idCuahang;
    private int phanTramKhuyenMai;
    private int giaDeDuocKhuyenMai;
    private int loaiKhuyenmai;
    private  String tenQuanHuyen;
    private String mota;



    public KhuyenMai() {
    }

    public KhuyenMai(String idCuahang, int phanTramKhuyenMai, int giaDeDuocKhuyenMai, int loaiKhuyenmai, String mota) {
        this.idCuahang = idCuahang;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
        this.loaiKhuyenmai = loaiKhuyenmai;
        this.mota = mota;
    }

    public KhuyenMai(String idCuahang, int giaDeDuocKhuyenMai, int loaiKhuyenmai, String tenQuanHuyen, String mota) {
        this.idCuahang = idCuahang;
        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
        this.loaiKhuyenmai = loaiKhuyenmai;
        this.tenQuanHuyen = tenQuanHuyen;
        this.mota = mota;
    }

    //
//    public KhuyenMai(String idCuahang, int phanTramKhuyenMai, int giaDeDuocKhuyenMai) {
//        this.idCuahang = idCuahang;
//        this.phanTramKhuyenMai = phanTramKhuyenMai;
//        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
//    }
//
//    public KhuyenMai(int loaiKhuyenmai,int phanTramKhuyenMai, int giaDeDuocKhuyenMai) {
//        this.phanTramKhuyenMai = phanTramKhuyenMai;
//        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
//        this.loaiKhuyenmai = loaiKhuyenmai;
//    }
//
//    public KhuyenMai(int phanTramKhuyenMai, int giaDeDuocKhuyenMai ) {
//        this.phanTramKhuyenMai = phanTramKhuyenMai;
//        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
//
//    }
//
//    public KhuyenMai(String idCuahang, int giaDeDuocKhuyenMai, int loaiKhuyenmai, String tenQuanHuyen) {
//        this.idCuahang = idCuahang;
//        this.giaDeDuocKhuyenMai = giaDeDuocKhuyenMai;
//        this.loaiKhuyenmai = loaiKhuyenmai;
//        this.tenQuanHuyen = tenQuanHuyen;
//    }
//


    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
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
