package java.android.quanlybanhang.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.android.quanlybanhang.R;
import java.android.quanlybanhang.login.LoginActivity;

public class ShopActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseAuth mFirebaseAuth;
    RelativeLayout ordermenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        odermenu
        ordermenu = findViewById(R.id.orderbutton);
        ordermenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ShopActivity.this, OrderMenu.class);
//                startActivity(intent);
            }
        });
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("aa");

        myRef.setValue("Hello, World!");
        mFirebaseAuth = FirebaseAuth.getInstance();

       /*lay id tung phan*/
        drawerLayout = findViewById(R.id.drawable_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*lay ra ten cua toolbar*/
//        setSupportActionBar(toolbar);

        /* lay ra action bar*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_homes);
    }
        /*dong mo action bar*/
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_homes:
                break;
            case R.id.ds_order:
//                Intent intent = new Intent(ShopActivity.this, OrderMenu.class);
//                startActivity(intent);
                break;
            case R.id.ds_chebien:
                Intent intent1 = new Intent(ShopActivity.this, AddCategory.class);
                startActivity(intent1);
                break;
            case R.id.ds_thuchi:
                Intent intent2 = new Intent(ShopActivity.this, AddProduct.class);
                startActivity(intent2);
                break;
            case R.id.quanly:
                Intent intent3 = new Intent(ShopActivity.this, ListProduct.class);
                startActivity(intent3);
                break;
            case R.id.profile:
                Intent intent4 = new Intent(ShopActivity.this, ListCategory.class);
                startActivity(intent4);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent5 = new Intent(ShopActivity.this, LoginActivity.class);
                startActivity(intent5);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}