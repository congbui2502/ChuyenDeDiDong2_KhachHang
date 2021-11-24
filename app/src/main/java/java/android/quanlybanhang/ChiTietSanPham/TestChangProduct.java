package java.android.quanlybanhang.ChiTietSanPham;

import java.android.quanlybanhang.OrderMon.Product;
import java.util.List;

public class TestChangProduct {
    private boolean flag;
    private List<Product> sanpham;


    public TestChangProduct(boolean flag, List<Product> testSP) {
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

    public List<Product> getSanpham() {
        return sanpham;
    }

    public void setSanpham(List<Product> sanpham) {
        this.sanpham = sanpham;
    }
}
