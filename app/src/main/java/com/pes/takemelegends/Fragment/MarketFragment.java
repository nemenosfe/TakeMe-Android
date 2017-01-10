package com.pes.takemelegends.Fragment;


import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.MarketAdapter;
import com.pes.takemelegends.Adapter.LogroAdapter;
import com.pes.takemelegends.Assets.DividerItemDecorator;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.RewardController;
import com.pes.takemelegends.Controller.UserController;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MarketFragment extends Fragment {

    private View rootview;
    private TextView userTV, lvlTV, takesTV;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SharedPreferencesManager shared;
    private UserController userController;

    public MarketFragment() {
        userController = ControllerFactory.getInstance().getUserController();
    }

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
        shared = new SharedPreferencesManager(getActivity().getApplicationContext());

        Integer[] dummy = {1, 2, 3, 4, 5, 6, 7, 8};
        MarketAdapter marketAdapter = new MarketAdapter(dummy,shared.getCurrentLevel());

        recyclerView.setAdapter(marketAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Obteniendo datos");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        userController.getUserData(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject user = response.getJSONObject("user");
                    Integer level = user.getInt("level");
                    userTV.setText(shared.getUsername());
                    lvlTV.setText("Nivel "+level);
                    takesTV.setText(user.getInt("takes") + " takes");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressDialog.dismiss();
                Toast.makeText(getContext(), getString(R.string.profile_error), Toast.LENGTH_SHORT).show();
            }
        }, getContext(), shared.getUserId(), shared.getUserProvider());

        return rootview;
    }

}
