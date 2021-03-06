package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.takemelegends.Activity.EventDetailsActivity;
import com.pes.takemelegends.R;

import org.jsoup.Jsoup;

import java.util.List;

public class EventHistorialAdapter extends RecyclerView.Adapter<EventHistorialAdapter.ViewHolder> {

    private final List<String[]> itemsData;
    private final Context context;

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
            viewHolder.status.setText(context.getString(R.string.text_was_at_time));
            viewHolder.takes.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        else {
            viewHolder.status.setText(context.getString(R.string.text_no_reward_obtained));
            viewHolder.takes.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
        viewHolder.eventName.setText(data[1]);
        viewHolder.eventName.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.eventDesc.setText(Jsoup.parse(data[2]).text());
        viewHolder.eventDesc.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.eventDate.setText(data[3]);
        viewHolder.status_hour.setText(data[6]);
        viewHolder.takes.setText(data[4]+"\ntakes");
        viewHolder.id.setText(data[5]);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView takes;
        final TextView status;
        final TextView status_hour;
        final TextView eventName;
        final TextView eventDesc;
        final TextView eventDate;
        final TextView id;
        private final Context context;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            context = itemLayoutView.getContext();
            takes = (TextView) itemLayoutView.findViewById(R.id.takes);
            status = (TextView) itemLayoutView.findViewById(R.id.status);
            status_hour = (TextView) itemLayoutView.findViewById(R.id.status_hour);
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
