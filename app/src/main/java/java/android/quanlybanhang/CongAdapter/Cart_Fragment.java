package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.DonHangOnline;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.Activity.AnimationUlti;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.util.List;

public class Cart_Fragment extends Fragment {
    private RecyclerView recyclerProduct;
    private ProductAdapter productAdapter;
    private View mView;
    private KhachHangActivity mainActivity;
    private CheckBox cbCartFragment;
    private TextView tvCartFragment;
    private Button btnCartFragment;
    private ProductAdapter.SetPos setPos;
    private String idQuan;


    public void setData(ProductAdapter.SetPos setPos)
    {
        this.setPos=setPos;
    }
    private List<SanPham> quans;

    public void getidQuan(String idQuan)
    {
        this.idQuan=idQuan;

    }



    public Cart_Fragment(List<SanPham> quan,String idQuan) {
        this.quans=quan;
        this.idQuan = idQuan;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView=inflater.inflate(R.layout.cart_fragment,container,false);
        Log.d("BBB","onCreate CartFragment");

        mainActivity=(KhachHangActivity)  getActivity();

        recyclerProduct=mView.findViewById(R.id.recycleCartFragment);
        cbCartFragment=mView.findViewById(R.id.cbCartFragment);
        tvCartFragment=mView.findViewById(R.id.tvCartFragment);
        btnCartFragment=mView.findViewById(R.id.btnCartFragment);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mainActivity);
        recyclerProduct.setLayoutManager(linearLayoutManager);

        productAdapter=new ProductAdapter(quans);
        productAdapter.setListCart();
        productAdapter.setData( mainActivity,new ProductAdapter.IclickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imageToCart, SanPham product) {
                AnimationUlti.translateAnimation(mainActivity.getViewAnimation(), imageToCart, mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imageToCart.setBackgroundResource(R.drawable.cart_unshopping);
                        product.setAddToCart(true);
                        productAdapter.notifyDataSetChanged();
                        mainActivity.setCountProductInBuild(quans.size()+1,mainActivity);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void setGiaDonHang(long donHang) {

                tvCartFragment.setText(addDauPhay(donHang)+" VNĐ");
            }


        },setPos);

        recyclerProduct.setAdapter(productAdapter);

        btnCartFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(productAdapter.getListProductIsChecked().size()>0)
                    {
                        DonHangFragment donHangFragment=new DonHangFragment(productAdapter.getListProductIsChecked());
                        donHangFragment.getIdQuan(idQuan);
                        mainActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,donHangFragment).commit();

                    }else {
                        Toast.makeText(getContext(),"Vui lòng chọn sản phẩm",Toast.LENGTH_SHORT).show();
                    }

            }
        });

        cbCartFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCartFragment.isChecked()==true)
                {
                    productAdapter.setCheckedCheckbox(true);

                    productAdapter.iclickAddToCartListener.setGiaDonHang(productAdapter.setGiaDonHang());
                }else {
                    productAdapter.setCheckedCheckbox(false);
                    productAdapter.iclickAddToCartListener.setGiaDonHang(productAdapter.setGiaDonHang());

                }

            }
        });

        return mView;
    }




    //them dau phay cho don gia
    public   static  String addDauPhay(long abc)
    {
        String xyz=abc+"";
        String [] list = xyz.split("");
        String kq="";
        int pos =1;
        for (int i = (list.length-1); i >=0 ; i--) {
            if(pos%3==0 && pos <list.length)
            {
                kq =","+ list[i]+kq;
            }else {
                kq = list[i]+kq;
            }
            pos++;
        }


        return  kq;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("BBB","onCreated CartFragment");


//        history_adapter=new History_Adapter();
//        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
//        recyclerViewHistory.setLayoutManager(manager);
//
//        history_adapter.setData(getList());
//
//
//        recyclerViewHistory.setAdapter(history_adapter);



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("BBB","onResume CartFragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("BBB","onStop CartFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BBB","onDestroy CartFragment");
    }

    //    private List<History_quan> getList()
//    {
//        quans=new ArrayList<History_quan>();
//
//        quans.add(new History_quan(123,"Cong Bui",3,"6/8/2021"));
//
//        quans.add(new History_quan(123,"Coffee House",3,"6/8/2021"));
//        quans.add(new History_quan(123,"HighLand",2,"6/8/2021"));
//
//
//        return quans;
//    }

//    private List<Product> getListProduct()
//    {
//        quans=new ArrayList<Product>();
//
//        quans.add(new Product(123,"Cong Bui",3+""));
//
//        quans.add(new Product(123,"Coffee House",3+""));
//        quans.add(new Product(123,"HighLand",2+""));
//
//
//        return quans;
//    }
    public  void setFirebase()
    {
        DatabaseReference mReference= FirebaseDatabase.getInstance().getReference().child("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("donhangonline");
        DonHangOnline donHangOnline=new DonHangOnline("JxZOOK1RzcMM7pL5I6naGZfYSsu2",20000,0,
                "08:27 19/10/2021",productAdapter.getListProductIsChecked());

        mReference.child("dondadat").push().setValue(donHangOnline);
        mReference.child("dondagiao").push().setValue(donHangOnline);
        mReference.child("dondanhan").push().setValue(donHangOnline);
        mReference.child("dondahuy").push().setValue(donHangOnline);
    }
}
