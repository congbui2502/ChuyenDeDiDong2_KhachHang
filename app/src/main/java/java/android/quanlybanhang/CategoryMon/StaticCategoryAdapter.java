package java.android.quanlybanhang.CategoryMon;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.Ban.StaticBanModel;
import java.android.quanlybanhang.ChiTietSanPham.Interface_CategorySp_Sp;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class StaticCategoryAdapter extends RecyclerView.Adapter<StaticCategoryAdapter.StaticCategoryHolder>
{
    int item_a =-1;
    Activity activity;
    boolean check = true;
    boolean select= true;
    int position;
    Interface_CategorySp_Sp interface_categorySp_sp;
    ArrayList<StaticCategoryMonModel> items;
public  StaticCategoryAdapter(ArrayList<StaticCategoryMonModel> items,Activity activity,Interface_CategorySp_Sp interface_categorySp_sp,int position){
    this.items = items;
    this.activity= activity;
    this.interface_categorySp_sp= interface_categorySp_sp;
    this.position= position;
}
    @NonNull
    @Override
    public StaticCategoryAdapter.StaticCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmucsanpham,parent,false);
    StaticCategoryHolder staticCategoryHolder = new StaticCategoryHolder(view);
        return staticCategoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticCategoryAdapter.StaticCategoryHolder holder, int position) {
            StaticCategoryMonModel CrrItem = items .get(position);
            holder.tvdanhmucsanpham.setText(CrrItem.getTenCategory());
        if(check){

            interface_categorySp_sp.GetBack1(0,items.get(0).getStaticMonOrderModels());
            check= false;
        }

        holder.linearLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_a = position;
                String a="1";
                ArrayList<StaticBanModel> item = new ArrayList<>();
                notifyDataSetChanged();
                Log.d("khungaa",item.size()+"");;
                for(int i=0;i<items.size();i++){
                    if(position==i){

                        interface_categorySp_sp.GetBack1(position,items.get(position).getStaticMonOrderModels());

                    }
                }
            }
        });
        if (select) {
            if(position==0)
                holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_bg);

            select = false;
        }

//        else if (items.get(position).getTrangthai().equals("4")){
//            holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
//
//        }

        else {
//            if(items.get(position).getStaticBanModels().get(position).getTrangthai().equals("2")){
////            holder.constraintLayouts.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
//                holder.linearLayouts.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
//            }
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

    public class StaticCategoryHolder extends RecyclerView.ViewHolder {
    public TextView tvdanhmucsanpham;
        LinearLayout linearLayouts;
        public StaticCategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvdanhmucsanpham = itemView.findViewById(R.id.tvdanhmucsanpham);
            linearLayouts = itemView.findViewById(R.id.linerlayouts);
        }
    }
}
