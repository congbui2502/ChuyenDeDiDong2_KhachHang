package com.example.shipper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.DangGiaoAdapter;


public class DangGiaoActivity extends AppCompatActivity {
    public static final String KEY_DIEMNHAN = "DIEMNHAN";
    public static final String KEY_DIEMGIAO = "DIEMGIAO";
    public static final String KEY_TONGGIA = "TONGGIA";
    public static final String KEY_BUNDLE = "BUNDLE";
    private TextView tvDiemNhan,tvDiemGiao,tvTongGia, tvThuNhap,tvTrangThai;
    private DatabaseReference databaseReference;
    private Context context;
    ListView listView;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    String idShipper;
    Button btnquaylai;
    ArrayList<DonHang> arrayList;
    private DangGiaoAdapter adapter;
    DonHang donHangActivity = new DonHang();
    private void AnhXa(){
        listView = findViewById(R.id.lv_danggiao);
        tvDiemNhan = findViewById(R.id.tv_diemnhan);
        tvDiemGiao= findViewById(R.id.tv_diemgiao);
        tvTongGia = findViewById(R.id.tv_tonggia);
        tvThuNhap = findViewById(R.id.tv_thunhap);
        tvTrangThai = findViewById(R.id.trangthai);
        btnquaylai = findViewById(R.id.btn_back);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_giao);
        AnhXa();
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        idShipper = mFirebaseAuth.getUid();
        arrayList = new ArrayList<>();
        getDataFromFirebase();
        adapter = new DangGiaoAdapter(DangGiaoActivity.this, R.layout.activity_dang_giao, arrayList);
        listView.setAdapter(adapter);
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangGiaoActivity.this,Home.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DangGiaoActivity.this, HoaDonActivity.class);
                DonHang donHang = arrayList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_DIEMNHAN,donHang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void getDataFromFirebase(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DonHangOnline").child("DaLayHang").child(idShipper);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList = new ArrayList<DonHang>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        DonHang donHang = snapshot1.getValue(DonHang.class);


                    arrayList.add(donHang);

                }
//                for (DataSnapshot snapshot1 : snapshot.getChildren()){
//
//                    DonHang donHang = snapshot1.getValue(DonHang.class);
//                    String diaChi = donHang.getDiaChi();
//                    String diemNhan = donHang.getDiemnhan();
//                    long thuNhap= donHang.getThunhap();
//                    String tenKhachhang = donHang.getTenKhachHang();
//                    String ghiChu = donHang.getGhiChu();
//                    String sdtkhachhang = donHang.getSdtkhachhang();
//                    long donGia= donHang.getDonGia();
//                    List<SanPham> sanpham = donHang.getSanpham();
//                    String time = donHang.getTime();
//                    String idQuan = donHang.getIdQuan();
//                    String shipper = donHang.getShipper();
//                    String phoneShipper = donHang.getPhoneShipper();
//                    String key = donHang.getKey();
//                    int trangthai = donHang.getTrangthai();
//                    String idKhachHang = donHang.getIdKhachhang();
//                    String idDonHang= donHang.getIdDonHang();
//                    donHangActivity = new DonHang(diaChi,diemNhan,donGia,tenKhachhang,sdtkhachhang,thuNhap,sanpham, ghiChu,time,
//                            idQuan,shipper,phoneShipper,key,trangthai,idKhachHang,idDonHang);
//                    arrayList.add(donHangActivity);
//
//
//                }
//                Toast.makeText(DangGiaoActivity.this,donHang.getDiaChi()+"aaaa",Toast.LENGTH_SHORT).show();
                adapter.setData(arrayList);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
