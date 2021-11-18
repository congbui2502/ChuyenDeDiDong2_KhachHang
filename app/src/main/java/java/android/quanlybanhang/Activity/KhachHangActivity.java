package java.android.quanlybanhang.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.CongAdapter.Account_fragment;
import java.android.quanlybanhang.CongAdapter.Cart_Fragment;
import java.android.quanlybanhang.CongAdapter.HomeFragment;
import java.android.quanlybanhang.CongAdapter.ProductAdapter;
import java.android.quanlybanhang.CongAdapter.Putnotification;
import java.android.quanlybanhang.CongAdapter.TableFragment;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class KhachHangActivity extends AppCompatActivity {


    private static  boolean MUST_DETACH_FROM_BACKSTACK = false;

    public void setProducts(List<SanPham> products) {
        this.products = products;
    }
    private Context context =this;
    private  List<SanPham> products;
    FrameLayout frameLayout;
    public    Fragment fragment;
    public HomeFragment homeFragment;

    private String idKhachHang="idKhachHang";
//    private Cart_Fragment history_fragment;
    private String Shop_Id="JxZOOK1RzcMM7pL5I6naGZfYSsu2";
    private int mCount;
    private View viewEndAnimation;
    private ImageView viewAnimation;
    private DatabaseReference mReference;
    private   AHBottomNavigation bottomNavigation;
    public static Cart_Fragment cart_fragment;

    public AHBottomNavigation getBottomNavigation() {
        return bottomNavigation;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public String getShop_Id() {
        return Shop_Id;
    }

    public List<SanPham> getProducts() {
        return products;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("BBB","onCreate Mainactivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.khachhang_activity);

        
//        viewEndAnimation= findViewById(R.id.viewEndAnimation);
//        viewAnimation=findViewById(R.id.view_animation);
            cart_fragment=new Cart_Fragment(products);
           cart_fragment.setData(new ProductAdapter.SetPos() {
               @Override
               public void setPos(int size) {
                   setCountProductInBuild(size);
               }
           });

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,cart_fragment);
        transaction.addToBackStack("cartfragment");
        transaction.commit();




        mReference=FirebaseDatabase.getInstance().getReference();
        Fragment fragment1=new HomeFragment(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment1).addToBackStack("").commit();

        setMenuItem();
//        addProduct();
//        getAddlist();

        products=getCartList();



//        BottomNavigationView  navigationView=findViewById(R.id.bottomNavigation);
//
//        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//           @Override
//           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//              Fragment cart_fragment=null;
//
//               Log.d("AAA",item.getItemId()+"");
//               Log.d("AAA",R.id.nav_home+"");
//               Log.d("AAA",R.id.nav_library+"");
//               Log.d("AAA",R.id.nav_myAccount+"");
//
//
//               if(item.getItemId()==R.id.nav_home)
//               {
//
//               }else  if(item.getItemId()==R.id.nav_library)
//               {
//                    cart_fragment=new History_Fragment();
//               }else  if(item.getItemId()==R.id.nav_myAccount)
//               {
//                   cart_fragment=new Account_fragment();
//
//               }else {
//                   cart_fragment=new HomeFragment();
//
//               }
//
//
//                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,cart_fragment).commit();
//
//              return true;
//           }
//       });

        Log.d("AAA",getListProduct().size()+"");

//        this.products=getListProduct();

//        history_fragment=new Cart_Fragment(this.products);
        setCountProductInBuild(products.size());

        CountDownTimer countDownTimer=new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                sendNotification();

            }
        }.start();

    }

    public void setCountProductInBuild(int count)
    {
        mCount=count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                .setTextColor(ContextCompat.getColor(this, R.color.green))
                .build();
            bottomNavigation.setNotification(notification, 1);

    }



