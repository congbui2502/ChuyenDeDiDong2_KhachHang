package java.android.quanlybanhang.DatBan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class RvDatBanAdapter extends RecyclerView.Adapter<RvDatBanAdapter.DatBanholder> {
    private ArrayList<ID_datban> items;
    DanhSachDatBan danhSachDatBan;
    public RvDatBanAdapter(ArrayList<ID_datban> items, DanhSachDatBan danhSachDatBan) {
        this.items = items;
        this.danhSachDatBan = danhSachDatBan;
    }
    public void SetData(ArrayList<ID_datban> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DatBanholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listdatban, parent, false);

        DatBanholder datBanholder = new DatBanholder(view);

        return datBanholder;
    }

    @Override
    public void onBindViewHolder(@NonNull DatBanholder holder, int position) {
        ID_datban CrrItem = items.get(position);
        holder.tv_namesale.setText(CrrItem.getDatBanModels().get(position).getNgaydat());
        holder.giatu.setText(CrrItem.getDatBanModels().get(position).getGiodat());
        holder.giaden.setText(CrrItem.getDatBanModels().get(position).getGioketthuc());
        holder.giakhuyenmai.setText(CrrItem.getDatBanModels().get(position).getTenban());

    }
    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public class DatBanholder extends RecyclerView.ViewHolder {
        TextView giatu, giaden, giakhuyenmai,tv_namesale;
        public DatBanholder(@NonNull View itemView) {
            super(itemView);
            tv_namesale = itemView.findViewById(R.id.tv_namesale);
            giatu = itemView.findViewById(R.id.giatu);
            giaden = itemView.findViewById(R.id.giaden);
            giakhuyenmai = itemView.findViewById(R.id.giakhuyenmai);
        }
    }
}
