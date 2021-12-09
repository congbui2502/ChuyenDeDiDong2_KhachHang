package java.android.quanlybanhang.CongAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.Activity.SuperQuangCaoActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanPhamNoiBatAdapter extends RecyclerView.Adapter<SanPhamNoiBatAdapter.TraiViewHolder>{
    private List<SanPham> trais;
    private IclickAddToCartListener iclickAddToCartListener;
    private AppCompatActivity activity;
    private CuaHang cuaHang;
    private int pos;
    private List<String> list_spinner;


    public interface IclickAddToCartListener{
        void onClickAddToCart(ImageView imageToCart,SanPham trai);
    }

    public void setData(KhachHangActivity activity,List<SanPham> list, IclickAddToCartListener listener)
    {
        this.activity=activity;
        this.iclickAddToCartListener=listener;
        this.trais=list;
        notifyDataSetChanged();
    }


    public void setData1(AppCompatActivity activity,List<SanPham> list )
    {
        this.activity=activity;
        this.trais=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TraiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sanphamnoibat,parent,false);
        return new TraiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraiViewHolder holder, int position) {
        SanPham trai=trais.get(position);
        pos=0;
        if (trai==null)
        {
            return;
        }


        holder.fdcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.fdcell.toggle(false);
                getDataFromFirebase(trai, holder.circleShop2, holder.tvShop2);
            }
        });

        //view 1
        holder.tvHagtag1.setText(trai.getNameProduct());
        Picasso.get().load(trai.getImgProduct()).into(holder.imageViewSanPhamNoiBat1);
        holder.imgaddSanPhamNoiBat1.setText(Cart_Fragment.addDauPhay(trai.getDonGia().get(0).getGiaBan())+" VND");
        holder.textViewgiaSPNoiBat1.setText(trai.getChitiet());

        //view 2

        SanPham sp1=trai;
        int oldList= trai.getDonGia().size();
        list_spinner=new ArrayList<>();
        for (int i = 0; i < trai.getDonGia().size(); i++) {
            list_spinner.add(trai.getDonGia().get(i).getTenDonGia());
        }
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_1, list_spinner);

        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner2.setAdapter(spin_adapter);

        holder.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler( parent,  view,  position, oldList, id,holder.tvGia2,sp1);
                pos=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Picasso.get().load(trai.getImgProduct()).into(holder.imgSanpham2);
        holder.tvTensp2.setText(trai.getNameProduct());
        holder.tvGia2.setText(Cart_Fragment.addDauPhay(trai.getDonGia().get(0).getGiaBan())+" VND");
        holder.tvDes2.setText(trai.getChitiet());
        holder.tvDatmua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperQuangCaoActivity.addCart(cuaHang,sp1,activity,trai.getDonGia().size()-oldList+pos);
                KhachHangActivity.getCartList(activity);
            }
        });

        holder.lineShop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "Ấn nút thành công",Toast.LENGTH_SHORT).show();
            }
        });

//        holder.cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChiTietFragment fragment=new ChiTietFragment();
//                fragment.setData(trai);
//                FragmentTransaction transaction= activity.getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container,fragment);
//                transaction.addToBackStack("");
//                transaction.commit();
//            }
//        });
    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position,int oldList,
                                       long id,TextView tvGia,SanPham sanPham) {
        tvGia.setText(Cart_Fragment.addDauPhay(sanPham.getDonGia().
                get(sanPham.getDonGia().size()-oldList+position).getGiaBan())+" VND");
    }
    @Override
    public int getItemCount() {
        if(trais!=null)
        {
            return trais.size();
        }

        return 0;
    }

    private void getDataFromFirebase(SanPham sanPham,CircleImageView imageView, TextView tvShop)
    {

        DatabaseReference mReference= FirebaseDatabase.getInstance().getReference("cuaHang");

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals(sanPham.getIdCuaHang()))
                {

                    for (DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String key= snapshot1.getKey();

                        if(key.equals("thongtin"))
                        {
                            cuaHang = snapshot1.getValue(CuaHang.class);
                            if(cuaHang!=null)
                            {
//                                Toast.makeText(getBaseContext(),cuaHang.getName(),Toast.LENGTH_SHORT).show();
                                Glide.with(activity.getApplicationContext()).load(cuaHang.getLogoUrl()).into(imageView);
                                tvShop.setText(cuaHang.getName());
                            }

                        }
                    }

                }


            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public class TraiViewHolder extends RecyclerView.ViewHolder{

        private FoldingCell fdcell;
        private ImageView imgSanpham2;
        private LinearLayout lineShop2;
        private CircleImageView circleShop2;
        private TextView tvShop2,tvTensp2,tvGia2,tvDes2,tvDatmua2;
        private Spinner spinner2;

        private ImageView imageViewSanPhamNoiBat1;
        private TextView tvHagtag1;
        private TextView textViewgiaSPNoiBat1;
        private TextView imgaddSanPhamNoiBat1;




        public TraiViewHolder(@NonNull View itemView) {
            super(itemView);
            fdcell= itemView.findViewById(R.id.folding_cell);

            imageViewSanPhamNoiBat1 =itemView.findViewById(R.id.imgRecy3);
            tvHagtag1 =itemView.findViewById(R.id.tvHagtag);
            imgaddSanPhamNoiBat1 =itemView.findViewById(R.id.tvTittle);
            textViewgiaSPNoiBat1 =itemView.findViewById(R.id.tvDesscription);


            imgSanpham2 =itemView.findViewById(R.id.imgSP);
            lineShop2=itemView.findViewById(R.id.lineShop);
            tvShop2 =itemView.findViewById(R.id.tvShop);
            circleShop2=itemView.findViewById(R.id.circleShop);
            tvTensp2=itemView.findViewById(R.id.tvTensp);
            tvGia2=itemView.findViewById(R.id.tvGia);
            tvDes2=itemView.findViewById(R.id.tvDescription);
            tvDatmua2=itemView.findViewById(R.id.tvDatmua);
            spinner2=itemView.findViewById(R.id.spinner);
        }
    }
}
