package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pes.takemelegends.Fragment.TotsEventsFragment;
import com.pes.takemelegends.R;
import com.pes.takemelegends.Utils.GPSTracker;

import java.util.List;

/**
 * Created by victorgallegobetorz on 18/11/16.
 */

public class EventCheckInAdapter extends RecyclerView.Adapter<EventCheckInAdapter.ViewHolder> {

    private List<String[]> itemsData;
    private Context context;

    public EventCheckInAdapter(List<String[]> itemsData, Context context) {

        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public EventCheckInAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_check_in_row, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String[] data = itemsData.get(position);
        if (data[0].equals("Present")) {
            viewHolder.btnCheckIn.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        else viewHolder.btnCheckIn.setClickable(false);
        viewHolder.eventName.setText(data[1]);
        viewHolder.eventDesc.setText(data[2]);
        viewHolder.eventDate.setText(data[3]);
        viewHolder.takes.setText(data[4]+"\ntakes");
        viewHolder.hours.setText(data[5]);
        viewHolder.id.setText(data[6]);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView takes, hours, eventName, eventDesc, eventDate, id;
        Button btnCheckIn;
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