package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.example.shipper.DonHang;
import com.example.shipper.R;

import java.util.ArrayList;
import java.util.List;

public class DonNhanAdapter extends ArrayAdapter<DonHang> {

    private Activity context;
    private int layout;
    private ArrayList<DonHang> arrayList;

    public void setData(List<DonHang> list){
        this.arrayList= (ArrayList<DonHang>) list;
        notifyDataSetChanged();
    }
    public DonNhanAdapter(Activity context, int layout, ArrayList<DonHang> arrayList) {
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

        view = context.getLayoutInflater().inflate(R.layout.activity_donnhan, parent, false);

        DonHang donHang = arrayList.get(position);
        if (donHang!=null){
            TextView textView1 = view.findViewById(R.id.trangthai);
            TextView textView2 = view.findViewById(R.id.tv_diemnhan);
            TextView textView3 = view.findViewById(R.id.tv_diemgiao);
            TextView textView4 = view.findViewById(R.id.tv_tonggia);
            TextView textView5= view.findViewById(R.id.tv_thunhap);

            textView1.setText("Đã nhận đơn");
            textView2.setText(donHang.getDiemnhan());
            textView3.setText(donHang.getDiaChi());
            textView4.setText(donHang.getDonGia()+"đ");
            textView5.setText(donHang.getThunhap()+"đ");
        }



        return view;
    }
}