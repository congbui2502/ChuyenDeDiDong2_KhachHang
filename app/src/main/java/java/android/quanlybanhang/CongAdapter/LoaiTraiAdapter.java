package java.android.quanlybanhang.CongAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.HomeProduct;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.Activity.AnimationUlti;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;

public class LoaiTraiAdapter extends RecyclerView.Adapter<LoaiTraiAdapter.LoaiTraiHolder>{
    private Context mContext;
    private List<HomeProduct> mLoaiTrais;
    private  int vitri = -1;

    private KhachHangActivity mainActivity;

    public LoaiTraiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<HomeProduct> list, KhachHangActivity mainActivity)
    {
        this.mainActivity=mainActivity;
        this.mLoaiTrais=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiTraiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.loaitrai,parent,false);

        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.autorun_viewpager2,parent,false);

        vitri++;
        Log.d("AAA",vitri+"");
        if(vitri==0)
        {
            return new LoaiTraiHolder(view1);
        }
            return new LoaiTraiHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LoaiTraiHolder holder, int position) {
        HomeProduct loaiTrai=mLoaiTrais.get(position);

        Log.d("qqq",mLoaiTrais.get(position).getCuahangs().size()+" ccc");



        if(loaiTrai!=null)
        {
            if(position==0)
            {

//                holder.tittle.setText(loaiTrai.getTittle());
                TraiAdapter traiAdapter=new TraiAdapter();
                traiAdapter.setData(loaiTrai.getSanphamnoibats(),mainActivity);
                holder.recycleView.setAdapter(traiAdapter);
                holder.recycleView.setOnFlingListener(null);
                LinearLayoutManager layoutManager = new
                        LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                holder.recycleView.setLayoutManager(layoutManager);
                PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
                pagerSnapHelper.attachToRecyclerView(holder.recycleView);
                holder.indicator2.attachToRecyclerView(holder.recycleView, pagerSnapHelper);
            }

            else  if(position == 1)
            {

                QuanNoiBatAdapter traiAdapter=new QuanNoiBatAdapter();
                traiAdapter.setData(loaiTrai.getCuahangs(),mainActivity,mContext);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
                holder.trais.setLayoutManager(linearLayoutManager);
                holder.trais.setAdapter(traiAdapter);

            } else  if(position == 2)
            {

                holder.tittle.setText(loaiTrai.getTittle());

                SanPhamNoiBatAdapter traiAdapter=new SanPhamNoiBatAdapter();
                traiAdapter.setData(mainActivity,loaiTrai.getSanphamQuangcao(), new SanPhamNoiBatAdapter.IclickAddToCartListener() {
                    @Override
                    public void onClickAddToCart(ImageView imageToCart, SanPham trai) {
                        AnimationUlti.translateAnimation( mainActivity.getViewAnimation(), imageToCart, mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                imageToCart.setBackgroundResource(R.drawable.cart_unshopping);
                                trai.setAddToCart(true);
                                traiAdapter.notifyDataSetChanged();
                                mainActivity.setCountProductInBuild(mLoaiTrais.size()+1,mainActivity);
                                mainActivity.getProducts().add(trai);
                                mainActivity.setCountProductInBuild( mainActivity.getProducts().size(),mainActivity);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                });

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
                holder.trais.setLayoutManager(linearLayoutManager);
                holder.trais.setAdapter(traiAdapter);

            }

        }



    }

    @Override
    public int getItemCount() {

        if(mLoaiTrais!=null)
        {
            return mLoaiTrais.size();
        }
        return 0;
    }

    public class LoaiTraiHolder extends RecyclerView.ViewHolder{
        private TextView tittle;
        private RecyclerView trais;

        private RecyclerView recycleView;
        private CircleIndicator2 indicator2;


        public LoaiTraiHolder(@NonNull View itemView) {
            super(itemView);

                if(vitri==0)
                {
                    recycleView =itemView.findViewById(R.id.recy1);

                    indicator2 =itemView.findViewById(R.id.indicator);
                }else {
                    tittle=itemView.findViewById(R.id.tvLoai);
                    trais=itemView.findViewById(R.id.recyLoai);
                }



        }
    }
}
