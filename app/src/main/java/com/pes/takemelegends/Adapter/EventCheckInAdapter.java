package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pes.takemelegends.Fragment.TotsEventsFragment;
import com.pes.takemelegends.R;

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
        EventCheckInAdapter.ViewHolder viewHolder = new EventCheckInAdapter.ViewHolder(itemLayoutView);
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