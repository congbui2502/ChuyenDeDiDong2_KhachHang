package com.example.shipper;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import adapter.DonHangAdapter;
import adapter.ShipperAdapter;

public class Home extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview;
        ArrayList<DonHang> arrayList;
        DonHangAdapter adapter;


        //listview.setAdapter(adapter);

        viewPager = findViewById(R.id.viewpaper);
        //viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.tablayout);
        ShipperAdapter adapter1 = new ShipperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter1.getData(this);
        viewPager.setAdapter(adapter1);
        tabLayout.setupWithViewPager(viewPager);

    }
}
