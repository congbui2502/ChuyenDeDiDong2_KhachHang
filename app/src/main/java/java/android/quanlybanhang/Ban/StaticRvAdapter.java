package java.android.quanlybanhang.Ban;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.KhuVuc.StaticModelKhuVuc;
import java.android.quanlybanhang.Activity.MonOrder;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Activity.OrderMenu;
import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRvHolderBan> {
    private OrderMenu orderMenu;
    public ArrayList<StaticBanModel> staticBanModels;
    ArrayList<StaticModelKhuVuc> items;
    boolean check = true;
    boolean select= true;

    public StaticRvAdapter(ArrayList<StaticBanModel> staticBanModels,OrderMenu orderMenu,  ArrayList<StaticModelKhuVuc> items){
        this.staticBanModels = staticBanModels;
        this.orderMenu = orderMenu;
        this.items = items;
    }
    public class StaticRvHolderBan extends RecyclerView.ViewHolder {

//

        public TextView tenPhucVu;
        public TextView tenBan;
        public TextView ngayGio;
        public TextView trangThai;
        ConstraintLayout constraintLayout;

        public StaticRvHolderBan(@NonNull View itemView) {
            super(itemView);
            tenPhucVu = itemView.findViewById(R.id.tvtenphucvu);
            tenBan = itemView.findViewById(R.id.tvtenban);
            ngayGio = itemView.findViewById(R.id.tvngaygio);
            trangThai = itemView.findViewById(R.id.tvtrangthai);
            constraintLayout = itemView.findViewById(R.id.constraintLayouts);
        }
    }

    @NonNull
    @Override
    public StaticRvHolderBan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ban,parent,false);

        StaticRvHolderBan staticRvHolderBan = new StaticRvHolderBan(view);

        return staticRvHolderBan;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvHolderBan holder, int position) {
        StaticBanModel CrrItem = staticBanModels.get(position);

        holder.tenPhucVu.setText(CrrItem.getTenNhanVien());
        holder.tenBan.setText(CrrItem.getTenban());
        holder.ngayGio.setText(CrrItem.getGioDaOder());
        holder.trangThai.setText(CrrItem.getTrangthai());
         if (staticBanModels.get(position).getTrangthai().equals("3")){
            holder.constraintLayout.setBackgroundResource(R.drawable.rv_ban_hong_bg);
            holder.constraintLayout.setEnabled(false);

        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(items.get(position).getTenkhuvuc().equals("Khu vuc A")){
                    Intent intent = new Intent(orderMenu,MonOrder.class);
                    intent.putExtra("tenban",CrrItem.getTenban());
                    orderMenu.startActivity(intent);
//                }
//                else {
//                    return;
//                }

            }
        });
        for(int i =0; i<staticBanModels.size();i++) {
            Log.d("mnn", i + "mm");
        }
//        Log.d("khungaa",items.get(position).getStaticBanModels().get(0).getTrangthai());
//        }        if(items.get(position).getStaticBanModels().get(0).getTrangthai().equals("aaa")) {
//            holder.constraintLayout.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
//        }
//        })items.get(position).getStaticBanModels().get(0).getTrangthai().equals("aaa")
//        holder.constraintLayout.setBackgroundResource(R.drawable.rv_khuvuc_hong_bg);
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return staticBanModels.size() ;
    }


}
