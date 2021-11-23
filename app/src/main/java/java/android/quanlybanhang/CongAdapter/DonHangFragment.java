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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.android.quanlybanhang.CongAdapter.AddressVN.DataAddress;
import java.android.quanlybanhang.CongAdapter.AddressVN.DiaChi;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.DonHangOnline;
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

    private String idQuan="JxZOOK1RzcMM7pL5I6naGZfYSsu2";
    private RecyclerView recyDonHang;
    private TextView giaKhuyenMai;
    private TextView tongTien;
    private TextView edtDiaChi;
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

    private String tenTinh;
    private String tenHuyen;
    private String tenXa;

    private ArrayAdapter<String> adapterTinh;
    private ArrayAdapter<String> adapterHuyen;
    private ArrayAdapter<String> adapterXa;
    private int ViTri = 0;

    private ArrayList<DiaChi> listDiaChi = new ArrayList<>();

    public DonHangFragment(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.donhang_fragment,container,false);
        mainActivity=(KhachHangActivity) getActivity();
        Log.d("aaa","nhap sai roi");
        recyDonHang=view.findViewById(R.id.recyDonHang);
        giaKhuyenMai=view.findViewById(R.id.tvKhuyenMai);
        tongTien=view.findViewById(R.id.tvTongGia);
        btnDatHang=view.findViewById(R.id.btnDatDon);
        edtDiaChi = view.findViewById(R.id.edtDiachi);
        dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_daichikhachhang);
        window = dialog.getWindow();
        LinearLayoutManager manager=new LinearLayoutManager(mainActivity);
        recyDonHang.setLayoutManager(manager);

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

        edtDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DiachiKH(Gravity.CENTER);
            }
        });

        giaKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KhuyenMai khuyenMai=new KhuyenMai("xZOOK1RzcMM7pL5I6naGZfYSsu2",10,
//                        0);
//                if(donHangAdapter.tinhTongTien()>khuyenMai.getGiaDeDuocKhuyenMai())
//                {
////                    String abc= Cart_Fragment.addDauPhay((donHangAdapter.tinhTongTien()-
////                            donHangAdapter.tinhTongTien()*khuyenMai.getPhanTramKhuyenMai()/100));
//
//                    String abc= Cart_Fragment.addDauPhay(33000);
//
//                    tongTien.setText(abc+" VND");
//
//                }
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
                        .child(idQuan).child("donhangonline").child("dondadat");
                Date date = Calendar.getInstance().getTime();
                // Display a date in day, month, year format
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String today = formatter.format(date);
                String millis =java.time.LocalTime.now().toString()+" " +today;
                String key = System.currentTimeMillis()+"";

                DonHangOnline donHangOnline=new DonHangOnline("idKhachhang",donHangAdapter.tinhTongTien()
                        ,0,millis,sanPhams,
                        "12/21 đường số 12 p.Linh Chieu",key,"Bùi Đình Công","0374193095");
                HomeFragment fragment =new HomeFragment(mainActivity);
                mReference.child(today).child(key).setValue(donHangOnline);
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

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        return view;
    }

    private void tinhtoan(KhuyenMai khuyenMai) {
        if(khuyenMai.getLoaiKhuyenmai()==1)
        {
            if(tongtien>khuyenMai.getGiaDeDuocKhuyenMai())
            {
                tongTien.setText((tongtien- tongtien*khuyenMai.getPhanTramKhuyenMai()/100)+" VNĐ");
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
        sonha = soNha.getText().toString();
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
}

