package java.android.quanlybanhang.OrderMon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.CategoryMon.StaticCategoryMonModel;
import java.android.quanlybanhang.ChiTietSanPham.ChiTietSanPham;
import java.android.quanlybanhang.ChiTietSanPham.ChiTietSanPham_Banh;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Activity.MonOrder;
import java.util.ArrayList;

public class StaticMonRvAdapter  extends RecyclerView.Adapter<StaticMonRvAdapter.StaticMonRvViewHolder>{
ArrayList<Product> staticMonOrderModels;
    public MonOrder monOrder;
    ArrayList<StaticCategoryMonModel> items;
    int pos;
    String tenban;
    public StaticMonRvAdapter(ArrayList<Product> staticMonOrderModels, MonOrder monOrder, ArrayList<StaticCategoryMonModel> items, int pos, String tenban){
        this.staticMonOrderModels = staticMonOrderModels;
        this.monOrder = monOrder;
        this.items = items;
        this.pos = pos;
        this.tenban = tenban;
    }
//
//public void GetTenSp(String str){
//        this.tenban = str;
//
//    }


    @NonNull
    @Override
    public StaticMonRvAdapter.StaticMonRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);

        StaticMonRvAdapter.StaticMonRvViewHolder staticMonRvViewHolder = new StaticMonRvAdapter.StaticMonRvViewHolder(view);

        return staticMonRvViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull StaticMonRvAdapter.StaticMonRvViewHolder holder, int position) {
        Product Crritem = staticMonOrderModels.get(position);
        holder.tvtensanpham.setText(Crritem.getNameProduct());
        holder.tvgiaSanpham.setText(Crritem.getImgProduct());
        holder.tvgiaSanpham.setText(Crritem.getGiaBan()+"");
        holder.tvstatus.setText(Crritem.getStatus());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("minato",items.get(position).getTenCategory()+"");
//                if(items.get(position).getTenCategory().equals("Bánh")){
//                    Intent intent = new Intent(monOrder, ChiTietSanPham_Banh.class);
//                    monOrder.startActivity(intent);
//                    notifyDataSetChanged();
//                }
//                else {
//                    Intent intent1 = new Intent(monOrder, ChiTietSanPham.class);
//                    monOrder.startActivity(intent1);
//                    notifyDataSetChanged();
//                }
                if(pos ==0){
                    Intent intent = new Intent(monOrder, ChiTietSanPham_Banh.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sp",Crritem);
                    Log.d("aaa",tenban.toString());
                    bundle.putString("tenban",tenban+"baba");
                    intent.putExtras(bundle);
                   monOrder.startActivity(intent);
                }
                else if(pos==1){
                    Intent intent1 = new Intent(monOrder, ChiTietSanPham.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sp",Crritem);
                    intent1.putExtras(bundle);
                    Log.d("aaa",tenban+"baba");
                    intent1.putExtra("tenban",tenban);
                    monOrder.startActivity(intent1);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return staticMonOrderModels.size();
    }


    public class StaticMonRvViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgsanpham;
        public TextView tvtensanpham;
        public TextView tvgiaSanpham;
        public  TextView tvstatus;
        ConstraintLayout constraintLayout;
        public StaticMonRvViewHolder(@NonNull View itemView) {

            super(itemView);
            imgsanpham = itemView.findViewById(R.id.imgsanpham);
            tvtensanpham = itemView.findViewById(R.id.tvtensanpham);
            tvgiaSanpham = itemView.findViewById(R.id.tvgiasanpham);
            tvstatus = itemView.findViewById(R.id.tvstatus);
            constraintLayout = itemView.findViewById(R.id.constraintLayouts);

        }
    }
}
