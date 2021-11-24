package java.android.quanlybanhang.DataT;

public class NhanVien {
    String username;
    String email;
    int chucVu;
    int caLam;
    String phone;
    String tenQuan;
    int id;

    public NhanVien() {
    }


    public NhanVien(String username, String email, int chucVu, int caLam, String phone, String tenQuan) {
        this.username = username;
        this.email = email;
        this.chucVu = chucVu;
        this.caLam = caLam;
        this.phone = phone;
        this.tenQuan = tenQuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public int getCaLam() {
        return caLam;
    }

    public void setCaLam(int caLam) {
        this.caLam = caLam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }
}
