package com.example.shipper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.SanPhamAdapter;
import fragment.HomeFragment;

public class LayHangActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;
    private static final String LOG_TAG = "AndroidExample";
    Button btnhuydon, btndalayhang,btncall,btnquaylai;
    TextView tvdiemnhan,tvdiemgiao,tvtonggia,tvtenkh,tvsodt,tvghichu,tvthunhap;
    private DonHang donHang;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    String idShipper;
    ListView tvtensp;
    private int soDon = 0;
    ArrayList<SanPham> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layhang);
        Anhxa();
        ControlButtonHuyDon();
        ControlButtonDaLayHang();
        ButtonCall();

        ButtonBack();
        mFirebaseAuth= FirebaseAuth.getInstance();
        idShipper = mFirebaseAuth.getUid();
        getSoDonHang();
        arrayList = new ArrayList<>();
        SanPhamAdapter adapter = new SanPhamAdapter(LayHangActivity.this, R.layout.activity_sanpham, arrayList);
        tvtensp.setAdapter(adapter);
        adapter.setData(donHang.getSanpham());
    }
    private void ButtonBack(){
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LayHangActivity.this,Home.class);
                startActivity(intent);
            }
        });
    }
    private void ButtonCall(){
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissionAndCall();
            }
        });
    }
    private void getSoDonHang(){


        String id=mFirebaseAuth.getUid();
        db.child("Shipper").child(id).child("donChuaGiao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                soDon = snapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                        soDon--;
                        db.child("Shipper").child(id).child("donChuaGiao").setValue(soDon);
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
    private void askPermissionAndCall() {

        // With Android Level >= 23, you have to ask the user
        // for permission to Call.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

            // Check if we have Call permission
            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);

            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSION_REQUEST_CODE_CALL_PHONE
                );
                return;
            }
        }
        this.callNow();
    }
    @SuppressLint("MissingPermission")
    private void callNow() {
        String phoneNumber = this.tvsodt.getText().toString();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            this.startActivity(callIntent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Your call failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE_CALL_PHONE: {

                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (CALL_PHONE).
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i( LOG_TAG,"Permission granted!");
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();

                    this.callNow();
                }
                // Cancelled or denied.
                else {
                    Log.i( LOG_TAG,"Permission denied!");
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    // When results returned
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_PERMISSION_REQUEST_CODE_CALL_PHONE) {
            if (resultCode == RESULT_OK) {
                // Do something with data (Result returned).
                Toast.makeText(this, "Action OK", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
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
        btnquaylai = findViewById(R.id.btn_back);
        btncall = (Button) findViewById(R.id.btn_call);
        btndalayhang = (Button) findViewById(R.id.btn_dalayhang);
        btnhuydon = (Button) findViewById(R.id.btn_huydon);
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

        tvdiemnhan.setText(donHang.getDiemnhan());
        tvdiemgiao.setText(donHang.getDiaChi());
        tvtonggia.setText( donHang.getDonGia()+"");
        tvtenkh.setText( donHang.getTenKhachhang());
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
//        btndalayhang.setText("Đã lấy hàng");
//        btnhuydon.setText("Hủy đơn");
}
}
