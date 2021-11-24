package java.android.quanlybanhang.KhuVuc;

import java.android.quanlybanhang.Ban.StaticBanModel;
import java.util.ArrayList;

public class StaticModelKhuVuc {


    public String getTenkhuvuc() {
        return tenkhuvuc;
    }

    public String getTrangthai() {
        return trangthai;
    }

    private String tenkhuvuc;

    public StaticModelKhuVuc(String tenkhuvuc, String trangthai, ArrayList<StaticBanModel> staticBanModels) {
        this.tenkhuvuc = tenkhuvuc;
        this.trangthai = trangthai;
        this.staticBanModels = staticBanModels;
    }

    private  String trangthai;
    ArrayList<StaticBanModel> staticBanModels ;

    public ArrayList<StaticBanModel> getStaticBanModels() {
        return staticBanModels;
    }


    public StaticModelKhuVuc() {
    }

    public StaticModelKhuVuc(String ten, String trangThai) {
        this.tenkhuvuc = ten;
        this.trangthai = trangThai;
    }


}
