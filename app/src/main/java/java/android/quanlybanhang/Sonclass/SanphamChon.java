package java.android.quanlybanhang.Sonclass;

import java.util.List;

public class SanphamChon {

    private List<SanPham> sanPham;
    private String idQuan;

    public SanphamChon() {
    }


    public SanphamChon( String idQuan,List<SanPham> sanPham) {
        this.sanPham = sanPham;
        this.idQuan = idQuan;
    }


    public List<SanPham> getSanPham() {
        return sanPham;
    }

    public void setSanPham(List<SanPham> sanPham) {
        this.sanPham = sanPham;
    }

    public String getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(String idQuan) {
        this.idQuan = idQuan;
    }
}

