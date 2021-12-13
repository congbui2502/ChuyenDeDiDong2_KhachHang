package java.android.quanlybanhang.DatBan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterDanhSachTheoDoi extends RecyclerView.Adapter<AdapterDanhSachTheoDoi.Danhsachtheodoihodel> {
  private   ArrayList<ID_datban> id_datbans;
    private   ArrayList<DatBanModel> datBanModels;
    private KhachHang khachHang = KhachHangActivity.khachHang;
    public  AdapterDanhSachTheoDoi(ArrayList<DatBanModel> datBanModels){
        this.datBanModels = datBanModels;
    }
    @NonNull
    @Override
    public Danhsachtheodoihodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theodoidonhang, parent, false);
        AdapterDanhSachTheoDoi.Danhsachtheodoihodel ratinghodel = new AdapterDanhSachTheoDoi.Danhsachtheodoihodel(view);
        return ratinghodel;
    }

    @Override
    public void onBindViewHolder(@NonNull Danhsachtheodoihodel holder, int position) {
        DatBanModel crr = datBanModels.get(position);
        holder.lbl_idban.setText(datBanModels.get(position).getTenban());
        holder.lblTienDat.setText(datBanModels.get(position).getSotiendadattruoc()+"");
        holder.lblKhachang.setText(datBanModels.get(position).getTen_cuahang());
        holder.lblThoiGian.setText(changeDate(datBanModels.get(position).getId_ngaydat()));
        if(datBanModels.get(position).getTrangthai_dat().equals("0")){
            holder.trangthai.setText("đang chờ duyệt");
            holder.liner_cuahang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                    builder1.setMessage("Bạn Chắc Chắn mUốn Hủy Đặt bàn Này,Trước khi cửa hàng duyệt");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FirebaseDatabase.getInstance().getReference("DuyetDatBan").child( khachHang.getIdKhachhang()).child(datBanModels.get(position).getId_bk()).child(datBanModels.get(position).getId_ngaydat()).removeValue();
                                    notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });
        }
        else if(datBanModels.get(position).getTrangthai_dat().equals("2")){

            holder.trangthai.setText("duyệt không thành công");
            holder.liner_cuahang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                    builder1.setMessage("Cửa Hàng Không Chấp Nhận Đơn Này,Bạn Hãy Xóa Lần đặt này ra khỏi danh sách");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FirebaseDatabase.getInstance().getReference("DuyetDatBan").child( khachHang.getIdKhachhang()).child(datBanModels.get(position).getId_bk()).child(datBanModels.get(position).getId_ngaydat()).removeValue();
                                    notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });

        }
        else {
            holder.trangthai.setText("duyệt thành công");
        }
    }

    @Override
    public int getItemCount() {
        if (datBanModels != null) {
            return datBanModels.size();
        }
        return 0;
    }

    public class Danhsachtheodoihodel extends RecyclerView.ViewHolder {
        TextView lblThoiGian,lbl_idban,lblKhachang,lblTienDat,trangthai;
        LinearLayout liner_cuahang;
        public Danhsachtheodoihodel(@NonNull View itemView) {

            super(itemView);
            lblThoiGian = itemView.findViewById(R.id.lblThoiGian);
            lbl_idban = itemView.findViewById(R.id.lbl_idban);
            lblKhachang = itemView.findViewById(R.id.lblKhachang);
            lblTienDat = itemView.findViewById(R.id.lblTienDat);
            trangthai = itemView.findViewById(R.id.trangthai);
            liner_cuahang =itemView.findViewById(R.id.liner_cuahang);

        }
    }
    //chuyeenr doii String sang ngay
    public String changeDate(String date) {
        long dates = Long.parseLong(date);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(dates);
        if (dates == 0) {
            return "";
        }
        Date date1 = new Date(timestamp.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String aaa = simpleDateFormat.format(date1);

        return aaa;

    }
}
