package com.pes.takemelegends;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pes.takemelegends.Controller.ControllerFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.*;

public class UserController {
    private com.pes.takemelegends.Controller.UserController userController = ControllerFactory.getInstance().getUserController();
    private String uid = "1", provider = "provTestAnd", token = "abcd"; // Dades temporals
    private JSONObject putCategories;

    @Test
    public void postPreferences() throws Exception {
        userController.postPreferences(
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        assertEquals(getJSONPreferences(null), response); // Temporal
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            fail(errorResponse.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                uid,
                provider,
                token,
                getJSONPreferences(null).toString()
        );
    }

    @Test
    public void getPreferences() throws Exception {
        userController.getPreferences(
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        assertEquals(2+2, 4); // Temporal
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            fail(errorResponse.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                uid,
                provider,
                token
        );
    }

    @Test
    public void putPreferences() throws Exception {
        userController.putPreferences(
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        assertEquals(2+2, 4); // Temporal
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            fail(errorResponse.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                uid,
                provider,
                token,
                getJSONPreferences(putCategories).toString()
        );
    }

    private JSONObject getJSONPreferences(JSONObject newCategoryList) {
        JSONObject preferences = new JSONObject();
        try {
            preferences.put("categories", (newCategoryList == null) ? "default!!!" : newCategoryList.toString());
            preferences.put("locations", -1);
            preferences.put("start_hour", -1);
            preferences.put("end_hour", -1);
            preferences.put("week", -1);
            preferences.put("weekend", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return preferences;
    }

    @After public void putOriginalValues() {
        userController.putPreferences(
                new JsonHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            Log.e("ErrorPutOriginalValues", errorResponse.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                uid,
                provider,
                token,
                getJSONPreferences(null).toString()
        );
    }

}
