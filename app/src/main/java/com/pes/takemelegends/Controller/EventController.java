package com.pes.takemelegends.Controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class EventController {

    private static final String BASE_URL = "http://10.4.41.167:8888/events";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getAllEvents(AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        client.get(BASE_URL, params, responseHandler);
    }

    public static void getEventsByCategory(AsyncHttpResponseHandler responseHandler, String category) {
        RequestParams params = new RequestParams();
        params.add("category", category);
        client.get(BASE_URL, params, responseHandler);
    }
}
