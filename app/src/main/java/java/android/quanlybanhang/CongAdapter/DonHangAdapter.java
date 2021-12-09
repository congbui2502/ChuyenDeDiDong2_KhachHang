package java.android.quanlybanhang.CongAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder>{
    private List<SanPham> trais;

    private KhachHangActivity mainActivity;

    public void setData(List<SanPham> list)
    {

        this.trais=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.donhang_recy,parent,false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        SanPham trai=trais.get(position);

        if (trai==null)
        {
            Log.d("QQQ","not data");

            return;
        }

        holder.tenMon.setText(trai.getNameProduct());
        holder.giaTien.setText(Cart_Fragment.addDauPhay(trai.getDonGia().get(0).getGiaBan()*trai.getSoluong())+" VND");
        holder.soLuong.setText("x"+trai.getSoluong());
        holder.tvLoai.setText(trai.getDonGia().get(0).getTenDonGia());

    }

    @Override
    public int getItemCount() {
        if(trais!=null)
        {
            return trais.size();
        }

        return 0;
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder{

        private TextView tenMon;
        private TextView soLuong;
        private TextView giaTien;
        private TextView tvLoai;

        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMon=itemView.findViewById(R.id.tvTensp);
            soLuong=itemView.findViewById(R.id.tvSoluongDat);
            giaTien=itemView.findViewById(R.id.tvGiaSP);
            tvLoai=itemView.findViewById(R.id.tvLoai);
        }
    }

    public long tinhTongTien()
    {
        long tong=0;
        for (int i = 0; i < trais.size(); i++) {
            tong=tong+ trais.get(i).getDonGia().get(0).getGiaBan()*trais.get(i).getSoluong();
        }
        return tong;
    }
}
