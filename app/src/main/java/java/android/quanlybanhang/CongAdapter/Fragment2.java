package java.android.quanlybanhang.CongAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.android.quanlybanhang.R;

public class Fragment2 extends Fragment {
    Button btnFr2,btnBack;
    Tttttttest tttttttest;
    int reSource;


    public Fragment2(Tttttttest tttttttest) {
        this.tttttttest=tttttttest;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_2,container,false);
        Log.d("zzz","onCreateView Fragment 2");

        if(savedInstanceState!=null)
        {
            reSource=savedInstanceState.getInt("resource");
            view.setBackgroundResource(reSource);

        }


        btnFr2 =view.findViewById(R.id.btnClickfr2);
        btnBack=view.findViewById(R.id.btnBackStack);
        btnFr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundResource(R.color.pink);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tttttttest.getSupportFragmentManager().popBackStack();
                Fragment3 fragment3=new Fragment3(tttttttest);
                FragmentTransaction transaction= tttttttest.getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.mom,fragment3,"fragment3");
//                transaction.addToBackStack("fragment2");
                transaction.commit();

            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        Log.d("qqq","qqq");
        super.onSaveInstanceState(outState);
        outState.putInt("resource",R.color.green);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("zzz","onViewCreated Fragment 2");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("zzz","onResume Fragment 2");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("zzz","onPause Fragment 2");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("zzz","onStop Fragment 2");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("zzz","onStart Fragment 2");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("zzz","onDestroy Fragment 2");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("zzz","onDestroyView Fragment 2");
    }


}
