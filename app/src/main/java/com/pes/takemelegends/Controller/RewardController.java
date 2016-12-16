package com.pes.takemelegends.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Adapter.EventAdapter;
import com.pes.takemelegends.Utils.SharedPreferencesManager;
import com.pes.takemelegends.Utils.URLResources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/*
GET 	/rewards/ 	    appkey 	        Veure la llista total de recompenses
GET 	/rewards/user/ 	appkey + token 	Veure les recompenses d'un usuari
POST 	/rewards/user/ 	appkey + token 	Compra d'una recompensa per un usuari
 */

public class RewardController {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getRewardsByLvl(AsyncHttpResponseHandler responseHandler, Context context){
        SharedPreferencesManager shared = new SharedPreferencesManager(context);
        RequestParams params = new RequestParams();
        params.add("appKey", URLResources.APP_KEY);
        params.put("token",shared.getUserToken());
        params.add("provider",shared.getUserProvider());
        params.put("uid", shared.getUserId());
        params.put("page_size",99);
        client.get(URLResources.REWARDS_URL, params, responseHandler);
    }


    public void getUserRewards(Context context) {
        SharedPreferencesManager shared = new SharedPreferencesManager(context);
        RequestParams params = new RequestParams();
        params.add("appKey", URLResources.APP_KEY);
        params.put("token",shared.getUserToken());
        params.add("provider",shared.getUserProvider());
        params.put("uid", shared.getUserId());
        params.put("page_size",99);
        client.get(URLResources.REWARDS_USER_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray rewardsArray = response.optJSONArray("rewards");
                for (int i = 0; i < rewardsArray.length(); i++) {
                    try {
                        JSONObject reward = rewardsArray.getJSONObject(i);
                        //rewardsbyLVL.get(reward.getInt("level")).add(reward);
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

    public void postUserReward(){

    }

}
