package java.android.quanlybanhang.AdapterT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.android.quanlybanhang.OrderMon.Product;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Activity.ListProduct;
import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder> {
    private ArrayList<Product> arrayList;
    private ListProduct context;

    public AdapterProduct( ListProduct context,ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = arrayList.get(position);
        holder.textViewName.setText(product.getNameProduct());
        holder.textViewGia.setText(product.getGiaBan()+"");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageButton imgXoa;
        private ImageButton imgSua;
        private TextView textViewName;
        private TextView textViewGia;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSua = itemView.findViewById(R.id.img_suaproduct);
            imgXoa = itemView.findViewById(R.id.img_xoaproduct);
            textViewName = itemView.findViewById(R.id.textnameProduct);
            textViewGia = itemView.findViewById(R.id.textgiaProduct);

            imgXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    context.delete(position);
                }
            });

        }
    }
}
