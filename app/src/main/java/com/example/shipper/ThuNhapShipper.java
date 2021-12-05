package com.example.shipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fragment.CaNhanFragment;

public class ThuNhapShipper extends AppCompatActivity {
    String arr[]={
            "Theo Ngày",
            "Theo Tuần",
            "Theo Tháng"};
    Spinner spinner;
    TextView tv_thunhap,soDonHang,tongDonHang;
    Button thoat;
    ArrayList<History> arrayList;

    private DatabaseReference databaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_nhap_shipper);
        arrayList=new ArrayList<>();
        spinner=findViewById(R.id.spnNgayGio);
        soDonHang=findViewById(R.id.textSoDonHang);
        tongDonHang=findViewById(R.id.textSoluong);
        thoat=findViewById(R.id.quaylai);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThuNhapShipper.this, Home.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spinner.setOnItemSelectedListener(new MyProcessEvent());
        tv_thunhap=findViewById(R.id.textGianhap);
    }
    //Class tạo sự kiện
    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
            Log.d("abcd", "onItemSelected: "+simpleDateFormat.format(calendar.getTime()));
            mFirebaseAuth=FirebaseAuth.getInstance();
            String id=mFirebaseAuth.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Shipper").child(id).child("lichSuDonOnline");
            switch (arg2){
                case 0:
                    arrayList.clear();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                History history=snapshot1.getValue(History.class);
                                String[] date= history.getTime().split(" ");
                                long thunhap=0;
                                long donGia=0;
                                if(simpleDateFormat.format(calendar.getTime()).equals(date[1])){
                                    arrayList.add(history);
                                }
                                for (int i=0;i<arrayList.size();i++){
                                    thunhap += arrayList.get(i).getThunhap();
                                    donGia += arrayList.get(i).getDonGia();
                                }
                                tv_thunhap.setText("Tổng Thu Nhập: "+thunhap+"");
                                soDonHang.setText("Số Đơn Hàng: "+arrayList.size());
                                tongDonHang.setText("Tổng Tiền Đơn Hàng: "+donGia+"");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    break;
                case 1:
                    arrayList.clear();
                    ArrayList<String> list= ngayTrongTuan();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                History history=snapshot1.getValue(History.class);
                                String[] date= history.getTime().split(" ");
                                long thunhap=0;
                                long donGia=0;
                                for (int i=0;i<list.size();i++){
                                    if(list.get(i).equals(date[1])){
                                        arrayList.add(history);
                                        Log.d("trietdeptrai", "onDataChange: "+arrayList.size());
                                    }
                                }
                                for (int i=0;i<arrayList.size();i++){
                                    thunhap += arrayList.get(i).getThunhap();
                                    donGia += arrayList.get(i).getDonGia();
                                }
                                tv_thunhap.setText("Tổng Thu Nhập: "+thunhap+"");
                                soDonHang.setText("Số Đơn Hàng: "+arrayList.size());
                                tongDonHang.setText("Tổng Tiền Đơn Hàng: "+donGia+"");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    break;
                case 2:
                    arrayList.clear();
                    ArrayList<String> list1= ngayTrongThang();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                History history=snapshot1.getValue(History.class);
                                long thunhap=0;
                                long donGia=0;
                                String[] date= history.getTime().split(" ");
                                for (int i=0;i<list1.size();i++){
                                    if(list1.get(i).equals(date[1])){
                                        arrayList.add(history);
                                        Log.d("trietdeptrai", "onDataChange: "+arrayList.size());
                                    }
                                }
                                for (int i=0;i<arrayList.size();i++){
                                    thunhap += arrayList.get(i).getThunhap();
                                    donGia += arrayList.get(i).getDonGia();
                                }
                                tv_thunhap.setText("Tổng Thu Nhập: "+thunhap+"");
                                soDonHang.setText("Số Đơn Hàng: "+arrayList.size());
                                tongDonHang.setText("Tổng Tiền Đơn Hàng: "+donGia+"");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    break;
                default:
            }
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            Toast.makeText(ThuNhapShipper.this, "None", Toast.LENGTH_SHORT).show();
        }

    }
    public ArrayList<String> ngayTrongTuan() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngayBatDau=df.format(calendar.getTime());
        calendar.add(Calendar.DATE,6);
        String ngayKT=df.format(calendar.getTime());
        ArrayList<String> arrNgay = new ArrayList<String>();
        int days = truThoiGian(ngayBatDau, ngayKT);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(ngayKT);
        } catch (ParseException e) {

        }
        cal.setTime(date);
        if (days != 0) {
            arrNgay.add(ngayKT.replaceAll("/", "-"));
            for (int i = 0; i < days; i++) {
                String ngay = CustomNgay(cal, -1).replaceAll("/", "-");
                arrNgay.add(ngay);
            }
        } else {
            String ngay = ngayBatDau.replaceAll("/", "-");
            arrNgay.add(ngay);
        }
        return arrNgay;
    }
    public int truThoiGian(String ngayBatDau, String ngayKetThuc){
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(ngayBatDau);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(ngayKetThuc);
        }catch (ParseException e){
            return 0;
        }
        //int days = Days.daysBetween(date1, date2).getDays();
        int days = (int)( (date2.getTime() - date1.getTime()) / (1000 * 60 * 60  * 24));
        return days;
    }
    private String CustomNgay(Calendar calendar, int amount) {
        String dinhDang = "dd/MM/yyyy";
        calendar.add(Calendar.DAY_OF_YEAR, amount);
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(dinhDang);
        String startDate = formatter.format(date);
        return startDate;
    }
    public ArrayList<String> ngayTrongThang(){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        String ngayBD="01/" + month+"/"+year;
        String ngayKT=setDateStartEnd(ngayBD);
        ArrayList<String> arrNgay = new ArrayList<String>();

        int days =truThoiGian(ngayBD, ngayKT);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(ngayKT);
        } catch (ParseException e) {

        }
        cal.setTime(date);
        if (days != 0) {
            arrNgay.add(ngayKT.replaceAll("/", "-"));
            for (int i = 0; i < days; i++) {
                String ngay = CustomNgay(cal, -1).replaceAll("/", "-");
                arrNgay.add(ngay);
            }
        } else {
            String ngay = ngayBD.replaceAll("/", "-");
            arrNgay.add(ngay);
        }
        return arrNgay;
    }
    private String setDateStartEnd(String dateT) {
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String ngayKT = "";
        try {
            cal1.setTime(sdf.parse(dateT));
            cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            ngayKT = df.format(cal1.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ngayKT;
    }
}
