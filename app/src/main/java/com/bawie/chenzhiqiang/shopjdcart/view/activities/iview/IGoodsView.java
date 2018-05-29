package com.bawie.chenzhiqiang.shopjdcart.view.activities.iview;

import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsBean;

import java.util.List;

public interface IGoodsView {
    void ShowGoodsToViews(List<GoodsBean.DataBean> list);

    int getPscid();
}
