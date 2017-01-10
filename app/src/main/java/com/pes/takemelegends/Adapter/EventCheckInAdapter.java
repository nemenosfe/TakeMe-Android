package com.pes.takemelegends.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Activity.EventDetailsActivity;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.EventController;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import cz.msebera.android.httpclient.Header;

import java.util.List;

public class EventCheckInAdapter extends RecyclerView.Adapter<EventCheckInAdapter.ViewHolder> {

    private List<String[]> itemsData;
    private Context context;
    private static GoogleApiClient mGoogleApiClient;
    private SharedPreferencesManager shared;

    static final double _eQuatorialEarthRadius = 6378.1370D;
    static final double _d2r = (Math.PI / 180D);


    public EventCheckInAdapter(List<String[]> itemsData, Context context, GoogleApiClient mGoogleApiClient) {
        this.itemsData = itemsData;
        this.context = context;
        EventCheckInAdapter.mGoogleApiClient = mGoogleApiClient;
        shared = new SharedPreferencesManager(context);
    }

    @Override
    public EventCheckInAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_check_in_row, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String[] data = itemsData.get(position);
        if (data[0].equals("Present") || shared.getDistance()>500) {
            viewHolder.btnCheckIn.setClickable(true);
            viewHolder.btnCheckIn.setBackgroundColor(context.getResources().getColor(R.color.lime));
        }
        else {
            viewHolder.btnCheckIn.setBackgroundColor(context.getResources().getColor(R.color.red));
            viewHolder.btnCheckIn.setAlpha(0.4f);
            viewHolder.btnCheckIn.setClickable(false);
        }
        viewHolder.eventName.setText(data[1]);
        viewHolder.eventName.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.eventDesc.setText(Jsoup.parse(data[2]).text());
        viewHolder.eventDesc.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.eventDate.setText(data[3]);
        viewHolder.takes.setText(data[4]+"\ntakes");
        viewHolder.id.setText(data[6]);
        viewHolder.lat = Double.parseDouble(data[7]);
        viewHolder.lng = Double.parseDouble(data[8]);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView takes, eventName, eventDesc, eventDate, id;
        Button btnCheckIn;
        LinearLayout details;
        public double lat, lng;
        private View itemLayoutView;
        private final Context context;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.itemLayoutView = itemLayoutView;

            this.context = itemLayoutView.getContext();
            btnCheckIn = (Button) this.itemLayoutView.findViewById(R.id.btnCheckIn);
            takes = (TextView) itemLayoutView.findViewById(R.id.takes);
            eventName = (TextView) itemLayoutView.findViewById(R.id.eventName);
            eventDesc = (TextView) itemLayoutView.findViewById(R.id.eventDesc);
            eventDate = (TextView) itemLayoutView.findViewById(R.id.eventDate);
            id = (TextView) itemLayoutView.findViewById(R.id.eventId);
            details = (LinearLayout) itemLayoutView.findViewById(R.id.event_details_layout);
            btnCheckIn.setOnClickListener(this);
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventDetailsActivity.class);
                    intent.putExtra("id", id.getText().toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        public static double HaversineInKM(double lat1, double long1, double lat2, double long2) {
            double dlong = (long2 - long1) * _d2r;
            double dlat = (lat2 - lat1) * _d2r;
            double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
                    * Math.pow(Math.sin(dlong / 2D), 2D);
            double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
            double d = _eQuatorialEarthRadius * c;
            return d * 1000;
        }

        private Location getLocation() {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

        @Override
        public void onClick(View view) {
            Location l = getLocation();
            double distance = HaversineInKM(l.getLatitude(), l.getLongitude(), lat, lng);
            final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(context);
            Integer minDistance = sharedPreferencesManager.getDistance();
            if (distance < minDistance) {
                EventController eventController = ControllerFactory.getInstance().getEventController();
                eventController.doCheckIn(new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        sharedPreferencesManager.setRefreshView(true);
                        sharedPreferencesManager.increaseCheckInEvents();
                        try {
                            if (response.getJSONObject("attendance").getJSONObject("achievement") != null) {
                                String description = response.getJSONObject("attendance").getJSONObject("achievement").getString("description");
                                String name = response.getJSONObject("attendance").getJSONObject("achievement").getString("name");

                                new AlertDialog.Builder(context).setTitle("¡Enhorabuena! " + description)
                                        .setMessage("Has desbloqueado el siguiente logro: " + name)
                                        .setCancelable(false)
                                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                }).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        btnCheckIn.setText(context.getString(R.string.error));
                    }
                },context, id.getText().toString());
            }
            else {
                new AlertDialog.Builder(context)
                    .setTitle("Estás demasiado lejos")
                    .setMessage("Para hacer check-in tienes que estar a menos de 500 metros del evento.")
                    .setCancelable(false)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
            }
        }
    }
}