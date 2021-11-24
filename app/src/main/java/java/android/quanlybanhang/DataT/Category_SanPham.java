package java.android.quanlybanhang.DataT;

public class Category_SanPham {
    String id;
    String nameCategory;

    public Category_SanPham(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Category_SanPham(String id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    public Category_SanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
