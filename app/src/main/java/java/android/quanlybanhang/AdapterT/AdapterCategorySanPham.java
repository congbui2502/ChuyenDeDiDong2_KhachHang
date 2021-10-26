package java.android.quanlybanhang.AdapterT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.android.quanlybanhang.DataT.Category_SanPham;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Activity.ListCategory;
import java.util.ArrayList;

public class AdapterCategorySanPham extends RecyclerView.Adapter<AdapterCategorySanPham.CategoryAdater> {

    private ArrayList<Category_SanPham> arrayList;
    private ListCategory context;

    public AdapterCategorySanPham(ListCategory context, ArrayList<Category_SanPham> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdater onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        CategoryAdater categoryAdater = new CategoryAdater(view);
        return categoryAdater;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdater holder, int position) {
        Category_SanPham category = arrayList.get(position);
        holder.nameCategory.setText(category.getNameCategory());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CategoryAdater extends RecyclerView.ViewHolder  {
        private TextView nameCategory;
        private ImageButton imgXoa, imgSua ;


        public CategoryAdater(View itemView) {
            super(itemView);

            nameCategory = (TextView) itemView.findViewById(R.id.text_name);
            imgXoa = (ImageButton) itemView.findViewById(R.id.img_xoa);
            imgSua = (ImageButton) itemView.findViewById(R.id.img_sua);
            imgXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    context.delete(position);
                }
            });
            imgSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    context.update(position);
                }
            });

        }


    }
}
