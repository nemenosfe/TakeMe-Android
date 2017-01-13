package com.pes.takemelegends.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pes.takemelegends.Fragment.MyEventsCheckInFragment;
import com.pes.takemelegends.Fragment.MyEventsHistorialFragment;

public class MyEventPageAdapter extends FragmentStatePagerAdapter{
    private final int numTabs;
    private final MyEventsHistorialFragment tab1;
    private final MyEventsCheckInFragment tab2;

    public MyEventPageAdapter(FragmentManager fm, int numOfTabs, MyEventsHistorialFragment tab1, MyEventsCheckInFragment tab2) {
        super(fm);
        this.numTabs = numOfTabs;
        this.tab1 = tab1;
        this.tab2 = tab2;
        tab2.fragmentHistorial = tab1;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return tab1;
            case 1:
                return tab2;
            default:
                return null;
        }
    }
}
