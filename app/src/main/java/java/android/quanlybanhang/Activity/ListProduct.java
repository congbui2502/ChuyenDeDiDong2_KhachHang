package java.android.quanlybanhang.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.android.quanlybanhang.AdapterT.AdapterProduct;
import java.android.quanlybanhang.OrderMon.Product;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListProduct  extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private FirebaseDatabase firebaseDatabase;
    private AdapterProduct adapterProduct;
    private FloatingActionButton floatingActionButton;
    private ArrayList<Product> listProduct;
    private RecyclerView recyclerView;
    private CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        floatingActionButton = findViewById(R.id.themsanpham);
        recyclerView = findViewById(R.id.recyclerViewProduct);
        imageView = findViewById(R.id.circle_imgView);
        firebaseDatabase =  FirebaseDatabase.getInstance();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Thêm sản phẩm  mới", Snackbar.LENGTH_LONG)
                        .setAction("Thêm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent = new Intent(ListProduct.this, AddProduct.class);
                                startActivity(intent);
                                finish();

                            }
                        }).show();
            }
        });
        mDatabase = firebaseDatabase.getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("sanpham");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listProduct = new ArrayList<>();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    DataSnapshot aaa = snapshot1;
                    for (DataSnapshot snapshot2 : aaa.getChildren()){
                        Product product = snapshot2.getValue(Product.class);
                        String img = product.getImgProduct();
//                        Picasso.get().load(img).into(imageView);
                        listProduct.add(product);
                    }
                    adapterProduct = new AdapterProduct(ListProduct.this,listProduct);
                    recyclerView.setAdapter(adapterProduct);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListProduct.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void delete(final int position){
         new AlertDialog.Builder(ListProduct.this).setMessage(
                "Do you want to delete this item"
        ).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                            DataSnapshot aaa = snapshot1;
                            Toast.makeText(ListProduct.this,listProduct.get(position).getId()+"",Toast.LENGTH_LONG).show();
                            mDatabase1 = firebaseDatabase.getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("sanpham").child(aaa.getKey());
                            mDatabase1.child(listProduct.get(position).getId()).removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        }).setNegativeButton("No", null)
                .show();
    }

    public void update(final int position){

    }

}
