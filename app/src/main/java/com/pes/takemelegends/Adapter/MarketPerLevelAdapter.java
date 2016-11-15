package com.pes.takemelegends.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pes.takemelegends.Activity.LoginActivity;
import com.pes.takemelegends.Activity.PreferencesActivity;
import com.pes.takemelegends.Activity.RewardsActivity;
import com.pes.takemelegends.R;

/**
 * Created by Oscar on 08/11/2016.
 */

public class MarketPerLevelAdapter extends RecyclerView.Adapter<MarketPerLevelAdapter.ViewHolder> {

    private String[] itemsData;

    public MarketPerLevelAdapter(String[] itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public MarketPerLevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_per_lvl_row, parent, false);
        MarketPerLevelAdapter.ViewHolder viewHolder = new MarketPerLevelAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MarketPerLevelAdapter.ViewHolder viewHolder, int position) {
        viewHolder.productName.setText("Clauer RCDE");
        viewHolder.productDesc.setText("description del cauer rcde");
        viewHolder.productTakes.setText("300 takes");
        viewHolder.productImage.setImageResource(R.drawable.ic_ticket);
        viewHolder.productBtn.setImageResource(R.drawable.ic_facebook);
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView productName, productDesc, productTakes;
        public ImageView productImage;
        public ImageButton productBtn;
        private final Context context;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            context = itemLayoutView.getContext();
            productName = (TextView) itemLayoutView.findViewById(R.id.productName);
            productDesc = (TextView) itemLayoutView.findViewById(R.id.productDescription);
            productTakes = (TextView) itemLayoutView.findViewById(R.id.productTakes);
            productBtn = (ImageButton) itemLayoutView.findViewById(R.id.productBuy);
            productImage = (ImageView) itemLayoutView.findViewById(R.id.productImage);
            productBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.reward_dialog);
            dialog.setTitle("Title...");
            dialog.show();
        }
    }

}
