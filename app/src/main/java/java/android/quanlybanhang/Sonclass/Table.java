package java.android.quanlybanhang.Sonclass;

import java.util.List;

public class Table {
    private String  nameTable;
    private List<Mon> danhSachMon;
    private String yeuCau;
    private String date;

    public Table() {
    }


    public List<Mon> getDanhSachMon() {
        return danhSachMon;
    }

    public void setDanhSachMon(List<Mon> danhSachMon) {
        this.danhSachMon = danhSachMon;
    }



    public Table(String nameTable, String yeuCau, String date) {
        this.nameTable = nameTable;
        this.yeuCau = yeuCau;
        this.date = date;
    }
    public Table(String nameTable, List<Mon> danhSachMon, String yeuCau, String date) {
        this.nameTable = nameTable;
        this.danhSachMon = danhSachMon;
        this.yeuCau = yeuCau;
        this.date = date;
    }

    public Table(String list_key, List<Mon>[] mons) {

    }

    public Table(String nameTable, List<Mon> danhSachMon) {
        this.nameTable = nameTable;
        this.danhSachMon = danhSachMon;
        this.date="22 05 2022";
        this.yeuCau="Best Of Luck";

    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }


    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameTable() {
        return nameTable;
    }


    public String getYeuCau() {
        return yeuCau;
    }

    public String getDate() {
        return date;
    }


}
