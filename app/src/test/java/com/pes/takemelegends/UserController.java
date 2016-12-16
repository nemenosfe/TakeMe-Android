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
    private JSONObject putCategories = getJSONprefForPutRequest();

    @Test
    public void postPreferences() throws Exception {
        userController.postPreferences(
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        assertEquals(getJSONPreferences(null), response);
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
                        assertEquals(getJSONPreferences(null), response);
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
                        assertEquals(getJSONPreferences(getJSONprefForPutRequest()), response);
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
            if (newCategoryList != null) {
                String[] arr_options = {"football", "basketball", "sports", "music", "art", "cinema", "theatre"};
                for (int index = 0; index < arr_options.length; ++index) {
                    preferences.put(arr_options[index], newCategoryList.get(arr_options[index]));
                }
            } else {
                preferences.put("football", true);
                preferences.put("basketball", false);
                preferences.put("sports", true);
                preferences.put("music", false);
                preferences.put("art", false);
                preferences.put("cinema", true);
                preferences.put("theatre", false);
            };
            preferences.put("location", "Barcelona");
            preferences.put("start_hour", "00:00");
            preferences.put("end_hour", "00:00");
            preferences.put("week", false);
            preferences.put("weekend", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return preferences;
    }

    private JSONObject getJSONprefForPutRequest() {
        JSONObject preferences = new JSONObject();
        try {
            preferences.put("football", false);
            preferences.put("basketball", false);
            preferences.put("sports", false);
            preferences.put("music", true);
            preferences.put("art", true);
            preferences.put("cinema", true);
            preferences.put("theatre", true);
            preferences.put("location", "Barcelona");
            preferences.put("start_hour", "00:00");
            preferences.put("end_hour", "00:00");
            preferences.put("week", true);
            preferences.put("weekend", true);
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
