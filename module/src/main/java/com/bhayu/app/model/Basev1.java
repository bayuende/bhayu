package com.bhayu.app.model;

/**
 * Created by mzennis on 4/19/17.
 */

public class Basev1<T> {

    private T data;
    private String success;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}