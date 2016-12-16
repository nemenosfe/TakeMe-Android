package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pes.takemelegends.Fragment.TotsEventsFragment;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.GPSTracker;

/**
 * Created by victorgallegobetorz on 18/11/16.
 */

public class EventCheckInAdapter extends RecyclerView.Adapter<EventCheckInAdapter.ViewHolder> {

    private String[] itemsData;
    private Context context;
    private final String imageUrl = "http://www.hutterites.org/wp-content/uploads/2012/03/placeholder.jpg";

    public EventCheckInAdapter(String[] itemsData, Context context) {

        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_check_in_row, parent, false);
        EventCheckInAdapter.ViewHolder viewHolder = new EventCheckInAdapter.ViewHolder(itemLayoutView, this.context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private Context context;
        private View itemLayoutView;
        private GPSTracker gps;

        ViewHolder(View itemLayoutView, Context context) {
            super(itemLayoutView);
            this.context = context;
            this.itemLayoutView = itemLayoutView;

            Button btnCheckIn = (Button) this.itemLayoutView.findViewById(R.id.btnCheckIn);
            btnCheckIn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
            //Toast.makeText(this.context, "asdf", Toast.LENGTH_SHORT).show();

            gps = new GPSTracker(this.context);

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                Toast.makeText(this.context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }

        }
    }
}