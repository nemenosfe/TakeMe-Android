package com.pes.takemelegends.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotsEventsFragment extends Fragment {


    public TotsEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tots_events, container, false);
    }

}
