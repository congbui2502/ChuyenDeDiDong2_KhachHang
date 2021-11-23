package java.android.quanlybanhang.Sonclass;

public class CuaHang {
    private String id;
    private String name;
    private  String logoUrl;

//
//    private String idShop;
//    private String nameShop;
//    private  String logoUrl;

    public CuaHang(String id, String logoUrl,String name) {
        this.id = id;
        this.logoUrl = logoUrl;
        this.name =name;
    }

    public CuaHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
