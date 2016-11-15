package com.pes.takemelegends.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsistireFragment extends Fragment {


    public AsistireFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_asistire, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.totsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] dummy = {"Festival", "BOOM Festival 2016","Portu" +
                "gal", "16/10/2016 - 20:45h"};
        EventAdapter totsAdapter = new EventAdapter(dummy, getActivity());

        recyclerView.setAdapter(totsAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}
