package com.pes.takemelegends.Controller;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Utils.SharedPreferencesManager;
import com.pes.takemelegends.Utils.URLResources;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class EventController {

    private SharedPreferencesManager sharedPreferences;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getAllEvents(AsyncHttpResponseHandler responseHandler, String category, String keywords, String date, String location,
        String within, String page_size, String page_number) {
        RequestParams params = new RequestParams();
        if (date!=null) params.add("date", date);
        if (location!=null)params.add("location", location);
        if (category!=null)params.add("category", category);
        if (keywords!=null)params.add("keywords", keywords);
        if (within!=null)params.add("within", within);
        if (page_size!=null)params.add("page_size", page_size);
        if (page_number!=null)params.add("page_number", page_number);
        params.add("appkey", URLResources.APP_KEY);
        client.get(URLResources.EVENTS_URL, params, responseHandler);
    }

    public void getEventInfo(AsyncHttpResponseHandler responseHandler, String id) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        params.add("appkey", URLResources.APP_KEY);
        client.get(URLResources.EVENTS_URL, responseHandler);
    }

    public void getEventsUser(AsyncHttpResponseHandler responseHandler, Context context, String page_size, String page_number) {
        RequestParams params = new RequestParams();
        sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String uid = sharedPreferences.getUserId();
        String provider = sharedPreferences.getUserProvider();
        params.add("token", token);
        params.add("appkey", URLResources.APP_KEY);
        params.add("uid", uid);
        params.add("provider", provider);
        if (page_size!=null) params.add("page_size", page_size);
        if (page_number!=null) params.add("page_number", page_number);
        client.get(context, URLResources.EVENTS_URL, params, responseHandler);
    }

    public void postAsistire(AsyncHttpResponseHandler responseHandler, Context context, String event_id) {
        RequestParams params = new RequestParams();
        sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String uid = sharedPreferences.getUserId();
        String provider = sharedPreferences.getUserProvider();
        params.add("token", token);
        params.add("appkey", URLResources.APP_KEY);
        JSONObject body = new JSONObject();
        StringEntity entity = null;
        try {
            body.put("event_id", event_id);
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
        client.post(context, URLResources.EVENTS_URL, entity, "application/json", responseHandler);
    }

}
