package java.android.quanlybanhang.Sonclass;

import java.util.List;

public class DonHangOnline {

    private  String idQuan;
    private String idKhachhang;
    private String idShipper;
    private long giaKhuyenMai;
    private int trangthai;
    private String time;

    private List<SanPham> sanpham;
    private String diaChi;
    private String key;
    private String tenKhachhang;
    private String sdtkhachhang;



    public DonHangOnline() {
    }

    public DonHangOnline(String idQuan, String idKhachhang, String idShipper,long giakhuyenmai,List<SanPham>dsChon,int trangthai) {
        this.idQuan = idQuan;
        this.idKhachhang = idKhachhang;
        this.idShipper = idShipper;
        this.giaKhuyenMai=giakhuyenmai;
        this.sanpham =dsChon;
        this.trangthai=trangthai;
    }

    public DonHangOnline(String idQuan, String idKhachhang, long giaKhuyenMai, int trangthai, List<SanPham> dsChon) {
        this.idQuan = idQuan;
        this.idKhachhang = idKhachhang;
        this.giaKhuyenMai = giaKhuyenMai;
        this.trangthai = trangthai;
        this.sanpham = dsChon;
    }

    public DonHangOnline(String idKhachhang, long giaKhuyenMai, int trangthai, String time, List<SanPham> dsChon) {
        this.idKhachhang = idKhachhang;
        this.giaKhuyenMai = giaKhuyenMai;
        this.trangthai = trangthai;
        this.time = time;
        this.sanpham = dsChon;
    }

    public DonHangOnline(String idKhachhang,long giaKhuyenMai, int trangthai, String time, List<SanPham> dsChon, String diaChi, String key, String tenKhachhang, String sdtkhachhang) {
        this.giaKhuyenMai = giaKhuyenMai;
        this.trangthai = trangthai;
        this.time = time;
        this.sanpham = dsChon;
        this.diaChi = diaChi;
        this.key = key;
        this.tenKhachhang = tenKhachhang;
        this.sdtkhachhang = sdtkhachhang;
        this.idKhachhang=idKhachhang;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTenKhachhang() {
        return tenKhachhang;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public String getSdtkhachhang() {
        return sdtkhachhang;
    }

    public void setSdtkhachhang(String sdtkhachhang) {
        this.sdtkhachhang = sdtkhachhang;
    }

    public long getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(long giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(String idQuan) {
        this.idQuan = idQuan;
    }

    public String getIdKhachhang() {
        return idKhachhang;
    }

    public void setIdKhachhang(String idKhachhang) {
        this.idKhachhang = idKhachhang;
    }

    public String getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(String idShipper) {
        this.idShipper = idShipper;
    }

    public List<SanPham> getSanpham() {
        return sanpham;
    }

    public void setSanpham(List<SanPham> sanpham) {
        this.sanpham = sanpham;
    }



    //tinh tien cho don hang

    private long tinhTongDonHang()
    {
        if (sanpham.size()==0)
        {
            return 0;
        }
        long tongtien=0;

        for (int i = 0; i < sanpham.size(); i++) {

            tongtien+= sanpham.get(i).getGiaBan()* sanpham.get(i).getSoluong();

        }

        tongtien=tongtien-giaKhuyenMai;



        return tongtien;

    }
}
