package java.android.quanlybanhang.DatBan.History.Adapter;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.DatBan.History.Data.DonHang;
import java.android.quanlybanhang.DatBan.History.Data.FormatDouble;
import java.android.quanlybanhang.DatBan.History.Data.SupportFragmentDonOnline;
import java.android.quanlybanhang.R;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangHolder>{

    private Context context;
    private ArrayList<DonHang> list;
    private Dialog dialog;
    private ItemDonHangAdapter itemDonHangAdapter;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private FormatDouble formatDouble;
    private int select;
    private SupportFragmentDonOnline support;



    public DonHangAdapter(Context context, ArrayList<DonHang> list, Dialog dialog) {
        this.context = context;
        this.list = list;
        this.dialog = dialog;
        this.select = 0;
        formatDouble = new FormatDouble();
        support = new SupportFragmentDonOnline();
    }

    @NonNull
    @Override
    public DonHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonHangHolder(LayoutInflater.from(context).inflate(R.layout.item_donhang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangHolder holder, int position) {
        int k = position;
        holder.layoutThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER, k);
            }
        });


        holder.lblHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialogHuy(Gravity.CENTER, k);
            }
        });
        if (list.get(position).getTrangthai() == 0) {
            holder.trangthai.setText("Đang chờ xác nhận");

        }else if (list.get(position).getTrangthai() == 1) {
            holder.trangthai.setText("Đang chờ shipper");

        }else if(list.get(position).getTrangthai() == 2){
            holder.trangthai.setText("Đang xừ lý");
            holder.lblHuy.setVisibility(View.GONE);
            holder.trangthai.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        }
        else if(list.get(position).getTrangthai() == 3){
            holder.trangthai.setText("Đang xừ lý");
            holder.lblHuy.setVisibility(View.GONE);
            holder.trangthai.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        }
        else if(list.get(position).getTrangthai() == 4){
            holder.trangthai.setText("Đang xừ lý");
            holder.lblHuy.setVisibility(View.GONE);
            holder.trangthai.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        }
        else if(list.get(position).getTrangthai() == 5){
            holder.trangthai.setText("Đang giao");
            holder.lblHuy.setVisibility(View.GONE);
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

    public class DonHangHolder extends RecyclerView.ViewHolder {
        private TextView lblThoiGian,lblDonGia, lblHuy, lblKhachang,trangthai, tv_id_donhang;
        private LinearLayout layoutThongTin;
        public DonHangHolder(@NonNull View ItemView) {
            super(ItemView);
            lblThoiGian = ItemView.findViewById(R.id.lblThoiGian);
            lblDonGia = ItemView.findViewById(R.id.lblTienDat);
            lblHuy = ItemView.findViewById(R.id.lblhuy);
            layoutThongTin = ItemView.findViewById(R.id.layoutThongTin);
            lblKhachang = ItemView.findViewById(R.id.lblKhachang);
            trangthai = ItemView.findViewById(R.id.trangthai);
            tv_id_donhang = ItemView.findViewById(R.id.lbl_idban);
        }
    }

    private void displayItem(RecyclerView recyclerView , Dialog dialog, int position){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(dialog.getContext(), 1));

        itemDonHangAdapter = new ItemDonHangAdapter(dialog, list.get(position).getSanpham());
        recyclerView.setAdapter(itemDonHangAdapter);
        itemDonHangAdapter.notifyDataSetChanged();
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

    private void openFeedbackDialogHuy(int gravity, int positon) {
        Dialog dialogHuy = new Dialog(context);
        dialogHuy.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogHuy.setContentView(R.layout.dialog_huy_don);

        Window window = dialogHuy.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_dong = dialogHuy.findViewById(R.id.btn_dong);
        Button btn_huy = dialogHuy.findViewById(R.id.btn_huy);


        if (Gravity.BOTTOM == gravity) {
            dialogHuy.setCancelable(true);
        } else {
            dialogHuy.setCancelable(false);
        }

        btn_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHuy.dismiss();
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHuy.dismiss();
                setFirebaseHuy(list.get(positon).getIdDonHang(),list.get(positon).getIdQuan(), positon);
                list.remove(positon);
                notifyDataSetChanged();
            }
        });
        dialogHuy.show();
    }


//
//    //TODO: setDuLieu Firebase xác nhận
    private void setFirebaseHuy (String IdDonHang,String ID_CUAHANG, int position) {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        mFirebaseDatabase.child("CuaHangOder/"+ID_CUAHANG+"/donhangonline/dondadat/"+support.ngayHientai(list.get(position).getDate())+"/"+IdDonHang+"/trangthai").setValue(7).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Đã hủy đơn hàng", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    //TODO: setDuLieu Firebase hủy đơn
//    private void setFirebaseHuyDonDonHang (String IdDonHang, int position) {
//        mFirebaseInstance = FirebaseDatabase.getInstance();
//        mFirebaseDatabase = mFirebaseInstance.getReference();
//        mFirebaseDatabase.child("CuaHangOder/"+ID_CUAHANG+"/donhangonline/dondadat/"+support.
//                ngayHientai(list.get(position).getDate())+"/"+IdDonHang).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(context, "Đã hủy đơn", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context, "Hủy thất bại", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
