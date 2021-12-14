package java.android.quanlybanhang.DatBan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.util.ArrayList;

public class DanhSachTheoDoiDatBan extends AppCompatActivity {
    private KhachHang khachHang = KhachHangActivity.khachHang;
    String id_bk;
    private ArrayList<DatBanModel> datBanModels;
    private ArrayList<ID_datban> ID_datbans;
    private DatabaseReference mDatabase, mDatabase1;
    ImageView rong;
    ProgressBar progressBar;
    AdapterDanhSachTheoDoi adapterDanhSachTheoDoi;
    RecyclerView rv_1;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_theo_doi_dat_ban);
        rong = findViewById(R.id.rong);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        rv_1 = findViewById(R.id.rv_1);
        hamtheodoidatban();

    }

    private void hamtheodoidatban() {
        mDatabase1 = FirebaseDatabase.getInstance().getReference("DuyetDatBan");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        ID_datbans = new ArrayList<>();
                        datBanModels = new ArrayList<>();
                        Log.d("keyTruongss", postSnapshot.getKey() + "for1");
                        Log.d("keyTruongss", khachHang.getIdKhachhang() + "forkh");
                        if (postSnapshot.getKey().equals(khachHang.getIdKhachhang())) {

                            DataSnapshot sss = postSnapshot;
                            for (DataSnapshot aaa : sss.getChildren()) {

                                Log.d("keyTruong", aaa.getKey() + "for2");
                                id = aaa.getKey();
                                DataSnapshot kkk = aaa;
                                for (DataSnapshot bbb : kkk.getChildren()) {

                                    Log.d("keyTruong", bbb.getKey() + "for3");
                                    String trangthai_dat = bbb.child("trangthai_dat").getValue() + "";
//                                if (trangthai_dat.equals("0")) {
                                    id_bk = bbb.child("id_bk").getValue() + "";
                                    String id_ngaydat = bbb.getKey();
                                    String giodat = bbb.child("giodat").getValue() + "";
                                    String gioketthuc = bbb.child("gioketthuc").getValue() + "";
                                    String ngaydat = bbb.child("ngaydat").getValue() + "";
                                    String ngayhientai = bbb.child("ngayhientai").getValue() + "";
                                    String sodienthoai = bbb.child("sodienthoai").getValue() + "";
                                    String sotiendattruoc = bbb.child("sotiendattruoc").getValue() + "";
                                    String tenkhachhang = bbb.child("tenkhachhang").getValue() + "";
                                    String tenban = bbb.child("tenban").getValue() + "";
                                    String trangthai = bbb.child("trangthai").getValue() + "";
                                    String ten_cuahang = bbb.child("ten_cuahang").getValue() + "";
                                    Log.d("keyTruong", tenban + "for4");
                                    datBanModels.add(new DatBanModel(tenban, id_ngaydat, giodat, gioketthuc, id_bk, ngaydat, ngayhientai, sodienthoai, sotiendattruoc, tenkhachhang, trangthai, ten_cuahang, trangthai_dat));
                                    ID_datban datban = new ID_datban(id, datBanModels);
                                    ID_datbans.add(datban);
                                    Log.d("keyTruong", datBanModels.size() + "for4");


//                                }
                                }
                            }
                            adapterDanhSachTheoDoi = new AdapterDanhSachTheoDoi(datBanModels);
                            rv_1.setLayoutManager(new LinearLayoutManager(DanhSachTheoDoiDatBan.this, LinearLayoutManager.VERTICAL, false));
                            rv_1.setAdapter(adapterDanhSachTheoDoi);
                            adapterDanhSachTheoDoi.notifyDataSetChanged();
                        }

                    }
                } else {
                    rong.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}