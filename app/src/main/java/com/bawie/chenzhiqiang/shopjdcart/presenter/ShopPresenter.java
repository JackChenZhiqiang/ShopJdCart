package com.bawie.chenzhiqiang.shopjdcart.presenter;

import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsName;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.HomeBean;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopCar;
import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IAddgView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IGoodsPageView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IGoodsView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IGroChildView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IHomeView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IShopView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IgroHomeView;

import java.util.List;

public interface ShopPresenter {
    //首页
    void ShowHomeToView(ShopCar shopCar, IHomeView iHomeView);

    void ShowHomes(HomeBean homeBean);

    //购物车
    void ShowShopToView(ShopCar shopCar, IShopView iShopView);

    void ShowGoods(List<ShopBean.DataBean> list);

    //九宫格(分类)
    void ShowgroHomeToView(ShopCar shopCar, IgroHomeView igroHomeView);

    void ShowgroHomes(List<GroupBean.DataBean> list);

    //分类子接口
    void ShowGroChildToView(ShopCar shopCar, IGroChildView iGroChildView,int position);

    void ShowGroChildDatas(List<GroChildBean.DataBean> list);

    //商品列表
    void ShowGoodsToViews(ShopCar shopCar, IGoodsView iGoodsView,int position);

    void ShowGoodsData(List<GoodsBean.DataBean> list);

    //商品详情页
    void ShowGoodsPageToViews(ShopCar shopCar, IGoodsPageView iGoodsPageView,int position);

    void ShowGoodsNameMsg(GoodsName.DataBean data);

    //添加购物车
    void ShowAddgToViews(ShopCar shopCar, IAddgView iAddgView,int pid);

    void ShowAddgData(String msg);

}
