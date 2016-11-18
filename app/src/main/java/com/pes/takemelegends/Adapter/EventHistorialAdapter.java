package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.pes.takemelegends.Activity.EventDetailsActivity;
import com.pes.takemelegends.R;
import com.squareup.picasso.Picasso;

/**
 * Created by victorgallegobetorz on 15/11/16.
 */

public class EventHistorialAdapter extends RecyclerView.Adapter<EventHistorialAdapter.ViewHolder> {

    private String[] itemsData;
    private Context context;
    private final String imageUrl = "http://www.hutterites.org/wp-content/uploads/2012/03/placeholder.jpg";

    public EventHistorialAdapter(String[] itemsData, Context context) {

        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public EventHistorialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_histrorial_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
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
;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
