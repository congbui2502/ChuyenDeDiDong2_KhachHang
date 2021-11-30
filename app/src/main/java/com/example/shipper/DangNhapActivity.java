package com.example.shipper;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fragment.HomeFragment;


public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor1;
    Button btnDangNhap, btnDangKi, btnClear;
    EditText edtDangNhap, edtMatKhau;
    CheckBox chk;
    TextView tvQuenMK;
    String strUser, strPass;
    private String idUser;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);


        btnDangNhap = (Button) findViewById(R.id.btn_dangnhap);
        edtDangNhap = (EditText) findViewById(R.id.edt_dangnhap);
        edtMatKhau = (EditText) findViewById(R.id.edt_matkhau);
        tvQuenMK = (TextView) findViewById(R.id.tv_quenmk);
        chk = (CheckBox) findViewById(R.id.saveUser);
        btnDangKi = (Button) findViewById(R.id.btn_dangki);
        btnClear = (Button) findViewById(R.id.btn_clearUser);
        HomeFragment.flag=0;
        btnDangNhap.setOnClickListener(this);
        initPreferences();
        String savedUser = sharedPreferences.getString("USER","");
        String savedPass = sharedPreferences1.getString("PASS","");
        edtDangNhap.setText(savedUser);
        edtMatKhau.setText(savedPass);
        chk.setOnClickListener(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, ForgotPasswordActivity.class);
               startActivity(intent);
            }
        });
        btnDangKi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DangNhapActivity.this,DangkyActivity.class);


                startActivity(intent);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor1 = sharedPreferences1.edit();
    }
    private void clearData() {
        edtMatKhau.setText("");
        edtDangNhap.setText("");
        editor.clear();
        editor.commit();
        editor1.clear();
        editor1.commit();
    }
    private void login() {
        strUser = edtDangNhap.getText().toString();
        strPass = edtMatKhau.getText().toString();
        mFirebaseAuth = FirebaseAuth.getInstance();
        if (strUser.isEmpty()) {
            edtDangNhap.setError("Plese enter email id");
            edtDangNhap.requestFocus();
        } else if (strPass.isEmpty()) {
            edtMatKhau.setError("Plese enter your password");
            edtMatKhau.requestFocus();
        } else if (strUser.isEmpty() && strPass.isEmpty()) {
            Toast.makeText(DangNhapActivity.this, "Fialds Are Empty!", Toast.LENGTH_LONG).show();
        } else if (!(strUser.isEmpty() && strPass.isEmpty())) {
            mFirebaseAuth.signInWithEmailAndPassword(strUser, strPass).addOnCompleteListener(DangNhapActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(DangNhapActivity.this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    } else {
                        idUser = mFirebaseAuth.getUid();
                        Intent intent = new Intent(DangNhapActivity.this, Home.class);
                        String user = edtDangNhap.getText().toString();
                        editor.putString("USER", user);
                        editor.commit();
                        startActivity(intent);
                    }
                }
            });
        } else {
            Toast.makeText(DangNhapActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == chk){
            String pass = edtMatKhau.getText().toString();
            editor1.putString("PASS",pass);
            editor1.commit();
            Toast.makeText(DangNhapActivity.this,"Đã lưu thông tin đăng nhập",Toast.LENGTH_SHORT).show();
        }
    }
}



