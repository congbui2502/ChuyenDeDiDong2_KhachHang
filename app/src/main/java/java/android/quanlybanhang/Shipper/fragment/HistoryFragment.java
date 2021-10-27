package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shipper.ChiTietActivity;
import com.example.shipper.DonHang;
import com.example.shipper.History;
import com.example.shipper.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.DonHangAdapter;
import adapter.HistoryAdapter;


public class HistoryFragment extends Fragment {
    private TextView tvDiemNhan,tvDiemGiao,tvThoiGian;
    private DatabaseReference databaseReference;
    private Context context;
    ListView recyclerView;
    ArrayList<History> arrayList;
    private HistoryAdapter adapter;
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
                Intent intent = new Intent(context,ChiTietActivity.class);
                History history = arrayList.get(position);
                Log.d("aaa","bbb");
                Bundle bundle = new Bundle();
                bundle.putSerializable(,history);
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

    private void getDataFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Shipper").child("idShipper").child("lichSuDonOnline");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    History historyActivity = item.getValue(History.class);
                    arrayList.add(historyActivity);
                    adapter.setData(arrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}