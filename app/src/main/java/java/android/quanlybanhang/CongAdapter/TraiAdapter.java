package java.android.quanlybanhang.CongAdapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.List;

public class TraiAdapter extends RecyclerView.Adapter<TraiAdapter.TraiViewHolder>{
    private List<SanPham> trais;
    private KhachHangActivity activity;

    public void setData(List<SanPham> list, KhachHangActivity activity)
    {
        this.trais=list;
        notifyDataSetChanged();
        this.activity=activity;
    }

    @NonNull
    @Override
    public TraiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quangcao,parent,false);
        return new TraiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraiViewHolder holder, int position) {
        SanPham trai=trais.get(position);

        if (trai==null)
        {
            return;
        }


//        holder.imageView.setImageResource(trai.getImgProduct());
        Picasso.get().load(trai.getImgProduct()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.lineBG.setBackground(new BitmapDrawable(activity.getApplicationContext().getResources(),bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

       holder.tvTrending.setText(trai.getNameProduct());
       holder.tvDescription.setText(trai.getChitiet());

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

        private LinearLayout lineBG;
        private TextView tvTrending,tvDescription;



        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            lineBG=itemView.findViewById(R.id.lineBG);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvTrending=itemView.findViewById(R.id.tvTrending);


        }
    }
}
