package com.pes.takemelegends.Controller;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.Utils.URLResources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RewardController {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private HashMap<Integer,ArrayList<JSONObject>> rewardsbyLVL;

    public void getRewards() {
        RequestParams params = new RequestParams();
        params.add("appKey", URLResources.APP_KEY);
        client.get(URLResources.EVENTS_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray rewardsArray = response.optJSONArray("rewards");
                for (int i = 0; i < rewardsArray.length(); i++) {
                    try {
                        JSONObject reward = rewardsArray.getJSONObject(i);
                        rewardsbyLVL.get(reward.getInt("level")).add(reward);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("getRewardsFail","FAILURE");
            }
        });

    }

    public void getUserRewards(AsyncHttpResponseHandler responseHandler) {
    }

    public void postUserReward(){

    }

}
