package java.android.quanlybanhang.DatBan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sa90.materialarcmenu.ArcMenu;

import java.android.quanlybanhang.ChiTietSanPham.Interface_KhuVuc_ban;
import java.android.quanlybanhang.DatBan.Ban.StaticBanModel;
import java.android.quanlybanhang.DatBan.Ban.StaticRvAdapter;
import java.android.quanlybanhang.DatBan.KhuVuc.StaticModelKhuVuc;
import java.android.quanlybanhang.DatBan.KhuVuc.StaticRvKhuVucAdapter;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;

import java.util.ArrayList;
import java.util.List;

public class DanhSachChonBan extends AppCompatActivity implements Interface_KhuVuc_ban {
    private RecyclerView recyclerView, recyclerView2;//rv khu vuc ban
    private StaticRvKhuVucAdapter staticRvKhuVucAdapter;//adapter khu vuc
    ArrayList<StaticBanModel> items = new ArrayList<>();//araylist ban
    StaticRvAdapter staticRvAdapter;
    private DatabaseReference mDatabase,mFirebaseDatabase;
    Interface_KhuVuc_ban interfaceKhuVucBan;
    private ArcMenu arcMenu;
    ArrayList<StaticModelKhuVuc> item;
    private Toolbar toolbar;
    private CuaHang cuaHang;
    private String id_shop;
    ProgressBar progressBar;
    ImageView img_nocart;
    String  ten_cuahang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        img_nocart = findViewById(R.id.img_nocart);
        Intent intent1 = getIntent();
        id_shop = intent1.getStringExtra("id_cuahang");
        ten_cuahang =intent1.getStringExtra("ten_cuahang");
        ArrayList<String> listImage = new ArrayList<>();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("cuaHang").child(id_shop).child("thongtin/image");
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    listImage.add(dataSnapshot.child("imageUrl").getValue().toString());
                }

                ImageSlider slider = findViewById(R.id.image_slider);
                List<SlideModel> slideModels = new ArrayList<>();
                for (int i = 0; i <listImage.size() ; i++) {
                    slideModels.add(new SlideModel(listImage.get(i).toString(), ScaleTypes. FIT));
                }
                slider.setImageList(slideModels, ScaleTypes. FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        mDatabase = FirebaseDatabase.getInstance().getReference("CuaHangOder").child(id_shop).child("khuvuc");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                item = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ArrayList<StaticBanModel> mm = new ArrayList<>();
                    String trangthai = postSnapshot.child("trangthai").getValue() + "";
                    String tenkhuvuc = postSnapshot.child("tenkhuvuc").getValue() + "";
                    String id_khuvuc = postSnapshot.getKey();
                    Log.d("khanhkh", tenkhuvuc);
                    DataSnapshot sss = postSnapshot.child("ban");
                    for (DataSnapshot aaa : sss.getChildren()) {
                        String tenban = aaa.child("tenban").getValue() + "";
                        String trangthai1 = aaa.child("trangthai").getValue() + "";
                        String id_ban = aaa.getKey();
                        mm.add(new StaticBanModel(id_ban,tenban, trangthai1));
                    }
                    StaticModelKhuVuc product = new StaticModelKhuVuc(tenkhuvuc, trangthai, id_khuvuc, mm);
                    item.add(product);
                }
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView = findViewById(R.id.rv_1);
                staticRvKhuVucAdapter = new StaticRvKhuVucAdapter(item, DanhSachChonBan.this, DanhSachChonBan.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachChonBan.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(staticRvKhuVucAdapter);
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    img_nocart.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        items = new ArrayList<>();
        recyclerView2 = findViewById(R.id.rv_2);
        staticRvAdapter = new StaticRvAdapter(items, DanhSachChonBan.this, item, "","",id_shop,ten_cuahang);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(gridLayoutManager);
        recyclerView2.setAdapter(staticRvAdapter);
        staticRvAdapter.notifyDataSetChanged();

    }


    @Override
    public void GetBack(int position, ArrayList<StaticBanModel> items, String id_khuvuc,String trangthai) {
        id_khuvuc = item.get(position).getId_khuvuc();
        trangthai = item.get(position).getTrangthai();
        staticRvAdapter = new StaticRvAdapter(items, DanhSachChonBan.this, item, id_khuvuc,trangthai,id_shop,ten_cuahang);
        staticRvAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(staticRvAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void SlideShow(){

    }

}