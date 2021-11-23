package java.android.quanlybanhang.Sonclass;

public class Product {

    private int resouceId;
    private String tenQuan;
    private String soMon;
    private boolean isAddToCart;


    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }




    public Product() {
    }

    public Product(int resouceId, String tenQuan, String soMon) {
        this.resouceId = resouceId;
        this.tenQuan = tenQuan;
        this.soMon = soMon;
    }

    public int getResouceId() {
        return resouceId;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }

    public String getSoMon() {
        return soMon;
    }

    public void setSoMon(String soMon) {
        this.soMon = soMon;
    }
}
