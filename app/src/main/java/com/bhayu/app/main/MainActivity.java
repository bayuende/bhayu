package com.bhayu.app.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.bhayu.app.R;
import com.bhayu.app.base.BaseView;
import com.bhayu.app.model.List;
import com.bhayu.app.news.NewsCallBackImpl;
import com.bhayu.app.news.NewsCallBack;

public class MainActivity extends AppCompatActivity implements NewsCallBack, BaseView {
    private NewsCallBackImpl newsCallBackImpl;
    private RecyclerView rvAll;
    private MainAdapter adapter;
    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MainAdapter(this);
        presenter = new MainPresenter(this);
        presenter.onCreate(this);

        newsCallBackImpl = new NewsCallBackImpl(this,this);
        newsCallBackImpl.executeGetNews("","",1,10,"news");
    }

    @Override
    public void initView() {
        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
        rvAll = findViewById(R.id.rvAll);
        rvAll.setHasFixedSize(true);
        rvAll.setLayoutManager(mLayoutManager);
        rvAll.setAdapter(adapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void startTask() {

    }

    @Override
    public void finishedTask() {

    }

    @Override
    public void failureTask(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void info(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoInternetConnection() {
        Toast.makeText(getApplicationContext(),"Koneksi internet tidak tersedia",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNews(List list) {
        adapter.clear();
        adapter.addAll(list.getNews());
        adapter.notifyDataSetChanged();
    }
}
