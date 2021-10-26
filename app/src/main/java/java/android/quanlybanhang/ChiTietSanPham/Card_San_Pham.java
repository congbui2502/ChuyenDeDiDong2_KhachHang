package java.android.quanlybanhang.ChiTietSanPham;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.android.quanlybanhang.OrderMon.Product;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class Card_San_Pham extends AppCompatActivity {
    private Toolbar toolbar;//tool bar khai bao id
    private RecyclerView rv_3;//tool bar khai bao id
     StaticCardAdapter staticCartAdapter;
     ArrayList<Product> listcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_san_pham);
        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
//             viet su kien cho toolbar
        ActionBar actionBar = getSupportActionBar();
//Thiết lập tiêu đề nếu muốn
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //
        rv_3 = findViewById(R.id.rv_3);
        listcard = new ArrayList<Product>();
        staticCartAdapter = new StaticCardAdapter();
        staticCartAdapter.Setdata(listcard);
        rv_3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_3.setAdapter(staticCartAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_OK&& data != null){
            Log.d("aaa","aaaaaaaaaaaab");
                Bundle bundle = data.getExtras();
           this.listcard = (ArrayList<Product>) bundle.getSerializable("list");
            staticCartAdapter.notifyDataSetChanged();
        }
    }
}