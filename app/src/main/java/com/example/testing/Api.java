package com.example.testing;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Field;

import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.POST;



public interface Api{


    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse.Root> login(@Field("email") String email,
                             @Field("password") String pass);


    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse.Root> register(@Field("email") String email,
                                @Field("password") String pass);


    @POST("productlist")
    Call<ProductListResponse.Root> productlist();





}