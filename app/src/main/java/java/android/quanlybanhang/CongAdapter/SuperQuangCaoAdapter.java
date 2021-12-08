package java.android.quanlybanhang.CongAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.Activity.SuperQuangCaoActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.ArrayList;
import java.util.List;

public class SuperQuangCaoAdapter extends RecyclerView.Adapter<SuperQuangCaoAdapter.TraiViewHolder>{
    private List<SanPham> trais;
    private IclickAddToCartListener iclickAddToCartListener;
    private AppCompatActivity activity;

    public interface IclickAddToCartListener{
        void onClickAddToCart(SanPham trai);
    }

    public void getList(List<SanPham> list)
    {
        this.trais=list;
        notifyDataSetChanged();
    }

    public void setData(SuperQuangCaoActivity activity,IclickAddToCartListener listener)
    {
        this.activity=activity;
        this.iclickAddToCartListener=listener;

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
        Picasso.get().load(trai.getImgProduct()).into(holder.imageViewSanPhamNoiBat);
        holder.imgaddSanPhamNoiBat.setText(trai.getDonGia().get(0).getGiaBan()+" VND");
        holder.textViewgiaSPNoiBat.setText(trai.getChitiet());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclickAddToCartListener.onClickAddToCart(trai);
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
