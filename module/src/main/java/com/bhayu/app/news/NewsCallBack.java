package com.bhayu.app.news;

import com.bhayu.app.model.List;

public interface NewsCallBack {
    void showNews(List list);
    void onNoInternetConnection();
    void failureTask(String msg);
    void finishedTask();
}
