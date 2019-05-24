package com.bhayu.app.network;

import com.bhayu.app.model.Basev2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mzennis on 12/13/16.
 */
public interface ApiInterface {
    @FormUrlEncoded
    @POST(ApiConfig.GET_ALL)
    Call<Basev2> getALl(
            @Field("device_id") String device_id,
            @Field("community_id") String community_id,
            @Field("data") String data
    );
}
