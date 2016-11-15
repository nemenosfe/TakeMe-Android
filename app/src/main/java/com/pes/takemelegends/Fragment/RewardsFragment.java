package com.pes.takemelegends.Fragment;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pes.takemelegends.Activity.LoginActivity;
import com.pes.takemelegends.Activity.MainActivity;
import com.pes.takemelegends.Activity.PreferencesActivity;
import com.pes.takemelegends.Activity.RewardsActivity;
import com.pes.takemelegends.Adapter.LogroAdapter;
import com.pes.takemelegends.Adapter.RewardsAdapter;
import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {


    public RewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton mFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_rewards, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.logrosRecyclerView);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fabBtn);

        mFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.main_ambar)));
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).exchangeTakes();
                getActivity().getFragmentManager().popBackStack();
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] dummy = {"Romo", "Romo", "Romo"};
        RewardsAdapter logrosAdapter = new RewardsAdapter(dummy);

        recyclerView.setAdapter(logrosAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}
