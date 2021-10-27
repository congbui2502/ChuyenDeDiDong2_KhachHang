package com.example.shipper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fragment.HomeFragment;

public class HoaDonActivity extends AppCompatActivity {
    Button btnhuydon, btnhoanthanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        Anhxa();
        ControlButton();
        ControlButtonHoanThanh();



    }
    private void ControlButton(){
        btnhuydon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Xác nhận hủy đơn");
                builder.setMessage("");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HoaDonActivity.this, Home.class);
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
    private void ControlButtonHoanThanh(){
        btnhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Xác nhận hoàn thành");
                builder.setMessage("");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HoaDonActivity.this,Home.class);
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
        btnhuydon = (Button) findViewById(R.id.btn_huydon);
        btnhoanthanh = (Button) findViewById(R.id.btn_hoanthanh);
    }
}