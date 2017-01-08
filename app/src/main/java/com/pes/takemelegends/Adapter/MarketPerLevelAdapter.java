package com.pes.takemelegends.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Activity.LoginActivity;
import com.pes.takemelegends.Activity.PreferencesActivity;
import com.pes.takemelegends.Activity.RewardsActivity;
import com.pes.takemelegends.Controller.ControllerFactory;
import com.pes.takemelegends.Controller.RewardController;
import com.pes.takemelegends.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Oscar on 08/11/2016.
 */

public class MarketPerLevelAdapter extends RecyclerView.Adapter<MarketPerLevelAdapter.ViewHolder> {

    private ArrayList<JSONObject> itemsData;

    public MarketPerLevelAdapter(ArrayList<JSONObject> itemsData) {
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
        try {
            viewHolder.productName.setText(itemsData.get(position).getString("name"));
            viewHolder.productDesc.setText(itemsData.get(position).getString("description"));
            viewHolder.productTakes.setText(itemsData.get(position).getString("takes"));
            viewHolder.productImage.setImageResource(R.drawable.ic_ticket);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView productName, productDesc, productTakes;
        public ImageView productImage;
        public ImageButton productBtn;
        private final Context context;
        private RewardController rewardController;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            context = itemLayoutView.getContext();
            rewardController = ControllerFactory.getInstance().getRewardController();
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            final View content =  inflater.inflate(R.layout.reward_dialog, null);
            TextView name = (TextView) content.findViewById(R.id.nameProduct);
            TextView price = (TextView) content.findViewById(R.id.price);
            TextView info = (TextView) content.findViewById(R.id.infoProduct);
            Button btnConfirm = (Button) content.findViewById(R.id.btn_confirm);
            Button btnCancel = (Button) content.findViewById(R.id.btn_cancel);
            name.setText(productName.getText());
            price.setText(productTakes.getText()+" takes");
            info.setText(productDesc.getText());
            dialog.setContentView(content);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    rewardController.postUserReward(new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                String takes = response.getJSONObject("purchase").getString("takes_left");
                                productTakes.setText(takes+" takes");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            dialog.dismiss();
                        }
                    }, context, productName.getText().toString());
                }
            });
            dialog.show();
        }
    }

}
