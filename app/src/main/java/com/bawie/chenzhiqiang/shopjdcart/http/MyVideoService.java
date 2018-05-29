package com.bawie.chenzhiqiang.shopjdcart.http;

import com.bawie.chenzhiqiang.shopjdcart.bean.VideoBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface MyVideoService {
    //视频
    @GET("satinApi")
    Observable<VideoBean> getVideos(@QueryMap Map<String,String> map);
}
