package java.android.quanlybanhang.Sonclass;


import java.io.Serializable;

public class SanPham implements Serializable {
    private String chitiet;
    private long giaBan;
    private long giaNhap;
    private  String nameProduct;
    private String imgProduct;
    private String nhomsanpham;
    private int soluong;
    private boolean addToCart;
    private long giamGia;
    private String idCuaHang;
    private String tittle;
    private boolean superquangcao;


    public SanPham() {
    }

    public SanPham(String imgProduct, String chitiet, long giaBan, long giaNhap, String nameProduct,
                   String nhomsanpham, int soluong,boolean addToCart) {
        this.chitiet = chitiet;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.nameProduct = nameProduct;
        this.nhomsanpham = nhomsanpham;
        this.soluong = soluong;
        this.imgProduct = imgProduct;
        this.addToCart =false;
    }


    public SanPham(String chitiet, long giaBan, String nameProduct, String imgProduct,
                   String nhomsanpham, int soluong, long giamGia, String idCuaHang, String tittle) {
        this.chitiet = chitiet;
        this.giaBan = giaBan;
        this.nameProduct = nameProduct;
        this.imgProduct = imgProduct;
        this.nhomsanpham = nhomsanpham;
        this.soluong = soluong;
        this.giamGia = giamGia;
        this.idCuaHang = idCuaHang;
        this.tittle = tittle;
    }

    public SanPham(String chitiet, long giaNhap, String nameProduct, String imgProduct, String nhomsanpham,
                   int soluong, long giamGia, String idCuaHang, String tittle, boolean superquangcao) {
        this.chitiet = chitiet;
        this.giaNhap = giaNhap;
        this.nameProduct = nameProduct;
        this.imgProduct = imgProduct;
        this.nhomsanpham = nhomsanpham;
        this.soluong = soluong;
        this.giamGia = giamGia;
        this.idCuaHang = idCuaHang;
        this.tittle = tittle;
        this.superquangcao = superquangcao;
    }



    public boolean isSuperquangcao() {
        return superquangcao;
    }

    public void setSuperquangcao(boolean superquangcao) {
        this.superquangcao = superquangcao;
    }

    public long getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(long giamGia) {
        this.giamGia = giamGia;
    }

    public String getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(String idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(long giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNhomsanpham() {
        return nhomsanpham;
    }

    public void setNhomsanpham(String nhomsanpham) {
        this.nhomsanpham = nhomsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }


    public boolean isAddToCart() {
        return addToCart;
    }

    public void setAddToCart(boolean addToCart) {
        this.addToCart = addToCart;
    }

}
