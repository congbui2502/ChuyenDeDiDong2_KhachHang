package java.android.quanlybanhang.TrietAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.Table;
import java.util.List;

public class TableViewHolder extends RecyclerView.Adapter<TableViewHolder.ViewHolderBan> {
    public TableViewHolder(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }
    public void setData(List<Table> list){
        this.tableList=list;
        notifyDataSetChanged();
    }
    private DatabaseReference mDatabase;
    Activity activity;
    Context context;
    List<Table> tableList;


    @Override
    public ViewHolderBan onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.design_ban, parent, false);
        return new ViewHolderBan(heroView);
    }

    @Override
    public void onBindViewHolder(ViewHolderBan holder, int position) {
        Table table = tableList.get(position);
        holder.examName.setText(table.getNameTable());
        holder.examMessage.setText(table.getYeuCau());
        holder.examDate.setText(table.getDate());

        MonBanViewHolder monBanViewHolder=new MonBanViewHolder();
        monBanViewHolder.setmList(table.getDanhSachMon());
        holder.monBan.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        holder.monBan.setAdapter(monBanViewHolder);
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }


    public class ViewHolderBan extends RecyclerView.ViewHolder {
        private TextView examName;
        private TextView examMessage;
        private TextView examDate;
        private RecyclerView monBan;

        public ViewHolderBan(View itemView) {
            super(itemView);
            examName
                    = itemView
                    .findViewById(R.id.examName);
            examDate
                    = itemView
                    .findViewById(R.id.examDate);
            examMessage
                    = itemView
                    .findViewById(R.id.examMessage);
            monBan
                    =itemView
                    .findViewById(R.id.monBan);

        }

    }

}
