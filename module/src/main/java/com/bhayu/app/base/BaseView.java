package com.bhayu.app.base;

/**
 * Created by agusn on 4/7/17.
 */

public interface BaseView {
    void initView();
    void initListener();
    void startTask();
    void finishedTask();
    void failureTask(String message);
    void info(String message);
    void onNoInternetConnection();
}