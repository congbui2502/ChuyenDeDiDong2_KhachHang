package java.android.quanlybanhang.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.android.quanlybanhang.DataT.Category_SanPham;
import java.android.quanlybanhang.OrderMon.Product;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {
    private EditText textName, textChitiet, textGianhap, textGiaban, textSoluong;
    private Spinner spnNhomsanpham;
    private Button btnAdd, btnChoose;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Product product;
    private ArrayList<String> arrayList;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri ImageUri;
    private StorageReference mStogref;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        textName = findViewById(R.id.textTensanpham);
        textChitiet = findViewById(R.id.textChitietsanpham);
        textGiaban = findViewById(R.id.textGianban);
        textGianhap = findViewById(R.id.textGianban);
        textSoluong = findViewById(R.id.textSoluong);
        spnNhomsanpham = findViewById(R.id.spnNhomsanpham);
        btnChoose = findViewById(R.id.btnChoose);
        imageView = findViewById(R.id.imgChoose);
        progressBar = findViewById(R.id.progressBar);
        btnAdd = findViewById(R.id.btnAddproduct);
        mStogref = FirebaseStorage.getInstance().getReference("uploads");
        mDatabase = FirebaseDatabase.getInstance().getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("danhmucsanpham");
        mDatabase1 = FirebaseDatabase.getInstance().getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("sanpham");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChoose();
            }
        });
       uploadFile();
    }

    private String getFileExtenstion(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        //
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Category_SanPham category = postSnapshot.getValue(Category_SanPham.class);
                    String name = category.getNameCategory();
                    arrayList.add(name);
                }
                if (arrayList.size() != 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddProduct.this, R.layout.support_simple_spinner_dropdown_item, arrayList);
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                    spnNhomsanpham.setAdapter(adapter);
                }
//
              btnAdd.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      //
                      if (ImageUri != null) {
                          StorageReference fileRefence = mStogref.child("uploads/" + System.currentTimeMillis() + "." + getFileExtenstion(ImageUri));
                          fileRefence.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                              @Override
                              public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                  Handler handler = new Handler();
                                  handler.postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          progressBar.setProgress(0);

                                      }
                                  }, 5000);

                                  Toast.makeText(AddProduct.this, "Upload successfull", Toast.LENGTH_SHORT).show();
                                  if (textName.getText().toString().isEmpty()){
                                      Toast.makeText(AddProduct.this,"Hãy nhập tên sản phẩm",Toast.LENGTH_LONG).show();
                                  }else if (textChitiet.getText().toString().isEmpty()){
                                      Toast.makeText(AddProduct.this,"Hãy nhập chi tiết sản phẩm",Toast.LENGTH_LONG).show();
                                  }else if(textGiaban.getText().toString().isEmpty()){
                                      Toast.makeText(AddProduct.this,"Hãy nhập giá bán sản phẩm",Toast.LENGTH_LONG).show();
                                  }else if(textGianhap.getText().toString().isEmpty()){
                                      Toast.makeText(AddProduct.this,"Hãy nhập giá nhập sản phẩm",Toast.LENGTH_LONG).show();
                                  }else if(textSoluong.getText().toString().isEmpty()){
                                      Toast.makeText(AddProduct.this,"Hãy nhập số lượng sản phẩm",Toast.LENGTH_LONG).show();
                                  }else if(spnNhomsanpham.getSelectedItem().toString().isEmpty()){
                                      Toast.makeText(AddProduct.this,"Hãy chọn nhóm sản phẩm",Toast.LENGTH_LONG).show();
                                  }else {
                                      String id = mDatabase1.push().getKey();
                                      String name = textName.getText().toString();
                                      String chitiet = textChitiet.getText().toString();
                                      Double giaban = Double.parseDouble(textGiaban.getText().toString());
                                      Double gianhap = Double.parseDouble(textGianhap.getText().toString());
                                      Integer soluong = Integer.parseInt(textSoluong.getText().toString());
                                      String nhomsanpham = spnNhomsanpham.getSelectedItem().toString();
                                      String img = taskSnapshot.getUploadSessionUri().toString();
                                      product = new Product(id, name, chitiet, nhomsanpham, gianhap, giaban, soluong, img);
                                      mDatabase1.child(nhomsanpham).child(id).setValue(product);
                                  }
                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                                  Toast.makeText(AddProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                              }
                          }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                              @Override
                              public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                  double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                                  progressBar.setProgress((int) progress);
                              }
                          });


                      }
                      else {
                          Toast.makeText(AddProduct.this, "No file upload", Toast.LENGTH_SHORT).show();
                      }
                  }
              });
                //
            }
            //

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //


    }

    private void openFileChoose()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK
                && data!=null && data.getData()!=null)
        {
            ImageUri=data.getData();
            imageView.setImageURI(ImageUri);
        }


    }
}

