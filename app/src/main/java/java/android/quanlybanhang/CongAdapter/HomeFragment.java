package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.Activity.DangNhapKhachHangActivity;
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
    private TextView tvSearch;
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
        tvSearch= view.findViewById(R.id.tvSearch);
        loaiTraiAdapter=new LoaiTraiAdapter(getContext());
        loaiTraiAdapter.setData(DangNhapKhachHangActivity.listHome,activity);
        recyclerView.setAdapter(loaiTraiAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);


        Log.d("BBB","onCreated HomeFragment");

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment fragment=new SearchFragment();
                fragment.getActivity(activity);
                FragmentTransaction transaction= activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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


}

