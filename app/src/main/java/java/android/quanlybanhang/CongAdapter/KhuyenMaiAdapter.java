package java.android.quanlybanhang.CongAdapter;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhuyenMai;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.KhuyenMaiViewHolder> {
    private List<KhuyenMai> sanPhams=new ArrayList<>();;
   private Dialog  dialog;
    private  View view;
    private KhuyenMaiAdapter.setTvKhuyenMai tvKhuyenMai;

    interface  setTvKhuyenMai {
        void setTv(KhuyenMai khuyenMai);
    }


    public void setData(KhuyenMaiAdapter.setTvKhuyenMai tvKhuyenMai,  Dialog dialog)
    {
        this.tvKhuyenMai=tvKhuyenMai;

        this.dialog=dialog;
    }
    public void setList(List<KhuyenMai> sanPhams)
    {
        this.sanPhams=sanPhams;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KhuyenMaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khuyenmai,parent,false);

        return new KhuyenMaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhuyenMaiViewHolder holder, int position) {
            if (sanPhams.size()==0)
            {
                return;
            }
            

            int abc=position;

            holder.tvDieuKienKhuyenMai.setText(sanPhams.get(position).getMota() );
            Log.d("bbb",sanPhams.get(abc).getLoaiKhuyenmai()+" abc");
            holder.lineKhuyenMai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("aaa","nhap sai roi");
//                  Toast.makeText(view.getContext(),"abc",Toast.LENGTH_LONG).show();
                    tvKhuyenMai.setTv(sanPhams.get(abc));
                    dialog.dismiss();
                }
            });

    }

    @Override
    public int getItemCount() {
        if (sanPhams.size()>0)
        {
            return sanPhams.size();
        }
        return 0;
    }

    public class KhuyenMaiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDieuKienKhuyenMai;
        private LinearLayout lineKhuyenMai;


        public KhuyenMaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDieuKienKhuyenMai=itemView.findViewById(R.id.tvDieukienkhuyenmai);

            lineKhuyenMai=itemView.findViewById(R.id.lineKhuyenmai);

        }
    }





}
