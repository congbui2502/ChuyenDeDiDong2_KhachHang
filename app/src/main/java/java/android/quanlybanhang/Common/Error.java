package java.android.quanlybanhang.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.android.quanlybanhang.R;

public class Error extends AppCompatActivity {

    Button again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(Error.this, R.color.red));
        setContentView(R.layout.activity_error);

        again = findViewById(R.id.btnAgain);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Error.this, IntroductoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}