package java.android.quanlybanhang.CongAdapter.History.Adapter;



import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.CongAdapter.History.Data.FormatDouble;
import java.android.quanlybanhang.CongAdapter.History.Data.SanPham;
import java.android.quanlybanhang.CongAdapter.History.Data.SupportFragmentDonOnline;
import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class ItemDonHangNhanAdapter extends RecyclerView.Adapter<ItemDonHangNhanAdapter.DonCho> {

    private Dialog dialog;
    private ArrayList<SanPham> list;
    private FormatDouble formatDouble;
    private SupportFragmentDonOnline support;

    public ItemDonHangNhanAdapter(Dialog dialog, ArrayList<SanPham> list) {
        this.dialog = dialog;
        this.list = list;
        formatDouble = new FormatDouble();
        support = new SupportFragmentDonOnline();
    }

    @NonNull
    @Override
    public DonCho onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonCho(LayoutInflater.from(dialog.getContext()).inflate(R.layout.item_dialog_don_online, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonCho holder, int position) {
        holder.name.setText(list.get(position).getNameProduct());
        holder.dongia.setText(formatDouble.formatStr(list.get(position).getDonGia().get(0).getGiaBan()));
        holder.soluong.setText(list.get(position).getSoluong()+"");
        holder.loai.setText(list.get(position).getDonGia().get(0).getTenDonGia());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DonCho extends RecyclerView.ViewHolder {
        private TextView name, loai, soluong, dongia;
        public DonCho(@NonNull View ItemView) {
            super(ItemView);
            name = ItemView.findViewById(R.id.name);
            loai = ItemView.findViewById(R.id.loai);
            soluong = ItemView.findViewById(R.id.soluong);
            dongia = ItemView.findViewById(R.id.dongia);
        }
    }
}

