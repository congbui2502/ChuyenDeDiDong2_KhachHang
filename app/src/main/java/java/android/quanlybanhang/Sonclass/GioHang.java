package java.android.quanlybanhang.Sonclass;

import java.io.Serializable;
import java.util.List;

public class GioHang implements Serializable {
    private String idQuan;
    private List<SanPham> sanPham;

    public GioHang(String idQuan, List<SanPham> sanPham) {
        this.idQuan = idQuan;
        this.sanPham = sanPham;
    }

    public GioHang() {
    }

    public String getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(String idQuan) {
        this.idQuan = idQuan;
    }

    public List<SanPham> getSanPham() {
        return sanPham;
    }

    public void setSanPham(List<SanPham> sanPham) {
        this.sanPham = sanPham;
    }
}
