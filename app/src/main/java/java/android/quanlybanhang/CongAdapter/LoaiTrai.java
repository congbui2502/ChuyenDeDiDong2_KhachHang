package java.android.quanlybanhang.CongAdapter;

import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.List;

public class LoaiTrai {
    private String tittle;
    private List<SanPham> trais;


    public LoaiTrai(String tittle, List<SanPham> trais) {
        this.tittle = tittle;
        this.trais = trais;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public List<SanPham> getTrais() {
        return trais;
    }

    public void setTrais(List<SanPham> trais) {
        this.trais = trais;
    }
}
