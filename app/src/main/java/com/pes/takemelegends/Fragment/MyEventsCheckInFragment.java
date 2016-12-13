package com.pes.takemelegends.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pes.takemelegends.Adapter.EventCheckInAdapter;
import com.pes.takemelegends.Adapter.EventHistorialAdapter;
import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsCheckInFragment extends Fragment {


    public MyEventsCheckInFragment() {
        // Required empty public constructor
    }


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_events_check_in, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.checkInRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] dummy = {"Festival", "BOOM Festival 2016","Portugal", "16/10/2016 - 20:45h"};
        EventCheckInAdapter totsAdapter = new EventCheckInAdapter(dummy, getActivity());

        recyclerView.setAdapter(totsAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


//        Button buttonDirecte = (Button) rootView.findViewById(R.id.checkIn);
//        buttonDirecte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return rootView;
    }

}
