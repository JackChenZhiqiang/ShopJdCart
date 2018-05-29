package com.bawie.chenzhiqiang.shopjdcart.http;

import com.bawie.chenzhiqiang.shopjdcart.bean.LoginBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.RegBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface MyService {
    //登录
    @POST("user/login")
    Observable<LoginBean> loginPost(@QueryMap Map<String,String> map);

    //注册
    @POST("user/reg")
    Observable<RegBean> regPost(@QueryMap Map<String,String> map);
}
