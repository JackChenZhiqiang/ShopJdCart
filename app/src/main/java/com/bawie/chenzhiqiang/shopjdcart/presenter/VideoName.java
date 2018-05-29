package com.bawie.chenzhiqiang.shopjdcart.presenter;

import com.bawie.chenzhiqiang.shopjdcart.bean.VideoBean;
import com.bawie.chenzhiqiang.shopjdcart.model.VideoData;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IVideoView;

import java.util.List;
import java.util.Map;

public interface VideoName {
    void video(VideoData videoData, IVideoView iVideoView);

    void ShowVideoToView(List<VideoBean.DataBean> list);
}
