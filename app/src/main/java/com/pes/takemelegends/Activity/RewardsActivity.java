package com.pes.takemelegends.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.MarketPerLevelAdapter;
import com.pes.takemelegends.Assets.DividerItemDecorator;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.RewardController;
import com.pes.takemelegends.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import cz.msebera.android.httpclient.Header;

public class RewardsActivity extends AppCompatActivity {

    private TextView userTV, lvlTV, takesTV;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ImageButton backBtn;
    private RewardController rewardController;
    private HashMap<Integer,ArrayList<JSONObject>> rewardsbyLVL;


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
        final Intent i = getIntent();
        rewardController = ControllerFactory.getInstance().getRewardController();
        rewardController.getRewardsByLvl(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray rewardsArray = response.optJSONArray("rewards");
                for (int i = 0; i < rewardsArray.length(); i++) {
                    try {
                        JSONObject reward = rewardsArray.getJSONObject(i);
                        rewardsbyLVL.get(reward.getInt("level")).add(reward);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                int lvl = i.getIntExtra("level",0);
                ArrayList<JSONObject> rewards = new ArrayList<>();
                if( lvl == 0) Toast.makeText(getApplicationContext(),"Fallo de api, level = 0.",Toast.LENGTH_LONG);
                else rewards = rewardsbyLVL.get(i.getIntExtra("level",0));

                linearLayoutManager = new LinearLayoutManager(getApplication());
                recyclerView.setLayoutManager(linearLayoutManager);

                DividerItemDecorator dividerItemDecoration = new DividerItemDecorator(recyclerView.getContext(),
                        linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);

                MarketPerLevelAdapter perLvlAdapter = new MarketPerLevelAdapter(rewards);

                recyclerView.setAdapter(perLvlAdapter);

                recyclerView.setItemAnimator(new DefaultItemAnimator());

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("getRewardsFail","FAILURE");
            }
        },getApplicationContext());


        userTV.setText("oscarSeGa");
        lvlTV.setText(Integer.toString(i.getIntExtra("level",0)));
        takesTV.setText("777 takes");
    }
}
