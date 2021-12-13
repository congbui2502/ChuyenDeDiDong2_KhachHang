package java.android.quanlybanhang.functions.DatBan.History;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.android.quanlybanhang.functions.DatBan.History.Adapter.DonHangNhanAdapter;
import java.android.quanlybanhang.functions.DatBan.History.Data.DonHang;
import java.android.quanlybanhang.functions.DatBan.History.Data.SupportFragmentDonOnline;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.util.ArrayList;
import java.util.Date;

public class DonHangNhanFrament extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DonHangNhanFrament(){

    }

    public static DonHangNhanFrament newInstance(String param1, String param2) {
        DonHangNhanFrament fragment = new DonHangNhanFrament();
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
    private DonHangNhanAdapter donHangNhanAdapter;
    private ArrayList<DonHang> donHangs;
    private Dialog dialog,dialog1;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private SupportFragmentDonOnline support = new SupportFragmentDonOnline();
    private String ID_CUAHANG = "YVnoULVR9xc0vJ9esj8VRbQ1HNg2";
    private TextView lblThongBao;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private View view;
    private ImageView image;
    private KhachHang khachHang = KhachHangActivity.khachHang;
    ArrayList<String> mLexItemList;
    Context mContext;
   public static int dialogthanhcong=0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_donhangnhan, container, false);

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
        donHangNhanAdapter = new DonHangNhanAdapter(view.getContext(), donHangs, dialog,DonHangNhanFrament.this);
        recyclerView.setAdapter(donHangNhanAdapter);
        donHangNhanAdapter.notifyDataSetChanged();
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
                            if(donHang.getIdKhachhang().equals(khachHang.getIdKhachhang())){
                                if (donHang.getTrangthai() == 6  ) {
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
    public void onRefresh() { getDataFireBase(view);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(dialogthanhcong==2){
            dilogChonDanhGia(10);
            Toast.makeText(getContext(),"dilog thanh cong",Toast.LENGTH_LONG).show();
            dialogthanhcong=0;
        }
    }
    private void dilogChonDanhGia( int gravity){
        dialog1 = new Dialog(getContext());
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.dialog_canhbao);
        Window window = dialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (Gravity.BOTTOM == gravity) {
            dialog1.setCancelable(true);
        } else {
            dialog1.setCancelable(false);
        }
        Button Okay = dialog1.findViewById(R.id.btn_cancel);
        TextView title = dialog1.findViewById(R.id.title);
        title.setText("Đánh Giá Thành Công!!!");
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }
}
