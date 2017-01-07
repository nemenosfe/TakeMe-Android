package com.pes.takemelegends.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.pes.takemelegends.Fragment.TotsEventsFragment;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.GPSTracker;

import java.util.List;

/**
 * Created by victorgallegobetorz on 18/11/16.
 */

public class EventCheckInAdapter extends RecyclerView.Adapter<EventCheckInAdapter.ViewHolder> {

    private List<String[]> itemsData;
    private static Context context;
    private static GoogleApiClient mGoogleApiClient;

    static final double _eQuatorialEarthRadius = 6378.1370D;
    static final double _d2r = (Math.PI / 180D);

    public static double HaversineInKM(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }



    private static Location getLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }


    public EventCheckInAdapter(List<String[]> itemsData, Context context, GoogleApiClient mGoogleApiClient) {

        this.itemsData = itemsData;
        this.context = context;
        this.mGoogleApiClient = mGoogleApiClient;
    }

    @Override
    public EventCheckInAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_check_in_row, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String[] data = itemsData.get(position);
        if (!data[0].equals("Present")) {
            viewHolder.btnCheckIn.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        else viewHolder.btnCheckIn.setClickable(false);
        viewHolder.eventName.setText(data[1]);
        viewHolder.eventDesc.setText(data[2]);
        viewHolder.eventDate.setText(data[3]);
        viewHolder.takes.setText(data[4]+"\ntakes");
        viewHolder.hours.setText(data[5]);
        viewHolder.id.setText(data[6]);
        viewHolder.lat = data[7];
        viewHolder.lng = data[8];
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView takes, hours, eventName, eventDesc, eventDate, id;
        Button btnCheckIn;
        public String lat, lng;
        private Context context;
        private View itemLayoutView;
        private GPSTracker gps;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.itemLayoutView = itemLayoutView;

            context = itemLayoutView.getContext();
            btnCheckIn = (Button) this.itemLayoutView.findViewById(R.id.btnCheckIn);
            takes = (TextView) itemLayoutView.findViewById(R.id.takes);
            hours = (TextView) itemLayoutView.findViewById(R.id.hours);
            eventName = (TextView) itemLayoutView.findViewById(R.id.eventName);
            eventDesc = (TextView) itemLayoutView.findViewById(R.id.eventDesc);
            eventDate = (TextView) itemLayoutView.findViewById(R.id.eventDate);
            id = (TextView) itemLayoutView.findViewById(R.id.eventId);
            btnCheckIn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
            //Toast.makeText(this.context, "asdf", Toast.LENGTH_SHORT).show();

//            gps = new GPSTracker(this.context);
//
//            // check if GPS enabled
//            if(gps.canGetLocation()){
//
//                double latitude = gps.getLatitude();
//                double longitude = gps.getLongitude();
//
//                // \n is for new line
//                Toast.makeText(this.context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//            }else{
//                // can't get location
//                // GPS or Network is not enabled
//                // Ask user to enable GPS/network in settings
//                gps.showSettingsAlert();
//            }



            //HaversineInKM();

            Location l = getLocation();

            Toast.makeText(this.context, "Check in" + lat + lng + "   g: " + l.getLatitude() + ' ' +  l.getLongitude(), Toast.LENGTH_LONG).show();

        }
    }
}