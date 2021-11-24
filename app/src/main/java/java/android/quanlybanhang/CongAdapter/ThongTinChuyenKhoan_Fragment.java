package java.android.quanlybanhang.CongAdapter;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinChuyenKhoan_Fragment extends Fragment {
    private KhachHangActivity mainActivity;
    private View mView;
    private TextView tenCH,thongtinCH,diachiCH,sdtCH,thongtinchuyenkhoanCH;
    private CircleImageView anhCH;
    private Button huyCH,themCH;
    private DatabaseReference mReference;
    private ThongTinCuaHang thongTinCuaHang;
    private String STR_CH = "cuaHang";
    private String STR_ID = "YVnoULVR9xc0vJ9esj8VRbQ1HNg2";
    private String STR_TT = "thongtin";

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
        mReference= FirebaseDatabase.getInstance().getReference();
        mReference.child(STR_CH).child(STR_ID).child(STR_TT).addValueEventListener(new ValueEventListener() {
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


        return mView;
    }
}
