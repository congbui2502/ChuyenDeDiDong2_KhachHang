package java.android.quanlybanhang.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhachHang;

public class DangkyKhachHang extends AppCompatActivity implements View.OnClickListener {
    private Button signinNow, signup;
    public static final String KEY_KHACHHANG="KHACHHANG";
    private KhachHang khachHang;
    private TextInputEditText username, email, phone,date, password, confirm_password;
    private CardView google;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor1;
    //Firebase
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mFirebaseAuth;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_khachhang);

        signinNow = findViewById(R.id.btn_signin_now);
        signup = findViewById(R.id.btn_signup);
        date = findViewById(R.id.edt_date);
        username = findViewById(R.id.edt_username);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        password = findViewById(R.id.edt_password);
        confirm_password = findViewById(R.id.edt_confirm_password);
        initPreferences();
        String savedUser = sharedPreferences.getString("USER","");
        String savedPass = sharedPreferences1.getString("PASS","");

        signinNow.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signin_now:
                Intent intent = new Intent(DangkyKhachHang.this,DangNhapKhachHangActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                signup();
                break;
        }
//        if (v == chk){
//            String pass = edtMatKhau.getText().toString();
//            editor1.putString("PASS",pass);
//            editor1.commit();
//            Toast.makeText(DangNhapActivity.this,"Đã lưu thông tin đăng nhập",Toast.LENGTH_SHORT).show();
//        }
    }

    private void signup(){
        progressBar2.setVisibility(View.VISIBLE);
        mFirebaseAuth = FirebaseAuth.getInstance();

        String mail = email.getText().toString();
        String userName = username.getText().toString();
        String mPhone = phone.getText().toString();
        String dt = date.getText().toString();
        String pass = password.getText().toString();
        String cpass = confirm_password.getText().toString();


        if(userName.isEmpty()){

            username.setError("Plese enter username");
            username.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        }else if(mail.isEmpty()){
            email.setError("Plese enter email");
            email.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        }else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("please provide valid email");
            email.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        } else if(mPhone.isEmpty()){
            phone.setError("Plese enter email");
            phone.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        } else if(dt.isEmpty()){
            date.setError("Plese enter date");
            date.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        }else if(pass.isEmpty()){
            password.setError("Plese enter password");
            password.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        }else if(cpass.isEmpty()){
            confirm_password.setError("Plese enter confirm password");
            confirm_password.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        }else if (!pass.equals(cpass)){
            confirm_password.setError("Passwords are not the sames");
            confirm_password.requestFocus();
            progressBar2.setVisibility(View.INVISIBLE);
        }
        else if(mail.isEmpty() && pass.isEmpty()){
            progressBar2.setVisibility(View.INVISIBLE);
            Toast.makeText(DangkyKhachHang.this,"Fialds Are Empty!", Toast.LENGTH_LONG).show();
        }else if(!(mail.isEmpty() && pass.isEmpty() && mPhone.isEmpty() &&userName.isEmpty() )){
            mFirebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(DangkyKhachHang.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(DangkyKhachHang.this, "SignUp UnSuccessful, plese Try Again", Toast.LENGTH_SHORT).show();
                    }else{
                        String UID = mFirebaseAuth.getUid();
                        mFirebaseInstance = FirebaseDatabase.getInstance();
                        mFirebaseDatabase = mFirebaseInstance.getReference();
                        KhachHang khachHang=new KhachHang(userName,mail,dt,mPhone,UID);
                        mFirebaseDatabase.child("KhachHang"+"/"+UID).setValue(khachHang);
//                        LoadingDialog loadingDialog = new LoadingDialog(SignUpActivity.this);
//                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                                public void run() {

                                    Toast.makeText(DangkyKhachHang.this, "Signup succes", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DangkyKhachHang.this,DangNhapKhachHangActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(KEY_KHACHHANG,khachHang);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                        }, 1000);
                        progressBar2.setVisibility(View.INVISIBLE);
                    }
                }
            });}else {
            Toast.makeText(DangkyKhachHang.this,"Error Occurred!", Toast.LENGTH_LONG).show();
        }

    }
    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor1 = sharedPreferences1.edit();
    }


}
