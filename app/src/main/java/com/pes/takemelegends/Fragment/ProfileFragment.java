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
import com.pes.takemelegends.Utils.SharedPreferencesManager;

public class ProfileFragment extends Fragment {

    private TextView name, currentLvl, nextLvl, nExp, totalTakes, totalEvents;
    private ProgressBar expBar;
    private ProfileViewPagerFragment logros;
    private FloatingActionButton mFab;
    private SharedPreferencesManager shared;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        shared = new SharedPreferencesManager(getActivity().getApplicationContext());

        name = (TextView) rootView.findViewById(R.id.nombre);
        currentLvl = (TextView) rootView.findViewById(R.id.currentLvl);
        nextLvl = (TextView) rootView.findViewById(R.id.nextLvl);
        nExp = (TextView) rootView.findViewById(R.id.nExp);
        totalEvents = (TextView) rootView.findViewById(R.id.totalEvents);
        totalTakes = (TextView) rootView.findViewById(R.id.totalTakes);
        expBar = (ProgressBar) rootView.findViewById(R.id.progressLvl);

        name.setText(shared.getUsername());
        currentLvl.setText("lvl " + shared.getCurrentLevel());
        nextLvl.setText("lvl " + (shared.getCurrentLevel()+1));
        nExp.setText(shared.getCurrentExperience()+"/api px");
        totalEvents.setText("api\neventos");
        totalTakes.setText(shared.getTotalTakes()+"\ntakes");
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
