package java.android.quanlybanhang.DatBan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.CongAdapter.QuanNoiBatAdapter;
import java.android.quanlybanhang.CongAdapter.ShopProductAdapter;
import java.android.quanlybanhang.CongAdapter.Shop_Adapter;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.ArrayList;
import java.util.List;

public class ShopProductFragment extends Fragment  {
    public static final String TAG= ShopProductFragment.class.getName();

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private FloatingActionButton fabAdd;
    private  RecyclerView recyProduct;
    private RecyclerView recyLoaiSP;
    private DatabaseReference mReference;
    private  List<SanPham> dsSanPhamTheoLoai;
    private List<String> dsLoaiSanPham;
    private boolean isExpanded=true;
    private KhachHangActivity khachHangActivity;
    private Context context;
    private ImageView imgShop;
    private ShopProductAdapter loaiTraiAdapter;
    private Shop_Adapter shop_adapter;
    private String Shop_Name="Highland";
    private CuaHang cuaHang;
    private String id_shop= "";
    private String logoUrl="";
    private Button bnt_datban;
    private Dialog dialogban;
    Window window;
    TextView DatBan, thoat,danhgia;
    RatingBar ratingstart;
    private DatabaseReference mDatabase;
    ArrayList<RatingModel> ratingModels;
    float sao,saos;
    TextView tv_danhgia;

