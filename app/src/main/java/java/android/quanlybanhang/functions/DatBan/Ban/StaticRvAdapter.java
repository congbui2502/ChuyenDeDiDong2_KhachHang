package java.android.quanlybanhang.functions.DatBan.Ban;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.functions.DatBan.KhuVuc.StaticModelKhuVuc;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.functions.DatBan.DanhSachChonBan;
import java.android.quanlybanhang.functions.DatBan.DatBan;
import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRvHolderBan> {
    private DanhSachChonBan danhSachChonBan;
    public ArrayList<StaticBanModel> staticBanModels;
    ArrayList<StaticModelKhuVuc> items;
    String Id_khuvuc;
    boolean check = true;
    boolean select= true;
    String trangthaistatic;
    String id_shop;
    String ten_cuahang;

    public StaticRvAdapter(ArrayList<StaticBanModel> staticBanModels, DanhSachChonBan danhSachChonBan, ArrayList<StaticModelKhuVuc> items, String Id_khuvuc,String trangthaistatic,String id_shop, String ten_cuahang){
        this.staticBanModels = staticBanModels;
        this.danhSachChonBan = danhSachChonBan;
        this.items = items;
        this.Id_khuvuc = Id_khuvuc;
        this.trangthaistatic = trangthaistatic;
        this.id_shop = id_shop;
        this.ten_cuahang =ten_cuahang;
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
        holder.tenBan.setText(CrrItem.getTenban());
        if (!trangthaistatic.equals("3")) {
            if (staticBanModels.get(position).getTrangthai().equals("3")) {
                holder.tenBan.setBackgroundResource(R.color.red);
                holder.constraintLayout.setEnabled(false);
            }
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(danhSachChonBan, DatBan.class);
                    intent.putExtra("ten_cuahang",ten_cuahang);
                    intent.putExtra("tenban",CrrItem.getTenban());
                    intent.putExtra("id_ban",CrrItem.getID());
                    intent.putExtra("id_khuvuc",Id_khuvuc);
                    intent.putExtra("id_shop",id_shop);
                    danhSachChonBan.startActivity(intent);


                }
            });


        }else {
            holder.tenBan.setBackgroundResource(R.color.red);
            holder.constraintLayout.setEnabled(false);
        }


    }

    @Override
    public int getItemCount() {
        if (staticBanModels!=null){
            return staticBanModels.size() ;
        }
        return 0;

    }

    public class StaticRvHolderBan extends RecyclerView.ViewHolder {
        public TextView tenBan;
        public TextView trangThai;
        ConstraintLayout constraintLayout;

        public StaticRvHolderBan(@NonNull View itemView) {
            super(itemView);
            tenBan = itemView.findViewById(R.id.tvtenban);

            constraintLayout = itemView.findViewById(R.id.constraintLayouts);
        }
    }
}
