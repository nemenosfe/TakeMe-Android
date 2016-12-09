package com.pes.takemelegends.Controller;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Utils.URLResources;

public class UserController {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getPreferences(JsonHttpResponseHandler responseHandler, String uid, String provider, String token) {
        RequestParams params = new RequestParams("token", token);
        params.add("appkey", URLResources.APP_KEY);
        //String url = URLResources.USERS_URL + uid + "-" + provider + "/preferences/";
        //client.get(url, params, responseHandler);
    }

    public void postPreferences(JsonHttpResponseHandler responseHandler, String uid, String provider, String token, String preferences) {
        RequestParams params = new RequestParams("token", token);
        params.add("appkey", URLResources.APP_KEY);
        params.add("preferences", preferences);
        //String url = URLResources.USERS_URL + uid + "-" + provider + "/preferences/";
        //client.post(url, params, responseHandler);
    }

    public void putPreferences(JsonHttpResponseHandler responseHandler, String uid, String provider, String token, String preferences) {
        RequestParams params = new RequestParams("token", token);
        params.add("appkey", URLResources.APP_KEY);
        params.add("preferences", preferences);
        //String url = URLResources.USERS_URL + uid + "-" + provider + "/preferences/";
        //client.put(url, params, responseHandler);
    }
}