package com.pes.takemelegends.Fragment;


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

import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.Adapter.LogroAdapter;
import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogrosFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton mFab;

    public LogrosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_logros, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.logrosRecyclerView);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fabBtn);

        mFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.main_ambar)));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] dummy = {"Festival", "BOOM Festival 2016","Portugal", "16/10/2016 - 20:45h"};
        LogroAdapter logrosAdapter = new LogroAdapter(dummy);

        recyclerView.setAdapter(logrosAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}
