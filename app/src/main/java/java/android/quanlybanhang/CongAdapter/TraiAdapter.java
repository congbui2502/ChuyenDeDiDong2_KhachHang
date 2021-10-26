package java.android.quanlybanhang.CongAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.traidep,parent,false);
        return new TraiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraiViewHolder holder, int position) {
        SanPham trai=trais.get(position);

        if (trai==null)
        {

            return;
        }

        holder.textView.setText(trai.getNameProduct());
//        holder.imageView.setImageResource(trai.getImgProduct());
        Picasso.get().load(trai.getImgProduct()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().popBackStack();
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

        private ImageView imageView;
        private TextView textView;

        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgTrai);
            textView=itemView.findViewById(R.id.tvName);
        }
    }
}
