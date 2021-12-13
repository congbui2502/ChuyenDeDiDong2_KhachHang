package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.functions.DatBan.History.Adapter.FragmentAdapter;
import java.android.quanlybanhang.functions.DatBan.History.Adapter.TablayoutAdapter;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class Account_fragment extends Fragment {
    private View mView;
    private TablayoutAdapter tablayoutAdapter;
    private RecyclerView recyclerView;
    private ArrayList<String> title;
    private ViewPager2 pager;
    private FragmentAdapter adapter;
    private KhachHangActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.account_fragment,container,false);
        title = new ArrayList<String>();
        title.add("Đơn hàng đang đặt");
        title.add("Đơn hàng đã nhận");
        title.add("Đơn hàng đã hủy");


        mainActivity=(KhachHangActivity)  getActivity();

        recyclerView = mView.findViewById(R.id.recylerView);
        pager = mView.findViewById(R.id.viewPager2);

        FragmentManager fm = mainActivity.getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        displayItem();

        return mView;
    }

    private void displayItem(){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager  = new LinearLayoutManager(mainActivity, LinearLayoutManager.HORIZONTAL, false);
        tablayoutAdapter = new TablayoutAdapter(mainActivity, title, pager);
        recyclerView.setAdapter(tablayoutAdapter);
        recyclerView.setLayoutManager(layoutManager);
        tablayoutAdapter.notifyDataSetChanged();
    }


}
