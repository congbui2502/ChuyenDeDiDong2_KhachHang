package java.android.quanlybanhang.CongAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.DonHangOnline;
import java.android.quanlybanhang.Sonclass.KhuyenMai;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DonHangFragment  extends Fragment {

    private String idQuan="JxZOOK1RzcMM7pL5I6naGZfYSsu2";
    private RecyclerView recyDonHang;
    private TextView giaKhuyenMai;
    private  TextView tongTien;
    private Button btnDatHang;
    private KhachHangActivity mainActivity;
    private KhuyenMaiAdapter.setTvKhuyenMai khuyenMai;
    private List<SanPham> sanPhams;
    private long tongtien;

    public DonHangFragment(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.donhang_fragment,container,false);
        mainActivity=(KhachHangActivity) getActivity();
        Log.d("aaa","nhap sai roi");
        recyDonHang=view.findViewById(R.id.recyDonHang);
        giaKhuyenMai=view.findViewById(R.id.tvKhuyenMai);
        tongTien=view.findViewById(R.id.tvTongGia);
        btnDatHang=view.findViewById(R.id.btnDatDon);

        LinearLayoutManager manager=new LinearLayoutManager(mainActivity);
        recyDonHang.setLayoutManager(manager);

        DonHangAdapter donHangAdapter=new DonHangAdapter();
        donHangAdapter.setData(sanPhams);
        recyDonHang.setAdapter(donHangAdapter);
        tongtien = donHangAdapter.tinhTongTien();
        tongTien.setText(tongtien+" VND");

        giaKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KhuyenMai khuyenMai=new KhuyenMai("xZOOK1RzcMM7pL5I6naGZfYSsu2",10,
//                        0);
//                if(donHangAdapter.tinhTongTien()>khuyenMai.getGiaDeDuocKhuyenMai())
//                {
////                    String abc= Cart_Fragment.addDauPhay((donHangAdapter.tinhTongTien()-
////                            donHangAdapter.tinhTongTien()*khuyenMai.getPhanTramKhuyenMai()/100));
//
//                    String abc= Cart_Fragment.addDauPhay(33000);
//
//                    tongTien.setText(abc+" VND");
//
//                }
                CustomDialogKhuyanMai customDialogKhuyanMai=new CustomDialogKhuyanMai(mainActivity);
                customDialogKhuyanMai.setData(new KhuyenMaiAdapter.setTvKhuyenMai() {
                    @Override
                    public void setTv(KhuyenMai khuyenMai) {
//                        Toast.makeText(getContext(),khuyenMai.getLoaiKhuyenmai()+ "abc",Toast.LENGTH_LONG).show();
                        tinhtoan(khuyenMai);
                    }
                },idQuan);
                customDialogKhuyanMai.show();

            }
        });

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mReference= FirebaseDatabase.getInstance().getReference()
                        .child(idQuan).child("donhangonline").child("dondadat");
                Date date = Calendar.getInstance().getTime();
                // Display a date in day, month, year format
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String today = formatter.format(date);


                String millis =java.time.LocalTime.now().toString()+" " +today;
                String key = System.currentTimeMillis()+"";

                DonHangOnline donHangOnline=new DonHangOnline("idKhachhang",donHangAdapter.tinhTongTien()
                        ,0,millis,sanPhams,
                        "12/21 đường số 12 p.Linh Chieu",key,"Bùi Đình Công","0374193095");
                HomeFragment fragment =new HomeFragment(mainActivity);
                mReference.child(today).child(key).setValue(donHangOnline);
                mainActivity.getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,fragment).commit();
                btnDatHang.setEnabled(false);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Đơn hàng của bạn đã được đặt.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        return view;
    }

    private void tinhtoan(KhuyenMai khuyenMai) {
        if(khuyenMai.getLoaiKhuyenmai()==1)
        {
            if(tongtien>khuyenMai.getGiaDeDuocKhuyenMai())
            {
                tongTien.setText((tongtien- tongtien*khuyenMai.getPhanTramKhuyenMai()/100)+" VNĐ");
            }else {
                Toast.makeText(getContext(), "Tổng tiền không đủ để sử dụng.",Toast.LENGTH_LONG).show();
            }


        }else if(khuyenMai.getLoaiKhuyenmai()==2){
            if(tongtien> khuyenMai.getGiaDeDuocKhuyenMai())
            {

            }else {
                Toast.makeText(getContext(), "Tổng tiền không đủ để sử dụng.",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "Không đúng loai khuyen mãi",Toast.LENGTH_LONG).show();
        }


    }
}
