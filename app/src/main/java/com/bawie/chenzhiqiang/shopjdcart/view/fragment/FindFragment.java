package com.bawie.chenzhiqiang.shopjdcart.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.adapter.RecycleVideoAdapter;
import com.bawie.chenzhiqiang.shopjdcart.bean.VideoBean;
import com.bawie.chenzhiqiang.shopjdcart.model.VideoImpel;
import com.bawie.chenzhiqiang.shopjdcart.presenter.VideoPresenter;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IVideoView;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment implements IVideoView{
    private static final String TAG = "FindFragment---";
    private RecyclerView recycle_video;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate (getActivity (), R.layout.fragment_find, null);

        recycle_video = view.findViewById (R.id.recycle_video);

        VideoPresenter videoPresenter = new VideoPresenter ();

        videoPresenter.video (new VideoImpel (videoPresenter),this);

        recycle_video.setLayoutManager (new LinearLayoutManager (getActivity ()));

        return view;
    }

    @Override
    public void ShowVideoToViews(List<VideoBean.DataBean> list) {

        RecycleVideoAdapter videoAdapter = new RecycleVideoAdapter (getActivity (), list);

        recycle_video.setAdapter (videoAdapter);

        Log.d (TAG, "ShowVideoToViews: "+list.size ());
    }
}
