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

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Adapter.EventAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AsistireFragment extends Fragment {


    public AsistireFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EventController eventController;
    private SharedPreferencesManager sharedPreferences;
    private List<String[]> events;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventController = ControllerFactory.getInstance().getEventController();
        sharedPreferences = new SharedPreferencesManager(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedPreferences.getNeedAttendanceUpdate())updateRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_asistire, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.totsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        updateRecyclerView();
        return rootView;
    }

    private void updateRecyclerView() {
        sharedPreferences.setAttendanceUpdate(false);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Obteniendo eventos");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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
                            events.add(new String[]{category, title, "España", startTime, id, image, attendances, takes});
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
                        if (event != null){
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
                            events.add(new String[]{category, title, "España", startTime, id, image, attendances, takes});
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                EventAdapter totsAdapter = new EventAdapter(events, getActivity());
                totsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(totsAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progressDialog.dismiss();
            }
        }, getActivity(), "50", null);
        //context, page_size, page_number

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
