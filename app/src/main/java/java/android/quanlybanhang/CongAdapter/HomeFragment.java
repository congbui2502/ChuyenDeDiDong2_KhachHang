package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.HomeProduct;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment  extends Fragment {

    private RecyclerView recyclerView;
    private LoaiTraiAdapter loaiTraiAdapter;

    private DatabaseReference mReference;
    private KhachHangActivity activity;

    public HomeFragment(KhachHangActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BBB","onCreate HomeFragment");
        mReference= FirebaseDatabase.getInstance().getReference();
        activity.getSupportFragmentManager().beginTransaction().addToBackStack(null);


        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycle);
        loaiTraiAdapter=new LoaiTraiAdapter(getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loaiTraiAdapter.setData(getList(),activity);
        recyclerView.setAdapter(loaiTraiAdapter);
        Log.d("BBB","onCreated HomeFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("BBB","onResume HomeFragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("BBB","onStop HomeFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BBB","onDestroy HomeFragment");
    }

    public List<HomeProduct> getList()
    {
        List<HomeProduct> loaiTrais=new ArrayList<>();
        List<SanPham> sanphamnoibat=new ArrayList<>();
        List<CuaHang> cuahang=new ArrayList<>();
        List<SanPham> sanphamquangcao=new ArrayList<>();

        mReference.child("sanphamnoibat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham=snapshot.getValue(SanPham.class);
                sanphamnoibat.add(sanPham);
                loaiTraiAdapter.notifyDataSetChanged();
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

        loaiTrais.add(new HomeProduct("Sản phẩm nổi bật",sanphamnoibat,new ArrayList<CuaHang>(),new ArrayList<SanPham>()));
        loaiTraiAdapter.notifyDataSetChanged();



        mReference.child("cuaHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CuaHang sanPham=snapshot.getValue(CuaHang.class);
                cuahang.add(sanPham);


                loaiTraiAdapter.notifyDataSetChanged();
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

        loaiTrais.add(new HomeProduct("Của hàng",new ArrayList<SanPham>(),cuahang,new ArrayList<SanPham>()));
        loaiTraiAdapter.notifyDataSetChanged();




        mReference.child("sanphamQuangcao").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham=snapshot.getValue(SanPham.class);
                sanphamquangcao.add(sanPham);
                loaiTraiAdapter.notifyDataSetChanged();
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

        loaiTrais.add(new HomeProduct("Sản phẩm mới",new ArrayList<SanPham>(),new ArrayList<CuaHang>(),sanphamquangcao));
        loaiTraiAdapter.notifyDataSetChanged();

        return loaiTrais;
    }
}

