package java.android.quanlybanhang.DatBan.History.Data;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class SupportFragmentDonOnline {
    public SupportFragmentDonOnline() {}

    public String formartDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dt = formatter.format(date);
        return dt;
    }

    public String ngayHientai(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dt = formatter.format(date);
        return dt;
    }

    public Double TinhTongTien(ArrayList<SanPham> sanPhams) {
        double tongGia = 0;
        for (int i = 0; i < sanPhams.size(); i++) {
            tongGia += sanPhams.get(i).getGiaBan();
        }
        return tongGia;
    }

    public Date formatDate(String strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.sss dd-MM-yyyy");

        try {
            Date date = simpleDateFormat.parse(strDate);
            return date;
        } catch (Exception e) {
            Date date = new Date();
            return date;
        }
    }

    public Date dateKey(String strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date = simpleDateFormat.parse(strDate);
            return date;
        } catch (Exception e) {
            Date date = new Date();
            return date;
        }
    }

    public String formatDateS(String strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.sss dd-MM-yyyy");
        String dt = "";
        try {
            Date date = simpleDateFormat.parse(strDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            dt = formatter.format(date);
            return dt;
        } catch (Exception e) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            dt = formatter.format(date);
            return dt;
        }
    }

    public void SapXepDate(ArrayList<DonHang> donHangs) {
        Collections.sort(donHangs, new Comparator<DonHang>() {
            public int compare(DonHang o1, DonHang o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

}
