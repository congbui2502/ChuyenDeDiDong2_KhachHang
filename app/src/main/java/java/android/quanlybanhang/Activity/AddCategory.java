package java.android.quanlybanhang.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.android.quanlybanhang.DataT.Category_SanPham;
import java.android.quanlybanhang.R;

public class AddCategory extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText editTextAddCategory;
    private Button btnAddCategory;
    private Category_SanPham category;
    private String STR_NHOMSANPHAM ="danhmucsanpham";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);

        editTextAddCategory = findViewById(R.id.editAdd);
        btnAddCategory = findViewById(R.id.btnAdd);
        mDatabase = FirebaseDatabase.getInstance().getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child(STR_NHOMSANPHAM);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mDatabase.push().getKey();
                if(editTextAddCategory.getText().toString().isEmpty()){
                    Toast.makeText(AddCategory.this,"Hãy nhập tên nhóm sản phẩm",Toast.LENGTH_LONG).show();
                }
                else {
                    String name = editTextAddCategory.getText().toString();
                    category = new Category_SanPham(id,name);
                    mDatabase.child(id).setValue(category);
                }
            }
        });
    }
}
