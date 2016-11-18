package com.pes.takemelegends.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pes.takemelegends.Fragment.AsistireFragment;
import com.pes.takemelegends.Fragment.MyEventsCheckInFragment;
import com.pes.takemelegends.Fragment.MyEventsFragment;
import com.pes.takemelegends.Fragment.MyEventsHistorialFragment;
import com.pes.takemelegends.Fragment.RecomenatsFragment;
import com.pes.takemelegends.Fragment.TotsEventsFragment;

/**
 * Created by Oscar on 22/10/2016.
 */

public class MyEventPageAdapter extends FragmentStatePagerAdapter{
    private int numTabs;

    public MyEventPageAdapter(FragmentManager fm, int numOfTabs) {
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
                MyEventsHistorialFragment tab1 = new MyEventsHistorialFragment();
                return tab1;
            case 1:
                MyEventsCheckInFragment tab2 = new MyEventsCheckInFragment();
                return tab2;
            default:
                return null;
        }
    }
}
