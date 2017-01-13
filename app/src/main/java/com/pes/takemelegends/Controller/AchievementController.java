package com.pes.takemelegends.Controller;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Utils.SharedPreferencesManager;
import com.pes.takemelegends.Utils.URLResources;

public class AchievementController {

    private static final AsyncHttpClient client = new AsyncHttpClient();

    public void getUserAchievements(AsyncHttpResponseHandler responseHandler, Context context) {
        SharedPreferencesManager sharedPreferences = new SharedPreferencesManager(context);
        RequestParams params = new RequestParams();
        params.add("appkey", URLResources.APP_KEY);
        params.add("uid", sharedPreferences.getUserId());
        params.add("provider", sharedPreferences.getUserProvider());
        client.get(URLResources.USER_ACHIEVEMENTS_URL, params, responseHandler);
    }
}
