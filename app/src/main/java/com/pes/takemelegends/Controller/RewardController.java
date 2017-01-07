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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/*
GET 	/rewards/ 	    appkey 	        Veure la llista total de recompenses
GET 	/rewards/user/ 	appkey + token 	Veure les recompenses d'un usuari
POST 	/rewards/user/ 	appkey + token 	Compra d'una recompensa per un usuari
 */

public class RewardController {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getRewardsByLvl(AsyncHttpResponseHandler responseHandler, Context context){
        RequestParams params = new RequestParams();
        params.add("appkey", URLResources.APP_KEY);
        params.put("page_size",99);
        client.get(URLResources.REWARDS_URL, params, responseHandler);
    }


    public void getUserRewards(AsyncHttpResponseHandler responseHandler, Context context) {
        SharedPreferencesManager shared = new SharedPreferencesManager(context);
        RequestParams params = new RequestParams();
        params.add("uid", shared.getUserId());
        params.add("token", shared.getUserToken());
        params.add("provider", shared.getUserProvider());
        params.add("appkey", URLResources.APP_KEY);
        params.put("page_size",99);
        client.get(URLResources.REWARDS_USER_URL, params, responseHandler);
    }

    public void postUserReward(AsyncHttpResponseHandler responseHandler, Context context, String reward_name){
        SharedPreferencesManager shared = new SharedPreferencesManager(context);
        String token = shared.getUserToken();
        String uid = shared.getUserId();
        String provider = shared.getUserProvider();
        JSONObject body = new JSONObject();
        try {
            body.put("token", token);
            body.put("appkey", URLResources.APP_KEY);
            body.put("reward_name", reward_name);
            body.put("uid", uid);
            body.put("provider", provider);
            StringEntity entity = new StringEntity(body.toString());
            client.post(context, URLResources.REWARDS_USER_URL, entity, "application/json", responseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
