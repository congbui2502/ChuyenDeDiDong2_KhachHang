package java.android.quanlybanhang.CongAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.android.quanlybanhang.R;
import java.util.ArrayList;
import java.util.List;

public class Shop_Adapter extends RecyclerView.Adapter<Shop_Adapter.TraiViewHolder>{
    private List<String> trais;
    private int flag;


    private List<TraiViewHolder> traiViewHolders;

    private IclickchangRecycleView iclickchangRecycleView;




    public interface IclickchangRecycleView{
        void changListProduct(String s);

        void setAdapterForProductList(String s);
    }

    public void setOnClick(IclickchangRecycleView onClick)
    {
        this.iclickchangRecycleView=onClick;
    }

    private SanPhamNoiBatAdapter.IclickAddToCartListener iclickAddToCartListener;



    public void setData(List<String> list)
    {
        this.trais=list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public TraiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            traiViewHolders=new ArrayList<>();
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.loai_douong,parent,false);
            return new TraiViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TraiViewHolder holder, int position) {
        String trai=trais.get(position).toString();

        traiViewHolders.add(holder);


        if (trai!=null)
        {
            if(position==0)
            {
                iclickchangRecycleView.setAdapterForProductList(trai);
                setIsClick(trai);
            }
            holder.loai_douong.setText(trai.toString());
            holder.loai_douong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        iclickchangRecycleView.changListProduct(trai);
                        setIsClick(holder.loai_douong.getText().toString());
                }
            });
        }
        else return;

    }

    @Override
    public int getItemCount() {
        if(trais!=null)
        {
            return trais.size();
        }

        return 0;
    }

    public class TraiViewHolder extends RecyclerView.ViewHolder{

        private  TextView loai_douong;


        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);

                loai_douong=itemView.findViewById(R.id.tvLoaiDouong);

        }
    }

    private void setIsClick(String s)
    {
        for (int i = 0; i < traiViewHolders.size(); i++) {
            if(traiViewHolders.get(i).loai_douong.getText().toString().equals(s))
            {
                traiViewHolders.get(i).loai_douong.setEnabled(false);
                traiViewHolders.get(i).loai_douong.setTextColor(R.color.blue);

            }

            else {

                traiViewHolders.get(i).loai_douong.setTextColor(R.color.white);
                traiViewHolders.get(i).loai_douong.setEnabled(true);

            }
        }
    }
}
