package java.android.quanlybanhang.Sonclass;

import java.util.List;

public class TestChangProduct {

    private boolean flag;
    private List<Table> sanpham;


    public TestChangProduct(boolean flag, List<Table> testSP) {
        this.flag = flag;
        this.sanpham = testSP;
    }

    public TestChangProduct() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Table> getSanpham() {
        return sanpham;
    }

    public void setSanpham(List<Table> sanpham) {
        this.sanpham = sanpham;
    }
}
