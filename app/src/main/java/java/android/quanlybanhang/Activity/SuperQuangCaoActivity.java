package java.android.quanlybanhang.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.CongAdapter.QuanNoiBatAdapter;
import java.android.quanlybanhang.CongAdapter.SanPhamNoiBatAdapter;
import java.android.quanlybanhang.CongAdapter.ShopProductFragment;
import java.android.quanlybanhang.CongAdapter.SuperQuangCaoAdapter;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.ArrayList;
import java.util.List;

public class SuperQuangCaoActivity extends AppCompatActivity {
    private ImageView imgSP,imgLogo;
    private TextView tvTenSP,tvGia,tvDes,tvDatMua,tvTenQuan;
    private SanPham sanPham;
    private DatabaseReference mReference;
    private CuaHang cuaHang;
    private RecyclerView recyclerView;
    private  SuperQuangCaoAdapter adapter;

    private List<SanPham> sanPhamList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);


        unit();
        Glide.with(this).load(sanPham.getImgProduct()).into(imgSP);
        tvTenSP.setText(sanPham.getNameProduct());
        if(sanPham.getDonGia()!=null)
        {
            tvGia.setText(sanPham.getDonGia().get(0).getGiaBan()+" VND");
        }

        tvDes.setText(sanPham.getChitiet());
        tvDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReference= FirebaseDatabase.getInstance().getReference();
                mReference.child("gioHang").child("idKhachHang").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.getKey().equals("idQuan"))
                        {
                            String idQuan = snapshot.getValue(String.class);
                            if(idQuan.equals(sanPham.getIdCuaHang()))
                            {
                                mReference.child("gioHang").child("idKhachHang").child("sanpham").push().setValue(sanPham);
                                Toast.makeText(getBaseContext(),"Sản phẩm đã được thêm vào giỏ",Toast.LENGTH_SHORT).show();

                            }else {
                                mReference.child("gioHang").child("idKhachHang").child("idQuan").setValue(sanPham.getIdCuaHang());
                                mReference.child("gioHang").child("idKhachHang").child("sanPham").removeValue();
                                mReference.child("gioHang").child("idKhachHang").child("sanPham").push().setValue(sanPham);
                                Toast.makeText(getBaseContext(),"Sản phẩm đã được thêm vào giỏ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });
        getData();

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction=  getSupportFragmentManager().beginTransaction();
//                ShopProductFragment fragment1=new ShopProductFragment(SuperQuangCaoActivity.this,this, new QuanNoiBatAdapter.getdata() {
//                    @Override
//                    public CuaHang getData() {
//                        return cuaHang;
//                    }
//                });
//                fragmentTransaction.replace(R.id.fragment_container,fragment1);
////                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

    }

    private void unit() {
        sanPhamList=new ArrayList<>();
        imgSP=findViewById(R.id.imgSP);
        imgLogo=findViewById(R.id.imgLogo);
        tvTenSP=findViewById(R.id.tvTensp);
        tvDatMua=findViewById(R.id.tvDatmua);
        tvDes=findViewById(R.id.tvDescription);
        tvGia=findViewById(R.id.tvGia);
        tvTenQuan=findViewById(R.id.tenQuanquan);
        recyclerView=findViewById(R.id.recySanpham);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SuperQuangCaoAdapter();
        adapter.setData(SuperQuangCaoActivity.this, new SuperQuangCaoAdapter.IclickAddToCartListener() {
            @Override
            public void onClickAddToCart(SanPham trai) {
                Glide.with(SuperQuangCaoActivity.this).load(trai.getImgProduct()).into(imgSP);
                tvTenSP.setText(trai.getNameProduct());
                tvGia.setText(trai.getDonGia().get(0).getGiaBan()+" VND");
                tvDes.setText(trai.getChitiet());
                tvDatMua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mReference= FirebaseDatabase.getInstance().getReference();
                        mReference.child("gioHang").child("idKhachHang").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                if (snapshot.getKey().equals("idQuan"))
                                {
                                    String idQuan = snapshot.getValue(String.class);
                                    if(idQuan.equals(trai.getIdCuaHang()))
                                    {
                                        mReference.child("gioHang").child("idKhachHang").child("sanPham").push().setValue(trai);
                                        Toast.makeText(getBaseContext(),"Sản phẩm đã được thêm vào giỏ",Toast.LENGTH_SHORT).show();

                                    }else {
                                        mReference.child("gioHang").child("idKhachHang").child("idQuan").setValue(trai.getIdCuaHang());
                                        mReference.child("gioHang").child("idKhachHang").child("sanPham").removeValue();
                                        mReference.child("gioHang").child("idKhachHang").child("sanPham").push().setValue(trai);
                                        Toast.makeText(getBaseContext(),"Sản phẩm đã được thêm vào giỏ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                });
                getData();
            }
        });
        recyclerView.setAdapter(adapter);
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
        if(bundle.getSerializable("sanpham")!=null)
        {
            sanPham= (SanPham) bundle.getSerializable("sanpham");
        }

    }

    private void getData()
    {
        mReference= FirebaseDatabase.getInstance().getReference();

        mReference.child("cuaHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals(sanPham.getIdCuaHang()))
                {

//                    Toast.makeText(getBaseContext(),snapshot.getKey(),Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String key= snapshot1.getKey();
//                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
                        if(key.equals("thongtin"))
                        {
                            cuaHang = snapshot1.getValue(CuaHang.class);
                            if(cuaHang!=null)
                            {
//                                Toast.makeText(getBaseContext(),cuaHang.getName(),Toast.LENGTH_SHORT).show();
                                Glide.with(getBaseContext()).load(cuaHang.getLogoUrl()).into(imgLogo);
                                tvTenQuan.setText(cuaHang.getName());
                            }

                        }else {
//                            Toast.makeText(getBaseContext(),"aaa",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mReference.child("cuaHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.getKey().equals(sanPham.getIdCuaHang())) {


                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                        String key = snapshot1.getKey();

                        if (key.equals("sanpham")) {
                            for (DataSnapshot snapshot2 : snapshot1.getChildren()) {
                                for (DataSnapshot snapshot3 : snapshot2.getChildren()) {
                                    SanPham sanPham = snapshot3.getValue(SanPham.class);


                                    sanPhamList.add(sanPham);
                               //     Toast.makeText(getBaseContext(),sanPhamList.size()+" abc",Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(getBaseContext(),sanPham.getNameProduct(),Toast.LENGTH_SHORT).show();
                                       adapter.getList(sanPhamList);
                                }

                            }
                        }else {
                           // Toast.makeText(getBaseContext(),"cmm",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}