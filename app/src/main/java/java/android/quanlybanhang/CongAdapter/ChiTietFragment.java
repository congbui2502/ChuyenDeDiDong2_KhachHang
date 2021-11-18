package java.android.quanlybanhang.CongAdapter;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.DonHangOnline;
import java.android.quanlybanhang.Sonclass.KhuyenMai;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChiTietFragment extends Fragment {
    private ImageView imgSP,imgLogo;
    private  TextView tvTenSP,tvGia,tvDes,tvDatMua,tvTenQuan;
    private SanPham sanPham;
    private DatabaseReference mReference;
    private CuaHang cuaHang;
    private RecyclerView recyclerView;
    SanPhamNoiBatAdapter adapter;

    private List<SanPham> sanPhamList;

    public void setData(SanPham sanPham)
    {
        this.sanPham=sanPham;
    }
    public ChiTietFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.chitietsanpham_layout,container,false);
        unit(view);
        Glide.with(getContext()).load(sanPham.getImgProduct()).into(imgSP);
        tvTenSP.setText(sanPham.getNameProduct());
        tvGia.setText(sanPham.getGiaBan()+" VND");
        tvDes.setText(sanPham.getChitiet());
        tvDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReference= FirebaseDatabase.getInstance().getReference();
                mReference.child("gioHang").child("idKhachHang").child("sanPham").push().setValue(sanPham);
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        getData();

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=  getActivity().getSupportFragmentManager().beginTransaction();
                ShopProductFragment fragment1=new ShopProductFragment((KhachHangActivity) getActivity(),getContext(), new QuanNoiBatAdapter.getdata() {
                    @Override
                    public CuaHang getData() {
                        return cuaHang;
                    }
                });
                fragmentTransaction.replace(R.id.fragment_container,fragment1);
//                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void unit(View view) {
        sanPhamList=new ArrayList<>();
        imgSP=view.findViewById(R.id.imgSP);
        imgLogo=view.findViewById(R.id.imgLogo);
        tvTenSP=view.findViewById(R.id.tvTensp);
        tvDatMua=view.findViewById(R.id.tvDatmua);
        tvDes=view.findViewById(R.id.tvDescription);
        tvGia=view.findViewById(R.id.tvGia);
        tvTenQuan=view.findViewById(R.id.tenQuanquan);
        recyclerView=view.findViewById(R.id.recySanpham);
         adapter=new SanPhamNoiBatAdapter();
        adapter.setData1((KhachHangActivity) getActivity(),sanPhamList);
        recyclerView.setAdapter(adapter);

    }

    private void getData()
    {
        mReference=FirebaseDatabase.getInstance().getReference("cuaHang");

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String key= snapshot1.getKey();
//                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
                    if(key.equals("thongtin"))
                    {
                         cuaHang = snapshot1.getValue(CuaHang.class);
                        if(cuaHang!=null)
                        {
                            Glide.with(getContext()).load(cuaHang.getLogoUrl()).into(imgLogo);
                            tvTenQuan.setText(cuaHang.getName());
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

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String key= snapshot1.getKey();
//                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
                    if(key.equals("sanpham"))
                    {
                        for (DataSnapshot snapshot2:snapshot1.getChildren())
                        {
                            for (DataSnapshot snapshot3:snapshot2.getChildren())
                            {
                                SanPham sanPham = snapshot3.getValue(SanPham.class);
                                sanPhamList.add(sanPham);
                                adapter.setData1((KhachHangActivity) getActivity(),sanPhamList);
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


}
