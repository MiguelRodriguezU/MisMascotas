package dev.mrodriguezul.mismascotas.restApi;


public class ConstantesRestApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com"+VERSION;
    public static final String ACCESS_TOKEN = "6343508898.5e0fcb3.190811d0ba114166872bc6cb1c77cb2d";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_SEARCH = "&q=sq";

    //public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    //public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_SEARCH = "users/search";
    public static final String URL_GET_SEARCH = KEY_GET_SEARCH + KEY_ACCESS_TOKEN + ACCESS_TOKEN + KEY_SEARCH;

    public static final String KEY_GET_RECENT_MEDIA_USER = "users/{id}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_FOLLOWERS = "users/self/follows";
    public static final String URL_GET_FOLLOWERS = KEY_GET_FOLLOWERS + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
