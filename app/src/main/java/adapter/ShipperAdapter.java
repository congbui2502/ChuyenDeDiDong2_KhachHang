package adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fragment.CaNhanFragment;
import fragment.HistoryFragment;
import fragment.HomeFragment;

public class ShipperAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Home","Lịch sử","Cá nhân"};
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private CaNhanFragment caNhanFragment;
    private Context context;
    public ShipperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);


    }
    public void getData(Context context){
        this.context=context;
        homeFragment =new HomeFragment();
        historyFragment = new HistoryFragment();
        historyFragment.setData(context);
        caNhanFragment = new CaNhanFragment();
        homeFragment.setData(context);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return homeFragment;
        }else if (position==1){
            return historyFragment;
        }
        else return caNhanFragment;
    }
    @Override
    public int getCount() {
        return listTab.length;
    }
    public CharSequence getPageTitle(int potision)
    {
        return listTab[potision];
    }
}