    public ShopProductFragment(KhachHangActivity mainActivity, Context context, QuanNoiBatAdapter.getdata getdata) {
            this.khachHangActivity =mainActivity;
            this.context=context;
            this.cuaHang=getdata.getData();
            this.id_shop=cuaHang.getId();
            this.Shop_Name=cuaHang.getName();
            this.logoUrl= cuaHang.getLogoUrl();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_product,container,false);
        initUI(view);
        initToolBar();
        initRecycleView();
        initToolbarAnimation();
        onClickButtonCart();
        OnclickMenuchucnang();
        hamlaysao();
        return view;
    }

    private  void initUI(View view)
    {
        this.dsSanPhamTheoLoai=new ArrayList<>();
        this.dsLoaiSanPham=new ArrayList<>();
        mReference=FirebaseDatabase.getInstance().getReference();
        appBarLayout=view.findViewById(R.id.AppBarLayout);
        ratingstart = view.findViewById(R.id.ratingstart);
        collapsingToolbarLayout=view.findViewById(R.id.CollapsingToolbarLayout);
        toolbar=view.findViewById(R.id.toolBar);
        imgShop=view.findViewById(R.id.imgShop);
        bnt_datban = view.findViewById(R.id.bnt_datban);
        fabAdd=view.findViewById(R.id.FloatingActionButton);
        recyProduct=view.findViewById(R.id.recyShopproduct);
        recyLoaiSP=view.findViewById(R.id.recyLoaisanPham);
        tv_danhgia = view.findViewById(R.id.tv_danhgia);
        Glide.with(context).load(logoUrl).into(imgShop);
        getloaiDoUong();
        onclickdatban();

    }

    private  void initToolBar()
    {
       khachHangActivity.setSupportActionBar(toolbar);

       if(khachHangActivity.getSupportActionBar()!=null)
       {
           khachHangActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

           toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(khachHangActivity.getSupportFragmentManager()!=null)
                   {
                       khachHangActivity.getSupportFragmentManager().popBackStack();
                   }

//                   if(getFragmentManager()!=null)
//                   {
//                       getFragmentManager().popBackStack();
//                   }else {
//                       Toast.makeText(mainActivity,"abc xyz",Toast.LENGTH_SHORT).show();
//
//                   }

//                   HomeFragment fragment1=new HomeFragment();
//
//                   getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment1).commit();
//                   fragmentTransaction.addToBackStack("");
                   Log.d("uuu","uuu");

               }
           });
       }
    }

    private void initRecycleView()
    {
        recyProduct.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyProduct.setLayoutManager(linearLayoutManager);
        loaiTraiAdapter=new ShopProductAdapter();
        loaiTraiAdapter.setData(dsSanPhamTheoLoai);
        recyProduct.setAdapter(loaiTraiAdapter);

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyLoaiSP.setLayoutManager(linearLayoutManager1);
         shop_adapter=new Shop_Adapter();
            shop_adapter.setData(dsLoaiSanPham);


        shop_adapter.setOnClick(new Shop_Adapter.IclickchangRecycleView() {
            @Override
            public void changListProduct(String s) {
                    loaiTraiAdapter.setData(getListproduct(s));
                    loaiTraiAdapter.notifyDataSetChanged();
            }

            @Override
            public void setAdapterForProductList(String s) {
                setRecycleView(s);
            }
        });
        recyLoaiSP.setAdapter(shop_adapter);
    }

    public void  setRecycleView(String s)
    {

        loaiTraiAdapter.setData( getListproduct(s));
        loaiTraiAdapter.notifyDataSetChanged();

    }



    private void initToolbarAnimation()
    {

        collapsingToolbarLayout.setTitle(Shop_Name);
        collapsingToolbarLayout.setExpandedTitleColor(R.color.black);


        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.background);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int myColor =palette.getVibrantColor(getResources().getColor(R.color.green));
                collapsingToolbarLayout.setContentScrimColor(myColor);
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.black));
            }
        });


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset)>200)
                {
                    isExpanded=false;
                }else {
                    isExpanded=true;
                }


            }
        });
    }

    private List<SanPham> getListproduct(String s)
    {
                dsSanPhamTheoLoai=new ArrayList<>();
            List<SanPham> sanPhams=new ArrayList<>();
            mReference.child("cuaHang").child(id_shop).child("sanpham").child(s).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    SanPham sanPham=snapshot.getValue(SanPham.class);
                    sanPhams.add(sanPham);

                    dsSanPhamTheoLoai.add((sanPham));
                    loaiTraiAdapter.notifyDataSetChanged();

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
            return sanPhams;
    }

    private void getloaiDoUong()
    {
        mReference.child("cuaHang").child(id_shop).child("sanpham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String bien="";
                bien=snapshot.getKey();

                dsLoaiSanPham.add(bien);
                shop_adapter.notifyDataSetChanged();

//                bien=previousChildName.toString();

                Log.d("CCC",bien+"acb");

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


    private  void  onClickButtonCart()
    {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Click Cart",Toast.LENGTH_SHORT).show();
//                Fragment fragment=khachHangActivity.getSupportFragmentManager().findFragmentByTag("homefragment");

                khachHangActivity.getSupportFragmentManager().popBackStack();

            }
        });
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("BBB","onCreated ShopFragment");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("BBB","onResume ShopFragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("BBB","onStop ShopFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BBB","onDestroy ShopFragment");
    }

    private void onclickdatban(){
//        bnt_datban.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(khachHangActivity, DanhSachChonBan.class);
//                intent.putExtra("id_cuahang",id_shop);
//                startActivity(intent);
//
//            }
//        });
    }
    private void HamTaodialog(int gravity) {

        dialogban = new Dialog(khachHangActivity);
        dialogban.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogban.setContentView(R.layout.dailongthanhtoan);

        window = dialogban.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        window.setAttributes(windownAttributes);
        if (Gravity.BOTTOM == gravity) {
            dialogban.setCancelable(true);
        } else {
            dialogban.setCancelable(false);
        }
        DatBan = dialogban.findViewById(R.id.tvdatban);
        thoat = dialogban.findViewById(R.id.thoat);
        danhgia = dialogban.findViewById(R.id.tvdanhgia);
        OnclickChucnang(DatBan, thoat,danhgia);
        dialogban.show();
    }

    public void OnclickMenuchucnang() {
        bnt_datban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HamTaodialog(Gravity.BOTTOM);
            }
        });
    }

    public void OnclickChucnang( TextView DatBan, TextView thoat,TextView tvdanhgia) {
        DatBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(khachHangActivity, "Đặt Bàn", Toast.LENGTH_LONG).show();
                dialogban.dismiss();
                Intent intent = new Intent(khachHangActivity, DanhSachChonBan.class);
                intent.putExtra("id_cuahang",id_shop);
                intent.putExtra("ten_cuahang",Shop_Name);
                startActivity(intent);

            }
        });
        tvdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(khachHangActivity, "Đánh Giá", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(khachHangActivity, DanhSachDanhGia.class);
                intent.putExtra("id_cuahang",id_shop);
                startActivity(intent);
                dialogban.dismiss();

            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(khachHangActivity, "Đóng", Toast.LENGTH_LONG).show();
                dialogban.dismiss();
            }
        });

    }
    private void hamlaysao(){
        mDatabase = FirebaseDatabase.getInstance().getReference("rating").child("idcuahang").child(id_shop);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ratingModels = new ArrayList<>();
                if(snapshot.getValue()!=null) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        for(DataSnapshot snap : snapshot1.getChildren()){
                            String comment = snap.child("comment").getValue() + "";
                            String date = snap.child("date").getValue() + "";
                            String numberrating = snap.child("numberrating").getValue() + "";
                            String tenkhachhang = snap.child("tenkhachhang").getValue() + "";
                            ratingModels.add(new RatingModel(comment, date, numberrating, tenkhachhang));
                            saos += Float.parseFloat(numberrating) ;
                        }
                    }
                    sao = saos / ratingModels.size() ;
                    tv_danhgia.setText(sao+"/"+"5"+ " ("+ratingModels.size()+"lượt đánh giá )");
                    ratingstart.setRating(sao);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






}
