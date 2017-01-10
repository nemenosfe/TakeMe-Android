package com.pes.takemelegends.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.EventCheckInAdapter;
import com.pes.takemelegends.Adapter.EventHistorialAdapter;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MyEventsCheckInFragment extends Fragment {


    public MyEventsCheckInFragment() {
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EventController eventController;
    private GoogleApiClient mGoogleApiClient;
    private View rootView;
    private SharedPreferencesManager sharedPreferencesManager;
    public MyEventsHistorialFragment fragmentHistorial;
    private List<String[]> events;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventController = ControllerFactory.getInstance().getEventController();
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
    }


    public void refresh() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.checkInRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Obteniendo eventos");
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        eventController.getEventsUser(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                events = new ArrayList<>();
                //Present
                try {
                    JSONObject present = response.getJSONObject("present");
                    JSONArray eventArray = present.optJSONArray("events");
                    for (int i = 0; i < eventArray.length(); i++) {
                        JSONObject event = eventArray.getJSONObject(i).getJSONObject("event");
                        if (event != null) {
                            int checkin_done = event.isNull("checkin_done") ? 0 : event.getInt("checkin_done");
                            String title = event.isNull("title") ? "" : event.getString("title");
                            String description = event.isNull("description") ? "" : event.getString("description");
                            String startTime = event.isNull("start_time") ? "" : event.getString("start_time");
                            String id = event.getString("id");
                            String takes = event.isNull("takes") ? "0" : String.valueOf(event.getInt("takes"));
                            Double lat = event.isNull("latitude") ? 0 : Double.valueOf(event.getDouble("latitude"));
                            Double lng = event.isNull("longitude") ? 0 : Double.valueOf(event.getDouble("longitude"));
                            if (checkin_done == 0) events.add(new String[]{"Present", String.valueOf(checkin_done), title, description, startTime, takes, id, String.valueOf(lat), String.valueOf(lng)});
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Future
                try {
                    JSONObject present = response.getJSONObject("future");
                    JSONArray eventArray = present.optJSONArray("events");
                    for (int i = 0; i < eventArray.length(); i++) {
                        JSONObject event = eventArray.getJSONObject(i).getJSONObject("event");
                        if (event != null) {
                            int checkin_done = event.isNull("checkin_done") ? 0 : event.getInt("checkin_done");
                            String title = event.isNull("title") ? "" : event.getString("title");
                            String description = event.isNull("description") ? "No description" : event.getString("description");
                            String startTime = event.isNull("start_time") ? "" : event.getString("start_time");
                            String id = event.getString("id");
                            String takes = event.isNull("takes") ? "0" : String.valueOf(event.getInt("takes"));
                            Double lat = event.isNull("latitude") ? 0 : Double.valueOf(event.getDouble("latitude"));
                            Double lng = event.isNull("longitude") ? 0 : Double.valueOf(event.getDouble("longitude"));
                            if (checkin_done == 0) events.add(new String[]{"Future", title, description, startTime, takes, "Not yet", id, String.valueOf(lat), String.valueOf(lng)});
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                EventCheckInAdapter checkinAdapter = new EventCheckInAdapter(events, getActivity(), mGoogleApiClient);
                recyclerView.setAdapter(checkinAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progressDialog.dismiss();
            }
        }, getActivity(), "50", null);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_events_check_in, container, false);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .build();

            mGoogleApiClient.connect();
        }

        refresh();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedPreferencesManager.needToRefreshView()) {
            EventCheckInAdapter checkinAdapter = new EventCheckInAdapter(events, getActivity(), mGoogleApiClient);
            checkinAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(checkinAdapter);
        }
    }
}
