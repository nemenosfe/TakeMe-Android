package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pes.takemelegends.R;

import java.util.List;

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

    private final List<String[]> itemsData;
    private final Context context;

    public RewardsAdapter(Context context, List<String[]> itemsData) {
        this.context = context;
        this.itemsData = itemsData;
    }

    @Override
    public RewardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reward_row, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(RewardsAdapter.ViewHolder viewHolder, int position) {
        String[] data = itemsData.get(position);
        viewHolder.rewardName.setText(data[0]);
        viewHolder.rewardDesc.setText(data[1]);
        viewHolder.rewardTakes.setText(data[2] + "takes");
        viewHolder.amount.setText("x"+data[3]);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView rewardName;
        private final TextView rewardDesc;
        private final TextView rewardTakes;
        private final TextView amount;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            rewardName = (TextView) itemLayoutView.findViewById(R.id.rewardName);
            rewardDesc = (TextView) itemLayoutView.findViewById(R.id.rewardDescription);
            rewardTakes = (TextView) itemLayoutView.findViewById(R.id.rewardTakes);
            amount = (TextView) itemLayoutView.findViewById(R.id.amount);
        }
    }
}
