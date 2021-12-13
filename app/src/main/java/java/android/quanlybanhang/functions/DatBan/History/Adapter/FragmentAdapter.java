package java.android.quanlybanhang.functions.DatBan.History.Adapter;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.android.quanlybanhang.functions.DatBan.History.DonHangFragment;
import java.android.quanlybanhang.functions.DatBan.History.DonHangHuyFrament;
import java.android.quanlybanhang.functions.DatBan.History.DonHangNhanFrament;

public class FragmentAdapter extends FragmentStateAdapter implements SwipeRefreshLayout.OnRefreshListener{
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DonHangFragment();
            case 1:
                return new DonHangNhanFrament();
            case 2:
                return new DonHangHuyFrament();

        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onRefresh() {

    }
}

