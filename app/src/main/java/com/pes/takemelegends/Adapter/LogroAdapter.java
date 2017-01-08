package com.pes.takemelegends.Adapter;

import android.content.Context;
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

import java.util.List;

/**
 * Created by Oscar on 24/10/2016.
 */

public class LogroAdapter extends RecyclerView.Adapter<LogroAdapter.ViewHolder> {

    private List<String[]> itemsData;
    private Context context;

    public LogroAdapter(Context context, List<String[]> itemsData) {
        this.context = context;
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
        String[] data = itemsData.get(position);
        viewHolder.titleLogro.setText(data[0]);
        viewHolder.descriptionLogro.setText(data[1]);
        viewHolder.numOfTakes.setText(data[2]+" takes");
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
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
