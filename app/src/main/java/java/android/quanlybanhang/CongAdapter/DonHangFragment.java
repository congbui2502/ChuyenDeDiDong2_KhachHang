package java.android.quanlybanhang.CongAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.android.quanlybanhang.CongAdapter.AddressVN.DataAddress;
import java.android.quanlybanhang.CongAdapter.AddressVN.DiaChi;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.DonHangOnline;
import java.android.quanlybanhang.Sonclass.KhachHang;
import java.android.quanlybanhang.Sonclass.KhuyenMai;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DonHangFragment  extends Fragment {

    private CuaHang cuaHang;
    private String idQuan;
    private RecyclerView recyDonHang;
    private TextView giaKhuyenMai;
    private TextView tongTien;
    private TextView edtDiaChi,tienShipper;
    private EditText soNha;
    private Button btnDatHang, btnThemDialog, btnHuyDialog;
    private KhachHangActivity mainActivity;
    private AutoCompleteTextView phuongxaAuto, thanhphoAuto, quan_huyenAuto;
    private KhuyenMaiAdapter.setTvKhuyenMai khuyenMai;
    private List<SanPham> sanPhams;
    private long tongtien;
    private Dialog dialog;
    private Window window;
    private String[] tinh;
    private String[] huyen;
    private String[] xa;
    private String sonha;
    private AlertDialog alert11;
    private long giaKhuyenmai;
    private String tenTinh;
    private String tenHuyen;
    private String tenXa;
    private KhachHang khachHang;
    private ArrayAdapter<String> adapterTinh;
    private ArrayAdapter<String> adapterHuyen;
    private ArrayAdapter<String> adapterXa;
    private int ViTri = 0;
    private long thunhap;
    private long donGia;

    private ArrayList<DiaChi> listDiaChi = new ArrayList<>();

    public void getIdQuan(String idQuan)
    {
        this.idQuan= idQuan;

    }

    public DonHangFragment(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.donhang_fragment,container,false);
        mainActivity=(KhachHangActivity) getActivity();
        khachHang= mainActivity.getKhachHang();
        Log.d("aaa","nhap sai roi");
        recyDonHang=view.findViewById(R.id.recyDonHang);
        giaKhuyenMai=view.findViewById(R.id.tvKhuyenMai);
        tongTien=view.findViewById(R.id.tvTongGia);
        btnDatHang=view.findViewById(R.id.btnDatDon);
        edtDiaChi = view.findViewById(R.id.edtDiachi);
        tienShipper= view.findViewById(R.id.tvTienShipper);
        dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_daichikhachhang);
        window = dialog.getWindow();
        LinearLayoutManager manager=new LinearLayoutManager(mainActivity);
        recyDonHang.setLayoutManager(manager);
        giaKhuyenmai =0;
        DonHangAdapter donHangAdapter=new DonHangAdapter();
        donHangAdapter.setData(sanPhams);
        recyDonHang.setAdapter(donHangAdapter);
        tongtien = donHangAdapter.tinhTongTien();
        tongTien.setText("Tổng: "+Cart_Fragment.addDauPhay(tongtien)+" VND");
        getDataShop();
        DataAddress dataAddress = new DataAddress();
        try {
            listDiaChi = dataAddress.readCompanyJSONFile(mainActivity);
//            setDataText();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] tienKM = tienShipper.getText().toString().split(" ");
         thunhap =Long.parseLong(tienKM[2]);
         donGia = tongtien+thunhap;

        tongTien.setText("Tổng: "+Cart_Fragment.addDauPhay(tongtien+ thunhap)+" VNĐ");


        edtDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiachiKH(Gravity.CENTER);
            }
        });

        giaKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getContext(),idQuan,Toast.LENGTH_SHORT).show();
                CustomDialogKhuyanMai customDialogKhuyanMai=new CustomDialogKhuyanMai(mainActivity);
                customDialogKhuyanMai.setData(new KhuyenMaiAdapter.setTvKhuyenMai() {
                    @Override
                    public void setTv(KhuyenMai khuyenMai) {
//                        Toast.makeText(getContext(),khuyenMai.getLoaiKhuyenmai()+ "abc",Toast.LENGTH_LONG).show();
                        tinhtoan(khuyenMai);
                    }
                },idQuan);
                customDialogKhuyanMai.show();

            }
        });

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mReference= FirebaseDatabase.getInstance().getReference()
                        .child("CuaHangOder")
                        .child(idQuan).child("donhangonline").child("dondadat");

                Date date = Calendar.getInstance().getTime();
                // Display a date in day, month, year format
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String today = formatter.format(date);
                String millis =java.time.LocalTime.now().toString()+" " +today;
                String key = System.currentTimeMillis()+"";

                View view1 = inflater.inflate(R.layout.dialog_phuongthucthanhtoan, null);
                Button btnThem=view1.findViewById(R.id.btnthemDiaLogDVT);
                Button btnHuyBo=view1.findViewById(R.id.btnhuyDiaLogDVT);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                LayoutInflater inflater = getLayoutInflater();
                builder.setView(view1);

                alert11 = builder.create();

                alert11.show();

                btnHuyBo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alert11.cancel();
                    }
                });
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton btnChuyenKhoan=view1.findViewById(R.id.PT1);
                        RadioButton btnTienMat=view1.findViewById(R.id.gtPT2);

                        if(btnChuyenKhoan.isChecked()==false && btnTienMat.isChecked() ==false)
                        {
                            Toast.makeText(getContext(),"Vui lòng chọn phương thức thanh toán",Toast.LENGTH_SHORT).show();
                        }else if (btnChuyenKhoan.isChecked() == false)
                        {
                            if(edtDiaChi.getText().toString().equals(""))
                            {
                                Toast.makeText(getContext(),"Vui lòng chọn địa chỉ giao hàng",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                DonHangOnline donHangOnline1=new DonHangOnline(idQuan, khachHang.getIdKhachhang(),giaKhuyenmai,
                                        0,millis,donGia, sanPhams,edtDiaChi.getText().toString(),key, khachHang.getNameKhachHang(),
                                        khachHang.getSdtKhachHang(), "abcxyz",thunhap,1, cuaHang.getName());

                                HomeFragment fragment =new HomeFragment(mainActivity);
                                mReference.child(today).child(key).setValue(donHangOnline1);
                                mainActivity.getSupportFragmentManager().beginTransaction().
                                        replace(R.id.fragment_container,fragment).commit();
                                btnDatHang.setEnabled(false);
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                                builder1.setMessage("Đơn hàng của bạn đã được đặt.");
                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert12 = builder1.create();
                                alert12.show();

                                alert11.cancel();

                            }
                        }
                        else {
                            DonHangOnline donHangOnline1=new DonHangOnline(idQuan, khachHang.getIdKhachhang(),giaKhuyenmai,
                                    0,millis,donGia, sanPhams,edtDiaChi.getText().toString(),key, khachHang.getNameKhachHang(),
                                    khachHang.getSdtKhachHang(), "abcxyz",thunhap,2, cuaHang.getName());

                            HomeFragment fragment =new HomeFragment(mainActivity);
                            mReference.child(today).child(key).setValue(donHangOnline1);
                            mainActivity.getSupportFragmentManager().beginTransaction().
                                    replace(R.id.fragment_container,fragment).commit();
                            btnDatHang.setEnabled(false);
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                            builder1.setMessage("Đơn hàng của bạn đã được đặt.");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alert12 = builder1.create();
                            alert12.show();

                            alert11.cancel();

                        }

                    }
                });


            }
        });
        return view;
    }

    private void tinhtoan(KhuyenMai khuyenMai) {

        String[] tienSP = tienShipper.getText().toString().split(" ");
        long tsp =Long.parseLong(tienSP[2]);
        tongTien.setText((tongtien+ tsp - tongtien*khuyenMai.getPhanTramKhuyenMai()/100)+" VNĐ");


        if(khuyenMai.getLoaiKhuyenmai()==1)
        {
            if(tongtien>khuyenMai.getGiaDeDuocKhuyenMai())
            {
                tongTien.setText((tongtien- tongtien*khuyenMai.getPhanTramKhuyenMai()/100)+" VNĐ");
                giaKhuyenmai = tongtien * khuyenMai.getPhanTramKhuyenMai()/100;
            }else {
                Toast.makeText(getContext(), "Tổng tiền không đủ để sử dụng.",Toast.LENGTH_LONG).show();
            }


        }else {
            Toast.makeText(getContext(), "không đúng loai khuyen mãi",Toast.LENGTH_LONG).show();
        }


    }

    private void DiachiKH(int gravity){

        phuongxaAuto = dialog.findViewById(R.id.spinner_xa);
        quan_huyenAuto = dialog.findViewById(R.id.spinner_huyen);
        thanhphoAuto = dialog.findViewById(R.id.spinner_tinh);
        soNha = dialog.findViewById(R.id.edtSoNha);
        btnThemDialog = dialog.findViewById(R.id.btnTaoDiaChiKhachhang);
        btnHuyDialog = dialog.findViewById(R.id.btnhuyTaoDiaChiKhachHang);
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window.getAttributes();
        windownAttributes.gravity = gravity;
        window.setAttributes(windownAttributes);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }


        tinh = ArrayTinh();
        adapterTinh = new ArrayAdapter<String>(mainActivity, R.layout.support_simple_spinner_dropdown_item, tinh);
        thanhphoAuto.setAdapter(adapterTinh);
        adapterTinh.notifyDataSetChanged();

        int i = 0;

        thanhphoAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tenTinh = parent.getItemAtPosition(position).toString();
                ViTri = position;
                String[] arrayHuyen = ArrayHuyen(position);
                adapterHuyen = new ArrayAdapter<String>(mainActivity, R.layout.support_simple_spinner_dropdown_item, arrayHuyen);
                quan_huyenAuto.setText(listDiaChi.get(position).getHuyens().get(0).getTenHuyen());
                tenHuyen = listDiaChi.get(position).getHuyens().get(0).getTenHuyen();
                quan_huyenAuto.setAdapter(adapterHuyen);

                adapterXa = new ArrayAdapter<String>(mainActivity, R.layout.support_simple_spinner_dropdown_item,
                        listDiaChi.get(position).getHuyens().get(0).getXa());
                phuongxaAuto.setText(listDiaChi.get(position).getHuyens().get(0).getXa().get(0));
                tenXa = listDiaChi.get(position).getHuyens().get(0).getXa().get(0);
                phuongxaAuto.setAdapter(adapterXa);

            }
        });
        quan_huyenAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tenHuyen = parent.getItemAtPosition(position).toString();

                adapterXa = new ArrayAdapter<String>(mainActivity, R.layout.support_simple_spinner_dropdown_item,
                        listDiaChi.get(ViTri).getHuyens().get(position).getXa());
                phuongxaAuto.setText(listDiaChi.get(ViTri).getHuyens().get(position).getXa().get(0));
                tenXa = listDiaChi.get(ViTri).getHuyens().get(position).getXa().get(0);
                phuongxaAuto.setAdapter(adapterXa);
            }
        });
        phuongxaAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tenXa = parent.getItemAtPosition(position).toString();
            }
        });
        btnHuyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThemDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonha = soNha.getText().toString();
                edtDiaChi.setText(sonha+","+tenXa+","+tenHuyen+","+tenTinh);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String[] ArrayTinh() {

        String[] arr = new String[listDiaChi.size()];

        for (int i = 0; i < listDiaChi.size(); i++) {
            arr[i] = listDiaChi.get(i).getTenTinhTP();
        }

        return arr;
    }

    private String[] ArrayHuyen(int pos) {
        String[] arr = new String[listDiaChi.get(pos).getHuyens().size()];

        for (int i = 0; i < listDiaChi.get(pos).getHuyens().size(); i++) {
            arr[i] = listDiaChi.get(pos).getHuyens().get(i).getTenHuyen();
        }

        return arr;
    }

    private void  getDataShop()
    {
        DatabaseReference mReference= FirebaseDatabase.getInstance().getReference("cuaHang");
        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals(idQuan))
                {
//                    Toast.makeText(getBaseContext(),snapshot.getKey(),Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String key= snapshot1.getKey();
//                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
                        if(key.equals("thongtin"))
                        {
                            cuaHang = snapshot1.getValue(CuaHang.class);

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

