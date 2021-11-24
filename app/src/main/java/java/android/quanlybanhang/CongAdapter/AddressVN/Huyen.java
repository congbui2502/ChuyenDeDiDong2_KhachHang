package java.android.quanlybanhang.CongAdapter.AddressVN;

import java.util.ArrayList;

public class Huyen {
    private String tenHuyen;
    private ArrayList<String> xa;

    public Huyen() {
    }

    public Huyen(String tenHuyen, ArrayList<String> xa) {
        this.tenHuyen = tenHuyen;
        this.xa = xa;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public ArrayList<String> getXa() {
        return xa;
    }

    public void setXa(ArrayList<String> xa) {
        this.xa = xa;
    }
}
