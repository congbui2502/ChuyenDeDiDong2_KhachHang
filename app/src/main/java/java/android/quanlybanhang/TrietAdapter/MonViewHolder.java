package java.android.quanlybanhang.TrietAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.Mon;
import java.util.List;

public class MonViewHolder extends RecyclerView.Adapter<MonViewHolder.ViewHolder>{

    Context context;
    List<Mon> monArrayList;

    public MonViewHolder(Context context) {
        this.context = context;

    }
    public void setData( List<Mon> monArrayList){
        this.monArrayList = monArrayList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView monName;
        private TextView core;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            monName
                    = itemView
                    .findViewById(R.id.monName);
            core
                    = itemView
                    .findViewById(R.id.monCore);


        }

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.design_mon, parent, false);
        return new ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mon mon = monArrayList.get(position);
        holder.monName.setText(mon.getTensanpham());
        holder.core.setText(mon.getSoluong());

    }

    @Override
    public int getItemCount() {
        if(monArrayList == null){
            return 0;
        }
        return monArrayList.size();
    }



}
