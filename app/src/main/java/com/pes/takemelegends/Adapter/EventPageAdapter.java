package com.pes.takemelegends.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pes.takemelegends.Fragment.AsistireFragment;
import com.pes.takemelegends.Fragment.RecomenatsFragment;
import com.pes.takemelegends.Fragment.TotsEventsFragment;

public class EventPageAdapter extends FragmentStatePagerAdapter{
    private final int numTabs;

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
                return new TotsEventsFragment();
            case 1:
                return new AsistireFragment();
            case 2:
                return new RecomenatsFragment();
            default:
                return null;
        }
    }
}
