package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shipper.DonHang;
import com.example.shipper.R;

import java.util.ArrayList;
import java.util.List;

public class DonHangAdapter extends ArrayAdapter<DonHang> {

    private Activity context;
    private int layout;
    private ArrayList<DonHang> arrayList;

    public void setData(List<DonHang> list){
        this.arrayList= (ArrayList<DonHang>) list;
        notifyDataSetChanged();
    }
    public DonHangAdapter(Activity context, int layout, ArrayList<DonHang> arrayList) {
        super(context,layout,arrayList);
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        view = context.getLayoutInflater().inflate(R.layout.activity_donhang, parent, false);

        DonHang donHang = arrayList.get(position);
        if (donHang!=null){
            TextView textView1 = view.findViewById(R.id.trangthai);
            TextView textView2 = view.findViewById(R.id.tv_diemnhan);
            TextView textView3 = view.findViewById(R.id.tv_diemgiao);
            TextView textView4 = view.findViewById(R.id.tv_tonggia);

            textView1.setText(donHang.getTrangthai()+"");
            textView2.setText(donHang.getDiemnhan());
            textView3.setText(donHang.getDiaChi());
            textView4.setText(donHang.getDonGia()+"");
        }



        return view;
    }
}
