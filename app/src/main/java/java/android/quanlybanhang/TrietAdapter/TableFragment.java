package java.android.quanlybanhang.TrietAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.firebase.database.ValueEventListener;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.Mon;
import java.android.quanlybanhang.Sonclass.Table;
import java.android.quanlybanhang.Sonclass.TestChangProduct;
import java.util.ArrayList;
import java.util.List;

public class TableFragment extends Fragment {
    private RecyclerView recyclerViewTable;
    private DatabaseReference mDatabase;
    private List<Table> tableList;
    private TableViewHolder tableViewHolder;
    private List<String> stringList;
    private List<String> keyListSelected;
    private   List<Mon> mons = new ArrayList<>();


    View v;
    public TableFragment() {
        // Required empty public constructor
        Log.d("III","ACascsa");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tables();
        v= inflater.inflate(R.layout.frag_ban, container, false);
        recyclerViewTable= v.findViewById(R.id.recycler);
        tableViewHolder=new TableViewHolder(getActivity(),getContext());
        tableViewHolder.setData(tableList);
        recyclerViewTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTable.setAdapter(tableViewHolder);
        keyListSelected=new ArrayList<>();
        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            tableList=new ArrayList<>();

    }
    public void tables(){
        List<Mon> mons=new ArrayList<>();
        stringList=new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("sanphamorder").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                stringList.add(snapshot.getKey());
                //xet xem co su thay doi cua Firebase
                onDataChange(snapshot.getKey());


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
    private void onDataChange(String list_key)
    {


        mDatabase.child("sanphamorder").child(list_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               mons = getListProductFromTable(list_key);
                Log.d("gg",snapshot.getKey());
                TestChangProduct testChangProduct=snapshot.getValue(TestChangProduct.class);
                if(testChangProduct.isFlag()==true)
                {

                    keyListSelected.add(list_key);
                    Table category = snapshot.getValue(Table.class);
                    tableList.add(new Table(list_key,mons,category.getYeuCau(),category.getDate()));
                    tableViewHolder.setData(tableList);

                }
                else {

                    for (int i=0;i<keyListSelected.size();i++)
                    {
                        if(keyListSelected.get(i).equals(list_key))
                        {
                            tableList.remove(i);
                            keyListSelected.remove(i);
                            tableViewHolder.setData(tableList);
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private List<Mon> getListProductFromTable(String key)
    {
        List<Mon> mons=new ArrayList<>();



        mDatabase.child("sanphamorder").child(key).child("sanpham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Mon mon=snapshot.getValue(Mon.class);
                mons.add(mon);
                tableViewHolder.notifyDataSetChanged();
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


//        mDatabase.child("sanphamorder").child(key).child("sanpham").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("gg",key);
////                Log.d("gg",snapshot.getKey());
//
////                Mon mon=snapshot.getValue(Mon.class);
////                mons.add(mon);
////                tableViewHolder.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return  mons;
    }
}
