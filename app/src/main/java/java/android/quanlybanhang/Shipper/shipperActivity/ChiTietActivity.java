package com.example.shipper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fragment.HomeFragment;

public class ChiTietActivity extends AppCompatActivity {
    Button btnnhandon, btnquaylai;
    TextView tvdiemnhan,tvdiemgiao,tvtonggia;
    public static final String KEY_DIEMNHAN = "DIEMNHAN";
    public static final String KEY_DIEMGIAO = "DIEMGIAO";
    public static final String KEY_TONGGIA = "TONGGIA";
    public static final String KEY_BUNDLE = "BUNDLE";
    private DonHang donHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        Anhxa();


        btnnhandon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietActivity.this,HoaDonActivity.class);

//                tvdiemnhan.setText(intent.getBundleExtra(HomeFragment.KEY_DIEMNHAN));
                //Bundle bundle = intent.getBundleExtra()

                startActivity(intent);
            }
        });
        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietActivity.this, Home.class);
                startActivity(intent);
            }
        });
    }



    private void Anhxa(){
        btnnhandon = (Button) findViewById(R.id.btn_nhandon);
        btnquaylai = (Button) findViewById(R.id.btn_quaylai);
        tvdiemnhan = (TextView) findViewById(R.id.diemnhan);
        tvdiemgiao = (TextView) findViewById(R.id.diemgiao);
        tvtonggia = (TextView) findViewById(R.id.tonggia);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        donHang = (DonHang) bundle.getSerializable(KEY_DIEMNHAN);
//        Log.d("abc",donHang.getTenKhachHang());
    }
}