//    private List<LoaiTrai> getList()
//    {
//        List<LoaiTrai> loaiTrais=new ArrayList<>();
//
//        List<SanPham> trais=new ArrayList<>();
//
//        trais.add(new Trai(R.mipmap.bamdth,"anh Bui"));
//        trais.add(new Trai(R.mipmap.baxe,"anh Cong"));
//        trais.add(new Trai(R.mipmap.cauho,"ho dep"));
//        trais.add(new Trai(R.mipmap.chuald,"chua"));
//        trais.add(new Trai(R.mipmap.sauae,"6 ae"));
//        trais.add(new Trai(R.mipmap.child,"child"));
//
//        loaiTrais.add(new LoaiTrai("Dep trai 1",trais));
//        loaiTrais.add(new LoaiTrai("Dep trai 2",trais));
//        loaiTrais.add(new LoaiTrai("Dep trai 3",trais));
//        loaiTrais.add(new LoaiTrai("Dep trai 4",trais));
//        loaiTrais.add(new LoaiTrai("Dep trai 5",trais));
//
//
//        return loaiTrais;
//    }



//    private  List<SanphamChon> dsSanPhamDaChon()
//    {
//        List<SanphamChon> ds=new ArrayList<SanphamChon>();
//
//        SanPham sanPham=new SanPham();
//        SanphamChon sanphamChon=new SanphamChon();
//
//
//        sanPham=new SanPham(R.mipmap.bohuc,"Nước tăng lực Red Bull loong 240ml ",15000,10000,"Red Bull","bbb",1);
//        sanphamChon=new SanphamChon(sanPham,3);
//        ds.add(sanphamChon);
//
//
//        sanPham=new SanPham(R.mipmap.numberone,"Nước tăng lực Number 1 chai 330ml ",10000,8000,"Numbre One","bbb",1);
//        sanphamChon=new SanphamChon(sanPham,2);
//        ds.add(sanphamChon);
//
//        return ds;
//    }


    private void setMenuItem()
    {
         bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_baseline_home_24, R.color.red);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_baseline_shopping_cart_24, R.color.blue);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_baseline_photo_library_24, R.color.blue);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_baseline_sentiment_satisfied_alt_24, R.color.yellow);
// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        //doi mau cho background menu khi item duoc chon
        bottomNavigation.setColored(false);

        //doi mau cho icon khi duoc chon
        bottomNavigation.setAccentColor(getResources().getColor(R.color.pink));


        //mau cua icon ko dc chon
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.black));
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                Log.d("AAA",""+position);
               if(position==0)
               {
//                   if(getSupportFragmentManager().findFragmentByTag("homefragment")!=null)
//                   {
//                       Log.d("jjj","abcxyz");
//
//                       fragment = getSupportFragmentManager().findFragmentByTag("homefragment");
//                   }else {
//                       fragment=new HomeFragment();
//                       Log.d("jjj","xyzabc");
//                   }
                   if(getSupportFragmentManager().findFragmentByTag("homefragment")!=null)
                   {
                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getSupportFragmentManager().findFragmentByTag("homefragment")).commit();
                   }else{
                       fragment=new HomeFragment(KhachHangActivity.this);

                       FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                       transaction.replace(R.id.fragment_container,fragment);
                       transaction.addToBackStack("cartfragment");
                       transaction.commit();
                   }

               }else  if(position==1)
               {
//                   if(getSupportFragmentManager().findFragmentByTag("cartfragment")!=null)
//                   {
//
//                       fragment = getSupportFragmentManager().findFragmentByTag("cartfragment");
//                   }else {
                      Cart_Fragment fragment1=new Cart_Fragment(products);
                      fragment1.setData(new ProductAdapter.SetPos() {
                          @Override
                          public void setPos(int size) {
                              setCountProductInBuild(size);
                          }
                      });

//                   }
                   FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.fragment_container,fragment1);
                   transaction.addToBackStack("cartfragment");
                   transaction.commit();

               }else  if(position==2)
               {
//                   if(getSupportFragmentManager().findFragmentByTag("accountfragment")!=null)
//                   {
//
//                       fragment = getSupportFragmentManager().findFragmentByTag("accountfragment");
//                   }else {
                       fragment=new Account_fragment();
//                   }
//
                   FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.fragment_container,fragment);
                   transaction.addToBackStack("accountfragment");
                   transaction.commit();
               }else {
//                   fragment=new ShopProductFragment(MainActivity.this,MainActivity.this);

//                   if(getSupportFragmentManager().findFragmentByTag("tablefragment")!=null)
//                   {
//
//                       fragment = getSupportFragmentManager().findFragmentByTag("tablefragment");
//                   }else {
                       fragment =new TableFragment();
//                   }

                   FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.fragment_container,fragment);
                   transaction.addToBackStack("tablefragment");
                   transaction.commit();

               }

                return true;
            }
        });
    }

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getViewAnimation() {
        return viewAnimation;
    }

    public List<SanPham> getListProduct()
    {

        List<SanPham> trais=new ArrayList<SanPham>();
        mReference.child("gioHang").child(idKhachHang).child("sanPham").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String key= snapshot1.getKey();
                    SanPham sanPham=snapshot.child(key).getValue(SanPham.class);
                    boolean flag=true;
                    for (int i = 0; i < products.size(); i++) {
                        if (sanPham.getNameProduct().equals(products.get(i).getNameProduct()))
                        {
                            flag=false;
                            break;
                        }
                    }
                    if(flag)
                    {
                        trais.add(sanPham);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return trais;
    }

    public List<SanPham> getCartList()
    {

        List<SanPham> trais=new ArrayList<SanPham>();
        mReference.child("gioHang").child(idKhachHang).child("sanPham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham=snapshot.getValue(SanPham.class);
                trais.add(sanPham);
                mReference.child("sanphamQuangcao").push().setValue(sanPham);

                setCountProductInBuild(trais.size());
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
        return trais;
    }

    public void addProduct()
    {
        mReference.child("cuaHang").push().setValue(new CuaHang("JxZOOK1RzcMM7pL5I6naGZfYSsu2","https://firebasestorage.googleapis.com/v0/b/quanlybanhang-26023.appspot.com/o/uploads%2Fuploads%2F1629291525186.jpg?alt=media&token=588f1c67-8120-4a44-9677-9cfc806a66f9",
                "Highland"));

        mReference.child("cuaHang").push().setValue(new CuaHang("JxZOOK1RzcMM7pL5I6naGZfYSsu2","https://firebasestorage.googleapis.com/v0/b/quanlybanhang-26023.appspot.com/o/uploads%2Fuploads%2F1629326499235.jpg?alt=media&token=b04912a3-fea2-462f-bb9b-8a491c25454c",
                "Trung Nguyên Coffee"));
    }

    public void getAddlist()
    {
        mReference.child("cuaHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CuaHang cuaHang=snapshot.getValue(CuaHang.class);
                Log.d("ppp",cuaHang.getName()+"nmb");
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

    public void sendNotification()
    {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.add);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String day =simpleDateFormat.format(new Date());

        simpleDateFormat=new SimpleDateFormat("HH:mm:ss");

        String time =simpleDateFormat.format(new Date());

        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.donga_bank_notification);
//        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);
        notificationLayout.setTextViewText(R.id.tvMessage,day+" "+time+" Ma xac thuc chu...");
        notificationLayout.setImageViewBitmap(R.id.imgLogo,bitmap);


        RemoteViews notificationLayoutmax = new RemoteViews(getPackageName(), R.layout.donga_bank_notification_max);
//        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);
        notificationLayoutmax.setTextViewText(R.id.tvMessagemax,day+" "+time+" Ma xac thuc chuyen khoan " +
                "ngoai he thong tren INTERNET la 387155. Vui long nhap ma xac thuc trong vong 5 phut " +
                "de hoan tat giao dich.");
        notificationLayoutmax.setImageViewBitmap(R.id.imgLogo,bitmap);




        Notification notification=new NotificationCompat.Builder(this, Putnotification.CHANNEL_ID)
//                .setContentTitle("this is my application")
//                .setContentText("cmm")
//                .setLargeIcon(bitmap)
                .setCustomContentView(notificationLayout)

                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setCustomBigContentView(notificationLayoutmax)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null)
        {
            notificationManager.notify(0,notification);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("BBB","onResume Mainactivity");
    }

    @Override
    protected void onPause() {
        Log.d("BBB","onPause Mainactivity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("BBB","onStop Mainactivity");
    }

    
}