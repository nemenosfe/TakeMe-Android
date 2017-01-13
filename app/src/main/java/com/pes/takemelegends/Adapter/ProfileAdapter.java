package com.pes.takemelegends.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pes.takemelegends.Fragment.LogrosFragment;
import com.pes.takemelegends.Fragment.RewardsFragment;

public class ProfileAdapter extends FragmentStatePagerAdapter {
    private int numTabs;

    public ProfileAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LogrosFragment tab1 = new LogrosFragment();
                return tab1;
            case 1:
                RewardsFragment tab2 = new RewardsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
