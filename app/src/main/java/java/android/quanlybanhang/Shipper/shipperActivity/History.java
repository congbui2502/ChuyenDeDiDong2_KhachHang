package com.example.shipper;

public class History {
    private int trangthai;

    private String diemnhan;

    private String diemgiao;

    private String thoigian;



    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getDiemnhan() {
        return diemnhan;
    }

    public void setDiemnhan(String diemnhan) {
        this.diemnhan = diemnhan;
    }

    public String getDiemgiao() {
        return diemgiao;
    }

    public void setDiemgiao(String diemgiao) {
        this.diemgiao = diemgiao;
    }

    public History() {

    }

    public History(int trangthai, String diemnhan, String diemgiao, String thoigian) {
        this.trangthai = trangthai;
        this.diemnhan = diemnhan;
        this.diemgiao = diemgiao;
        this.thoigian = thoigian;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}
