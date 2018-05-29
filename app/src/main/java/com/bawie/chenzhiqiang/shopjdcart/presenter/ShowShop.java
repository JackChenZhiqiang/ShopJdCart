package com.bawie.chenzhiqiang.shopjdcart.presenter;

import android.util.Log;

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

import java.sql.SQLTransactionRollbackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowShop implements ShopPresenter{
    private IShopView iShopView;
    private static final String TAG = "ShowShop---";
    private IHomeView iHomeView;
    private IgroHomeView igroHomeView;
    private IGroChildView iGroChildView;
    private IGoodsView iGoodsView;
    private IGoodsPageView iGoodsPageView;
    private IAddgView iAddgView;

    //首页
    @Override
    public void ShowHomeToView(ShopCar shopCar, IHomeView iHomeView) {
           this.iHomeView = iHomeView;

           Map<String,String> map = new HashMap<> ();

           map.put ("token","android");

           shopCar.home (map);
    }

    @Override
    public void ShowHomes(HomeBean homeBean) {
        Log.d (TAG, "ShowHomes: "+homeBean.getData ());
            iHomeView.ShowHomeToViews (homeBean);
    }

    //购物车
    @Override
    public void ShowShopToView(ShopCar shopCar, IShopView iShopView) {
        this.iShopView =iShopView;

        Map<String,String> map = new HashMap<> ();

        map.put ("uid","71");

        shopCar.shop (map);
    }

    @Override
    public void ShowGoods(List<ShopBean.DataBean> list) {
        Log.d (TAG, "ShowGoods: "+list.size ());

        iShopView.ShowShopToViews (list);
    }

    //分类
    @Override
    public void ShowgroHomeToView(ShopCar shopCar, IgroHomeView igroHomeView) {
        this.igroHomeView = igroHomeView;

        Map<String,String> map = new HashMap<> ();

        map.put ("token","android");

        shopCar.groHome (map);
    }

    @Override
    public void ShowgroHomes(List<GroupBean.DataBean> list) {
        Log.d (TAG, "ShowgroHomes(九宫格): "+list.size ());

        igroHomeView.ShowgroHomeViews (list);
    }

    //子分类
    @Override
    public void ShowGroChildToView(ShopCar shopCar, IGroChildView iGroChildView,int position) {
        this.iGroChildView = iGroChildView;

        Map<String,String> map = new HashMap<> ();

        map.put ("cid",iGroChildView.getCid ()+"");

        shopCar.groChildGroup (map);
    }

    @Override
    public void ShowGroChildDatas(List<GroChildBean.DataBean> list) {
        Log.d (TAG, "ShowGroChildDatas:(右) "+list.size ());

        iGroChildView.ShowGroChildToViews (list);
    }

    //商品列表
    @Override
    public void ShowGoodsToViews(ShopCar shopCar, IGoodsView iGoodsView,int position) {
        this.iGoodsView = iGoodsView;

        Map<String,String> map = new HashMap<> ();

        map.put ("pscid",iGoodsView.getPscid ()+"");

        shopCar.goodsMsg (map);
    }

    @Override
    public void ShowGoodsData(List<GoodsBean.DataBean> list) {
        iGoodsView.ShowGoodsToViews (list);
    }

    //商品详情页
    @Override
    public void ShowGoodsPageToViews(ShopCar shopCar, IGoodsPageView iGoodsPageView,int position) {
        this.iGoodsPageView = iGoodsPageView;

        Map<String,String> map = new HashMap<> ();

        map.put ("pid",iGoodsPageView.getPid ()+"");

        shopCar.goodsPage (map);
    }

    @Override
    public void ShowGoodsNameMsg(GoodsName.DataBean data) {
        Log.d (TAG, "ShowGoodsNameMsg: 详情页"+data);

        iGoodsPageView.ShowGoodsPageToView (data);
    }

    @Override
    public void ShowAddgToViews(ShopCar shopCar, IAddgView iAddgView,int pid) {
        this.iAddgView = iAddgView;

        Map<String,String> map = new HashMap<> ();

        map.put ("uid","1");

        map.put ("pid",iAddgView.getAddPid ()+"");

        shopCar.addGoods (map);
    }

    @Override
    public void ShowAddgData(String msg) {
        Log.d (TAG, "ShowAddgData:(加入购物车----) "+msg);

      iAddgView.ShowAddgToViews (msg);
    }

}
