package com.pes.takemelegends.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pes.takemelegends.Adapter.MarketPerLevelAdapter;
import com.pes.takemelegends.Assets.DividerItemDecorator;
import com.pes.takemelegends.R;

public class RewardsActivity extends AppCompatActivity {

    private TextView userTV, lvlTV, takesTV;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        userTV = (TextView) findViewById(R.id.username);
        lvlTV = (TextView) findViewById(R.id.currentLvl);
        takesTV = (TextView) findViewById(R.id.totalTakes);
        recyclerView = (RecyclerView) findViewById(R.id.rewardsRecyclerView);
        backBtn= (ImageButton) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
                finish();
            }
        });

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
