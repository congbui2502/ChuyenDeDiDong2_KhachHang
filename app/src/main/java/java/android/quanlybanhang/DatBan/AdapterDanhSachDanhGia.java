package java.android.quanlybanhang.DatBan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterDanhSachDanhGia extends RecyclerView.Adapter<AdapterDanhSachDanhGia.danhsachhodel> {
    ArrayList<RatingModel> ratingModels;
    public AdapterDanhSachDanhGia(ArrayList<RatingModel> ratingModels){
        this.ratingModels = ratingModels;

    }
    @NonNull
    @Override
    public danhsachhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachrating, parent, false);
        danhsachhodel ratinghodel = new danhsachhodel(view);
        return ratinghodel;
    }

    @Override
    public void onBindViewHolder(@NonNull danhsachhodel holder, int position) {
        RatingModel crr = ratingModels.get(position);
        holder.tv_tenkhachhang.setText(crr.getTenkhachhang());
        holder.tv_comment.setText(crr.getComment());
        holder.tv_sorating.setText(crr.getNumberrating()+"sao");
        holder.ratingBar2.setRating(Float.parseFloat(crr.getNumberrating()));
        holder.tv_date.setText(changeDate(ratingModels.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        if (ratingModels != null) {
            return ratingModels.size();
        }
        return 0;
    }

    public class danhsachhodel extends RecyclerView.ViewHolder {
        TextView tv_date, tv_tenkhachhang, tv_sorating,tv_comment;
        RatingBar ratingBar2;
        public danhsachhodel(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_tenkhachhang = itemView.findViewById(R.id.tv_tenkhachhang);
            tv_sorating = itemView.findViewById(R.id.tv_sorating);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            ratingBar2 = itemView.findViewById(R.id.ratingBar2);
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
