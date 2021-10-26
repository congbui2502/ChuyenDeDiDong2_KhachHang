package java.android.quanlybanhang.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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


import java.android.quanlybanhang.AdapterT.AdapterCategorySanPham;
import java.android.quanlybanhang.DataT.Category_SanPham;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class ListCategory extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private ArrayList<Category_SanPham> listCategory;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private AdapterCategorySanPham adapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        recyclerView = findViewById(R.id.rv_2);
        floatingActionButton = findViewById(R.id.themnhomsanpham);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("danhmucsanpham");
       mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               listCategory = new ArrayList<>();
               for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                   Category_SanPham category = postSnapshot.getValue(Category_SanPham.class);
                   listCategory.add(category);
               }
               adapterCategory = new AdapterCategorySanPham(ListCategory.this,listCategory);
               recyclerView.setAdapter(adapterCategory);
               LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListCategory.this, LinearLayoutManager.VERTICAL, false);
               recyclerView.setLayoutManager(linearLayoutManager);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Thêm nhóm sản phẩm  mới", Snackbar.LENGTH_LONG)
                        .setAction("Thêm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent = new Intent(ListCategory.this, AddCategory.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }
        });
    }

    public void delete( int position){

        new AlertDialog.Builder(ListCategory.this).setMessage(
                "Do you want to delete this item"
        ).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mDatabase.child(listCategory.get(position).getId()).removeValue();

            }
        }).setNegativeButton("No", null)
                .show();
    }
    public void update( int position){
//        Toast.makeText(this,mDatabase.getKey()+"",Toast.LENGTH_LONG).show();

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.dailong_updatecategory, null);
        EditText editText = customLayout.findViewById(R.id.editUpdateCategory);
        builder.setView(customLayout);
        editText.setText(listCategory.get(position).getNameCategory());

        builder.setNegativeButton("Thoat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });  builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabase.child(listCategory.get(position).getId()).child("nameCategory").setValue(editText.getText().toString());
              }

        });

        builder.create().show();


    }
}
