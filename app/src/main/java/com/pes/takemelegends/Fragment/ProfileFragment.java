package com.pes.takemelegends.Fragment;

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
    private SharedPreferencesManager shared;
    private Boolean godMode;

    public ProfileFragment() {
        godMode = false;
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

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!godMode) {
                    shared.setDistance(100.0);
                    name.setText(name.getText() + " *");
                    godMode = true;
                }
                else {
                    shared.setDistance(0.5);
                    name.setText(shared.getUsername());
                    godMode = false;
                }
            }
        });

        name.setText(shared.getUsername());
        currentLvl.setText("Nivel " + shared.getCurrentLevel());
        nextLvl.setText("Nivel " + (shared.getCurrentLevel()+1));
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
