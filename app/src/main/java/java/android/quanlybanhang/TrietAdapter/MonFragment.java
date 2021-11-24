package java.android.quanlybanhang.TrietAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;

import java.android.quanlybanhang.R;
import java.android.quanlybanhang.Sonclass.Mon;
import java.android.quanlybanhang.Activity.BepActivity;
import java.util.ArrayList;
import java.util.List;

public class MonFragment extends Fragment {
    private RecyclerView recyclerViewTable;
    private DatabaseReference mDatabase;
    private BepActivity bepActivity;
    private List<Mon> list;
    private List<String> stringList;
    private MonViewHolder monViewHolder=new MonViewHolder(getContext());
    View v;
    public MonFragment(BepActivity bepActivity) {
        this.bepActivity=bepActivity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.frag_mon, container, false);
        recyclerViewTable= v.findViewById(R.id.recycler);
        recyclerViewTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTable.setAdapter(monViewHolder);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringList=new ArrayList<>();



    }

}
