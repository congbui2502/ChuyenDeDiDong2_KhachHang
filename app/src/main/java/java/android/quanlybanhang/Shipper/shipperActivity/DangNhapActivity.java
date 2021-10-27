package com.example.shipper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class DangNhapActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtDangNhap, edtMatKhau;
    CheckBox chk;
    String user,pass;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        btnDangNhap = (Button) findViewById(R.id.btn_dangnhap);
        edtDangNhap = (EditText) findViewById(R.id.edt_dangnhap);
        edtMatKhau = (EditText) findViewById(R.id.edt_matkhau);
        chk = (CheckBox) findViewById(R.id.saveUser);



    }

    String strUser, strPass;
    public int isLogin(String user, String pass){
        if (user.equals("hung")&& pass.equals("123")){
            return 1;
        }else {
            return 0;
        }
    }

    public void checkLogin(View view){
        strUser = edtDangNhap.getText().toString();
        strPass = edtMatKhau.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Khong duoc de trong",
                    Toast.LENGTH_LONG).show();

        }
        else if (isLogin(strUser,strPass)!=0)
        {
            Toast.makeText(getApplicationContext(),"Dang nhap thanh cong",
                    Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(DangNhapActivity.this, Home.class);
                    startActivity(intent);
                }
            },2000);
        }
        else{
            Toast.makeText(getApplicationContext(),"Ten dang nhap hoac mat khau khong dung",
                    Toast.LENGTH_LONG).show();
        }
    }

}



