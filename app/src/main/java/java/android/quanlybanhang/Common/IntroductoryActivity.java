package java.android.quanlybanhang.Common;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.android.quanlybanhang.R;

import java.android.quanlybanhang.Activity.ShopActivity;

public class IntroductoryActivity extends AppCompatActivity {

    LottieAnimationView lottie;
    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(IntroductoryActivity.this, R.color.hong_nhat));
        setContentView(R.layout.activity_introductory);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        lottie = findViewById(R.id.lottie);

        int secs = 4; // Delay in seconds



        Utils.delay(secs, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                boolean checkNetwork = isOnline();
                if(checkNetwork == true){
                    if (mFirebaseUser != null) {
                        Intent intent = new Intent(IntroductoryActivity.this, ShopActivity.class);
                        intent = new Intent(IntroductoryActivity.this, ShopActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(IntroductoryActivity.this, OnboardingScreenActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Intent intent = new Intent(IntroductoryActivity.this, Error.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        lottie.cancelAnimation();

    }

    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }
}