package java.android.quanlybanhang.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.GioHang;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DangkyKhachHang extends AppCompatActivity implements View.OnClickListener {
    private Button signinNow, signup,btnChooseimg;

    private ImageView imageView;
    private TextInputEditText username, email, phone,date, password, confirm_password;
    private CardView google;
    private static final int   PICK_IMAGE_REQUEST=1;
    //Firebase
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mFirebaseAuth;
    private Uri ImageUri;

    private StorageReference mStogref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_khachhang);
        mStogref= FirebaseStorage.getInstance().getReference("khachhang");
        imageView = findViewById(R.id.image_view);
        signinNow = findViewById(R.id.btn_signin_now);
        signup = findViewById(R.id.btn_signup);
        date = findViewById(R.id.edt_date);
        username = findViewById(R.id.edt_username);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        password = findViewById(R.id.edt_password);
        confirm_password = findViewById(R.id.edt_confirm_password);
        btnChooseimg = findViewById(R.id.btnSelectImg);
        signinNow.setOnClickListener(this);
        signup.setOnClickListener(this);
        btnChooseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChoose();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signin_now:
                Intent intent = new Intent(DangkyKhachHang.this, DangNhapKhachHangActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                signup();
                break;
        }
    }

    private void signup(){
        mFirebaseAuth = FirebaseAuth.getInstance();

        String mail = email.getText().toString();
        String userName = username.getText().toString();
        String mPhone = phone.getText().toString();
        String dt = date.getText().toString();
        String pass = password.getText().toString();
        String cpass = confirm_password.getText().toString();


        if(userName.isEmpty()){
            username.setError("Plese enter username");
            username.requestFocus();
        }else if(mail.isEmpty()){
            email.setError("Plese enter email");
            email.requestFocus();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("please provide valid email");
            email.requestFocus();
        } else if(mPhone.isEmpty()){
            phone.setError("Plese enter email");
            phone.requestFocus();
        } else if(dt.isEmpty()){
            date.setError("Plese enter date");
            date.requestFocus();
        }else if(pass.isEmpty()){
            password.setError("Plese enter password");
            password.requestFocus();
        }else if(cpass.isEmpty()){
            confirm_password.setError("Plese enter confirm password");
            confirm_password.requestFocus();
        }else if (!pass.equals(cpass)){
            confirm_password.setError("Passwords are not the sames");
            confirm_password.requestFocus();
        }
        else if(mail.isEmpty() && pass.isEmpty()){
            Toast.makeText(DangkyKhachHang.this,"Fialds Are Empty!", Toast.LENGTH_LONG).show();
        }else if(!(mail.isEmpty() && pass.isEmpty() && mPhone.isEmpty() &&userName.isEmpty() )){
            mFirebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(DangkyKhachHang.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(DangkyKhachHang.this, "SignUp UnSuccessful, plese Try Again", Toast.LENGTH_SHORT).show();
                    }else{
                        String UID = mFirebaseAuth.getUid();
                        uploadFile(userName,dt,mail,mPhone,UID);
//                        mFirebaseInstance = FirebaseDatabase.getInstance();
//                        mFirebaseDatabase = mFirebaseInstance.getReference();
//                        KhachHang khachHang=new KhachHang(userName,dt,mail,mPhone,UID);
//                        mFirebaseDatabase.child("KhachHang"+"/"+UID).setValue(khachHang);
////                        LoadingDialog loadingDialog = new LoadingDialog(SignUpActivity.this);
////                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(DangkyKhachHang.this, "Signup succes", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangkyKhachHang.this, DangNhapKhachHangActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }
                }
            });}else {
            Toast.makeText(DangkyKhachHang.this,"Error Occurred!", Toast.LENGTH_LONG).show();
        }

    }
    private String getFileExtenstion(Uri uri)
    {
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();

        return  mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile(String userName,String dt,String mail,String mPhone, String UID)
    {
        if(ImageUri!=null)
        {
            StorageReference fileRefence=  mStogref.child(System.currentTimeMillis()+"."+getFileExtenstion(ImageUri));
            fileRefence.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRefence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            mFirebaseInstance = FirebaseDatabase.getInstance();
                            String img = uri.toString();
                            mFirebaseDatabase = mFirebaseInstance.getReference();
                            KhachHang khachHang=new KhachHang(userName,dt,mail,mPhone,UID,img);
                            mFirebaseDatabase.child("KhachHang"+"/"+UID).setValue(khachHang);
                            List<SanPham> sanPhams=new ArrayList<>();
                            GioHang gioHang=new GioHang("abc",sanPhams);

                            mFirebaseDatabase.child("gioHang").child(UID).push().setValue(gioHang);

                            Toast.makeText(DangkyKhachHang.this,"Upload successfull",Toast.LENGTH_SHORT).show();
                        }
                    });


//                    SanPham sanPham= new SanPham(taskSnapshot.getUploadSessionUri().toString(),"abc xyz",10000,8000,"Cong Bui","aaaa",1);
//                    mReference.push().setValue(sanPham);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DangkyKhachHang.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                    double progress=(100.0*snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
//                    progressBar.setProgress((int) progress);
                }
            });

        }
        else {
            Toast.makeText(DangkyKhachHang.this,"No file upload",Toast.LENGTH_SHORT).show();
        }

    }


    private void openFileChoose()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
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
