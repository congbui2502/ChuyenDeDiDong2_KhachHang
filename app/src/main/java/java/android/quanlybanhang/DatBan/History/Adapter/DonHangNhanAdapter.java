package java.android.quanlybanhang.DatBan.History.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.DatBan.DanhGiaCuaHang;
import java.android.quanlybanhang.DatBan.History.Data.DonHang;
import java.android.quanlybanhang.DatBan.History.Data.FormatDouble;
import java.android.quanlybanhang.DatBan.History.Data.SupportFragmentDonOnline;
import java.android.quanlybanhang.DatBan.History.DonHangNhanFrament;
import java.android.quanlybanhang.R;

import java.util.ArrayList;

public class DonHangNhanAdapter extends RecyclerView.Adapter<DonHangNhanAdapter.DaNhan> {

    private Context context;
    private ArrayList<DonHang> list;
    private Dialog dialog,dialog1;
    private ItemDonHangNhanAdapter itemDonHangNhanAdapter;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private FormatDouble formatDouble;
    private int select;
    private SupportFragmentDonOnline support;
    private String ID_CUAHANG;
    private DonHangNhanFrament donHangFragment;


    public DonHangNhanAdapter(Context context, ArrayList<DonHang> list, Dialog dialog) {
        this.context = context;
        this.list = list;
        this.dialog = dialog;
        this.select = 0;
        formatDouble = new FormatDouble();
        support = new SupportFragmentDonOnline();
    }
    public DonHangNhanAdapter(Context context, ArrayList<DonHang> list, Dialog dialog,DonHangNhanFrament donHangFragment) {
        this.context = context;
        this.list = list;
        this.dialog = dialog;
        this.select = 0;
        formatDouble = new FormatDouble();
        support = new SupportFragmentDonOnline();
        this.donHangFragment = donHangFragment;
    }
    @NonNull
    @Override
    public DaNhan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaNhan(LayoutInflater.from(context).inflate(R.layout.item_donhangnhan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaNhan holder, int position) {
        int k = position;
        DonHang donHang = list.get(position);
        holder.layoutThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER, k);
            }
        });



        if (list.get(position).getTrangthai() == 6) {
            holder.trangthai.setText("Đã nhận");

        }

        holder.lblThoiGian.setText(support.formartDate(list.get(position).getDate()));
        holder.lblKhachang.setText(list.get(position).getTencuahang());
        holder.lblDonGia.setText(formatDouble.formatStr(list.get(position).getDonGia()));
        holder.tv_id_donhang.setText(list.get(position).getIdDonHang());
        holder.lbldanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilogChonDanhGia(10,donHang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DaNhan extends RecyclerView.ViewHolder {
        private TextView lblThoiGian, lblDonGia, lblKhachang, trangthai, tv_id_donhang, lbldanhgia;;
        private LinearLayout layoutThongTin;

        public DaNhan(@NonNull View ItemView) {
            super(ItemView);
            lblThoiGian = ItemView.findViewById(R.id.lblThoiGian);
            lblDonGia = ItemView.findViewById(R.id.lblTienDat);
            layoutThongTin = ItemView.findViewById(R.id.layoutThongTin);
            lblKhachang = ItemView.findViewById(R.id.lblKhachang);
            trangthai = ItemView.findViewById(R.id.trangthai);
            tv_id_donhang = ItemView.findViewById(R.id.lbl_idban);
            lbldanhgia = ItemView.findViewById(R.id.lbldanhgia);
        }
    }

    private void displayItem(RecyclerView recyclerView , Dialog dialog, int position){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(dialog.getContext(), 1));

        itemDonHangNhanAdapter = new ItemDonHangNhanAdapter(dialog, list.get(position).getSanpham());
        recyclerView.setAdapter(itemDonHangNhanAdapter);
        itemDonHangNhanAdapter.notifyDataSetChanged();
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
    private void dilogChonDanhGia( int gravity,DonHang donHang){
        dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.dailongchondanhgia);
        TextView danhgiashipper = dialog1.findViewById(R.id.tvdatban);
       TextView thoat = dialog1.findViewById(R.id.thoat);
        TextView   danhgiacuahang = dialog1.findViewById(R.id.tvdanhgia);
        Window window = dialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        danhgiacuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(donHangFragment.getActivity(), DanhGiaCuaHang.class);
                intent.putExtra("list",donHang);
                intent.putExtra("flag","1");
                donHangFragment.getActivity().startActivity(intent);
                dialog1.dismiss();
            }
        });
        danhgiashipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(donHangFragment.getActivity(), DanhGiaCuaHang.class);
                intent.putExtra("list",donHang);
                intent.putExtra("flag","2");
                donHangFragment.getActivity().startActivity(intent);
                dialog1.dismiss();
            }
        });


        if (Gravity.BOTTOM == gravity) {
            dialog1.setCancelable(true);
        } else {
            dialog1.setCancelable(false);
        }

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }
}
