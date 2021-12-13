package java.android.quanlybanhang.CongAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.android.quanlybanhang.Activity.ChangePassActivity;

import java.android.quanlybanhang.Activity.DangNhapKhachHangActivity;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.DatBan.DanhSachTheoDoiDatBan;
import java.android.quanlybanhang.DatBan.DatBanModel;
import java.android.quanlybanhang.DatBan.ID_datban;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.android.quanlybanhang.Sonclass.Mon;
import java.android.quanlybanhang.Sonclass.Table;
import java.android.quanlybanhang.Sonclass.TestChangProduct;
import java.android.quanlybanhang.TrietAdapter.TableViewHolder;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TableFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase, mDatabase1;
    private TextView tv_name, tv_ngaysinh, tv_sodt, tv_email, tv_doimk, tv_dangxuat, tv_datban;
    private CircleImageView avatar;
    private KhachHang khachHang = KhachHangActivity.khachHang;
    String id_bk;
    private ArrayList<DatBanModel> datBanModels;
    private ArrayList<ID_datban> ID_datbans;


    public TableFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_ban, container, false);
        tv_name = v.findViewById(R.id.tv_hoten);
        tv_ngaysinh = v.findViewById(R.id.tv_ngaysinh);
        tv_sodt = v.findViewById(R.id.tv_sdt);
        tv_email = v.findViewById(R.id.tv_email);
        avatar = v.findViewById(R.id.profile_image);
        tv_doimk = v.findViewById(R.id.tv_doimk);
        tv_dangxuat = v.findViewById(R.id.logout);
        tv_datban = v.findViewById(R.id.tv_datban);
        onDataChange();
        DoiMatKhau();
        DangXuat();
//        hamtheodoidatban();
        tv_datban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhSachTheoDoiDatBan.class);
                startActivity(intent);
            }
        });
        return v;

    }

    private void DangXuat() {
        tv_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Đăng xuất");
                builder.setMessage("Bạn có muốn đăng xuất?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), DangNhapKhachHangActivity.class);

                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    public void DoiMatKhau() {
        tv_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePassActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void onDataChange() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        String id = mFirebaseAuth.getUid();

        if (id != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("KhachHang").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    KhachHang sp = snapshot.getValue(KhachHang.class);
                    String imgAvatar = sp.getImgAvatar();
                    String nameKH = sp.getNameKhachHang();
                    String phoneKH = sp.getSdtKhachHang();
                    String emailKH = sp.getEmailKhachHang();
                    String dateKH = sp.getDateKhachHang();

                    Picasso.get().load(imgAvatar).into(avatar);
                    tv_name.setText(nameKH);
                    tv_ngaysinh.setText(dateKH);
                    tv_sodt.setText(phoneKH);
                    tv_email.setText(emailKH);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }

//    private void hamtheodoidatban() {
//        mDatabase1 = FirebaseDatabase.getInstance().getReference("DuyetDatBan");
//        mDatabase1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.getValue() != null) {
//                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                        ID_datbans = new ArrayList<>();
//                        if (postSnapshot.getKey().equals(khachHang.getIdKhachhang()))
//                            Log.d("keyTruong", postSnapshot.getKey() + "for1");
//                        DataSnapshot sss = postSnapshot;
//                        for (DataSnapshot aaa : sss.getChildren()) {
//                            datBanModels = new ArrayList<>();
//                            Log.d("keyTruong", aaa.getKey() + "for2");
//                            DataSnapshot kkk = aaa;
//                            for (DataSnapshot bbb : kkk.getChildren()) {
//                                Log.d("keyTruong", bbb.getKey() + "for3");
//                                String trangthai_dat = aaa.child("trangthai_dat").getValue() + "";
//                                if (trangthai_dat.equals("0")) {
//                                    id_bk = bbb.child("id_bk").getValue() + "";
//                                    String id_ngaydat = bbb.getKey();
//                                    String giodat = bbb.child("giodat").getValue() + "";
//                                    String gioketthuc = bbb.child("gioketthuc").getValue() + "";
//                                    String ngaydat = bbb.child("ngaydat").getValue() + "";
//                                    String ngayhientai = bbb.child("ngayhientai").getValue() + "";
//                                    String sodienthoai = bbb.child("sodienthoai").getValue() + "";
//                                    String sotiendattruoc = bbb.child("sotiendattruoc").getValue() + "";
//                                    String tenkhachhang = bbb.child("tenkhachhang").getValue() + "";
//                                    String tenban = bbb.child("tenban").getValue() + "";
//                                    String trangthai = bbb.child("trangthai").getValue() + "";
//                                    String id = bbb.getKey();
//                                    datBanModels.add(new DatBanModel(tenban, id_ngaydat, giodat, gioketthuc, id_bk, ngaydat, ngayhientai, sodienthoai, sotiendattruoc, tenkhachhang, trangthai));
//                                    ID_datban datban = new ID_datban(id, datBanModels);
//                                    ID_datbans.add(datban);
//                                }
//
//                            }
//                        }
//                    }
//                } else {
////                            rong.setVisibility(View.VISIBLE);
//                }
////                        progressBar.setVisibility(View.INVISIBLE);
////                        datBanAdapter = new DatBanOnlineAdapter(ID_datbans, XacNhanDatBan.this);
////                        recyclerView.setLayoutManager(new LinearLayoutManager(XacNhanDatBan.this, LinearLayoutManager.VERTICAL, false));
////                        recyclerView.setAdapter(datBanAdapter);
////                        datBanAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }

}
