package java.android.quanlybanhang.DatBan.History;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.DatBan.History.Adapter.DonHangAdapter;
import java.android.quanlybanhang.DatBan.History.Data.DonHang;
import java.android.quanlybanhang.DatBan.History.Data.SupportFragmentDonOnline;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhachHang;

import java.util.ArrayList;
import java.util.Date;

public class DonHangFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DonHangFragment(){

    }

    public static DonHangFragment newInstance(String param1, String param2) {
        DonHangFragment fragment = new DonHangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    private DonHangAdapter donHangFragmentAdapter;
    private ArrayList<DonHang> donHangs;
    private Dialog dialog;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private SupportFragmentDonOnline support = new SupportFragmentDonOnline();
    private TextView lblThongBao;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private View view;
    private ImageView image;
    private KhachHang khachHang = KhachHangActivity.khachHang;
    DonHangFragment donHangFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_donhang, container, false);

        recyclerView = view.findViewById(R.id.recycleview);
        dialog = new Dialog(view.getContext());

        lblThongBao = view.findViewById(R.id.lblThongBao);
        progressBar = view.findViewById(R.id.progressBar);
        refreshLayout = view.findViewById(R.id.swipeRefreshlayout);
        image = view.findViewById(R.id.image);

        progressBar.setVisibility(View.VISIBLE);
        refreshLayout.setOnRefreshListener(this);

        getDataFireBase(view);
        return view;
    }

    private void displayItem(View view){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        donHangFragmentAdapter = new DonHangAdapter(view.getContext(), donHangs, dialog);
        recyclerView.setAdapter(donHangFragmentAdapter);
        donHangFragmentAdapter.notifyDataSetChanged();
    }

    private void getDataFireBase(View view) {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        mFirebaseDatabase.child("CuaHangOder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                donHangs = new ArrayList<>();
                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot snapshot = postSnapshot.child("donhangonline/dondadat");
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                       for(DataSnapshot snap : snapshot1.getChildren()){
                           DonHang donHang = snap.getValue(DonHang.class);
                           Log.d("donHangne",donHang.getIdDonHang()+"fhdghf");
                           if(donHang.getIdKhachhang().equals(khachHang.getIdKhachhang())){
                               if (donHang.getTrangthai() == 0 || donHang.getTrangthai() == 1 || donHang.getTrangthai() == 2 ||  donHang.getTrangthai() == 3 || donHang.getTrangthai() == 4 ||  donHang.getTrangthai() == 5 ) {
                                   donHangs.add(donHang);
                                   String key = snap.getKey();
                                   donHangs.get(i).setIdDonHang(key);
                                   donHangs.get(i).setKey(key);
                                   Date date = support.formatDate(donHangs.get(i).getTime());
                                   donHangs.get(i).setDate(date);
                                   i++;
                               }
                           }
                       }

                    }
                }
//                Log.d("zzzz",donHangs.get(0).getSanpham().get(0).getDonGia().get(0).getTenDonGia()+"");
                refreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.INVISIBLE);
                if (donHangs.size() > 0) {
                    lblThongBao.setText("");
                    image.setImageResource(0);
                }else {
                    image.setImageResource(R.drawable.empty_list);
                    lblThongBao.setText("Không có đơn hàng");
                }
                support.SapXepDate(donHangs);
                displayItem(view);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FIREBASE", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }



    @Override
    public void onRefresh() {
        getDataFireBase(view);
    }
}
