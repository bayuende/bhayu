package com.bhayu.app.helper;

public class SecretKeyHelper {

    public static String defaultKey() {
        return getDefaultKey();
    }
    public static String encryptionKey(){
        return getEncryptionKey();
    }
    public static String comunityID(){
        return getCommunityId();
    }
    public static String baseUrl(){
        return getBaseUrl();
    }

    static {
        System.loadLibrary("bhayu");
    }

    public native static String getDefaultKey();
    public native static String getEncryptionKey();
    public native static String getCommunityId();
    public native static String getBaseUrl();

}
