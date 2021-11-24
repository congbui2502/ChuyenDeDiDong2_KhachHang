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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.android.quanlybanhang.R;

public class Fragment1 extends Fragment {
    Button btnFr1,btnNextFra;
    Tttttttest mainActivity;


    public Fragment1(Tttttttest mainActivity) {
        this.mainActivity=mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_1,container,false);
        Log.d("zzz","Create Fragment 1");

        btnFr1=view.findViewById(R.id.btnClickfr1);
        btnNextFra=view.findViewById(R.id.btnNextFragment);
        btnFr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundResource(R.color.green);
            }
        });


        btnNextFra.setOnClickListener(new View.OnClickListener() {
            FragmentTransaction transaction= mainActivity.getSupportFragmentManager().beginTransaction();
            @Override
            public void onClick(View v) {
                if(mainActivity.getSupportFragmentManager().findFragmentByTag("fragment2")==null)
                {
                    Fragment2 fragment2=new Fragment2(mainActivity);

                    transaction.add(R.id.mom,fragment2,"fragment2");
//                    transaction.addToBackStack("fragment1");
                    transaction.commit();
                }else {
                    mainActivity.getSupportFragmentManager().popBackStack("fragment1", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }



            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("zzz","onViewCreated Fragment 1");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("zzz","Resume Fragment 1");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("zzz","onPause Fragment 1");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("zzz","onStop Fragment 1");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("zzz","onStart Fragment 1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("zzz","onDestroy Fragment 1");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("zzz","onDestroyView Fragment 1");
    }
}
