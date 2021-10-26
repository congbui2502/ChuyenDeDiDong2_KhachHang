package java.android.quanlybanhang.CongAdapter;

public class Trai {


    private int resourceId;
    private String tittle;
    private long gia;



    private boolean isAddToCart;


    public Trai(int resourceId, String tittle, long gia) {
        this.resourceId = resourceId;
        this.tittle = tittle;
        this.gia = gia;
    }

    public Trai(int resourceId, String tittle) {
        this.resourceId = resourceId;
        this.tittle = tittle;

    }
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }


}
