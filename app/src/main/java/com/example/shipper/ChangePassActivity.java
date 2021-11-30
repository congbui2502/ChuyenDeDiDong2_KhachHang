package com.example.shipper;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePassActivity extends AppCompatActivity {

    private EditText edtOldPass,edtNewPass,edtCfPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
//        edtOldPass = findViewById(R.id.edt_oldpass);
//        edtNewPass = findViewById(R.id.edt_newpass);
//        edtCfPass = findViewById(R.id.edt_cfpass);
    }
//    private void ChangePassword(){}
}
