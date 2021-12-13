package java.android.quanlybanhang.CongAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.util.List;
import java.android.quanlybanhang.CongAdapter.ShopProductFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuanNoiBatAdapter extends RecyclerView.Adapter<QuanNoiBatAdapter.TraiViewHolder>{
    private List<CuaHang> trais;
    private KhachHangActivity mainActivity;
    private Context context;
    public interface getdata{
        CuaHang getData();
    }
    public void setData(List<CuaHang> list, KhachHangActivity mainActivity, Context context)
    {
        this.mainActivity=mainActivity;
        this.trais=list;
        notifyDataSetChanged();
        this.context=context;
    }


    @NonNull
    @Override
    public TraiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quannoibat,parent,false);

        return new TraiViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull TraiViewHolder holder, int position) {
        CuaHang trai=trais.get(position);

        if (trai==null)
        {
            return;
        }


        holder.textView.setText(trai.getName());
        Glide.with(context).load(trai.getLogoUrl()).into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction =  mainActivity.getSupportFragmentManager().beginTransaction();
               ShopProductFragment fragment1=new ShopProductFragment(mainActivity,context, new getdata() {
                    @Override
                    public CuaHang getData() {
                        return trai;
                    }
                });
                fragmentTransaction.replace(R.id.fragment_container,fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=  mainActivity.getSupportFragmentManager().beginTransaction();
                ShopProductFragment fragment1=new ShopProductFragment(mainActivity,context, new getdata() {
                    @Override
                    public CuaHang getData() {
                        return trai;
                    }
                });
                fragmentTransaction.add(R.id.fragment_container,fragment1,"homefragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//        holder.imageView.setImageResource(trai.getImgProduct());


    }

    @Override
    public int getItemCount() {
        if(trais!=null)
        {
            return trais.size();
        }

        return 0;
    }

    public class TraiViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView textView;

        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgQuan);
            textView=itemView.findViewById(R.id.tvQuan);
        }
    }
}
