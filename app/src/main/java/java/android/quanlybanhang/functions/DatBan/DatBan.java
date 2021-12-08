package java.android.quanlybanhang.functions.DatBan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.CongAdapter.ShopProductFragment;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatBan extends AppCompatActivity implements View.OnClickListener {
    private int mYear, mMonth, mDay, mHour, mMinute;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT_12 = "hh:mm:ss a";
    private static final String TIME_FORMAT_24 = "HH:mm:ss";
    private Toolbar toolbar;
    String id_ban;
    String id_khuvuc;
    String tenban;
    TextView tvtenkhachhang, tvsodienthoai, tvngayhientai, tvngaydatban, tvgiodatban, tvgiodatbankt, title;
    EditText editTextNumber;
    Button bnt_datngay, bnt_datgio, bnt_datgiokt, bnt_themkhuvuc;
    private CuaHang cuaHang;

    private KhachHang khachHang = KhachHangActivity.khachHang;
    private KhachHangActivity mainActivity;
    private Window window, window1, window2;
    private Dialog dialog, dialog1, dialog2, dialog3, dialog4;
    Timestamp timestamp;
    Timestamp timestamp1;
    String id_shop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ban);
        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        id_ban = intent.getStringExtra("id_ban");
        id_khuvuc = intent.getStringExtra("id_khuvuc");
        id_shop = intent.getStringExtra("id_shop");
        tenban = intent.getStringExtra("tenban");
        actionBar.setTitle("Đặt Bàn:" + tenban + "");
        tvtenkhachhang = findViewById(R.id.tvtenkhachhang);
        tvsodienthoai = findViewById(R.id.tvsodienthoai);
        editTextNumber = findViewById(R.id.editTextNumber);
        tvngayhientai = findViewById(R.id.tvngayhientai);
        tvngaydatban = findViewById(R.id.tvngaydatban);
        tvgiodatban = findViewById(R.id.tvgiodatban);
        tvgiodatbankt = findViewById(R.id.tvgiodatbankt);
        bnt_datngay = findViewById(R.id.bnt_datngay);
        bnt_datgio = findViewById(R.id.bnt_datgio);
        bnt_datgiokt = findViewById(R.id.bnt_datgiokt);
        bnt_themkhuvuc = findViewById(R.id.bnt_themkhuvuc);
        bnt_datgio.setOnClickListener(this);
        bnt_datngay.setOnClickListener(this);
        bnt_datgiokt.setOnClickListener(this);
        bnt_themkhuvuc.setOnClickListener(this);
        tvtenkhachhang.setText(khachHang.getNameKhachHang());
        tvsodienthoai.setText(khachHang.getSdtKhachHang());
        tvngayhientai.setText(hamlaydate());
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        switch (v.getId()) {
            case R.id.bnt_datngay:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tvngaydatban.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.bnt_datgio:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvgiodatban.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
            case R.id.bnt_datgiokt:
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvgiodatbankt.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog1.show();
                break;
            case R.id.bnt_themkhuvuc:
                dailonghoitruockhitao();
                dialog4.show();
                break;
        }
    }

    private void dailonghoitruockhitao() {
        dialog4 = new Dialog(DatBan.this);
        dialog4.setContentView(R.layout.dialog_thanhtoan_aleart);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog4.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog4.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog4.setCancelable(false); //Optional
        dialog4.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
        title = dialog4.findViewById(R.id.title);
        title.setText("Bạn!!Chắc Chắn Muốn Đặt Bàn");
        Button Okay = dialog4.findViewById(R.id.btn_okay);
        Button Cancel = dialog4.findViewById(R.id.btn_cancel);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa = tvgiodatban.getText().toString() + " " + tvngaydatban.getText().toString();
                String dd = tvgiodatbankt.getText().toString() + " " + tvngaydatban.getText().toString();
                try {
                    java.util.Date date1 = new SimpleDateFormat("hh:mm dd-MM-yyyy").parse(aa);
                    timestamp = new Timestamp(date1.getTime());
                    timestamp.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    java.util.Date date = new SimpleDateFormat("hh:mm dd-MM-yyyy").parse(dd);
                    timestamp1 = new Timestamp(date.getTime());
                    timestamp1.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (editTextNumber.getText().toString().isEmpty()) {
                    editTextNumber.setError("Hãy Nhập Số Tiền Đặt Trước");
                    editTextNumber.requestFocus();
                } else if (Double.parseDouble(editTextNumber.getText() + "") < 50000) {
                    editTextNumber.setError("số tiền lớn hơn 50.000 đ");
                    editTextNumber.requestFocus();
                } else if (tvngaydatban.getText().toString().equals("00:00:00")) {
                    hamdialogkiemtra();
                    title.setText("chưa nhập ngày đặt");
                    dialog3.show();

                } else if (tvgiodatban.getText().toString().equals("00:00:00")) {
                    hamdialogkiemtra();
                    title.setText("chưa chọn giờ đặt");
                    dialog3.show();

                } else if (tvgiodatbankt.getText().toString().equals("00:00:00")) {
                    hamdialogkiemtra();
                    title.setText("chưa chọn giờ kết thúc");
                    dialog3.show();
                } else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CuaHangOder").child(id_shop).child("DuyetDatBan").child(id_ban + "_" + id_khuvuc).child(timestamp.getTime() + "");
                    databaseReference.child("tenkhachhang").setValue(khachHang.getNameKhachHang());
                    databaseReference.child("id_bk").setValue(id_ban + "_" + id_khuvuc);
                    databaseReference.child("sodienthoai").setValue(khachHang.getSdtKhachHang());
                    databaseReference.child("sotiendattruoc").setValue(editTextNumber.getText().toString());
                    databaseReference.child("ngayhientai").setValue(tvngayhientai.getText().toString());
                    databaseReference.child("ngaydat").setValue(tvngaydatban.getText().toString());
                    databaseReference.child("giodat").setValue(tvgiodatban.getText().toString());
                    databaseReference.child("tenban").setValue(tenban);
                    databaseReference.child("trangthai").setValue("0");
                    databaseReference.child("gioketthuc").setValue(tvgiodatbankt.getText().toString());
                    Intent intent = new Intent(DatBan.this, KhachHangActivity.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                dialog4.dismiss();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog4.dismiss();
            }
        });

    }

    private void hamdialogkiemtra() {
        dialog3 = new Dialog(this);
        dialog3.setContentView(R.layout.dialog_canhbao);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog3.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog3.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog3.setCancelable(false);
        dialog3.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button Okay = dialog3.findViewById(R.id.btn_cancel);
        title = dialog3.findViewById(R.id.title);
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.dismiss();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_dsdatban, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemk) {

        int item_id = itemk.getItemId();
        TextView ad;


        if (item_id == R.id.menu1) {
            Intent intent = new Intent(DatBan.this, DanhSachDatBan.class);
            intent.putExtra("id_datban",  "");
            startActivity(intent);
            finish();
        }


        if (item_id == android.R.id.home) {
            onBackPressed();
            return true;
        }
    }

    public String hamlaydate() {
        String date = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new java.util.Date());
        return date;
    }
}