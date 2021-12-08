package java.android.quanlybanhang.KhuVuc;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.android.quanlybanhang.Ban.StaticBanModel;
import java.android.quanlybanhang.ChiTietSanPham.Interface_KhuVuc_ban;

import java.android.quanlybanhang.KhuVuc.StaticModelKhuVuc;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class StaticRvKhuVucAdapter  extends RecyclerView.Adapter<StaticRvKhuVucAdapter.StaticRvViewHolder>{
    //    Context context;
    ArrayList<StaticModelKhuVuc> items;
    int item_a =-1;
    Interface_KhuVuc_ban interfacekhuVucban;

    Activity activity;
    boolean check = true;
    boolean select= true;
    private  String id_khuvuc;
    private DatabaseReference kvDatabase;//khai bao database
    private int stamp = 0;
    private int stamp2 = 0;
    public StaticRvKhuVucAdapter(ArrayList<StaticModelKhuVuc> items, Activity activity, Interface_KhuVuc_ban interfacekhuVucban) {
        this.items = items;
        this.activity= activity;
        this.interfacekhuVucban = interfacekhuVucban;
//        this.context = context;
        this.id_khuvuc= id_khuvuc;

    }
//

    @NonNull
    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khuvuc,parent,false);
        StaticRvViewHolder staticRvViewHolder = new StaticRvViewHolder(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder holder,int position) {
        StaticModelKhuVuc CrItem=items.get(position);
        holder.textView.setText(CrItem.getTenkhuvuc());
        if(check){
            if (stamp < items.size()) {
                if (items.get(position).getTrangthai().equals("3")) {
                    stamp++;
                }
                interfacekhuVucban.GetBack(stamp,items.get(stamp).getStaticBanModels(),items.get(stamp).getId_khuvuc(),items.get(stamp).getTrangthai());
                check= false;

            }

        }


        holder.linearLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_a = position;
                String a="1";
                ArrayList<StaticBanModel> item = new ArrayList<>();
                notifyDataSetChanged();

                for(int i=0;i<items.size();i++){
                    if(position==i){

                        interfacekhuVucban.GetBack(position,items.get(position).getStaticBanModels(),items.get(i).getId_khuvuc(),items.get(i).getTrangthai());

                    }
                }
            }
        });
        if (select) {
            if(position == stamp2) {
                if (items.get(position).getTrangthai().equals("3")) {
                    holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
                    holder.linearLayouts.setEnabled(false);
                } else {
                    holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_bg);
                    select = false;
                }
                if (stamp2 != items.size()) {
                    stamp2++;
                }
            }
        }

        else if (items.get(position).getTrangthai().equals("3")){
            holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
            holder.linearLayouts.setEnabled(false);

        }
        else {
            if (item_a == position) {
                holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_bg);
            } else {
                holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_selected_bg);
            }
        }


    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRvViewHolder extends RecyclerView.ViewHolder{
        TextView textView ;
        TextView textview1;
        LinearLayout linearLayouts;
        View view ;
        ConstraintLayout constraintLayouts ;
        public StaticRvViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.tvdanhmucsanpham);
            linearLayouts = itemView.findViewById(R.id.linerlayouts);

        }
    }

}
