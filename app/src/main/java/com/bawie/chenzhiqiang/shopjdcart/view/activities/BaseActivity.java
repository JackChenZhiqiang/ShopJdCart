package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by a on 2018/4/10.
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(creatView());

        //初始化界面
        initViews();

        //初始化数据
        initDatas();
    }

    protected abstract void initDatas();

    protected abstract void initViews();

    //创建view的方法
    abstract View creatView();
}
