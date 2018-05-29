package com.bawie.chenzhiqiang.shopjdcart.http.utils;

import com.bawie.chenzhiqiang.shopjdcart.http.HttpConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopUtils {
    private final Retrofit retrofit;
    private static ShopUtils shopUtils;

    public static ShopUtils getInstance() {

        if (shopUtils==null){
            shopUtils = new ShopUtils ();
        }
        return shopUtils;
    }

    public ShopUtils(){

        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.shop_url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public<T> T createRequest(Class<T> cls){
        T t = retrofit.create(cls);
        return t;
    }
}
