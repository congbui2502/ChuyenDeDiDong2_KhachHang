package java.android.quanlybanhang.CategoryMon;

import java.android.quanlybanhang.OrderMon.Product;
import java.util.ArrayList;

public class StaticCategoryMonModel {
    public String getTenCategory() {
        return tenCategory;
    }

    public String getImage() {
        return image;
    }

    public void setTenCategory(String tenCategory) {
        this.tenCategory = tenCategory;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public StaticCategoryMonModel(String tenCategory, String image) {
        this.tenCategory = tenCategory;
        this.image = "";
    }

    public StaticCategoryMonModel(String tenCategory) {
        this.tenCategory = tenCategory;
    }

    private String tenCategory;
    private String image ;


    public ArrayList<Product> getStaticMonOrderModels() {
        return staticMonOrderModels;
    }

    public void setStaticMonOrderModels(ArrayList<Product> staticMonOrderModels) {
        this.staticMonOrderModels = staticMonOrderModels;
    }

    ArrayList<Product> staticMonOrderModels;

    public StaticCategoryMonModel(String tenCategory, ArrayList<Product> staticMonOrderModels) {
        this.tenCategory = tenCategory;
        this.staticMonOrderModels = staticMonOrderModels;
    }

    public StaticCategoryMonModel(String nameProduct, Double giaBan, String imgProduct, String status) {
    }
}
