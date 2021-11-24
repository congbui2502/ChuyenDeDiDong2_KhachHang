package java.android.quanlybanhang.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;

public class ImageUploadActivity extends AppCompatActivity {
    private static final int   PICK_IMAGE_REQUEST=1;
    private Button btnChoose,btnUpload;
    private EditText edtChooseName;
    private TextView textViewShowUpload;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri ImageUri;

    private StorageReference mStogref;
    private DatabaseReference mReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        unit();


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openFileChoose();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();

            }
        });
    }

    private  void unit()
    {
        mStogref= FirebaseStorage.getInstance().getReference("uploads");


        mReference=FirebaseDatabase.getInstance().getReference("uploads");

        btnChoose=findViewById(R.id.btnChoose);
        btnUpload=findViewById(R.id.btn_upload);
        edtChooseName=findViewById(R.id.edt_choose_image);
        textViewShowUpload=findViewById(R.id.text_show_upload);
        imageView=findViewById(R.id.imgChoose);
        progressBar=findViewById(R.id.progressBar);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_baseline_home_24, R.color.red);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_baseline_photo_library_24, R.color.blue);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.color.yellow);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        //doi mau cho background menu khi item duoc chon
        bottomNavigation.setColored(false);

        //doi mau cho icon khi duoc chon
        bottomNavigation.setAccentColor(getResources().getColor(R.color.pink));

        //mau cua icon ko dc chon
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.black));

        AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(ImageUploadActivity.this, R.color.red))
                .setTextColor(ContextCompat.getColor(ImageUploadActivity.this, R.color.green))
                .build();
        bottomNavigation.setNotification(notification, 1);



        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                Log.d("AAA",""+position);
                return true;
            }
        });

    }

    private String getFileExtenstion(Uri uri)
    {
        ContentResolver  cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();

        return  mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile()
    {
        if(ImageUri!=null)
        {
               StorageReference fileRefence=   mStogref.child("uploads/"+System.currentTimeMillis()+"."+getFileExtenstion(ImageUri));
               fileRefence.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       Handler handler=new Handler();
                       handler.postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               progressBar.setProgress(0);

                           }
                       },5000);

                       Toast.makeText(ImageUploadActivity.this,"Upload successfull",Toast.LENGTH_SHORT).show();

                       SanPham sanPham= new SanPham(taskSnapshot.getUploadSessionUri().toString(),"abc xyz",10000,8000,"Cong Bui","aaaa",1,false);
                       mReference.push().setValue(sanPham);

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(ImageUploadActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                   }
               }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                       double progress=(100.0*snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
                       progressBar.setProgress((int) progress);
                   }
               });



        }else {
            Toast.makeText(ImageUploadActivity.this,"No file upload",Toast.LENGTH_SHORT).show();
        }

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


    private void setMenuItem()
    {
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_baseline_home_24, R.color.red);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_baseline_photo_library_24, R.color.blue);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.color.yellow);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        //doi mau cho background menu khi item duoc chon
        bottomNavigation.setColored(false);

        //doi mau cho icon khi duoc chon
        bottomNavigation.setAccentColor(getResources().getColor(R.color.pink));

        //mau cua icon ko dc chon
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.black));

        AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(ImageUploadActivity.this, R.color.red))
                .setTextColor(ContextCompat.getColor(ImageUploadActivity.this, R.color.green))
                .build();
        bottomNavigation.setNotification(notification, 1);



        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                Log.d("AAA",""+position);
                return true;
            }
        });

    }
}