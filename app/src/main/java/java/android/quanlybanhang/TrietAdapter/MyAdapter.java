package java.android.quanlybanhang.TrietAdapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.android.quanlybanhang.Activity.BepActivity;


public class MyAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    private BepActivity bepActivity;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs, BepActivity
                      bepActivity) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.bepActivity=bepActivity;
    }



    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TableFragment tableFragment = new TableFragment();
                return tableFragment;
//            case 1:
//                MonFragment monFragment = new MonFragment(bepActivity);
//                return monFragment;

            default:
                TableFragment tableFragment1 = new TableFragment();

                return tableFragment1;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
