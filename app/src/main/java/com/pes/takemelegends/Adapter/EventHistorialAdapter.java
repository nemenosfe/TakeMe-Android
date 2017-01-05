package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pes.takemelegends.Activity.EventDetailsActivity;
import com.pes.takemelegends.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by victorgallegobetorz on 15/11/16.
 */

public class EventHistorialAdapter extends RecyclerView.Adapter<EventHistorialAdapter.ViewHolder> {

    private List<String[]> itemsData;
    private Context context;

    public EventHistorialAdapter(List<String[]>  itemsData, Context context) {

        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public EventHistorialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_histrorial_row, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String[] data = itemsData.get(position);
        if (data[0].equals("1")) {
            viewHolder.status.setText(context.getString(R.string.text_was_at_time)+"\nFalta hora en API!");
            viewHolder.takes.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        else {
            viewHolder.status.setText(context.getString(R.string.text_no_reward_obtained));
            viewHolder.takes.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
        viewHolder.eventName.setText(data[1]);
        viewHolder.eventDesc.setText(data[2]);
        viewHolder.eventDate.setText(data[3]);
        viewHolder.takes.setText(data[4]+"\ntakes");
        viewHolder.id.setText(data[5]);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView takes, status, eventName, eventDesc, eventDate, id;
        private final Context context;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            context = itemLayoutView.getContext();
            takes = (TextView) itemLayoutView.findViewById(R.id.takes);
            status = (TextView) itemLayoutView.findViewById(R.id.status);
            eventName = (TextView) itemLayoutView.findViewById(R.id.eventName);
            eventDesc = (TextView) itemLayoutView.findViewById(R.id.eventDesc);
            eventDate = (TextView) itemLayoutView.findViewById(R.id.eventDate);
            id = (TextView) itemLayoutView.findViewById(R.id.eventId);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, EventDetailsActivity.class);
            intent.putExtra("id", id.getText().toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
