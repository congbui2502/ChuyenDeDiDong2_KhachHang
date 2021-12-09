package java.android.quanlybanhang.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.HomeProduct;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.login.ForgetPasswordActivity;
import java.util.ArrayList;
import java.util.List;


public class DangNhapKhachHangActivity extends AppCompatActivity {
    Button btnDangNhap,btnDangki;
    EditText edtDangNhap, edtMatKhau;
    TextView tv_quemk;
    CheckBox chk;
    String user,pass;
    private String idUser;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReference;
    public  static KhachHang  khachHang;
    public static List<HomeProduct> listHome;
    private int flag=-1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap_khachhang);
        listHome=getList();
        btnDangki = findViewById(R.id.btn_dangky);
        btnDangNhap = (Button) findViewById(R.id.btn_dangnhap);
        edtDangNhap = (EditText) findViewById(R.id.edt_dangnhap);
        edtMatKhau = (EditText) findViewById(R.id.edt_matkhau);
        tv_quemk=findViewById(R.id.tv_quenmk);
        chk = (CheckBox) findViewById(R.id.saveUser);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tv_quemk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callForgetPassword();
            }
        });
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhapKhachHangActivity.this,DangkyKhachHang.class);
                startActivity(intent);
            }
        });

    }
    private void callForgetPassword() {
        Intent intent = new Intent(DangNhapKhachHangActivity.this, ForgetPasswordActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(edtDangNhap, "edt_username");
        pairs[1] = new Pair<View, String>(btnDangNhap, "button_sign");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DangNhapKhachHangActivity.this, pairs);
            startActivity(intent, options.toBundle());
        }
    }

    String strUser, strPass;
    private void login() {
        strUser = edtDangNhap.getText().toString();
        strPass = edtMatKhau.getText().toString();
        mFirebaseAuth = FirebaseAuth.getInstance();
        if (strUser.isEmpty()) {
            edtDangNhap.setError("Plese enter email id");
            edtDangNhap.requestFocus();
        } else if (strPass.isEmpty()) {
            edtMatKhau.setError("Plese enter your password");
            edtMatKhau.requestFocus();
        } else if (strUser.isEmpty() && strPass.isEmpty()) {
            Toast.makeText(DangNhapKhachHangActivity.this, "Fialds Are Empty!", Toast.LENGTH_LONG).show();
        } else if (!(strUser.isEmpty() && strPass.isEmpty())) {
            mFirebaseAuth.signInWithEmailAndPassword(strUser, strPass).addOnCompleteListener(DangNhapKhachHangActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(DangNhapKhachHangActivity.this, "Signin error", Toast.LENGTH_SHORT).show();
                    } else {
                        idUser = mFirebaseAuth.getUid();

                        mReference= FirebaseDatabase.getInstance().getReference("KhachHang");
                        mReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                if(snapshot.getKey().equals(idUser))
                                {
                                    if(flag>0 && listHome.size()>0)
                                    {
                                        khachHang = snapshot.getValue(KhachHang.class);
                                        Intent intent = new Intent(DangNhapKhachHangActivity.this, KhachHangActivity.class);
                                        Bundle bundle= new Bundle();
                                        bundle.putSerializable("khachhang",khachHang);

                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }

                }
            });
        } else {
            Toast.makeText(DangNhapKhachHangActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
        }
    }

    public List<HomeProduct> getList()
    {

        List<HomeProduct> loaiTrais=new ArrayList<>();
        List<SanPham> sanphamnoibat=new ArrayList<>();
        List<CuaHang> cuahang=new ArrayList<>();
        List<SanPham> sanphamquangcao=new ArrayList<>();
        mReference= FirebaseDatabase.getInstance().getReference();
        mReference.child("sanphamnoibat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham=snapshot.getValue(SanPham.class);
                sanphamnoibat.add(sanPham);
                flag++;


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loaiTrais.add(new HomeProduct("Sản phẩm nổi bật",sanphamnoibat,new ArrayList<CuaHang>(),new ArrayList<SanPham>()));




        mReference.child("cuaHang").addChildEventListener(new ChildEventListener() {
            String idShop="";
            String nameShop="";
            String logoUrl="";

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                idShop = snapshot.getKey();
                idShop=snapshot.child("thongtin").child("id").getValue(String.class);
                logoUrl=snapshot.child("thongtin").child("logoUrl").getValue(String.class);
                nameShop=snapshot.child("thongtin").child("name").getValue(String.class);

                CuaHang sanPham=new CuaHang(idShop,logoUrl,nameShop);
                cuahang.add(sanPham);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loaiTrais.add(new HomeProduct("Của hàng",new ArrayList<SanPham>(),cuahang,new ArrayList<SanPham>()));


        mReference.child("sanPhamQuangCao").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                for (DataSnapshot snapshot1: snapshot.getChildren())
                {
                    if(snapshot1.getKey().equals("sanpham"))
                    {
                        for (DataSnapshot snapshot2: snapshot1.getChildren())
                        {
                            SanPham sanPham=snapshot2.getValue(SanPham.class);
                            sanPham.setSoluong(1);
                            sanphamquangcao.add(sanPham);

                        }
                    }
                }




            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loaiTrais.add(new HomeProduct("Sản phẩm mới",new ArrayList<SanPham>(),new ArrayList<CuaHang>(),sanphamquangcao));


        return loaiTrais;
    }

}



