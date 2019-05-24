package com.bhayu.app.base;

import android.content.Context;
import android.view.View;

/**
 * Created by agusn on 4/11/17.
 */

public interface BaseFragmentPresenter {
    Context getContext();
    void onAttach(Context context);
    void onCreateView(View view);
    void onViewCreated(View view);
    void onDetach();
}