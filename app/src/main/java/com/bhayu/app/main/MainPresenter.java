package com.bhayu.app.main;

import android.content.Context;

import com.bhayu.app.base.BasePresenter;
import com.bhayu.app.base.BaseView;

public class MainPresenter implements BasePresenter {
    private Context context;
    private BaseView baseView;
    public MainPresenter(BaseView baseView){
        this.baseView = baseView;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(Context context) {
        this.context = context;
        baseView.initView();
        baseView.initListener();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
