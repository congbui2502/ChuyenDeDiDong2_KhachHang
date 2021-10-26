package java.android.quanlybanhang.CongAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.List;

public class SanPhamNoiBatAdapter extends RecyclerView.Adapter<SanPhamNoiBatAdapter.TraiViewHolder>{
    private List<SanPham> trais;
    private IclickAddToCartListener iclickAddToCartListener;

    public interface IclickAddToCartListener{
        void onClickAddToCart(ImageView imageToCart,SanPham trai);
    }




    public void setData(List<SanPham> list, IclickAddToCartListener listener)
    {
        this.iclickAddToCartListener=listener;
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

        holder.textViewSanPhamNoiBat.setText(trai.getNameProduct());
//        holder.imageViewSanPhamNoiBat.setBackgroundResource(trai.getImgProduct());
        Picasso.get().load(trai.getImgProduct()).into(holder.imageViewSanPhamNoiBat);
        holder.textViewgiaSPNoiBat.setText(trai.getGiaBan()+" VND");

        if(trai.isAddToCart())
        {
            holder.imgaddSanPhamNoiBat.setBackgroundResource(R.drawable.cart_unshopping);

        }
        else {
            holder.imgaddSanPhamNoiBat.setBackgroundResource(R.drawable.cart_shopping);
        }

        holder.imgaddSanPhamNoiBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!trai.isAddToCart())
                {
                    iclickAddToCartListener.onClickAddToCart(holder.imgaddSanPhamNoiBat,trai);
                }



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
        private TextView textViewSanPhamNoiBat;
        private TextView textViewgiaSPNoiBat;
        private ImageView imgaddSanPhamNoiBat;

        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSanPhamNoiBat=itemView.findViewById(R.id.imgSanPhamNoiBat);
            textViewSanPhamNoiBat=itemView.findViewById(R.id.tvTenSanPhamNoiBat);
            imgaddSanPhamNoiBat=itemView.findViewById(R.id.imgAddtoCartSanPhamNoiBat);
            textViewgiaSPNoiBat=itemView.findViewById(R.id.tvgiaSanPhamNoiBat);
        }
    }
}
