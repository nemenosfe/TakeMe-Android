package com.pes.takemelegends.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pes.takemelegends.Adapter.EventPageAdapter;
import com.pes.takemelegends.Adapter.MyEventPageAdapter;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment {

    private MyEventsCheckInFragment checkInFragment;
    private MyEventsHistorialFragment historialFragment;
    private SharedPreferencesManager sharedPreferencesManager;

    public MyEventsFragment() {
        checkInFragment = new MyEventsCheckInFragment();
        historialFragment = new MyEventsHistorialFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.my_events));
        setHasOptionsMenu(true);

        sharedPreferencesManager = new SharedPreferencesManager(getActivity());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_events, container, false);

        //TabLayout
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_historic));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.text_check_in));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.main_ambar));
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final PagerAdapter adapter = new MyEventPageAdapter(
                getChildFragmentManager(), tabLayout.getTabCount(), historialFragment, checkInFragment);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getText().toString().equals(getString(R.string.text_historic))) {
                    if (sharedPreferencesManager.getRefreshHistorial()) {
                        sharedPreferencesManager.setRefreshHistorial(false);
                        historialFragment.refresh();
                    }
                }
                else if (tab.getText().toString().equals(getString(R.string.text_check_in))) {
                    if (sharedPreferencesManager.getRefreshCheckin()) {
                        sharedPreferencesManager.setRefreshCheckin(false);
                        checkInFragment.refresh();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
