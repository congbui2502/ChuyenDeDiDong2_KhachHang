package java.android.quanlybanhang.ChiTietSanPham;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import java.android.quanlybanhang.R;

public class ChiTietSanPham_Banh extends AppCompatActivity {
    private Toolbar toolbar;//tool bar khai bao id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham_banh);
        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
//             viet su kien cho toolbar
        ActionBar actionBar = getSupportActionBar();
//Thiết lập tiêu đề nếu muốn
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);    }
}