package com.pes.takemelegends.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.Adapter.EventHistorialAdapter;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
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
public class MyEventsHistorialFragment extends Fragment {

    public MyEventsHistorialFragment() {
        // Required empty public constructor
    }

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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_events_historial, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.historialRecyclerView);
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
                List<String[]> events = new ArrayList<>();
                //Present
                try {
                    JSONObject present = response.getJSONObject("past");
                    JSONArray eventArray = present.optJSONArray("events");
                    for (int i = 0; i < eventArray.length(); i++) {
                        JSONObject event = eventArray.getJSONObject(i).getJSONObject("event");
                        if (event != null) {
                            JSONObject cat = event.getJSONObject("categories");
                            String checkin_done = event.isNull("checkin_done") ? "" : event.getString("checkin_done");
                            String title = event.isNull("title") ? "" : event.getString("title");
                            String description = event.isNull("description") ? "" : event.getString("description");
                            String startTime = event.isNull("start_time") ? "" : event.getString("start_time");
                            String id = event.getString("id");
                            String takes = event.isNull("takes") ? "0" : String.valueOf(event.getInt("takes"));
                            events.add(new String[]{checkin_done, title, description, startTime, takes, id});
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                EventHistorialAdapter historialAdapter = new EventHistorialAdapter(events, getActivity());
                recyclerView.setAdapter(historialAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progressDialog.dismiss();
            }
        }, getActivity(), "50", null);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}
