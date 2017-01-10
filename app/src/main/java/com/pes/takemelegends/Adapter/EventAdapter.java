package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pes.takemelegends.Activity.EventDetailsActivity;
import com.pes.takemelegends.Activity.PreferencesActivity;
import com.pes.takemelegends.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Oscar on 22/10/2016.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<String[]> itemsData;
    private Context context;

    public EventAdapter(List<String[]> itemsData, Context context) {
        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String[] data = itemsData.get(position);
        viewHolder.typeTV.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.typeTV.setText(capitalize(data[0]));
        viewHolder.titleTV.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.titleTV.setText(data[1]);
        viewHolder.location.setText(data[2]);
        viewHolder.date.setText(data[3]);
        viewHolder.id.setText(data[4]);
        if(data[5].equals("http://www.hutterites.org/wp-content/uploads/2012/03/placeholder.jpg")){
            String placeholder = "ph_"+data[0];
            int id = context.getResources().getIdentifier(placeholder, "drawable", context.getPackageName());
            if (id!=0) viewHolder.eventImage.setImageResource(id);
            else Picasso.with(context).load(data[5]).into(viewHolder.eventImage);
        }
        else Picasso.with(context).load(data[5]).into(viewHolder.eventImage);

        Spannable spannable = new SpannableString(data[6]+" asistentes");
        //spannable.setSpan(new RelativeSizeSpan(1.2f), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        int start = data[6].length();
        int end = start + 11;
        spannable.setSpan(new RelativeSizeSpan(0.6f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.asistentsBtn.setText(spannable);

        Spannable spannable2 = new SpannableString(data[7]+" takes");
        start = data[7].length();
        end = start + 6;
        //spannable2.setSpan(new RelativeSizeSpan(1.2f), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable2.setSpan(new RelativeSizeSpan(0.6f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.takesBtn.setText(spannable2);
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView typeTV, titleTV, takesBtn, asistentsBtn, location, date, id;
        ImageButton shareBtn;
        ImageView eventImage;
        private final Context context;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            context = itemLayoutView.getContext();

            id = (TextView) itemLayoutView.findViewById(R.id.event_id);
            typeTV = (TextView) itemLayoutView.findViewById(R.id.type);
            titleTV = (TextView) itemLayoutView.findViewById(R.id.title);
            takesBtn = (TextView) itemLayoutView.findViewById(R.id.takesBtn);
            asistentsBtn = (TextView) itemLayoutView.findViewById(R.id.asistentsBtn);
            location = (TextView) itemLayoutView.findViewById(R.id.location);
            date = (TextView) itemLayoutView.findViewById(R.id.date);
            eventImage = (ImageView) itemLayoutView.findViewById(R.id.imageEvent);
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
