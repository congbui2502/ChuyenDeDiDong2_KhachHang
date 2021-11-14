package com.example.shipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fragment.HistoryFragment;

public class LichSuActivity extends AppCompatActivity {
    Button btnquaylai;
    TextView tvdiemnhan,tvdiemgiao,tvtonggia,tvtenkh,tvsodt,tvghichu,tvthunhap,tvtensp;
    public static final String KEY_LICHSU = "LICHSU";
    private History history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietlichsu_activity);
        Anhxa();
//        btnquaylai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LichSuActivity.this, Home.class);
//                startActivity(intent);
//            }
//        });
    }



    private void Anhxa(){
        tvdiemnhan = (TextView) findViewById(R.id.diemnhan);
        tvdiemgiao = (TextView) findViewById(R.id.diemgiao);
        tvtonggia = (TextView) findViewById(R.id.tonggia);
        tvtenkh = (TextView) findViewById(R.id.tenKH);
        tvsodt = (TextView) findViewById(R.id.sdt);
        tvghichu = (TextView) findViewById(R.id.ghiChu);
        tvthunhap = (TextView) findViewById(R.id.tv_thunhap);
        tvtensp = (TextView) findViewById(R.id.tenSanPham);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        history = (History) bundle.getSerializable(HistoryFragment.KEY_LICHSU);
        tvdiemnhan.setText(history.getDiemnhan());
        tvdiemgiao.setText(history.getDiaChi());
        tvtonggia.setText( history.getThunhap()+"");
        tvtenkh.setText( history.getTenKhachhang());
        tvsodt.setText(history.getSdtkhachhang());
        tvthunhap.setText(history.getThunhap()+"");
        tvghichu.setText(history.getGhiChu());
    }
}
