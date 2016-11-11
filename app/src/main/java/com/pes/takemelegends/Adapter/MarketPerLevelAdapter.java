package com.pes.takemelegends.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName, productDesc, productTakes;
        public ImageView productImage;
        public ImageButton productBtn;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            productName = (TextView) itemLayoutView.findViewById(R.id.productName);
            productDesc = (TextView) itemLayoutView.findViewById(R.id.productDescription);
            productTakes = (TextView) itemLayoutView.findViewById(R.id.productTakes);
            productBtn = (ImageButton) itemLayoutView.findViewById(R.id.productBuy);
            productImage = (ImageView) itemLayoutView.findViewById(R.id.productImage);
        }
    }

}
