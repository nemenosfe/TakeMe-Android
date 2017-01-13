package com.pes.takemelegends.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.EventCheckInAdapter;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;

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
    public MyEventsHistorialFragment fragmentHistorial;
    private List<String[]> events;
    private SwipeRefreshLayout swipeContainer;
    private EventCheckInAdapter eventCheckInAdapter;
    private MyEventsCheckInFragment instance;
    private TextView noResults;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventController = ControllerFactory.getInstance().getEventController();
        instance = this;
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
                            Double lat = event.isNull("latitude") ? 0 : event.getDouble("latitude");
                            Double lng = event.isNull("longitude") ? 0 : event.getDouble("longitude");
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
                            Double lat = event.isNull("latitude") ? 0 : event.getDouble("latitude");
                            Double lng = event.isNull("longitude") ? 0 : event.getDouble("longitude");
                            if (checkin_done == 0) events.add(new String[]{"Future", title, description, startTime, takes, "Not yet", id, String.valueOf(lat), String.valueOf(lng)});
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                eventCheckInAdapter = new EventCheckInAdapter(events, getActivity(), mGoogleApiClient, instance);
                eventCheckInAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(eventCheckInAdapter);

                if (events.size()>0) {
                    noResults.setVisibility(View.GONE);
                }
                else noResults.setVisibility(TextView.VISIBLE);
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

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        noResults = (TextView) rootView.findViewById(R.id.no_results);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.main_ambar);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .build();

            mGoogleApiClient.connect();
        }

        refresh();

        return rootView;
    }
}
