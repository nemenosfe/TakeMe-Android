package com.pes.takemelegends.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TotsEventsFragment extends Fragment {

    public TotsEventsFragment() {}

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EventController eventController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventController = ControllerFactory.getInstance().getEventController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tots_events, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.totsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        eventController.getAllEvents(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                List<String[]> events = new ArrayList<>();
                EventAdapter totsAdapter = new EventAdapter(events, getActivity());
                recyclerView.setAdapter(totsAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getActivity(), errorResponse.optString("message"), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}
