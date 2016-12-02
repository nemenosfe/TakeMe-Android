package com.pes.takemelegends.Controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.pes.takemelegends.Utils.URLResources;

public class AchievementController {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getAllAchievements(AsyncHttpResponseHandler responseHandler) {
        client.get(URLResources.ACHIEVEMENTS_URL, responseHandler);
    }

    public void getUserAchievements(AsyncHttpResponseHandler responseHandler) {
        client.get(URLResources.USER_ACHIEVEMENTS_URL, responseHandler);
    }
}
