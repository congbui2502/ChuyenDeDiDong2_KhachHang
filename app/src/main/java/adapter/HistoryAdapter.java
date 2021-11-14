package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shipper.DonHang;
import com.example.shipper.History;
import com.example.shipper.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends ArrayAdapter<History> {

    private Activity context;
    private int layout;
    private ArrayList<History> arrayList;

    public HistoryAdapter( Activity context, int layout, ArrayList<History> arrayList) {
        super(context, layout, arrayList);
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }


    public void setData(List<History> list){
        this.arrayList= (ArrayList<History>) list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        view = context.getLayoutInflater().inflate(R.layout.activity_racycer_history, parent, false);

        History history = arrayList.get(position);
        TextView textView1 = view.findViewById(R.id.tv_trangthails);
        TextView textView2 = view.findViewById(R.id.tv_diemnhan);
        TextView textView3 = view.findViewById(R.id.tv_diemgiao);
        TextView textView4 = view.findViewById(R.id.tv_thoigian);
        TextView textView5 = view.findViewById(R.id.tv_thoigiannhan);
        TextView textView6 = view.findViewById(R.id.tv_thoigiangiao);

        textView1.setText(history.getTrangthai()+"");
        textView2.setText(history.getDiemnhan());
        textView3.setText(history.getDiaChi());
        textView4.setText(history.getTime());
        textView5.setText(history.getTgNhanDon());
        textView6.setText(history.getTgHoanThanh());

        return view;
    }
}
