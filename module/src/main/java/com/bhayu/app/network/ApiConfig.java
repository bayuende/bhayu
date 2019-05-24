package com.bhayu.app.network;

import com.bhayu.app.helper.SecretKeyHelper;

/**
 * Created by mzennis on 12/16/16.
 */
public class ApiConfig {
    public static String BASE_URL = "https://"+ SecretKeyHelper.baseUrl();
    public static final String GET_ALL = "/v1/news/get-all";
    public static final String GET_HEADLINE = "/v1/news/get-headline";
}



