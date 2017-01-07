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
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.entity.StringEntity;

public class EventController {

    //private SharedPreferencesManager sharedPreferences;

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
        params.add("page_size", "50");
        client.get(URLResources.EVENTS_URL, params, responseHandler);
    }

    public void getEventInfo(AsyncHttpResponseHandler responseHandler, String id) {
        RequestParams params = new RequestParams();
        //params.add("id", id);
        params.add("appkey", URLResources.APP_KEY);
        //String url = "http://10.4.41.167:8888/events/"+id+"?appkey=7384d85615237469c2f6022a154b7e2c";
        client.get(URLResources.EVENTS_URL+"/"+id, params, responseHandler);
    }

    public void getEventsUser(AsyncHttpResponseHandler responseHandler, Context context, String page_size, String page_number) {
        RequestParams params = new RequestParams();
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String uid = sharedPreferences.getUserId();
        String provider = sharedPreferences.getUserProvider();
        params.add("token", token);
        params.add("appkey", URLResources.APP_KEY);
        params.add("uid", uid);
        params.add("provider", provider);
        if (page_size!=null) params.add("page_size", page_size);
        if (page_number!=null) params.add("page_number", page_number);
        client.get(context, URLResources.EVENTS_URL+"/user", params, responseHandler);
    }

    public void getRecommendations(AsyncHttpResponseHandler responseHandler, Context context, String page_size, String page_number) {
        RequestParams params = new RequestParams();
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String provider = sharedPreferences.getUserProvider();
        String uid = sharedPreferences.getUserId();
        params.add("appkey", URLResources.APP_KEY);
        params.add("token", token);
        if (page_size!=null) params.add("page_size", page_size);
        if (page_number!=null) params.add("page_number", page_number);
        client.get(context, URLResources.USERS_RECOMMENDATIONS+'/'+uid+"-"+provider, params, responseHandler);
    }

    public void postAsistire(AsyncHttpResponseHandler responseHandler, Context context, String event_id) {
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String uid = sharedPreferences.getUserId();
        String provider = sharedPreferences.getUserProvider();
        JSONObject body = new JSONObject();
        try {

            body.put("token", token);
            body.put("appkey", URLResources.APP_KEY);
            body.put("event_id", event_id);
            body.put("uid", uid);
            body.put("provider", provider);
            StringEntity entity = new StringEntity(body.toString());
            client.post(context, URLResources.EVENTS_URL+"/user", entity, "application/json", responseHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void doCheckIn(AsyncHttpResponseHandler responseHandler, Context context, String event_id) {
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String uid = sharedPreferences.getUserId();
        String provider = sharedPreferences.getUserProvider();
        JSONObject body = new JSONObject();
        StringEntity entity = null;
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        try {
            body.put("uid", uid);
            body.put("provider", provider);
            body.put("checkin_done", 1);
            body.put("appkey",  URLResources.APP_KEY);
            body.put("token", token);
            body.put("time_checkin", currentDateandTime);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            entity = new StringEntity(body.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.put(context, URLResources.EVENTS_URL+'/'+event_id+"/user/", entity, "application/json", responseHandler);
    }

    public void deleteAsistire(AsyncHttpResponseHandler responseHandler, Context context, String event_id) {
        RequestParams params = new RequestParams();
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        String token = sharedPreferences.getUserToken();
        String uid = sharedPreferences.getUserId();
        String provider = sharedPreferences.getUserProvider();
        params.add("token", token);
        params.add("appkey", URLResources.APP_KEY);
        JSONObject body = new JSONObject();
        StringEntity entity = null;
        try {
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
        client.delete(context, URLResources.EVENTS_URL+event_id+URLResources.USERS_URL, entity, "application/json", responseHandler);
    }

}
