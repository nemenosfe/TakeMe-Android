package com.pes.takemelegends.Controller;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Utils.SharedPreferencesManager;
import com.pes.takemelegends.Utils.URLResources;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.entity.StringEntity;

public class UserController {
    private static AsyncHttpClient client = new AsyncHttpClient();
    private SharedPreferencesManager sharedPreferences;

    public void getPreferences(AsyncHttpResponseHandler responseHandler, Context context) {
        RequestParams params = new RequestParams();
        sharedPreferences = new SharedPreferencesManager(context);
        String uid = sharedPreferences.getUserId(),
                provider = sharedPreferences.getUserProvider(),
                token = sharedPreferences.getUserToken(),
                urlPreferences = URLResources.USERS_URL + '/' + uid + "-" + provider + "/preferences";
        params.add("token", token);
        params.add("appkey", URLResources.APP_KEY);
        client.get(context, urlPreferences, params, responseHandler);
    }

    public void postPreferences(AsyncHttpResponseHandler responseHandler, Context context, String categories, String locations) {
        RequestParams params = new RequestParams();
        sharedPreferences = new SharedPreferencesManager(context);
        String uid = sharedPreferences.getUserId(),
                provider = sharedPreferences.getUserProvider(),
                token = sharedPreferences.getUserToken(),
                urlPreferences = URLResources.USERS_URL + '/' + uid + "-" + provider + "/preferences";
        params.add("token", token);
        params.add("appkey", URLResources.APP_KEY);
        JSONObject body = new JSONObject();
        StringEntity entity = null;
        try {
            body.put("token", token);
            body.put("appkey", URLResources.APP_KEY);
            body.put("categories", categories);
            body.put("locations", locations);
            body.put("uid", uid);
            body.put("provider", provider);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            entity = new StringEntity(body.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(context, urlPreferences, entity, "application/json", responseHandler);
    }

    public void putPreferences(AsyncHttpResponseHandler responseHandler, Context context, String categories, String locations) {
        RequestParams params = new RequestParams();
        sharedPreferences = new SharedPreferencesManager(context);
        String uid = sharedPreferences.getUserId(),
                provider = sharedPreferences.getUserProvider(),
                token = sharedPreferences.getUserToken(),
                urlPreferences = URLResources.USERS_URL + '/' + uid + "-" + provider + "/preferences";

        JSONObject body = new JSONObject();
        StringEntity entity = null;
        try {
            body.put("token", token);
            body.put("appkey", URLResources.APP_KEY);
            body.put("uid", uid);
            body.put("provider", provider);
            body.put("categories", categories);
            body.put("locations", locations);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            entity = new StringEntity(body.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.put(context, urlPreferences, entity, "application/json", responseHandler);
    }

    public void postUser(StringEntity body, Context context, JsonHttpResponseHandler responseHandler) {
        String url = URLResources.USERS_URL;
        client.post(context,url,body,"application/json",responseHandler);
    }

}