package com.pes.takemelegends.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.takemelegends.R;

/**
 * Created by Oscar on 15/11/2016.
 */

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

    private String[] itemsData;

    public RewardsAdapter(String[] itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public RewardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reward_row, parent, false);
        RewardsAdapter.ViewHolder viewHolder = new RewardsAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RewardsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.rewardName.setText("Nom del producte");
        viewHolder.rewardDesc.setText("Descripció del producte que pot arribar a ser dos linies.");
        viewHolder.rewardTakes.setText("300 takes");
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView rewardName, rewardDesc, rewardTakes;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            rewardName = (TextView) itemLayoutView.findViewById(R.id.rewardName);
            rewardDesc = (TextView) itemLayoutView.findViewById(R.id.rewardDescription);
            rewardTakes = (TextView) itemLayoutView.findViewById(R.id.rewardTakes);

        }
    }
}
