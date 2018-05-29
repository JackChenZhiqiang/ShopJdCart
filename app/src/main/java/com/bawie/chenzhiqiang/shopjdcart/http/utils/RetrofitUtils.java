package com.bawie.chenzhiqiang.shopjdcart.http.utils;

import com.bawie.chenzhiqiang.shopjdcart.http.HttpConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtiles;
    private final Retrofit retrofit;

    public static RetrofitUtils getInstance() {

        if (retrofitUtiles==null){
            retrofitUtiles=new RetrofitUtils();
        }
        return retrofitUtiles;
    }

    public RetrofitUtils(){

        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public<T> T createRequest(Class<T> cls){
        T t = retrofit.create(cls);
        return t;
    }
}
