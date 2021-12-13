package java.android.quanlybanhang.DatBan.History.Data;


public class DiaChi {
    private String soNha;
    private String xa;
    private String huyen;
    private String tinh;
    private String tenCuaHang;

    public DiaChi() {
    }

    public DiaChi(String soNha, String xa, String huyen, String tinh) {
        this.soNha = soNha;
        this.xa = xa;
        this.huyen = huyen;
        this.tinh = tinh;
    }

    public DiaChi(String soNha, String xa, String huyen, String tinh, String tenCuaHang) {
        this.soNha = soNha;
        this.xa = xa;
        this.huyen = huyen;
        this.tinh = tinh;
        this.tenCuaHang = tenCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getXa() {
        return xa;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getHuyen() {
        return huyen;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }
}

