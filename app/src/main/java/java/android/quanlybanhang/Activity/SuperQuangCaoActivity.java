package java.android.quanlybanhang.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.CongAdapter.Cart_Fragment;
import java.android.quanlybanhang.CongAdapter.QuanNoiBatAdapter;
import java.android.quanlybanhang.CongAdapter.SanPhamNoiBatAdapter;
import java.android.quanlybanhang.CongAdapter.ShopProductFragment;
import java.android.quanlybanhang.CongAdapter.SuperQuangCaoAdapter;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.DonGia;
import java.android.quanlybanhang.Sonclass.GioHang;
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
    private SuperQuangCaoAdapter adapter;
    private Spinner spinner;
    private List<SanPham> sanPhamList;
    private List<String> list_spinner;
    private int pos,oldList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);
        unit();
        getData();
        oldList= sanPham.getDonGia().size();
        Glide.with(this).load(sanPham.getImgProduct()).into(imgSP);
        tvTenSP.setText(sanPham.getNameProduct());
        tvGia.setText(Cart_Fragment.addDauPhay(sanPham.getDonGia().get(0).getGiaBan())+" VND");
        tvDes.setText(sanPham.getChitiet());
        tvDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    addCart(cuaHang,sanPham,SuperQuangCaoActivity.this,pos);
            }
        });
        list_spinner=new ArrayList<>();
        for (int i = 0; i < sanPham.getDonGia().size(); i++) {
            list_spinner.add(sanPham.getDonGia().get(i).getTenDonGia());
        }
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(SuperQuangCaoActivity.this,
                android.R.layout.simple_list_item_1, list_spinner);

        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spin_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler( parent,  view,  position,  id);
                pos=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        tvGia.setText(Cart_Fragment.addDauPhay(sanPham.getDonGia().
                get(sanPham.getDonGia().size()-oldList+position).getGiaBan())+" VND");
    }

    private void unit() {
        pos=0;
        sanPhamList=new ArrayList<>();
        imgSP=findViewById(R.id.imgSP);
        imgLogo=findViewById(R.id.imgLogo);
        tvTenSP=findViewById(R.id.tvTensp);
        tvDatMua=findViewById(R.id.tvDatmua);
        tvDes=findViewById(R.id.tvDescription);
        tvGia=findViewById(R.id.tvGia);
        tvTenQuan=findViewById(R.id.tenQuanquan);
        recyclerView=findViewById(R.id.recySanpham);
        adapter= new SuperQuangCaoAdapter();
        adapter.setData(SuperQuangCaoActivity.this, new SuperQuangCaoAdapter.IclickAddToCartListener() {
            @Override
            public void setSanPham(SanPham trai) {
                sanPham=trai;
                list_spinner=new ArrayList<>();
                for (int i = 0; i < trai.getDonGia().size(); i++) {
                    list_spinner.add(trai.getDonGia().get(i).getTenDonGia());
                }
                ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(SuperQuangCaoActivity.this,
                        android.R.layout.simple_list_item_1, list_spinner);

                spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spin_adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        onItemSelectedHandler( parent,  view,  position,  id);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Glide.with(SuperQuangCaoActivity.this).load(trai.getImgProduct()).into(imgSP);
                tvTenSP.setText(trai.getNameProduct());
                tvGia.setText(Cart_Fragment.addDauPhay(trai.getDonGia().get(0).getGiaBan())+" VND");
                tvDes.setText(trai.getChitiet());


                imgLogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();

        if(bundle.getSerializable("sanpham")!=null)
        {
            sanPham= (SanPham) bundle.getSerializable("sanpham");
            list_spinner=new ArrayList<>();
            for (int i = 0; i < sanPham.getDonGia().size(); i++) {
                list_spinner.add(sanPham.getDonGia().get(0).getTenDonGia());
            }
        }

        spinner=findViewById(R.id.spinner);

    }

    private void getData()
    {
        mReference= FirebaseDatabase.getInstance().getReference("cuaHang");

        mReference.addChildEventListener(new ChildEventListener() {
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

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals(sanPham.getIdCuaHang()))
                {
                    for (DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String key= snapshot1.getKey();

                        if(key.equals("sanpham"))
                        {
                            for (DataSnapshot snapshot2:snapshot1.getChildren())
                            {
                                for (DataSnapshot snapshot3:snapshot2.getChildren())
                                {
                                    SanPham sanPham = snapshot3.getValue(SanPham.class);
                                    sanPham.setSoluong(1);
                                    sanPhamList.add(sanPham);
                                    adapter.getListSP(sanPhamList);

                                }

                            }
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

//        mReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                for (DataSnapshot snapshot1:snapshot.getChildren())
//                {
//                    String key= snapshot1.getKey();
////                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
//                    if(key.equals("sanpham"))
//                    {
//                        for (DataSnapshot snapshot2:snapshot1.getChildren())
//                        {
//                            for (DataSnapshot snapshot3:snapshot2.getChildren())
//                            {
//                                SanPham sanPham = snapshot3.getValue(SanPham.class);
//                                sanPhamList.add(sanPham);
////                                adapter.setData1((KhachHangActivity) getActivity(),sanPhamList);
//                            }
//
//                        }
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }


    public static void addCart(CuaHang cuaHang,SanPham sanPham,AppCompatActivity activity,int pos)
    {


        for (int i = 0; i < sanPham.getDonGia().size(); i++) {
            if(i==pos)
            {
                sanPham.getDonGia().add(0,sanPham.getDonGia().get(i));
            }
        }
        DatabaseReference mReference1= FirebaseDatabase.getInstance().getReference().child("gioHang");

        mReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key =snapshot.getKey();


                if(key.equals((KhachHangActivity.khachHang.getIdKhachhang())))
                {
                    for (DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String key3=snapshot1.getKey() ;
                        //Toast.makeText(getApplicationContext(),key3,Toast.LENGTH_SHORT).show();
                        GioHang gioHang = snapshot1.getValue(GioHang.class);
                        if (gioHang.getIdQuan().equals(cuaHang.getId()))
                        {
                            boolean flag = true;
                            for (int i = 0; i < gioHang.getSanPham().size(); i++) {
                                if(sanPham.getNameProduct().equals(gioHang.getSanPham().
                                        get(i).getNameProduct()) &&
                                        sanPham.getDonGia().get(0).getTenDonGia().equals(gioHang.getSanPham().
                                                get(i).getDonGia().get(0).getTenDonGia()))
                                {
                                    flag= false;
                                    gioHang.getSanPham().get(i).setSoluong(
                                            gioHang.getSanPham().get(i).getSoluong()+1);
                                }
                            }
                            if(flag)
                            {
                                gioHang.getSanPham().add(sanPham);
                                Toast.makeText(activity.getApplicationContext(),"Đã thêm vào giỏ hàng 1",Toast.LENGTH_SHORT).show();
                                mReference1.child(KhachHangActivity.khachHang.getIdKhachhang()).child(key3).setValue(gioHang);

                            }else {
                                    Toast.makeText(activity.getApplicationContext(),"Đã thêm vào giỏ hàng 2",Toast.LENGTH_SHORT).show();
                                    mReference1.child(KhachHangActivity.khachHang.getIdKhachhang()).child(key3).setValue(gioHang);

                            }

                        }else {
                            List<SanPham> sanPhams1=new ArrayList<>();
                            sanPhams1.add(sanPham);
                            GioHang gioHang1=new GioHang(cuaHang.getId(), sanPhams1);
                            mReference1.child(KhachHangActivity.khachHang.getIdKhachhang()).child(key3).setValue(gioHang1);
                            Toast.makeText(activity.getApplicationContext(),"Đã thêm vào giỏ hàng 3",Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}