package com.bawie.chenzhiqiang.shopjdcart.model;

import android.util.Log;

import com.bawie.chenzhiqiang.shopjdcart.bean.VideoBean;
import com.bawie.chenzhiqiang.shopjdcart.http.MyVideoService;
import com.bawie.chenzhiqiang.shopjdcart.http.utils.VideoUtils;
import com.bawie.chenzhiqiang.shopjdcart.presenter.VideoName;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class VideoImpel implements VideoData {
    private static final String TAG = "VideoImpel---";
    private final VideoName videoName;

    public VideoImpel(VideoName videoName){
        this.videoName = videoName;
    }

    @Override
    public void video(Map<String, String> map) {
        VideoUtils videoUtils = VideoUtils.getInstance ();

        MyVideoService myVideoService = videoUtils.createRequest (MyVideoService.class);

        Observable<VideoBean> observable = myVideoService.getVideos (map);

        observable.subscribeOn (Schedulers.newThread ())
                  .observeOn (AndroidSchedulers.mainThread ())
                  .subscribe (new Observer<VideoBean> () {
                      @Override
                      public void onCompleted() {
                          Log.d (TAG, "onCompleted: 登录成功-----");
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.d (TAG, "onError: 登录失败--"+e.getMessage ());
                      }

                      @Override
                      public void onNext(VideoBean videoBean) {
                          Log.d (TAG, "onNext: 视频bean"+videoBean);
                            videoName.ShowVideoToView (videoBean.getData ());
                      }
                  });
    }
}
