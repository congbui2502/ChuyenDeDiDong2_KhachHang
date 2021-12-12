package java.android.quanlybanhang.CongAdapter.History.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.android.quanlybanhang.CongAdapter.History.Data.DonHang;
import java.android.quanlybanhang.CongAdapter.History.Data.FormatDouble;
import java.android.quanlybanhang.CongAdapter.History.Data.SupportFragmentDonOnline;
import java.android.quanlybanhang.R;

import java.util.ArrayList;

public class DonHangHuyAdapter extends RecyclerView.Adapter<DonHangHuyAdapter.DaHuy> {

    private Context context;
    private ArrayList<DonHang> list;
    private Dialog dialog;
    private ItemDonHangHuyAdapter itemDonHangHuyAdapter;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private FormatDouble formatDouble;
    private int select;
    private SupportFragmentDonOnline support;



    public DonHangHuyAdapter(Context context, ArrayList<DonHang> list, Dialog dialog) {
        this.context = context;
        this.list = list;
        this.dialog = dialog;
        this.select = 0;
        formatDouble = new FormatDouble();
        support = new SupportFragmentDonOnline();
    }

    @NonNull
    @Override
    public DaHuy onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaHuy(LayoutInflater.from(context).inflate(R.layout.item_donhanghuy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaHuy holder, int position) {
        int k = position;
        holder.layoutThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER, k);
            }
        });



        if (list.get(position).getTrangthai() == 7) {
            holder.trangthai.setText("Đã hủy");

        }

        holder.lblThoiGian.setText(support.formartDate(list.get(position).getDate()));
        holder.lblKhachang.setText(list.get(position).getTencuahang());
        holder.lblDonGia.setText(formatDouble.formatStr(list.get(position).getDonGia()));
        holder.tv_id_donhang.setText(list.get(position).getIdDonHang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DaHuy extends RecyclerView.ViewHolder {
        private TextView lblThoiGian, lblDonGia, lblKhachang, trangthai, tv_id_donhang;
        private LinearLayout layoutThongTin;

        public DaHuy(@NonNull View ItemView) {
            super(ItemView);
            lblThoiGian = ItemView.findViewById(R.id.lblThoiGian);
            lblDonGia = ItemView.findViewById(R.id.lblDonGia);
            layoutThongTin = ItemView.findViewById(R.id.layoutThongTin);
            lblKhachang = ItemView.findViewById(R.id.lblKhachang);
            trangthai = ItemView.findViewById(R.id.trangthai);
            tv_id_donhang = ItemView.findViewById(R.id.tv_id_donhang);
        }
    }

    private void displayItem(RecyclerView recyclerView , Dialog dialog, int position){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(dialog.getContext(), 1));
        itemDonHangHuyAdapter = new ItemDonHangHuyAdapter(dialog, list.get(position).getSanpham());
        recyclerView.setAdapter(itemDonHangHuyAdapter);
        itemDonHangHuyAdapter.notifyDataSetChanged();
    }

    private void openFeedbackDialog(int gravity, int position) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_don_hang);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tenkhachhang = dialog.findViewById(R.id.tenkhachhang);
        TextView diachi = dialog.findViewById(R.id.diachi);
        RecyclerView recycleview = dialog.findViewById(R.id.recycleview);
        TextView tongtien = dialog.findViewById(R.id.tongtien);
        ImageView close = dialog.findViewById(R.id.close);
        TextView khuyenmai = dialog.findViewById(R.id.khuyenmai);
        TextView thanhTien = dialog.findViewById(R.id.thanhTien);

        tenkhachhang.setText(list.get(position).getTencuahang());
        diachi.setText(list.get(position).getDiaChi());
        tongtien.setText(formatDouble.formatStr(support.TinhTongTien(list.get(position).getSanpham())));
        khuyenmai.setText(formatDouble.formatStr(list.get(position).getGiaKhuyenMai()));
        thanhTien.setText(formatDouble.formatStr(support.TinhTongTien(list.get(position).getSanpham()) - list.get(position).getGiaKhuyenMai()));

        displayItem(recycleview, dialog, position);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

