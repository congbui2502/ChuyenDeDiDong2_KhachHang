package java.android.quanlybanhang.functions.DatBan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DanhSachDatBan extends AppCompatActivity {
    ProgressBar progressBar;
    TextView rong;
    private Toolbar toolbar;
    private DatabaseReference mDatabase;
    String id_ban;
    String id_bk;
    String id;
    RecyclerView recyclerView;
    RvDatBanAdapter datBanAdapter;
    ArrayList<DatBanModel> datBanModels;
    ArrayList<ID_datban> ID_datbans = new ArrayList<>();
    private DatabaseReference mDatabase1;
    String id_khuvuc, tenban, id_shop, abc;
    Button bnt_loc;
    private DatePickerDialog datePickerDialog;
    private String ngay;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dat_ban);
        Intent intent = getIntent();
        id_ban = intent.getStringExtra("id_ban");
        id_khuvuc = intent.getStringExtra("id_khuvuc");
        tenban = intent.getStringExtra("tenban");
        id_shop = intent.getStringExtra("id_shop");
        abc = id_ban + "_" + id_khuvuc;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        rong = findViewById(R.id.tvchuadattruoc);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.toolbars);
        bnt_loc = findViewById(R.id.bnt_loc);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách Đặt " + tenban.toUpperCase(Locale.ROOT));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //loc

        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.rv_1);
        datBanAdapter = new RvDatBanAdapter(ID_datbans, DanhSachDatBan.this);
        datBanAdapter.SetData(ID_datbans);
        recyclerView.setAdapter(datBanAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachDatBan.this, LinearLayoutManager.VERTICAL, false));

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String mDay = day + "";
        if (day < 10) {
            mDay = 0 + "" + day;
        }

        ngay = mDay + "-" + (month + 1) + "-" + year;

        datePickerDialog = new DatePickerDialog(DanhSachDatBan.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String d = dayOfMonth + "";
                if (dayOfMonth < 10) {
                    d = "0" + dayOfMonth;
                }
                ngay = d + "-" + (month + 1) + "-" + year;
//                Log.d("ngaydat",ngay+"");

                hamdatban();

            }
        }, year, month, day);


        bnt_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();

            }
        });
        hamdatban();
    }

    private void hamdatban() {
        databaseReference.child("CuaHangOder").child(id_shop).child("DatBan").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ID_datbans = new ArrayList<>();
                ID_datbans.clear();
                datBanAdapter.SetData(ID_datbans);
                if (snapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        datBanModels = new ArrayList<>();
                        id = postSnapshot.getKey();
                        DataSnapshot sss = postSnapshot;
                        for (DataSnapshot aaa : sss.getChildren()) {
                            id_bk = aaa.child("id_bk").getValue() + "";
                            if (aaa.child("ngaydat").getValue().toString().equals(ngay)) {
                                if (id_bk.equals(abc)) {
                                    Log.d("ngaydat", aaa.getValue() + "jdjshdjshd");
                                    String id_ngaydat = aaa.getKey();
                                    String giodat = aaa.child("giodat").getValue() + "";
                                    String gioketthuc = aaa.child("gioketthuc").getValue() + "";
                                    String ngaydat = aaa.child("ngaydat").getValue() + "";
                                    String ngayhientai = aaa.child("ngayhientai").getValue() + "";
                                    String sodienthoai = aaa.child("sodienthoai").getValue() + "";
                                    String sotiendattruoc = aaa.child("sotiendattruoc").getValue() + "";
                                    String tenkhachhang = aaa.child("tenkhachhang").getValue() + "";
                                    String tenban = aaa.child("tenban").getValue() + "";
                                    datBanModels.add(new DatBanModel(id_ngaydat, giodat, gioketthuc, id_bk, ngaydat, ngayhientai, sodienthoai, sotiendattruoc, tenkhachhang, tenban));
                                    ID_datban datban = new ID_datban(id, datBanModels);
                                    ID_datbans.add(datban);
                                    datBanAdapter.SetData(ID_datbans);
                                }

                            } else {
                                Log.d("ngaydat", "khong");
                            }

                        }
                    }


                } else {
                    rong.setVisibility(View.VISIBLE);

                }
                progressBar.setVisibility(View.INVISIBLE);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}