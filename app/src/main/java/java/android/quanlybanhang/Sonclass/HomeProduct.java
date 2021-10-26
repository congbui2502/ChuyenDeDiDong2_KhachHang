package java.android.quanlybanhang.Sonclass;

import java.util.List;

public class HomeProduct {


    private String tittle;
    private List<SanPham> sanphamnoibats;
    private List<CuaHang> cuahangs;
    private List<SanPham> sanphamQuangcao;

    public HomeProduct() {
    }

    public HomeProduct(String tittle,List<SanPham> sanphamnoibats, List<CuaHang> cuahangs, List<SanPham> sanphamQuangcao) {
        this.sanphamnoibats = sanphamnoibats;
        this.cuahangs = cuahangs;
        this.sanphamQuangcao = sanphamQuangcao;
        this.tittle=tittle;
    }


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public List<SanPham> getSanphamnoibats() {
        return sanphamnoibats;
    }

    public void setSanphamnoibats(List<SanPham> sanphamnoibats) {
        this.sanphamnoibats = sanphamnoibats;
    }

    public List<CuaHang> getCuahangs() {
        return cuahangs;
    }

    public void setCuahangs(List<CuaHang> cuahangs) {
        this.cuahangs = cuahangs;
    }

    public List<SanPham> getSanphamQuangcao() {
        return sanphamQuangcao;
    }

    public void setSanphamQuangcao(List<SanPham> sanphamQuangcao) {
        this.sanphamQuangcao = sanphamQuangcao;
    }

}
