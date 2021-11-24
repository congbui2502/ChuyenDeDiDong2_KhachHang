package java.android.quanlybanhang.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import java.android.quanlybanhang.R;


public class DangNhapKhachHang extends AppCompatActivity {
    Button btnDangNhap;
    EditText edtDangNhap, edtMatKhau;
    TextView tv_quemk;
    CheckBox chk;
    String user,pass;
    private String idUser;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap_khachhang);

        btnDangNhap = (Button) findViewById(R.id.btn_dangnhap);
        edtDangNhap = (EditText) findViewById(R.id.edt_dangnhap);
        edtMatKhau = (EditText) findViewById(R.id.edt_matkhau);
        tv_quemk=findViewById(R.id.tv_quenmk);
        chk = (CheckBox) findViewById(R.id.saveUser);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tv_quemk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callForgetPassword();
            }
        });

    }
    private void callForgetPassword() {
        Intent intent = new Intent(DangNhapKhachHang.this, ForgotPasswordActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(edtDangNhap, "edt_username");
        pairs[1] = new Pair<View, String>(btnDangNhap, "button_sign");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DangNhapKhachHang.this, pairs);
            startActivity(intent, options.toBundle());
        }
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
            Toast.makeText(DangNhapKhachHang.this, "Fialds Are Empty!", Toast.LENGTH_LONG).show();
        } else if (!(strUser.isEmpty() && strPass.isEmpty())) {
            mFirebaseAuth.signInWithEmailAndPassword(strUser, strPass).addOnCompleteListener(DangNhapKhachHang.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(DangNhapKhachHang.this, "Signin error", Toast.LENGTH_SHORT).show();
                    } else {
                        idUser = mFirebaseAuth.getUid();
                        Intent intent = new Intent(DangNhapKhachHang.this, DangXuatKhachHang.class);
                        startActivity(intent);
                    }

                }
            });
        } else {
            Toast.makeText(DangNhapKhachHang.this, "Error Occurred!", Toast.LENGTH_LONG).show();
        }
    }

}



