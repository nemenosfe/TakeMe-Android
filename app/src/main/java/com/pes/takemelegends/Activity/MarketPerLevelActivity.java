package com.pes.takemelegends.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketPerLevelActivity extends Fragment {


    public MarketPerLevelActivity() {
        // Required empty public constructor
    }

    private View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_market_per_level, container, false);

        return rootview;
    }

}
