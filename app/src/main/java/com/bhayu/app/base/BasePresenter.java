package com.bhayu.app.base;

import android.content.Context;

/**
 * Created by agusn on 4/7/17.
 */

public interface BasePresenter {
    Context getContext();
    void onCreate(Context context);
    void onPause();
    void onResume();
    void onDestroy();
}
