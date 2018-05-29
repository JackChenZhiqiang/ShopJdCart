package com.bawie.chenzhiqiang.shopjdcart.http.utils;

import com.bawie.chenzhiqiang.shopjdcart.http.HttpConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoUtils {
    private final Retrofit retrofit;
    private static VideoUtils videoUtils;

    public static VideoUtils getInstance() {

        if (videoUtils==null){
            videoUtils = new VideoUtils ();
        }
        return videoUtils;
    }

    public VideoUtils(){

        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.video_url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public<T> T createRequest(Class<T> cls){
        T t = retrofit.create(cls);
        return t;
    }
}
