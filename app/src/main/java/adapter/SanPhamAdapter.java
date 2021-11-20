package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.shipper.R;
import com.example.shipper.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Activity context;
    private int layout;
    private List<SanPham> arrayList;

    public void setData(List<SanPham> list){
        this.arrayList= (List<SanPham>) list;
        notifyDataSetChanged();
    }
    public SanPhamAdapter(Activity context, int layout, ArrayList<SanPham> arrayList) {
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
        view = context.getLayoutInflater().inflate(R.layout.activity_sanpham, parent, false);
        TextView textView = view.findViewById(R.id.tv_sanpham);
        TextView textView1 = view.findViewById(R.id.tv_soluong);
        textView.setText(arrayList.get(position).getNameProduct());
        textView1.setText(arrayList.get(position).getSoluong()+"");
        return view;
    }
}
