package com.bawie.chenzhiqiang.shopjdcart.model;

import java.util.Map;

public interface ShopCar {
    //首页
    void home(Map<String,String> map);

    //购物车
    void shop(Map<String,String> map);

    //九宫格（分类）
    void groHome(Map<String,String> map);

    //分类子接口
    void groChildGroup(Map<String,String> map);

    //商品列表
    void goodsMsg(Map<String,String> map);

    //商品详情页
    void goodsPage(Map<String,String> map);

    //添加购物车
    void addGoods(Map<String,String> map);
}
