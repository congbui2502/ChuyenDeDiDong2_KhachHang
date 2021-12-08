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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.android.quanlybanhang.CongAdapter.AddressVN.DataAddress;
import java.android.quanlybanhang.CongAdapter.AddressVN.DiaChi;
import java.android.quanlybanhang.R;
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

    private String idQuan;
    private RecyclerView recyDonHang;
    private TextView giaKhuyenMai;
    private TextView tongTien;
    private TextView edtDiaChi,tienShipper;
    private EditText soNha,edtghichu;
    private Button btnDatHang, btnThemDialog, btnHuyDialog;
    private KhachHangActivity mainActivity;
    private AutoCompleteTextView phuongxaAuto, thanhphoAuto, quan_huyenAuto;
    private KhuyenMaiAdapter.setTvKhuyenMai khuyenMai;
    private List<SanPham> sanPhams;
    private long tongtien;
    private Dialog dialog;
    private Window window;
    private Dialog dialog1;
    private Window window1;
    private String[] tinh;
    private String[] huyen;
    private String[] xa;
    private String sonha;
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
    private String ghiChu;
    private RadioGroup radioGroup;
    private Button btnThem,btnHuy;
    private Date date;
    private DateFormat formatter;
    private RadioButton btnChuyenKhoan,btnTienMat;
    private String today,millis,key;
    private DatabaseReference mReference;
    private DatabaseReference mReference1;
    private String STR_CH = "cuaHang";
    private String STR_TT = "thongtin";
    private boolean chuyenKhoan = false ;
    private boolean tienMat = false ;

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
        recyDonHang=view.findViewById(R.id.recyDonHang);
        giaKhuyenMai=view.findViewById(R.id.tvKhuyenMai);
        tongTien=view.findViewById(R.id.tvTongGia);
        btnDatHang=view.findViewById(R.id.btnDatDon);
        edtDiaChi = view.findViewById(R.id.edtDiachi);
        tienShipper= view.findViewById(R.id.tvTienShipper);
        edtghichu = view.findViewById(R.id.edtghichu);

        //

        mReference= FirebaseDatabase.getInstance().getReference()
                .child("CuaHangOder")
                .child(idQuan).child("donhangonline").child("dondadat");
        mReference1= FirebaseDatabase.getInstance().getReference();


        date = Calendar.getInstance().getTime();
        // Display a date in day, month, year format
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        today = formatter.format(date);
        millis =java.time.LocalTime.now().toString()+" " +today;
        key = System.currentTimeMillis()+"";
