package java.android.quanlybanhang.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    Button signinNow, signup;
    TextView logoText, sloganText, forgetPass;
    ImageView imageView;
    TextInputEditText username, email, phone, password, tenQuan;
    FloatingActionButton facebook, google;

    //Firebase
    DatabaseReference mFirebaseDatabase;
    FirebaseDatabase mFirebaseInstance;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signinNow = findViewById(R.id.btn_signin_now);
        signup = findViewById(R.id.btn_signup);
        logoText = findViewById(R.id.logoText);
        sloganText = findViewById(R.id.sloganText);
        username = findViewById(R.id.edt_username);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        password = findViewById(R.id.edt_password);
        imageView = findViewById(R.id.imageView);
        forgetPass = findViewById(R.id.lbl_forget_pass);
        facebook =  findViewById(R.id.btn_facebook);
        google = findViewById(R.id.btn_google);
        tenQuan = findViewById(R.id.edtTenQuan);

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
                onBackPressed();
                break;
            case R.id.btn_signup:

                signup();
                break;
        }

    }

    private void signup(){
        mFirebaseAuth = FirebaseAuth.getInstance();

        String mail = email.getText().toString();
        String userName = username.getText().toString();
        String mPhone = phone.getText().toString();
        String pass = password.getText().toString();
        String tenquan = tenQuan.getText().toString();


        if(userName.isEmpty()){
            username.setError("Plese enter username");
            username.requestFocus();

        }else if(tenquan.isEmpty()){
            tenQuan.setError("Plese enter name of the store");
            tenQuan.requestFocus();
        }else if(mail.isEmpty()){
            email.setError("Plese enter email");
            email.requestFocus();
        }else if(mPhone.isEmpty()){
            phone.setError("Plese enter email");
            phone.requestFocus();
        }else if(pass.isEmpty()){
            password.setError("Plese enter password");
            password.requestFocus();
        }else if(mail.isEmpty() && pass.isEmpty()){
            Toast.makeText(SignUpActivity.this,"Fialds Are Empty!", Toast.LENGTH_LONG).show();
        }else if(!(mail.isEmpty() && pass.isEmpty() && tenquan.isEmpty() && mPhone.isEmpty() &&userName.isEmpty() )){
            Log.d("Test", mail);
            Log.d("Test", pass);
            mFirebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "SignUp UnSuccessful, plese Try Again ", Toast.LENGTH_SHORT).show();
                    }else{
                        String UID = mFirebaseAuth.getUid();
                        mFirebaseInstance = FirebaseDatabase.getInstance();
                        mFirebaseDatabase = mFirebaseInstance.getReference(UID);
                        mFirebaseAuth.signOut();

                        //setup account

                        mFirebaseDatabase.setValue("1111111");
                        mFirebaseDatabase.child("user").child(UID).child("username").setValue(userName);
                        mFirebaseDatabase.child("user").child(UID).child("email").setValue(mail);
                        mFirebaseDatabase.child("user").child(UID).child("tenQuan").setValue(tenquan);
                        mFirebaseDatabase.child("user").child(UID).child("chucVu").setValue(1);
                        mFirebaseDatabase.child("user").child(UID).child("phone").setValue(mPhone);
                        mFirebaseDatabase.child("user").child(UID).child("caLam").setValue(1);

                        LoadingDialog loadingDialog = new LoadingDialog(SignUpActivity.this);
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                Toast.makeText(SignUpActivity.this, "Signup succes", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                intent.putExtra("email", mail);
                                startActivity(intent);
                                finish();
                            }
                        }, 3000);
                    }
                }
            });}else {
            Toast.makeText(SignUpActivity.this,"Error Occurred!", Toast.LENGTH_LONG).show();
        }

    }
}