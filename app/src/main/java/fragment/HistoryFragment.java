package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shipper.DonHang;
import com.example.shipper.History;
import com.example.shipper.LichSuActivity;
import com.example.shipper.R;
import com.example.shipper.SanPham;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.HistoryAdapter;


public class HistoryFragment<cardview> extends Fragment {
    public static final String KEY_LICHSU = "LICHSU";
    private TextView tvDiemNhan,tvDiemGiao,tvThoiGian;
    private DatabaseReference databaseReference;
    private FirebaseAuth mFirebaseAuth;
    private Context context;
    ListView recyclerView;
    ArrayList<History> arrayList;
    private HistoryAdapter adapter;
    History historyActivity = new History();
    public void setData(Context context){
        this.context=context;
    }
    private void AnhXa(View view){
        recyclerView = view.findViewById(R.id.recycerhis);
        tvDiemNhan = view.findViewById(R.id.tv_diemnhan);
        tvDiemGiao= view.findViewById(R.id.tv_diemgiao);
        tvThoiGian = view.findViewById(R.id.tv_thoigian);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        recyclerView = view.findViewById(R.id.recycerhis);
        AnhXa(view);
        arrayList = new ArrayList<>();
        adapter = new HistoryAdapter((Activity) view.getContext(), R.layout.activity_racycer_history, arrayList);
        recyclerView.setAdapter(adapter);
        getDataFromFirebase();
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, LichSuActivity.class);
                History history = arrayList.get(position);
                Log.d("aaa","bbb");
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_LICHSU,history);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public String formatDateS(String strDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.sss dd-MM-yyyy");
        String dt = "";
        try {
            Date date = simpleDateFormat.parse(strDate);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            dt = formatter.format(date);
            return dt;
        } catch (Exception e) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            dt = formatter.format(date);
            return dt;
        }
    }

    private void getDataFromFirebase() {
        mFirebaseAuth=FirebaseAuth.getInstance();
        String id=mFirebaseAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Shipper").child(id).child("lichSuDonOnline");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<History>();
//                for (DataSnapshot snapshot1 : snapshot.getChildren()){
//                    History history = snapshot1.getValue(History.class);
//                    String time = formatDateS(history.getTime());
//
//                    arrayList.add(history);
//
//                }
//                adapter.setData(arrayList);
//                adapter.notifyDataSetChanged();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    for (DataSnapshot snapshot2 : snapshot.getChildren()) {
                        History history = snapshot1.getValue(History.class);
                        String diaChi = history.getDiaChi();
                        String diemNhan = history.getDiemnhan();
                        long thuNhap = history.getThunhap();
                        String tenKhachhang = history.getTenKhachHang();
                        String ghiChu = history.getGhiChu();
                        String sdtkhachhang = history.getSdtkhachhang();
                        long donGia = history.getDonGia();
                        List<SanPham> sanpham = history.getSanpham();
                        String time = formatDateS(history.getTime());
                        String idQuan = history.getIdQuan();
                        String shipper = history.getShipper();
                        String phoneShipper = history.getPhoneShipper();
                        String key = history.getKey();
                        int trangthai = history.getTrangthai();
                        String idKhachHang = history.getIdKhachhang();
                        String idDonHang = history.getIdDonHang();

                        historyActivity = new History(diaChi, diemNhan, donGia, tenKhachhang, sdtkhachhang, thuNhap, sanpham, ghiChu, time,
                                idQuan, shipper, phoneShipper, key, trangthai, idKhachHang, idDonHang);

                    }
                    arrayList.add(0,historyActivity);
                    adapter.setData(arrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}