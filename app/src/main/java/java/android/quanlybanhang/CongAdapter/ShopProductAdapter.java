package java.android.quanlybanhang.CongAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.List;

public class ShopProductAdapter extends RecyclerView.Adapter<ShopProductAdapter.TraiViewHolder>{
    private List<SanPham> trais;
    private SanPhamNoiBatAdapter.IclickAddToCartListener iclickAddToCartListener;

    public interface IclickAddToCartListener{
        void onClickAddToCart(ImageView imageToCart,SanPham trai);

    }
    public void setData(List<SanPham> list)
    {
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

        Picasso.get().load(trai.getImgProduct()).into( holder.imageViewSanPhamNoiBat);
        holder.textViewgiaSPNoiBat.setText(trai.getGiaBan()+" VND");
//        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.foldingCell.toggle(false);
//            }
//        });

//        Picasso.get().load(trai.getImgProduct()).into( holder.imgSMTo);
//        holder.tvTenSPTo.setText(trai.getNameProduct());
//        holder.tvGiaTo.setText(trai.getGiaBan()+" VND");
//        holder.tvDesTo.setText(trai.getChitiet());
//        holder.tvDatmuaTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(trais!=null)
        {
            return trais.size();
        }

        return 0;
    }

    public class TraiViewHolder extends RecyclerView.ViewHolder {

        private FoldingCell foldingCell;
        private ImageView imageViewSanPhamNoiBat,imgSMTo;
        private TextView textViewSanPhamNoiBat,tvTenSPTo,tvGiaTo,tvDesTo,tvDatmuaTo;
        private TextView textViewgiaSPNoiBat;



        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSanPhamNoiBat=itemView.findViewById(R.id.imgRecy3);
            textViewSanPhamNoiBat=itemView.findViewById(R.id.tvHagtag);

            textViewgiaSPNoiBat=itemView.findViewById(R.id.tvDesscription);
            imgSMTo=itemView.findViewById(R.id.imgSP);
            tvTenSPTo=itemView.findViewById(R.id.tvTensp);
            tvGiaTo=itemView.findViewById(R.id.tvGia);
            tvDesTo=itemView.findViewById(R.id.tvDescription);
            tvDatmuaTo=itemView.findViewById(R.id.tvDatmua);
            foldingCell=itemView.findViewById(R.id.folding_cell);


        }
    }
}
