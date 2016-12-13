package com.pes.takemelegends.Controller;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Utils.URLResources;

import cz.msebera.android.httpclient.entity.StringEntity;

public class UserController {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getPreferences(JsonHttpResponseHandler responseHandler, String uid, String provider, String token) {
        RequestParams params = new RequestParams("token", token);
        params.add("appkey", URLResources.APP_KEY);
        String url = URLResources.USERS_URL + uid + "-" + provider + "/preferences/";
        client.get(url, params, responseHandler);
    }

    public void postPreferences(JsonHttpResponseHandler responseHandler, String uid, String provider, String token, String preferences) {
        RequestParams params = new RequestParams("token", token);
        params.add("appkey", URLResources.APP_KEY);
        params.add("preferences", preferences);
        String url = URLResources.USERS_URL + uid + "-" + provider + "/preferences/";
        client.post(url, params, responseHandler);
    }

    public void putPreferences(JsonHttpResponseHandler responseHandler, String uid, String provider, String token, String preferences) {
        RequestParams params = new RequestParams("token", token);
        params.add("appkey", URLResources.APP_KEY);
        params.add("preferences", preferences);
        String url = URLResources.USERS_URL + uid + "-" + provider + "/preferences/";
        client.put(url, params, responseHandler);
    }

    public void postUser(StringEntity body, Context context, JsonHttpResponseHandler responseHandler) {
        String url = URLResources.USERS_URL;
        client.post(context,url,body,"application/json",responseHandler);
    }
}