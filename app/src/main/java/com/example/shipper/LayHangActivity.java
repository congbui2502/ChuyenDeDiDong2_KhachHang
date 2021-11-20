package com.example.shipper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import fragment.HomeFragment;

public class LayHangActivity extends AppCompatActivity {
    Button btnhuydon, btndalayhang;
    TextView tvdiemnhan,tvdiemgiao,tvtonggia,tvtenkh,tvsodt,tvghichu,tvthunhap;
    private DonHang donHang;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    String idShipper;
    ListView tvtensp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        Anhxa();
        ControlButtonHuyDon();
        ControlButtonDaLayHang();
        mFirebaseAuth= FirebaseAuth.getInstance();
        idShipper = mFirebaseAuth.getUid();
    }
    private void ControlButtonHuyDon(){
        btnhuydon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LayHangActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Xác nhận hủy đơn");
                builder.setMessage("");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LayHangActivity.this, Home.class);
                        mFirebaseAuth=FirebaseAuth.getInstance();
                        String id = mFirebaseAuth.getUid();
                        String date = formatDateS(donHang.getTime());
//                        String [] keys = donHang.getTime().split(" ");
                        donHang.setTrangthai(0);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        db.child(donHang.getIdQuan()).child("donhangonline").child("dondadat").child(date).child(donHang.getKey()).child("trangthai").setValue(1);
                        db.child("DonHangOnline").child("DaDatDon").child(donHang.getIdKhachhang()).child(donHang.getIdDonHang()).setValue(donHang);
                        db.child("DonHangOnline").child("ShipperDaNhan").child(idShipper).child(donHang.getIdDonHang()).removeValue();
                        db.child("Shipper").child(id).child("lichSuDonOnline").push().setValue(donHang);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(HomeFragment.KEY_DIEMNHAN,donHang);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
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

    private void ControlButtonDaLayHang(){
        btndalayhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LayHangActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Xác nhận đã lấy hàng");
                builder.setMessage("");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LayHangActivity.this,HoaDonActivity.class);
                        mFirebaseAuth=FirebaseAuth.getInstance();
                        String id = mFirebaseAuth.getUid();
                        String [] keys = donHang.getTime().split(" ");
                        donHang.setTrangthai(2);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        String date = formatDateS(donHang.getTime());
                        db.child("CuaHangOder").child(donHang.getIdQuan()).child("donhangonline").child("dondadat").child(date).child(donHang.getKey()).child("trangthai").setValue(5);
                        db.child("DonHangOnline").child("DaLayHang").child(idShipper).child(donHang.getIdDonHang()).setValue(donHang);
                        db.child("DonHangOnline").child("ShipperDaNhan").child(idShipper).child(donHang.getIdDonHang()).removeValue();
//                        db.child("Shipper").child(id).child("lichSuDonOnline").push().setValue(donHang);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(HomeFragment.KEY_DIEMNHAN,donHang);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
    private void Anhxa(){
        btndalayhang = (Button) findViewById(R.id.btn_nhandon);
        btnhuydon = (Button) findViewById(R.id.btn_quaylai);
        tvdiemnhan = (TextView) findViewById(R.id.diemnhan);
        tvdiemgiao = (TextView) findViewById(R.id.diemgiao);
        tvtonggia = (TextView) findViewById(R.id.tonggia);
        tvtenkh = (TextView) findViewById(R.id.tenKH);
        tvsodt = (TextView) findViewById(R.id.sdt);
        tvghichu = (TextView) findViewById(R.id.ghiChu);
        tvthunhap = (TextView) findViewById(R.id.tv_thunhap);
        tvtensp = (ListView) findViewById(R.id.tenSanPham);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        donDaNhan = (DonDaNhan) bundle.getSerializable(DaNhanActivity.KEY_DIEMNHAN);
        donHang = (DonHang) bundle.getSerializable(HomeFragment.KEY_DIEMNHAN);
        Log.d("bbb",donHang.getTenKhachHang());
        tvdiemnhan.setText(donHang.getDiemnhan());
        tvdiemgiao.setText(donHang.getDiaChi());
        tvtonggia.setText( donHang.getDonGia()+"");
        tvtenkh.setText( donHang.getTenKhachHang());
        tvsodt.setText(donHang.getSdtkhachhang());
        tvthunhap.setText(donHang.getThunhap()+"");
        tvghichu.setText(donHang.getGhiChu());

//        tvdiemnhan.setText(donDaNhan.getDiemnhan());
//        tvdiemgiao.setText(donDaNhan.getDiaChi());
//        tvtonggia.setText( donDaNhan.getDonGia()+"");
//        tvtenkh.setText( donDaNhan.getTenKhachHang());
//        tvsodt.setText(donDaNhan.getSdtkhachhang());
//        tvthunhap.setText(donDaNhan.getThunhap()+"");
//        tvghichu.setText(donDaNhan.getGhiChu());
        btndalayhang.setText("Đã lấy hàng");
        btnhuydon.setText("Hủy đơn");
}
}
