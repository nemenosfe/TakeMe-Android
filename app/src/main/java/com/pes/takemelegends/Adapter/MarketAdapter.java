package com.pes.takemelegends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pes.takemelegends.Activity.RewardsActivity;
import com.pes.takemelegends.R;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder>  {

    private Integer[] itemsData;
    private int currentLvl;

    public MarketAdapter(Integer[] itemsData, int lvl) {
        this.itemsData = itemsData;
        this.currentLvl = lvl;
    }



    @Override
    public MarketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_row, parent, false);
        MarketAdapter.ViewHolder viewHolder = new MarketAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(MarketAdapter.ViewHolder viewHolder, int position) {
        // Necessitem el current level del user, fin a linkar amb API estara hardcoded a 3
        viewHolder.level.setText("Nivel " + itemsData[position]);
        if (currentLvl < itemsData[position]) viewHolder.lock.setImageResource(R.drawable.ic_lock_close);
        else viewHolder.lock.setImageResource(R.drawable.ic_lock_open);
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView level;
        public ImageView lock;
        private final Context context;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            context = itemLayoutView.getContext();
            level = (TextView) itemLayoutView.findViewById(R.id.lvl);
            lock = (ImageView) itemLayoutView.findViewById(R.id.lock);
            itemLayoutView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, RewardsActivity.class);
            intent.putExtra("id", getLayoutPosition());
            intent.putExtra("level", getLayoutPosition()+1);
            context.startActivity(intent);
        }


    }

}