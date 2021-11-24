package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.android.quanlybanhang.R;

public class Tttttttest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tttttttest);

        Fragment1 fragment1=new Fragment1(this);

        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.mom,fragment1,"fragment1").commit();

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}