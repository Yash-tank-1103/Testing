package com.example.testing;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitClient {



    private static String BASE_URL="https://single2mingal.com/Glossary/public/api/v1/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private OkHttpClient.Builder builder = new OkHttpClient.Builder();

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    public static synchronized RetrofitClient getInstance(){
        if (retrofitClient==null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }



    public Api getsignupApi(){
        return retrofit.create(Api.class);
    }
    public Api getloginApi(){return retrofit.create(Api.class);}
    public Api getProductlistApi(){return retrofit.create(Api.class);}



}
