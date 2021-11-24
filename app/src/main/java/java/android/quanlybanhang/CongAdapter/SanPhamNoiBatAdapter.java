package java.android.quanlybanhang.CongAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.List;

public class SanPhamNoiBatAdapter extends RecyclerView.Adapter<SanPhamNoiBatAdapter.TraiViewHolder>{
    private List<SanPham> trais;
    private IclickAddToCartListener iclickAddToCartListener;
    private AppCompatActivity activity;


    public interface IclickAddToCartListener{
        void onClickAddToCart(ImageView imageToCart,SanPham trai);
    }




    public void setData(KhachHangActivity activity,List<SanPham> list, IclickAddToCartListener listener)
    {
        this.activity=activity;
        this.iclickAddToCartListener=listener;
        this.trais=list;
        notifyDataSetChanged();
    }


    public void setData1(AppCompatActivity activity,List<SanPham> list )
    {
        this.activity=activity;
        this.trais=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TraiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sanphamnoibat,parent,false);
        return new TraiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraiViewHolder holder, int position) {
        SanPham trai=trais.get(position);

        if (trai==null)
        {
            return;
        }

        holder.tvHagtag.setText(trai.getNameProduct());
//        holder.imageViewSanPhamNoiBat.setBackgroundResource(trai.getImgProduct());
        Picasso.get().load(trai.getImgProduct()).into(holder.imageViewSanPhamNoiBat);
        holder.imgaddSanPhamNoiBat.setText(trai.getGiaBan()+" VND");
        holder.textViewgiaSPNoiBat.setText(trai.getChitiet());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietFragment fragment=new ChiTietFragment();
                fragment.setData(trai);
                FragmentTransaction transaction= activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack("");
                transaction.commit();
            }
        });



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

        private ImageView imageViewSanPhamNoiBat;
        private TextView tvHagtag;
        private TextView textViewgiaSPNoiBat;
        private TextView imgaddSanPhamNoiBat;
        private CardView cardView;

        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSanPhamNoiBat=itemView.findViewById(R.id.imgRecy3);
            tvHagtag=itemView.findViewById(R.id.tvHagtag);
            imgaddSanPhamNoiBat=itemView.findViewById(R.id.tvTittle);
            textViewgiaSPNoiBat=itemView.findViewById(R.id.tvDesscription);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
