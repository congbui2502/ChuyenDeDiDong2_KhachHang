package java.android.quanlybanhang.TrietAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.Mon;
import java.util.ArrayList;
import java.util.List;

public class MonBanViewHolder extends RecyclerView.Adapter<MonBanViewHolder.ViewHolder> {
    List<Mon> mList=new ArrayList<>();
    public void setmList(List<Mon> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_monban,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mon mon=mList.get(position);
        if (mon==null){
            return;
        }
        holder.tvMon.setText(mon.getTensanpham());
        holder.tvSoLuong.setText(mon.getSoluong());


    }

    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMon;
        private TextView tvSoLuong;
        public ViewHolder(View itemView) {
            super(itemView);
            tvMon
                    =itemView.findViewById(R.id.Mon);
            tvSoLuong
                    =itemView.findViewById(R.id.soLuong);
        }
    }
}
