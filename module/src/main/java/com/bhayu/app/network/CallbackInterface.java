package com.bhayu.app.network;

import android.support.annotation.Nullable;


public interface CallbackInterface {

    void onRequestSuccess(int requestId, @Nullable String rawData);

    void onRequestFailure(int requestId, RequestManager.RequestFailureType failureType, String errorCode, String message);

}
