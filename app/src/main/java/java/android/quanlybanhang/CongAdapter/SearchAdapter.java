package java.android.quanlybanhang.CongAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.Activity.SuperQuangCaoActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.KhuyenMai;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.KhuyenMaiViewHolder> {
    private List<CuaHang> cuaHangList;
    private List<SanPham> sanPhamList;
    private Context context;
    private KhachHangActivity activity;


    public void setData(List<CuaHang> cuaHangList,List<SanPham> sanPhamList,Context context,KhachHangActivity activity)
    {
        this.cuaHangList=cuaHangList;
        this.sanPhamList=sanPhamList;
        this.context=context;
        this.activity=activity;
        notifyDataSetChanged();

    }
    public void setList(List<KhuyenMai> sanPhams)
    {

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KhuyenMaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_layout,parent,false);
        return new KhuyenMaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhuyenMaiViewHolder holder, int position) {

            int abc=position;
            if (sanPhamList.size()==0 && cuaHangList.size()==0)
            {
                return;
            }
            if(abc< cuaHangList.size())
            {
                holder.tenCH_SP.setText(cuaHangList.get(abc).getName());
                Glide.with(context).load(cuaHangList.get(abc).getLogoUrl()).into(holder.imgLogo);
                holder.lineSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction fragmentTransaction =  activity.getSupportFragmentManager().beginTransaction();
                        ShopProductFragment fragment1=new ShopProductFragment(activity,context, new QuanNoiBatAdapter.getdata() {
                            @Override
                            public CuaHang getData() {
                                return cuaHangList.get(abc);
                            }
                        });
                        fragmentTransaction.replace(R.id.fragment_container,fragment1);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });

            }else {
                holder.tenCH_SP.setText(sanPhamList.get(abc-cuaHangList.size()).getNameProduct());
                Glide.with(context).load(sanPhamList.get(abc- cuaHangList.size()).getImgProduct()).into(holder.imgLogo);

                holder.lineSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent resultIntent = new Intent(activity, SuperQuangCaoActivity.class);
                        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("sanpham", sanPhamList.get(abc-cuaHangList.size()));
                        resultIntent.putExtras(bundle);
                        activity.startActivity(resultIntent);
                    }
                });

            }



    }

    @Override
    public int getItemCount() {
        if (sanPhamList.size()>0 || cuaHangList.size()>0)
        {
            return sanPhamList.size()+ cuaHangList.size();
        }
        return 0;
    }

    public class KhuyenMaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tenCH_SP;
        private CircleImageView imgLogo;
        private LinearLayout lineSearch;



        public KhuyenMaiViewHolder(@NonNull View itemView) {
            super(itemView);
           tenCH_SP=itemView.findViewById(R.id.tvTenCH_SP);
           imgLogo=itemView.findViewById(R.id.imgLogo);
           lineSearch=itemView.findViewById(R.id.lineSearch);

        }
    }





}
