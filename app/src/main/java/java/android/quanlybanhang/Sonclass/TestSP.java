package java.android.quanlybanhang.Sonclass;

public class TestSP {
    private int idsanpham;
    private String tensanpham;
    private int soluong;

    public TestSP() {
    }

    public TestSP(int idSP, String tenSP, int soLuong) {
        this.idsanpham = idSP;
        this.tensanpham = tenSP;
        this.soluong = soLuong;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


}
