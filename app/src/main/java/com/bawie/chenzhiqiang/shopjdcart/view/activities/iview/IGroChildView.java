package com.bawie.chenzhiqiang.shopjdcart.view.activities.iview;

import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;

import java.util.List;

public interface IGroChildView {
    void ShowGroChildToViews(List<GroChildBean.DataBean> list);

    int getCid();
}
