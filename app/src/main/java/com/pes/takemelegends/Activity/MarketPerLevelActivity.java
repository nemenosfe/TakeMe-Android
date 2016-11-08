package com.pes.takemelegends.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.takemelegends.Adapter.MarketAdapter;
import com.pes.takemelegends.Adapter.MarketPerLevelAdapter;
import com.pes.takemelegends.Assets.DividerItemDecorator;
import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketPerLevelActivity extends Fragment {


    public MarketPerLevelActivity() {
        // Required empty public constructor
    }

    private View rootview;
    private TextView userTV, lvlTV, takesTV;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_market_per_level, container, false);

        userTV = (TextView) rootview.findViewById(R.id.username);
        lvlTV = (TextView) rootview.findViewById(R.id.currentLvl);
        takesTV = (TextView) rootview.findViewById(R.id.totalTakes);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.logrosRecyclerView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecorator dividerItemDecoration = new DividerItemDecorator(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        String[] dummy = {"Clauer RCDE", "description del cauer rcde", "300 takes"};
        MarketPerLevelAdapter perLvlAdapter = new MarketPerLevelAdapter(dummy);

        recyclerView.setAdapter(perLvlAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        userTV.setText("oscarSeGa");
        lvlTV.setText("Nivel 3");
        takesTV.setText("777 takes");

        return rootview;
    }

}
