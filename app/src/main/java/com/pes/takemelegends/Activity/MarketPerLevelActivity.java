package com.pes.takemelegends.Activity;


import android.app.Activity;
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
public class MarketPerLevelActivity extends Activity {


    public MarketPerLevelActivity() {
        // Required empty public constructor
    }

    private TextView userTV, lvlTV, takesTV;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_per_lvl_row);

        userTV = (TextView) findViewById(R.id.username);
        lvlTV = (TextView) findViewById(R.id.currentLvl);
        takesTV = (TextView) findViewById(R.id.totalTakes);
        recyclerView = (RecyclerView) findViewById(R.id.rewardsRecyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
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
    }

}
