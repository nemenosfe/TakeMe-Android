package com.pes.takemelegends.Utils;

public class URLResources {
    /*
        uid: 1, provider: "provider", token: "5ba039ba572efb08d6442074d7d478d5"
        uid: 2, provider: "provider", token: "randomToken"
        uid: 3, provider: "provider", token: "364b99c40b84b5207e89a207a606720a"
     */
    private static final String BASE_URL = "http://10.4.41.167:8888";
    public static final String APP_KEY = "7384d85615237469c2f6022a154b7e2c";
    public static final String ACHIEVEMENTS_URL = BASE_URL + "/achievements";
    public static final String USER_ACHIEVEMENTS_URL = ACHIEVEMENTS_URL + "/user";
    public static final String EVENTS_URL = BASE_URL + "/events";
    public static final String REWARDS_URL = BASE_URL + "/rewards";
    public static final String REWARDS_USER_URL = BASE_URL + "/rewards/users";
    public static final String USERS_URL = BASE_URL + "/users/";

}
