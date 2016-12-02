package com.pes.takemelegends.Controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pes.takemelegends.Utils.URLResources;

public class EventController {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void getAllEvents(AsyncHttpResponseHandler responseHandler) {
        client.get(URLResources.EVENTS_URL, responseHandler);
    }

    public void getEventInfo(AsyncHttpResponseHandler responseHandler, String id) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        client.get(URLResources.EVENTS_URL, responseHandler);
    }

    public void getEventsUser(AsyncHttpResponseHandler responseHandler, String id)

    public void getEventsByCategory(AsyncHttpResponseHandler responseHandler, String category) {
        RequestParams params = new RequestParams();
        params.add("category", category);
        client.get(URLResources.EVENTS_URL, params, responseHandler);
    }
}
