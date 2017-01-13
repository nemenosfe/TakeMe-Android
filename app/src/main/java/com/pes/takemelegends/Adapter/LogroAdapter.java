package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.takemelegends.R;

import java.util.List;

public class LogroAdapter extends RecyclerView.Adapter<LogroAdapter.ViewHolder> {

    private final List<String[]> itemsData;
    private final Context context;

    public LogroAdapter(Context context, List<String[]> itemsData) {
        this.context = context;
        this.itemsData = itemsData;
    }

    @Override
    public LogroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.logro_row, parent, false);
        return new ViewHolder(itemLayoutView);
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

        public final TextView numOfTakes;
        public final TextView titleLogro;
        public final TextView descriptionLogro;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            numOfTakes = (TextView) itemLayoutView.findViewById(R.id.numOfTakes);
            titleLogro = (TextView) itemLayoutView.findViewById(R.id.titleLogro);
            descriptionLogro = (TextView) itemLayoutView.findViewById(R.id.descriptionLogro);

        }
    }

}
