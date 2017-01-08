package com.pes.takemelegends.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
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

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Activity.LoginActivity;
import com.pes.takemelegends.Activity.MainActivity;
import com.pes.takemelegends.Activity.PreferencesActivity;
import com.pes.takemelegends.Activity.RewardsActivity;
import com.pes.takemelegends.Adapter.LogroAdapter;
import com.pes.takemelegends.Adapter.RewardsAdapter;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.RewardController;
import com.pes.takemelegends.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {


    public RewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton mFab;
    private RewardController rewardController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_rewards, container, false);

        rewardController = ControllerFactory.getInstance().getRewardController();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.logrosRecyclerView);
        mFab = (FloatingActionButton) rootView.findViewById(R.id.fabBtn);

        mFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.main_ambar)));
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).exchangeTakes();
                getActivity().getFragmentManager().popBackStack();
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Obteniendo listado de recomensas");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        rewardController.getUserRewards(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                List<String[]> rewards = new ArrayList<>();
                JSONArray eventArray = response.optJSONArray("rewards");
                for (int i = 0; i < eventArray.length(); i++) {
                    try {
                        JSONObject achievement = eventArray.getJSONObject(i).getJSONObject("reward");
                        String name = achievement.getString("name");
                        String description = achievement.getString("description");
                        String takes = achievement.getString("takes");
                        rewards.add(new String[]{name, description, takes});
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RewardsAdapter rewardsAdapter = new RewardsAdapter(getActivity(), rewards);
                recyclerView.setAdapter(rewardsAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progressDialog.dismiss();
            }
        }, getActivity());

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}
