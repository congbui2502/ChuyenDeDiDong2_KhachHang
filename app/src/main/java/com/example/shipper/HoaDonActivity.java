package com.example.shipper;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.Manifest;
import android.annotation.SuppressLint;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class HoaDonActivity extends AppCompatActivity {
    Button btnthatbai, btnhoanthanh,btncall,btnback;
    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;

    private static final String LOG_TAG = "AndroidExample";
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    TextView tvdiemnhan,tvdiemgiao,tvtonggia,tvtenkh,tvsodt,tvghichu,tvthunhap;
    ListView tvtensp;
    private int soDon;
    private DonHang donHang;
    ArrayList<SanPham> arrayList;
    String phone, name;
    String idShipper;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        mFirebaseAuth=FirebaseAuth.getInstance();
        Anhxa();
        ControlButton();
        ControlButtonHoanThanh();
        onDataChange();
        idShipper = mFirebaseAuth.getUid();
//        ButtonBack();
        arrayList = new ArrayList<>();
        SanPhamAdapter adapter = new SanPhamAdapter(HoaDonActivity.this, R.layout.activity_sanpham, arrayList);
        tvtensp.setAdapter(adapter);
        adapter.setData(donHang.getSanpham());
        getSoDonHang();
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissionAndCall();
            }
        });

    }
    private void ButtonBack(){
        Intent intent = new Intent(HoaDonActivity.this,Home.class);
        startActivity(intent);
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
    private void ControlButton(){
        btnthatbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Xác nhận thất bại");
                builder.setMessage("");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HoaDonActivity.this, Home.class);
                        mFirebaseAuth=FirebaseAuth.getInstance();
                        String id = mFirebaseAuth.getUid();
                        String date = formatDateS(donHang.getTime());
                        soDon--;
                        donHang.setTrangthai(3);
                        db.child("Shipper").child(id).child("donChuaGiao").setValue(soDon);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        db.child("CuaHangOder").child(donHang.getIdQuan()).child("donhangonline").child("dondadat").child(date).child(donHang.getKey()).child("trangthai").setValue(6);
                        db.child("DonHangOnline").child("ShipperDaNhan").child(donHang.getIdKhachhang()).child(donHang.getIdDonHang()).removeValue();
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
    private void onDataChange()
    {
        mFirebaseAuth= FirebaseAuth.getInstance();
        String id=mFirebaseAuth.getUid();
        if (id!=null){
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Shipper").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Shipper sp=snapshot.getValue(Shipper.class);
                    name = sp.getNameShipper();
                    phone = sp.getPhoneShipper();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }
    private void ControlButtonHoanThanh(){
        btnhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=mFirebaseAuth.getUid();

                AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Xác nhận hoàn thành");
                builder.setMessage("");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HoaDonActivity.this,Home.class);
                        mFirebaseAuth=FirebaseAuth.getInstance();
                        soDon--;
                        db.child("Shipper").child(id).child("donChuaGiao").setValue(soDon);

                        donHang.setTrangthai(4);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        String date = formatDateS(donHang.getTime());

                        db.child("CuaHangOder").child(donHang.getIdQuan()).child("donhangonline").child("dondadat").child(date).child(donHang.getKey()).child("trangthai").setValue(6);
                        db.child("DonHangOnline").child("ShipperDaGiao").child(donHang.getIdKhachhang()).child(donHang.getKey()).setValue(donHang);
                        db.child("DonHangOnline").child("ShipperDaGiao").child(donHang.getIdKhachhang()).child(donHang.getKey()).child("idShipper").setValue(idShipper);
                        db.child("DonHangOnline").child("ShipperDaGiao").child(donHang.getIdKhachhang()).child(donHang.getKey()).child("nameShipper").setValue(name);
                        db.child("DonHangOnline").child("ShipperDaGiao").child(donHang.getIdKhachhang()).child(donHang.getKey()).child("phoneShipper").setValue(phone);
                        db.child("CuaHangOder").child(donHang.getIdQuan()).child("donhangonline").child("donhoanthanh").child(date).child(donHang.getKey()).setValue(donHang);
                        db.child("CuaHangOder").child(donHang.getIdQuan()).child("donhangonline").child("donhoanthanh").child(date).child(donHang.getKey()).child("idShipper").setValue(idShipper);
                        db.child("DonHangOnline").child("DaLayHang").child(idShipper).child(donHang.getIdDonHang()).removeValue();
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
    private void Anhxa(){
        btncall = (Button) findViewById(R.id.btn_call);
        btnhoanthanh = (Button) findViewById(R.id.btn_hoanthanh);
        btnback = (Button) findViewById(R.id.btn_trove);
        btnthatbai = (Button) findViewById(R.id.btn_thatbai);
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
        donHang = (DonHang) bundle.getSerializable(HomeFragment.KEY_DIEMNHAN);

        tvdiemnhan.setText(donHang.getDiemnhan());
        tvdiemgiao.setText(donHang.getDiaChi());
        tvtonggia.setText( donHang.getDonGia()+"");
        tvtenkh.setText( donHang.getTenKhachhang());
        tvsodt.setText(donHang.getSdtkhachhang());
        tvthunhap.setText(donHang.getThunhap()+"");
        tvghichu.setText(donHang.getGhiChu());


//        btnhoanthanh.setText("Hoàn thành");
//        btnthatbai.setText("Thất bại");

    }
}
