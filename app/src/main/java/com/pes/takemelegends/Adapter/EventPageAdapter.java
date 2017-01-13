package com.pes.takemelegends.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pes.takemelegends.Fragment.AsistireFragment;
import com.pes.takemelegends.Fragment.RecomenatsFragment;
import com.pes.takemelegends.Fragment.TotsEventsFragment;

public class EventPageAdapter extends FragmentStatePagerAdapter{
    private int numTabs;

    public EventPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numTabs = numOfTabs;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TotsEventsFragment tab1 = new TotsEventsFragment();
                return tab1;
            case 1:
                AsistireFragment tab2 = new AsistireFragment();
                return tab2;
            case 2:
                RecomenatsFragment tab3 = new RecomenatsFragment();
                return tab3;
            default:
                return null;
        }
    }
}
