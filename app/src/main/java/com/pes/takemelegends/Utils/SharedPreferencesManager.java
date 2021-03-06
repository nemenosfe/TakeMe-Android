package com.pes.takemelegends.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String PREFS_NAME = "TakeMeLegends";

    private static String userUid = "";
    private static String userToken = "";
    private static String userProvider = "";
    private static Boolean firstTime = true;
    private static String username = "";
    private static Integer currentLevel = 0;
    private static Integer totalTakes = 0;
    private static Integer currentExperience = 0;
    private static Integer numberOfCheckins = 0;
    private static Float experienceToNextLevel = 0.0f;
    private static Boolean needAttendanceUpdate = false;
    private static Boolean hasPreferences = false;
    private static Boolean todosUpdate = false;
    private static Boolean recomendadosUpdate = false;
    private static Boolean refreshCheckin = false;
    private static Boolean refreshHistorial = false;
    private static Integer distance = 500;
    private final SharedPreferences sp;



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
        currentExperience = (Integer) getObject("currentExperience", Integer.class.getSimpleName());
        experienceToNextLevel = (Float) getObject("experienceToNextLevel", Float.class.getSimpleName());
        numberOfCheckins = (Integer) getObject("numberOfChekins", Integer.class.getSimpleName());
        needAttendanceUpdate = (Boolean) getObject("needAttendanceUpdate", Boolean.class.getSimpleName());
        hasPreferences = (Boolean) getObject("hasPreferences", Boolean.class.getSimpleName());
        todosUpdate = (Boolean) getObject("todosUpdate", Boolean.class.getSimpleName());
        recomendadosUpdate = (Boolean) getObject("recomendadosUpdate", Boolean.class.getSimpleName());
        refreshCheckin = (Boolean) getObject("refreshCheckin", Boolean.class.getSimpleName());
        refreshHistorial = (Boolean) getObject("refreshHistorial", Boolean.class.getSimpleName());
        distance = (Integer) getObject("distance", Integer.class.getSimpleName());
    }

    public Integer getDistance() { return distance; }
    public void setDistance(Integer distance) {
        SharedPreferencesManager.distance = distance;
        setValue("distance", distance);
    }

    public Boolean getRefreshCheckin() { return refreshCheckin; }
    public void setRefreshCheckin(Boolean b) {
        refreshCheckin = b;
        setValue("refreshCheckin", b);
    }

    public Boolean getRefreshHistorial() { return refreshHistorial; }
    public void setRefreshHistorial(Boolean b) {
        refreshHistorial = b;
        setValue("refreshHistorial", b);
    }

    public Boolean getTodosUpdate() {return todosUpdate;}
    public void setTodosUpdate(Boolean need) {
        todosUpdate = need;
        setValue("todosUpdate", need);
    }

    public Boolean getRecomendadosUpdate() {return recomendadosUpdate;}
    public void setRecomendadosUpdate(Boolean need) {
        recomendadosUpdate = need;
        setValue("recomendadosUpdate", need);
    }

    public Boolean getNeedAttendanceUpdate() {return needAttendanceUpdate;}
    public void setAttendanceUpdate(Boolean need) {
        needAttendanceUpdate = need;
        setValue("needAttendanceUpdate", need);
    }

    public Boolean hasPreferences() {
        return hasPreferences;
    }
    public void setHasPreferences(Boolean hasPreferences) {
        SharedPreferencesManager.hasPreferences = hasPreferences;
        setValue("hasPreferences", hasPreferences);
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String uT) {
        userToken = uT;
        setValue("userToken",uT);
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String uN) {
        username = uN;
        setValue("username",uN);
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
    public void setUserId(String uId) {
        userUid = uId;
        setValue("userUid",uId);
    }
    public int getCurrentExperience() {
        return currentExperience;
    }
    public void setCurrentExperience(int cE) {
        currentExperience = cE;
        setValue("currentExperience", cE);
    }

    public String getUserProvider() {
        return userProvider;
    }
    public void setUserProvider(String userCompanyId) {
        userProvider = userCompanyId;
        setValue("userProvider",userCompanyId);
    }

    public int getNumberOfChekins(){return numberOfCheckins;}
    public void setNumberOfCheckins(int numberOfCheckins) {
        SharedPreferencesManager.numberOfCheckins = numberOfCheckins;
        setValue("numberOfCheckins", numberOfCheckins);
    }

    public void increaseCheckInEvents() {
        setValue("numberOfChekins", numberOfCheckins++);
    }

    public Float getExperienceToNextLevel(){return experienceToNextLevel;}
    public void setExperienceToNextLevel(float experienceOfTheNextLevel) {
        experienceToNextLevel = experienceOfTheNextLevel;
        setValue("experienceToNextLevel", experienceOfTheNextLevel);
    }

    public Boolean isFirstTime() { return firstTime; }
        public void setFirstTime(Boolean fT) {
        firstTime = fT;
        setValue("firstTime", fT);
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
            case "Float":
                return sp.getFloat(key, 0.0f);
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
            case "Float":
                editor.putFloat(key, (Float) value);
                break;
        }
        editor.commit();
    }
}
