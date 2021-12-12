package java.android.quanlybanhang.functions.DatBan;

import java.util.ArrayList;

public class ID_datban {
    String id;
    private ArrayList<DatBanModel> datBanModels;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DatBanModel> getDatBanModels() {
        return datBanModels;
    }

    public void setDatBanModels(ArrayList<DatBanModel> datBanModels) {
        this.datBanModels = datBanModels;
    }



    public ID_datban(String id, ArrayList<DatBanModel> datBanModels) {
        this.id = id;
        this.datBanModels = datBanModels;
    }
}
