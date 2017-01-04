package com.pes.takemelegends.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.takemelegends.Adapter.MarketAdapter;
import com.pes.takemelegends.Adapter.LogroAdapter;
import com.pes.takemelegends.Assets.DividerItemDecorator;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.RewardController;
import com.pes.takemelegends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment {

    //http://stackoverflow.com/questions/24471109/recyclerview-onclick
    public MarketFragment() {
        // Required empty public constructor
    }

    private View rootview;
    private TextView userTV, lvlTV, takesTV;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.exchange));
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_market, container, false);

        userTV = (TextView) rootview.findViewById(R.id.username);
        lvlTV = (TextView) rootview.findViewById(R.id.currentLvl);
        takesTV = (TextView) rootview.findViewById(R.id.totalTakes);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.logrosRecyclerView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecorator dividerItemDecoration = new DividerItemDecorator(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Integer[] dummy = {1, 2, 3, 4, 5, 6, 7, 8};
        MarketAdapter marketAdapter = new MarketAdapter(dummy);

        recyclerView.setAdapter(marketAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        userTV.setText("oscarSeGa");
        lvlTV.setText("Nivel 3");
        takesTV.setText("777 takes");

        return rootview;
    }

}
