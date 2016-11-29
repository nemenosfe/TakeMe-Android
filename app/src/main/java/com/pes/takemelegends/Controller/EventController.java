package com.pes.takemelegends.Controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class EventController {

    private static final String BASE_URL = "http://10.4.41.167:8888/events";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getAllEvents(AsyncHttpResponseHandler responseHandler) {
        client.get(BASE_URL, responseHandler);
    }

    public void getEventsByCategory(AsyncHttpResponseHandler responseHandler, String category) {
        RequestParams params = new RequestParams();
        params.add("category", category);
        client.get(BASE_URL, params, responseHandler);
    }
}
