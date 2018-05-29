package com.bawie.chenzhiqiang.shopjdcart.view.activities.iview;

import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;

import java.util.List;

public interface IShopView {
    void ShowShopToViews(List<ShopBean.DataBean> list);

    void ShowCount(String count);
}
