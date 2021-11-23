package java.android.quanlybanhang.CongAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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


    interface  setTvKhuyenMai {
        void setTv(KhuyenMai khuyenMai);
    }


    public void setData(List<CuaHang> cuaHangList,List<SanPham> sanPhamList,Context context)
    {
        this.cuaHangList=cuaHangList;
        this.sanPhamList=sanPhamList;
        this.context=context;
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
            if (sanPhamList.size()==0 && cuaHangList.size()==0)
            {
                return;
            }
            if(position< cuaHangList.size())
            {
                holder.tenCH_SP.setText(cuaHangList.get(position).getName());
                Glide.with(context).load(cuaHangList.get(position).getLogoUrl()).into(holder.imgLogo);
            }else {
                holder.tenCH_SP.setText(sanPhamList.get(position-cuaHangList.size()).getNameProduct());
                Glide.with(context).load(sanPhamList.get(position- cuaHangList.size()).getImgProduct()).into(holder.imgLogo);
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



        public KhuyenMaiViewHolder(@NonNull View itemView) {
            super(itemView);
           tenCH_SP=itemView.findViewById(R.id.tvTenCH_SP);
           imgLogo=itemView.findViewById(R.id.imgLogo);

        }
    }





}
