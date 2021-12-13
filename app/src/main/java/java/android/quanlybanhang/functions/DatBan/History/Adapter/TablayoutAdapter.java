package java.android.quanlybanhang.functions.DatBan.History.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.android.quanlybanhang.R;
import java.util.ArrayList;

public class TablayoutAdapter extends RecyclerView.Adapter<TablayoutAdapter.LayoutHelp>{
    private Context context;
    private ArrayList<String> title;
    private int selectedItem;
    private ViewPager2 pager;

    public TablayoutAdapter(Context context, ArrayList<String> title, ViewPager2 pager) {
        this.context = context;
        this.title = title;
        selectedItem = 0;
        this.pager = pager;
    }

    @NonNull
    @Override
    public LayoutHelp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LayoutHelp(LayoutInflater.from(context).inflate(R.layout.item_recycleview_tab_don_hang_online, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutHelp holder, int position) {
        holder.name.setText(title.get(position));

        if (selectedItem == position) {
            holder.item.setBackgroundResource(R.drawable.bg_tab_don_online2);
        }else {
            holder.item.setBackgroundResource(R.drawable.bg_tab_don_online);
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = position;
                notifyDataSetChanged();
                pager.setCurrentItem(position);
            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                selectedItem = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class LayoutHelp extends RecyclerView.ViewHolder {
        private TextView name;
        private LinearLayout item;
        public LayoutHelp(@NonNull View ItemView) {
            super(ItemView);
            name = ItemView.findViewById(R.id.name);
            item = ItemView.findViewById(R.id.item);
        }
    }
}
