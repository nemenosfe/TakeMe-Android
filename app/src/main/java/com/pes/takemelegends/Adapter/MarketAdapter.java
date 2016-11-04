package com.pes.takemelegends.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pes.takemelegends.R;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder> {

    private String[] itemsData;

    public MarketAdapter(String[] itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public MarketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_row, parent, false);
        MarketAdapter.ViewHolder viewHolder = new MarketAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MarketAdapter.ViewHolder viewHolder, int position) {
        viewHolder.level.setText("Level 3");
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView level;
        public ImageView lock;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            level = (TextView) itemLayoutView.findViewById(R.id.lvl);

        }
    }

}