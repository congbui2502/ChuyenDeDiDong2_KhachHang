package java.android.quanlybanhang.functions.DatBan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class DanhSachDanhGia extends AppCompatActivity {
    private DatabaseReference mDatabase;
    String id_shop;
    RecyclerView recyclerView;
    AdapterDanhSachDanhGia adapterDanhSachDanhGia;
    ArrayList<RatingModel> ratingModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia);
        Intent intent1 = getIntent();
        id_shop = intent1.getStringExtra("id_cuahang");
        mDatabase = FirebaseDatabase.getInstance().getReference("rating").child("idcuahang").child(id_shop);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ratingModels = new ArrayList<>();
                if(snapshot.getValue()!=null) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        for(DataSnapshot snap : snapshot1.getChildren()){
                            Log.d("keyaaa", snap.getKey() + "");
                            String comment = snap.child("comment").getValue() + "";
                            String date = snap.child("date").getValue() + "";
                            String numberrating = snap.child("numberrating").getValue() + "";
                            String tenkhachhang = snap.child("tenkhachhang").getValue() + "";
                            ratingModels.add(new RatingModel(comment, date, numberrating, tenkhachhang));
                        }
                    }
                    Log.d("ratingModelss", ratingModels.size() + "");
                    recyclerView = findViewById(R.id.rv_1);
                    adapterDanhSachDanhGia = new AdapterDanhSachDanhGia(ratingModels);
                    recyclerView.setAdapter(adapterDanhSachDanhGia);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachDanhGia.this, LinearLayoutManager.VERTICAL, false));
                    adapterDanhSachDanhGia.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}