//

        dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_daichikhachhang);
        window = dialog.getWindow();

        dialog1 = new Dialog(mainActivity);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.dialog_phuongthucthanhtoan);
        window1 = dialog1.getWindow();

        radioGroup = dialog1.findViewById(R.id.radioGruop);
        btnChuyenKhoan = dialog1.findViewById(R.id.PT1);
        btnTienMat = dialog1.findViewById(R.id.PT2);
        btnThem = dialog1.findViewById(R.id.btnthemDiaLogPT);
        btnHuy = dialog1.findViewById(R.id.btnhuyDiaLogPT);
        mReference1.child(STR_CH).child(idQuan).child(STR_TT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String a="";
                String b="";
                if(snapshot.child("chuyenKhoan").getValue().toString()!=null &&
                        snapshot.child("tienMat").getValue().toString()!=null)
                {
                     a =  snapshot.child("chuyenKhoan").getValue().toString();
                     b =  snapshot.child("tienMat").getValue().toString();
                }


                if(a.equals("true")){
                    chuyenKhoan = true;
                }
                if(b.equals("true")){
                    tienMat = true;
                }

                if(chuyenKhoan == false){
                    btnChuyenKhoan.setVisibility(View.GONE);
                }
                if(tienMat == false){
                    btnTienMat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.PT1:
                        btnChuyenKhoan.setChecked(true);
                        break;
                    case R.id.PT2:
                        btnTienMat.setChecked(true);
                        break;
                }


            }
        });

        LinearLayoutManager manager=new LinearLayoutManager(mainActivity);
        recyDonHang.setLayoutManager(manager);
        giaKhuyenmai =0;
        DonHangAdapter donHangAdapter=new DonHangAdapter();
        donHangAdapter.setData(sanPhams);
        recyDonHang.setAdapter(donHangAdapter);
        tongtien = donHangAdapter.tinhTongTien();
        tongTien.setText(tongtien+" VND");


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

        tongTien.setText((tongtien+ thunhap)+" VNĐ");


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
                dathang(Gravity.CENTER);
            }
        });
        return view;
    }



    private void dathang(int gravity){
        if (window1 == null) {
            return;
        }
        window1.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windownAttributes = window1.getAttributes();
        windownAttributes.gravity = gravity;
        window1.setAttributes(windownAttributes);
        if(Gravity.BOTTOM == gravity){
            dialog1.setCancelable(true);
        }
        else {
            dialog1.setCancelable(false);

        }

        ghiChu = edtghichu.getText().toString();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChuyenKhoan.isChecked()==false && btnTienMat.isChecked() ==false)
                {
                    Toast.makeText(getContext(),"Vui lòng chọn phương thức thanh toán",Toast.LENGTH_SHORT).show();
                }else if (btnChuyenKhoan.isChecked() == false)
                {
                    if(edtDiaChi.getText().toString().equals(""))
                    {
                        Toast.makeText(getContext(),"Vui lòng chọn địa chỉ giao hàng",Toast.LENGTH_SHORT).show();
                    }else {
                        if(edtDiaChi.getText().toString().isEmpty()){
                            edtDiaChi.setError("Chọn địa chỉ");
                            edtDiaChi.getText().toString();
                        }
                        else {
                            DonHangOnline donHangOnline1 = new DonHangOnline(idQuan, khachHang.getIdKhachhang(), giaKhuyenmai,
                                    0, millis, donGia, sanPhams, edtDiaChi.getText().toString(), key, khachHang.getNameKhachHang(),
                                    khachHang.getSdtKhachHang(), ghiChu, thunhap, 1);

                            HomeFragment fragment = new HomeFragment(mainActivity);
                            mReference.child(today).child(key).setValue(donHangOnline1);
                            mainActivity.getSupportFragmentManager().beginTransaction().
                                    replace(R.id.fragment_container, fragment).commit();
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

                        }
                    }
                }
                else {
                    if(edtDiaChi.getText().toString().isEmpty()){
                        Toast.makeText(mainActivity,"Chưa có địa chỉ",Toast.LENGTH_LONG).show();
                    }else {
                        DonHangOnline donHangOnline1 = new DonHangOnline(idQuan, khachHang.getIdKhachhang(), giaKhuyenmai,
                                0, millis, donGia, sanPhams, edtDiaChi.getText().toString(), key, khachHang.getNameKhachHang(),
                                khachHang.getSdtKhachHang(), ghiChu, thunhap, 2);
                        ThongTinChuyenKhoan_Fragment thongTinChuyenKhoan_fragment = new ThongTinChuyenKhoan_Fragment(donHangOnline1);


                        if (idQuan != null) {
                            thongTinChuyenKhoan_fragment.getIdQuan(idQuan);
                        } else {
                            Toast.makeText(getContext(), "cmm", Toast.LENGTH_SHORT).show();
                        }

                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, thongTinChuyenKhoan_fragment).commit();
                        dialog1.dismiss();


                    }

                }
            }
        });

        dialog1.show();
    }

    private void tinhtoan(KhuyenMai khuyenMai) {

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
            Toast.makeText(getContext(), "Không đúng loai khuyen mãi",Toast.LENGTH_LONG).show();
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
                if(tenTinh.isEmpty()){
                    Toast.makeText(mainActivity,"Chưa có địa chỉ",Toast.LENGTH_LONG).show();
                }
                else if(sonha.isEmpty()){
                    Toast.makeText(mainActivity,"Chưa có số nhà",Toast.LENGTH_LONG).show();
                }
                else {
                    edtDiaChi.setText(sonha+","+tenXa+","+tenHuyen+","+tenTinh);
                    dialog.dismiss();
                }


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
}

