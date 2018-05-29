package com.bawie.chenzhiqiang.shopjdcart.view.activities.iview;

import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsName;

public interface IGoodsPageView {
    void ShowGoodsPageToView(GoodsName.DataBean data);

    int getPid();
}
