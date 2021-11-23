package java.android.quanlybanhang.ChiTietSanPham;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.OrderMon.Product;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class StaticCardAdapter extends RecyclerView.Adapter<StaticCardAdapter.StaticRvCardHolder>{
    ArrayList<Product> items;


    public StaticCardAdapter(ArrayList<Product> items, Activity activity) {
        this.items = items;

    }
    public StaticCardAdapter() {
            this.items = new ArrayList<>();
    }
public void Setdata(ArrayList<Product> staticMonOrderModel){
    this.items = staticMonOrderModel;
        notifyDataSetChanged();

}
    @NonNull
    @Override
    public StaticRvCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        StaticRvCardHolder staticRvViewHolder = new StaticRvCardHolder(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvCardHolder holder, int position) {
        Product CrItem=items.get(position);
        holder.tensanpham.setText(CrItem.getNameProduct());
        holder.giasanpham.setText(CrItem.getGiaBan()+"");


    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRvCardHolder extends RecyclerView.ViewHolder{
        TextView tensanpham ;
        TextView giasanpham;
        LinearLayout linearLayouts;
        ConstraintLayout constraintLayouts ;
        public StaticRvCardHolder(@NonNull View itemView) {
            super(itemView);
            tensanpham =itemView.findViewById(R.id.tvtensanpham);
            tensanpham = itemView.findViewById(R.id.tvgiasanpham);

        }
    }

}
