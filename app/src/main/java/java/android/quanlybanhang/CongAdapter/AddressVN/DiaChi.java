package java.android.quanlybanhang.CongAdapter.AddressVN;

import java.util.ArrayList;

public class DiaChi {
    private String tenTinhTP;
    private ArrayList<Huyen> huyens;

    public DiaChi() {
    }

    public DiaChi(String tenTinhTP, ArrayList<Huyen> huyens) {
        this.tenTinhTP = tenTinhTP;
        this.huyens = huyens;
    }

    public String getTenTinhTP() {
        return tenTinhTP;
    }

    public void setTenTinhTP(String tenTinhTP) {
        this.tenTinhTP = tenTinhTP;
    }

    public ArrayList<Huyen> getHuyens() {
        return huyens;
    }

    public void setHuyens(ArrayList<Huyen> huyens) {
        this.huyens = huyens;
    }
}
