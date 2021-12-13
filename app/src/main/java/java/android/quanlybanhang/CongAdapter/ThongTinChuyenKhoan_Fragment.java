package java.android.quanlybanhang.CongAdapter;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.DonHangOnline;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinChuyenKhoan_Fragment extends Fragment {
    private KhachHangActivity mainActivity;
    private View mView;
    private TextView tenCH,thongtinCH,diachiCH,sdtCH,thongtinchuyenkhoanCH;
    private CircleImageView anhCH;
    private Button huyCH,themCH;
    private DatabaseReference mReference;
    private DatabaseReference mReference1;
    private String STR_CH = "cuaHang";
    private String idQuan ;
    private String STR_TT = "thongtin";
    private DonHangOnline donHangOnline;
    private Date date;
    private DateFormat formatter;
    private String today,millis,key;

    public ThongTinChuyenKhoan_Fragment(DonHangOnline donHangOnline){
        this.donHangOnline = donHangOnline;
    }

    public void getIdQuan(String idQuan)
    {
        this.idQuan= idQuan;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.thongtincuahang,container,false);
        mainActivity=(KhachHangActivity)  getActivity();
        tenCH = mView.findViewById(R.id.nameCH);
        thongtinCH = mView.findViewById(R.id.thongtinCH);
        diachiCH = mView.findViewById(R.id.diachiCH);
        sdtCH = mView.findViewById(R.id.sdtCH);
        thongtinchuyenkhoanCH = mView.findViewById(R.id.thongtinchuyenkhoanCH);
        anhCH = mView.findViewById(R.id.anhCH);
        huyCH = mView.findViewById(R.id.btnhuyCK);
        themCH = mView.findViewById(R.id.btnthemCK);

        date = Calendar.getInstance().getTime();
        // Display a date in day, month, year format
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        today = formatter.format(date);
        millis =java.time.LocalTime.now().toString()+" " +today;
        key = System.currentTimeMillis()+"";

        mReference= FirebaseDatabase.getInstance().getReference();
        mReference1= FirebaseDatabase.getInstance().getReference();

        mReference.child(STR_CH).child(idQuan).child(STR_TT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                tenCH.setText(snapshot.child("name").getValue()+"");
                thongtinCH.setText(snapshot.child("moTa").getValue()+"");
                diachiCH.setText(snapshot.child("soNha").getValue()+","+snapshot.child("phuongXa").getValue()+","+snapshot.child("quanHuyen").getValue()+","+snapshot.child("tinhThanhPho").getValue()+"");
                sdtCH.setText(snapshot.child("soDienThoai").getValue()+"");
                thongtinchuyenkhoanCH.setText(snapshot.child("thongTinChuyenKhoan").getValue()+"");
                Picasso.get().load(snapshot.child("logoUrl").getValue()+"").into(anhCH);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        themCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReference1= FirebaseDatabase.getInstance().getReference()
                        .child("CuaHangOder")
                        .child(idQuan).child("donhangonline").child("dondadat");
                    HomeFragment fragment =new HomeFragment(mainActivity);
                    mReference1.child(today).child(key).setValue(donHangOnline);
                    mainActivity.getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,fragment).commit();

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

                    AlertDialog alert12 = builder1.create();
                    alert12.show();
            }
        });




        return mView;
    }
}
