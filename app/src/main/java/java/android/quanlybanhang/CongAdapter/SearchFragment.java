package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.android.quanlybanhang.Activity.KhachHangActivity;
import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.CuaHang;
import java.android.quanlybanhang.Sonclass.SanPham;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private EditText edtSearch;
    private RecyclerView recyclerView;
    private DatabaseReference mReference;
    private  List<CuaHang> cuaHangList;
    private List<SanPham> sanPhamList;

    private  List<CuaHang> cuaHangSearchList;
    private List<SanPham> sanPhamSearchList;
    private KhachHangActivity activity;
    private SearchAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_fragment,container,false);
        unit(view);
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                   String key = edtSearch.getText().toString();
                setData(key);
            }
        });
        return view;
    }

    public  void getActivity(KhachHangActivity activity)
    {
        this.activity=activity;
    }

    private void unit(View view) {
        edtSearch=view.findViewById(R.id.edtSearch);
        recyclerView=view.findViewById(R.id.recySearch);
        getCuaHang();
        adapter= new SearchAdapter();
        adapter.setData(cuaHangList,sanPhamList,getContext(),activity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getCuaHang()
    {
             cuaHangList=new ArrayList<>();
                sanPhamList= new ArrayList<>();

        mReference= FirebaseDatabase.getInstance().getReference("cuaHang");
       Toast.makeText(getContext(),mReference.getKey(),Toast.LENGTH_SHORT).show();

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String key= snapshot1.getKey();
//                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
                    if(key.equals("thongtin"))
                    {
                        CuaHang cuaHang = snapshot1.getValue(CuaHang.class);

                        cuaHangList.add(cuaHang);
                        adapter.notifyDataSetChanged();

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

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String key= snapshot1.getKey();
//                    Toast.makeText(getContext(),key,Toast.LENGTH_SHORT).show();
                    if(key.equals("sanpham"))
                    {
                        for (DataSnapshot snapshot2:snapshot1.getChildren())
                        {
                            for (DataSnapshot snapshot3:snapshot2.getChildren())
                            {
                                SanPham sanPham = snapshot3.getValue(SanPham.class);
                                sanPhamList.add(sanPham);
                                adapter.notifyDataSetChanged();
                            }

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

    private void setData(String key) {

        if(edtSearch.getText().toString().equals(""))
        {
            Log.d("aaa","aaa");
            adapter.setData(cuaHangList,sanPhamList,getContext(),activity);
            return;
        }
        cuaHangSearchList=new ArrayList<>();
        for (int i = 0; i < cuaHangList.size(); i++) {
            if(cuaHangList.get(i).getName().toUpperCase().contains(key.toUpperCase()))
            {
                cuaHangSearchList.add(cuaHangList.get(i));
            }

        }

        sanPhamSearchList=new ArrayList<>();
        for (int i = 0; i < sanPhamList.size(); i++) {
            if(sanPhamList.get(i).getNameProduct().toUpperCase().contains(key.toUpperCase()))
            {
                sanPhamSearchList.add(sanPhamList.get(i));
            }

        }

        adapter.setData(cuaHangSearchList,sanPhamSearchList,getContext(),activity);



    }



}
