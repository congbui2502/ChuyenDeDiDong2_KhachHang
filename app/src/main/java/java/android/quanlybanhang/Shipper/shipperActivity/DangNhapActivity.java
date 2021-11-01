package com.example.shipper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class DangNhapActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtDangNhap, edtMatKhau;
    CheckBox chk;
    String user,pass;
    private String idUser;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        btnDangNhap = (Button) findViewById(R.id.btn_dangnhap);
        edtDangNhap = (EditText) findViewById(R.id.edt_dangnhap);
        edtMatKhau = (EditText) findViewById(R.id.edt_matkhau);
        chk = (CheckBox) findViewById(R.id.saveUser);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    String strUser, strPass;
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
                        Toast.makeText(DangNhapActivity.this, "Signin error", Toast.LENGTH_SHORT).show();
                    } else {
                        idUser = mFirebaseAuth.getUid();
                        Intent intent = new Intent(DangNhapActivity.this, Home.class);
                        startActivity(intent);
                    }

                }
            });
        } else {
            Toast.makeText(DangNhapActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
        }
    }

//    public void checkLogin(View view){
//        strUser = edtDangNhap.getText().toString();
//        strPass = edtMatKhau.getText().toString();
//        if (strUser.isEmpty() || strPass.isEmpty())
//        {
//            Toast.makeText(getApplicationContext(),"Khong duoc de trong",
//                    Toast.LENGTH_LONG).show();
//
//        }
//        else if (isLogin(strUser,strPass)!=0)
//        {
//            Toast.makeText(getApplicationContext(),"Dang nhap thanh cong",
//                    Toast.LENGTH_LONG).show();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(DangNhapActivity.this, Home.class);
//                    startActivity(intent);
//                }
//            },2000);
//        }
//        else{
//            Toast.makeText(getApplicationContext(),"Ten dang nhap hoac mat khau khong dung",
//                    Toast.LENGTH_LONG).show();
//        }
//    }

}



