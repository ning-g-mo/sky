package com.mengze.sky.network;

import com.mengze.sky.model.ApifoxModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/openapi/getHeightDm")
    Call<ApifoxModel> getHeight(@Query("cdk") String cdk, @Query("code") String code);
}