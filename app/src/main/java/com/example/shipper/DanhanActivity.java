package com.example.shipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.DonHangAdapter;
import adapter.DonNhanAdapter;
import adapter.SanPhamAdapter;

public class DanhanActivity extends AppCompatActivity {
    public static final String KEY_DIEMNHAN = "DIEMNHAN";
    public static final String KEY_DIEMGIAO = "DIEMGIAO";
    public static final String KEY_TONGGIA = "TONGGIA";
    public static final String KEY_BUNDLE = "BUNDLE";
    private TextView tvDiemNhan,tvDiemGiao,tvTongGia, tvThuNhap,tvTrangThai;
    private DatabaseReference databaseReference;
    private Context context;
    ListView listView;
    private DonDaNhan donDaNhan;
    ArrayList<DonDaNhan> arrayList;
    private DonNhanAdapter adapter;
    DonDaNhan donDaNhanActivity = new DonDaNhan();
    public void setData(Context context){
        this.context=context;
    }
    private void AnhXa(View view){
        listView = view.findViewById(R.id.lv_danhan);
        tvDiemNhan = view.findViewById(R.id.tv_diemnhan);
        tvDiemGiao= view.findViewById(R.id.tv_diemgiao);
        tvTongGia = view.findViewById(R.id.tv_tonggia);
        tvThuNhap = view.findViewById(R.id.tv_thunhap);
        tvTrangThai = view.findViewById(R.id.trangthai);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhan);
        arrayList = new ArrayList<>();
        DonNhanAdapter adapter = new DonNhanAdapter(DanhanActivity.this, R.layout.activity_donnhan, arrayList);
        listView.setAdapter(adapter);
        getDataFromFirebase();


    }
    private void getDataFromFirebase(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DonHangOnline").child("ShipperDaNhan");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList = new ArrayList<DonDaNhan>();

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    for (DataSnapshot snapshot2 : snapshot1.getChildren()){

                        DonDaNhan donDaNhan = snapshot2.getValue(DonDaNhan.class);
                        String diaChi = donDaNhan.getDiaChi();
                        String diemNhan = donDaNhan.getDiemnhan();
                        long thuNhap= donDaNhan.getThunhap();
                        String tenKhachhang = donDaNhan.getTenKhachHang();
                        String ghiChu = donDaNhan.getGhiChu();
                        String sdtkhachhang = donDaNhan.getSdtkhachhang();
                        long donGia= donDaNhan.getDonGia();
                        List<SanPham> sanpham = donDaNhan.getSanpham();
                        String time = donDaNhan.getTime();
                        Log.d("asd",sanpham.size()+"");
                        String idQuan = donDaNhan.getIdQuan();
                        String shipper = donDaNhan.getShipper();
                        String phoneShipper = donDaNhan.getPhoneShipper();
                        String key = donDaNhan.getKey();
                        int trangthai = donDaNhan.getTrangthai();
                        String idKhachHang = donDaNhan.getIdKhachhang();
                        String idDonHang= donDaNhan.getIdDonHang();
                        donDaNhanActivity = new DonDaNhan(diaChi,diemNhan,donGia,tenKhachhang,sdtkhachhang,thuNhap,sanpham, ghiChu,time,
                                idQuan,shipper,phoneShipper,key,trangthai,idKhachHang,idDonHang);
                        arrayList.add(donDaNhanActivity);
                    }
                }

                adapter.setData(arrayList);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}