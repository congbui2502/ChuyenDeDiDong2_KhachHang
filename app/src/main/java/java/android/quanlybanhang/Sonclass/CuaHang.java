package java.android.quanlybanhang.Sonclass;

public class CuaHang {
    private String idShop;



    private String nameShop;
    private  String logo;

    public CuaHang(String idShop, String logo,String nameShop) {
        this.idShop = idShop;
        this.logo = logo;
        this.nameShop=nameShop;
    }

    public CuaHang() {
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

}
