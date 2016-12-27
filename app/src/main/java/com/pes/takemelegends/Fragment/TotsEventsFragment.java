package com.pes.takemelegends.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;

import org.json.JSONArray;
import org.json.JSONException;
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

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Obteniendo eventos");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        eventController.getAllEvents(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                List<String[]> events = new ArrayList<>();
                JSONArray eventArray = response.optJSONArray("events");
                for (int i = 0; i < eventArray.length(); i++) {
                    try {
                        JSONObject event = eventArray.getJSONObject(i).getJSONObject("event");
                        //TODO: Obtenir categoria de la API
                        JSONObject cat = event.getJSONObject("categories");
                        String category = cat.isNull("category") ? "" : cat.getJSONArray("category").getJSONObject(0).getString("id");
                        String title = event.isNull("title") ? "" : event.getString("title");
                        String startTime = event.isNull("start_time") ? "" : event.getString("start_time");
                        String id = event.getString("id");
                        String image = "http://www.hutterites.org/wp-content/uploads/2012/03/placeholder.jpg";
                        if (!event.isNull("images")) {
                            JSONObject imageObject = event.getJSONObject("images");
                            if (!imageObject.isNull("medium")) image = imageObject.getJSONObject("medium").getString("url");
                            else if (!imageObject.isNull("thumb")) image = imageObject.getJSONObject("thumb").getString("url");
                        }
                        String attendances = String.valueOf(event.getInt("number_attendances"));
                        String takes = event.isNull("takes") ? "0" : String.valueOf(event.getInt("takes"));
                        events.add(new String[]{category, title, "Spain", startTime, id, image, attendances, takes});
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                EventAdapter totsAdapter = new EventAdapter(events, getActivity());
                recyclerView.setAdapter(totsAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getActivity(), errorResponse.optString("message"), Toast.LENGTH_SHORT).show();
                Log.v("APIerror", errorResponse.optString("message"));
            }
        }, null, null, "Future", "Barcelona", null, null, null);
        //'category','keywords','date','location','within','page_size','page_number'

        return rootView;
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

}
