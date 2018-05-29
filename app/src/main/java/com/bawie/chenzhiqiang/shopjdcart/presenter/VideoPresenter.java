package com.bawie.chenzhiqiang.shopjdcart.presenter;

import android.util.Log;

import com.bawie.chenzhiqiang.shopjdcart.bean.VideoBean;
import com.bawie.chenzhiqiang.shopjdcart.model.VideoData;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IVideoView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoPresenter implements VideoName{
    private IVideoView iVideoView;
    private static final String TAG = "VideoPresenter---";

    @Override
    public void video(VideoData videoData, IVideoView iVideoView) {
        this.iVideoView = iVideoView;

        Map<String,String> map = new HashMap<> ();

        map.put ("type","4");

        map.put ("page","1");

        videoData.video (map);
    }

    @Override
    public void ShowVideoToView(List<VideoBean.DataBean> list) {
        Log.d (TAG, "ShowVideoToView: "+list.size ());

        iVideoView.ShowVideoToViews (list);
    }
}
