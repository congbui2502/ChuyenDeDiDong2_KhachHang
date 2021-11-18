package java.android.quanlybanhang.CongAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.KhuyenMai;
import java.util.ArrayList;
import java.util.List;

public class CustomDialogKhuyanMai extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    private RecyclerView recyKhuyenMai;
    private List<KhuyenMai> sanPhams;
    private FirebaseStorage mStorage;
    private DatabaseReference mReference;
    private KhuyenMaiAdapter adapter;
    private String idCuaHang;


    private KhuyenMaiAdapter.setTvKhuyenMai tvKhuyenMai;

    public void setData(KhuyenMaiAdapter.setTvKhuyenMai tvKhuyenMai,String idCuaHang)
    {
        this.tvKhuyenMai=tvKhuyenMai;
        this.idCuaHang=idCuaHang;
    }



    public CustomDialogKhuyanMai(Activity a) {
        super(a);

        // TODO Auto-generated constructor stub

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_khuyenmai);
        recyKhuyenMai=findViewById(R.id.recyKhuyenmai);
        sanPhams=new ArrayList<KhuyenMai>();
        getData();
        d=this;
        adapter=new KhuyenMaiAdapter();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyKhuyenMai.setLayoutManager(layoutManager);
        adapter.setData(tvKhuyenMai,d);

        recyKhuyenMai.setAdapter(adapter);

        mReference= FirebaseDatabase.getInstance().getReference();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tvCo:
////               deleteData();
//                break;
//            case R.id.tvKhong:
//                dismiss();
//                break;
//            default:
//                break;
        }
        dismiss();
    }

    private  void getData()
    {
        mReference =FirebaseDatabase.getInstance().getReference().child("khuyenmai").child(idCuaHang);

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                KhuyenMai khuyenMai=snapshot.getValue(KhuyenMai.class);
                sanPhams.add(khuyenMai);
                adapter.setList(sanPhams);

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
