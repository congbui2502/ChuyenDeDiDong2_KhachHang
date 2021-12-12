package java.android.quanlybanhang.functions.DatBan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.CongAdapter.History.Data.DonHang;
import java.android.quanlybanhang.CongAdapter.History.DonHangNhanFrament;
import java.android.quanlybanhang.R;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DanhGiaCuaHang extends AppCompatActivity {
  private ArrayList<DonHang> list;
    DonHang donHang;
    Button bnt_dongy;
    RatingBar ratingBar;
    EditText editTextNumber;
    public static  int ss =0;

    DonHangNhanFrament donHangNhanFrament= new DonHangNhanFrament();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia_cua_hang);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DonHangNhanFrament.dialogthanhcong=1;

        if((DonHang) bundle.getSerializable("list")!=null){
            donHang =(DonHang) bundle.getSerializable("list");
        }

        bnt_dongy = findViewById(R.id.bnt_dongy);
        ratingBar = findViewById(R.id.ratingBar);
        editTextNumber= findViewById(R.id.editTextNumber);
        bnt_dongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                float result = ratingBar.getRating();
                if(editTextNumber.getText().toString().isEmpty()){
                    editTextNumber.setError("Bạn quên nhập đánh giá!!!");
                    editTextNumber.requestFocus();
                }
                else if(result<1){
                    Toast.makeText(DanhGiaCuaHang.this, "Bạn ơi ,vui lòng đánh giá 1 sao", Toast.LENGTH_LONG).show();
                }
                else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("rating").child("idcuahang").child(donHang.getIdQuan()).child(donHang.getIdKhachhang()).child(donHang.getIdDonHang());
                    databaseReference.child("tenkhachhang").setValue(donHang.getTenKhachhang());
                    databaseReference.child("numberrating").setValue(result);
                    databaseReference.child("comment").setValue(editTextNumber.getText()+"");
                    databaseReference.child("date").setValue(hamlaydate());
                    databaseReference.child("date").setValue(hamlaydate());
                    DonHangNhanFrament.dialogthanhcong=2;
                    onBackPressed();
                }

            }
        });
    }
    //ham chuyen ngay thanh chuoi
    public String hamlaydate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime()+"";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }

}