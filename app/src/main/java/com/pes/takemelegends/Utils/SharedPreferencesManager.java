package com.pes.takemelegends.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    public static final String PREFS_NAME = "TakeMeLegends";

    private static String userUid = "";
    private static String userToken = "";
    private static String userProvider = "";
    private static Boolean firstTime = true;
    private static String username = "";
    private static Integer currentLevel = 0;
    private static Integer totalTakes = 0;
    SharedPreferences sp;

    /**
     * creates a shared preferences controller with the actual appication context
     * load the actual values.
     * @param context actual application context
     */
    public SharedPreferencesManager(Context context) {
        sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        userUid = (String) getObject("userUid", String.class.getSimpleName());
        userToken = (String) getObject("userToken", String.class.getSimpleName());
        userProvider = (String) getObject("userProvider", String.class.getSimpleName());
        username = (String) getObject("username", String.class.getSimpleName());
        firstTime = (Boolean) getObject("firstTime", Boolean.class.getSimpleName());
        currentLevel = (Integer) getObject("currentLevel", Integer.class.getSimpleName());
        totalTakes = (Integer) getObject("totalTakes", Integer.class.getSimpleName());
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        setValue("userToken",userToken);
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
        setValue("username",username);
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer level) {
        currentLevel = level;
        setValue("currentLevel",level);
    }

    public Integer getTotalTakes() {
        return totalTakes;
    }

    public void setTotalTakes(Integer takes) {
        totalTakes = takes;
        setValue("totalTakes",takes);
    }

    public String getUserId() {
        return userUid;
    }
    public void setUserId(String userId) {
        this.userUid = userId;
        setValue("userUid",userId);
    }

    public String getUserProvider() {
        return userProvider;
    }
    public void setUserProvider(String userCompanyId) {
        this.userProvider = userCompanyId;
        setValue("userProvider",userCompanyId);
    }

    public Boolean isFirstTime() { return firstTime; }
    public void setFirstTime(Boolean firstTime) {
        this.firstTime = firstTime;
        setValue("firstTime", firstTime);
    }

    /**
     *retrieves the object stored in the key
     * @param key Key to recover the file
     * @param classtype Descriptor to know the class of the object to find
     * @return The value of the object searched or a default value
     */
    private Object getObject(String key, String classtype) {
        switch (classtype) {
            case "Boolean":
                return sp.getBoolean(key,false);
            case "Integer":
                return sp.getInt(key, 0);
            case "String":
                return sp.getString(key, "");
            default:
                return null;
        }
    }

    /**
     * test if value is boolean or int or string
     * then save it to shared preference with param key
     * @param key name of the shared preference value
     * @param value object to save.
     */
    private void setValue(String key, Object value) {
        SharedPreferences.Editor editor = sp.edit();
        switch (value.getClass().getSimpleName()){
            case "Boolean":
                editor.putBoolean(key, (Boolean) value);
                break;
            case "Integer":
                editor.putInt(key, (Integer) value);
                break;
            case "String":
                editor.putString(key, (String) value);
                break;
        }
        boolean t = editor.commit();

        int c = 0;
        int b = 2;
        int s = c + b;

    }
}