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
import android.widget.Toast;

import com.example.shipper.ChiTietActivity;
import com.example.shipper.DonHang;
import com.example.shipper.R;
import com.example.shipper.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.DonHangAdapter;


public class HomeFragment extends Fragment {
    public static final String KEY_DIEMNHAN = "DIEMNHAN";
    public static final String KEY_DIEMGIAO = "DIEMGIAO";
    public static final String KEY_TONGGIA = "TONGGIA";
    public static final String KEY_BUNDLE = "BUNDLE";
    private TextView tvDiemNhan,tvDiemGiao,tvTongGia;
    private DatabaseReference databaseReference;
    private Context context;
    ListView listView;
    ArrayList<DonHang> arrayList;
    private DonHangAdapter adapter;
    DonHang donHangActivity = new DonHang();
    public void setData(Context context){
        this.context=context;
    }
    private void AnhXa(View view){
        listView = view.findViewById(R.id.lvdonhang);
        tvDiemNhan = view.findViewById(R.id.tv_diemnhan);
        tvDiemGiao= view.findViewById(R.id.tv_diemgiao);
        tvTongGia = view.findViewById(R.id.tv_tonggia);
    }
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        listView =(ListView) view.findViewById(R.id.lvdonhang);
        AnhXa(view);
        arrayList = new ArrayList<>();

         adapter = new DonHangAdapter((Activity) view.getContext(), R.layout.activity_donhang, arrayList);
        listView.setAdapter(adapter);
        getDataFromFirebase();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ChiTietActivity.class);
                DonHang donHang = arrayList.get(position);
                Toast.makeText(getContext(),position+"",Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_DIEMNHAN,donHang);
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

    public HomeFragment() {

    }
    private void getDataFromFirebase(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("DonHangOnline").child("DaDatDon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList = new ArrayList<DonHang>();

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    for (DataSnapshot snapshot2 : snapshot1.getChildren()){

                        DonHang donHang = snapshot2.getValue(DonHang.class);
                        String diaChi = donHang.getDiaChi();
                        String diemNhan = donHang.getDiemnhan();
                        long thuNhap= donHang.getThunhap();
                        String tenKhachhang = donHang.getTenKhachHang();
                        String ghiChu = donHang.getGhiChu();
                        String sdtkhachhang = donHang.getSdtkhachhang();
                        long donGia= donHang.getDonGia();
                        List<SanPham> sanpham = donHang.getSanpham();
                        String time = donHang.getTime();
                        String idQuan = donHang.getIdQuan();
                        String shipper = donHang.getShipper();
                        String phoneShipper = donHang.getPhoneShipper();
                        String key = donHang.getKey();
                        int trangthai = donHang.getTrangthai();
                        String idKhachHang = donHang.getIdKhachhang();
                        String idDonHang= donHang.getIdDonHang();
                        donHangActivity = new DonHang(diaChi,diemNhan,donGia,tenKhachhang,sdtkhachhang,thuNhap,sanpham, ghiChu,time,
                                idQuan,shipper,phoneShipper,key,trangthai,idKhachHang,idDonHang);
                        arrayList.add(donHangActivity);
                    }
                }

                adapter.setData(arrayList);
                adapter.notifyDataSetChanged();
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}