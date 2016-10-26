package com.pes.takemelegends.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pes.takemelegends.R;

/**
 * Created by Oscar on 24/10/2016.
 */

public class LogroAdapter extends RecyclerView.Adapter<LogroAdapter.ViewHolder> {

    private String[] itemsData;

    public LogroAdapter(String[] itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public LogroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.logro_row, parent, false);
        LogroAdapter.ViewHolder viewHolder = new LogroAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LogroAdapter.ViewHolder viewHolder, int position) {
        viewHolder.numOfTakes.setText("200 takes");
        viewHolder.titleLogro.setText("Awesome logro man!");
        viewHolder.descriptionLogro.setText("Has ganado el logro que tu quieras.");
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView numOfTakes, titleLogro, descriptionLogro;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            numOfTakes = (TextView) itemLayoutView.findViewById(R.id.numOfTakes);
            titleLogro = (TextView) itemLayoutView.findViewById(R.id.titleLogro);
            descriptionLogro = (TextView) itemLayoutView.findViewById(R.id.descriptionLogro);

        }
    }

}
