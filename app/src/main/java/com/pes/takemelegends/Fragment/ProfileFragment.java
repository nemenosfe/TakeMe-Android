package com.pes.takemelegends.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.UserController;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileFragment extends Fragment {

    private TextView name, currentLvl, nextLvl, nExp, totalTakes, totalEvents;
    private RelativeLayout infoUser;
    private PercentRelativeLayout infoExp;
    private ProgressBar expBar;
    private ProfileViewPagerFragment logros;
    private SharedPreferencesManager shared;
    private UserController userController;

    public ProfileFragment() {
        userController = ControllerFactory.getInstance().getUserController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        shared = new SharedPreferencesManager(getContext());

        name = (TextView) rootView.findViewById(R.id.nombre);
        currentLvl = (TextView) rootView.findViewById(R.id.currentLvl);
        nextLvl = (TextView) rootView.findViewById(R.id.nextLvl);
        nExp = (TextView) rootView.findViewById(R.id.nExp);
        totalEvents = (TextView) rootView.findViewById(R.id.totalEvents);
        totalTakes = (TextView) rootView.findViewById(R.id.totalTakes);
        expBar = (ProgressBar) rootView.findViewById(R.id.progressLvl);
        infoUser = (RelativeLayout) rootView.findViewById(R.id.infoUser);
        infoExp = (PercentRelativeLayout) rootView.findViewById(R.id.expLayout);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shared.getDistance() == 500) {
                    shared.setDistance(10000000);
                    name.setText(shared.getUsername() + " *");
                    Toast.makeText(getContext(), getString(R.string.profile_god_mode_on), Toast.LENGTH_SHORT).show();
                }
                else {
                    shared.setDistance(500);
                    name.setText(shared.getUsername());
                    Toast.makeText(getContext(), getString(R.string.profile_god_mode_off), Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                    if (shared.getDistance() == 500) {
                        name.setText(shared.getUsername());
                    }
                    else name.setText(shared.getUsername() + " *");
                    Integer level = user.getInt("level");
                    currentLvl.setText("Nivel " + level);
                    nextLvl.setText("Nivel " + (level+1));
                    nExp.setText(user.getInt("experience") + "/" + user.getInt("experience_of_next_level") + " xp");
                    totalEvents.setText(user.getInt("number_checkins") + "\nevento(s)");
                    totalTakes.setText(user.getInt("takes") + "\ntakes");
                    expBar.setProgress(77);
                    expBar.getProgressDrawable().setColorFilter(
                            getResources().getColor(R.color.main_ambar),
                            android.graphics.PorterDuff.Mode.SRC_IN);
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

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        logros = new ProfileViewPagerFragment();
        transaction.replace(R.id.logrosContainer,logros);
        transaction.addToBackStack("logros");
        transaction.commit();
        return rootView;
    }
}
