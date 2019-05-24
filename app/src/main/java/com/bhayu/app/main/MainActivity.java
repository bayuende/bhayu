package com.bhayu.app.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.bhayu.app.R;
import com.bhayu.app.Test;
import com.bhayu.app.model.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;
    private RecyclerView rvAll;
    private MainAdapterAll adapterAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterAll = new MainAdapterAll(this);
        presenter = new MainPresenter(this);
        presenter.onCreate(this);
        presenter.getAll();
    }

    @Override
    public void initView() {
        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
        rvAll = findViewById(R.id.rvAll);
        rvAll.setHasFixedSize(true);
        rvAll.setLayoutManager(mLayoutManager);
        rvAll.setAdapter(adapterAll);
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
    public void showAll(List list) {
        adapterAll.clear();
        adapterAll.addAll(list.getNews());
        adapterAll.notifyDataSetChanged();
    }
}
