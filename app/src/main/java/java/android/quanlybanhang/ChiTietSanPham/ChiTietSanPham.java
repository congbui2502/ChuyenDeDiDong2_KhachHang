package java.android.quanlybanhang.ChiTietSanPham;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.android.quanlybanhang.OrderMon.Product;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Activity.MonOrder;

public class ChiTietSanPham extends AppCompatActivity {
    private Toolbar toolbar;//tool bar khai bao id
    Product staticMonOrderModel;
    TextView tensp,giasp,giatongsp;
    String tenban;
    Button bnt_xacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham_do_uong);
        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
//             viet su kien cho toolbar
        ActionBar actionBar = getSupportActionBar();
//Thiết lập tiêu đề nếu muốn
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        StaticMonOrderModel staticMonOrderModel = getIntent().getSerializableExtra("sp");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        staticMonOrderModel = (Product) bundle.getSerializable("sp");

                String tensps = staticMonOrderModel.getNameProduct();
                Double giasanphams = staticMonOrderModel.getGiaBan();
                int soluong = staticMonOrderModel.getSoluong();

                tensp = findViewById(R.id.tvtensanpham);
                giasp = findViewById(R.id.tvgiasanpham);
                giatongsp = findViewById(R.id.tvgiatongsanpham);
                tensp.setText(tensps);
                giasp.setText(giasanphams+"");
                tenban = bundle.getString("tenban") ;
                Log.d("aaa",tenban+"acbank");
                bnt_xacnhan = findViewById(R.id.bnt_xacnhan);
                bnt_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        AddFirebasedata(new StaticMonOrderModel(tensps,soluong));
                        Intent intent1 = new Intent(ChiTietSanPham.this, MonOrder.class);
                        Bundle bundle1= new Bundle();
                        bundle1.putSerializable("card",new Product(tensps,soluong));
                        intent1.putExtras(bundle1);
//                        intent1.putExtra("tenban",tenban);
                        startActivityForResult(intent1,RESULT_OK);

                    }
                });


    }
//
//    public void AddFirebasedata(StaticMonOrderModel  staticMonOrderModel){
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("sanphamorder");
//        databaseReference.child(tenban).child("sanpham").push().setValue(staticMonOrderModel);
//
//    }

}