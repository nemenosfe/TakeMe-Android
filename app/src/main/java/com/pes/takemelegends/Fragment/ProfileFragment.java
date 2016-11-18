package com.pes.takemelegends.Fragment;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pes.takemelegends.R;

public class ProfileFragment extends Fragment {

    private TextView name, currentLvl, nextLvl, nExp, totalTakes, totalEvents;
    private ProgressBar expBar;
    private ProfileViewPagerFragment logros;
    private FloatingActionButton mFab;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        name = (TextView) rootView.findViewById(R.id.nombre);
        currentLvl = (TextView) rootView.findViewById(R.id.currentLvl);
        nextLvl = (TextView) rootView.findViewById(R.id.nextLvl);
        nExp = (TextView) rootView.findViewById(R.id.nExp);
        totalEvents = (TextView) rootView.findViewById(R.id.totalEvents);
        totalTakes = (TextView) rootView.findViewById(R.id.totalTakes);
        expBar = (ProgressBar) rootView.findViewById(R.id.progressLvl);

        name.setText("Óscar Serrano García");
        currentLvl.setText("lvl3");
        nextLvl.setText("lvl4");
        nExp.setText("777/1000 px");
        totalEvents.setText("33\neventos");
        totalTakes.setText("777\ntakes");
        expBar.setProgress(77);
        expBar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.main_ambar),
                android.graphics.PorterDuff.Mode.SRC_IN);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        logros = new ProfileViewPagerFragment();
        transaction.replace(R.id.logrosContainer,logros);
        transaction.addToBackStack("logros");
        transaction.commit();
        return rootView;
    }

}
