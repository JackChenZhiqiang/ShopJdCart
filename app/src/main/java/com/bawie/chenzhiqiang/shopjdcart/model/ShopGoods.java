package com.bawie.chenzhiqiang.shopjdcart.model;

import android.util.Log;

import com.bawie.chenzhiqiang.shopjdcart.bean.AddShopBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsName;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.HomeBean;
import com.bawie.chenzhiqiang.shopjdcart.http.MyShopService;
import com.bawie.chenzhiqiang.shopjdcart.http.utils.ShopUtils;
import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;
import com.bawie.chenzhiqiang.shopjdcart.presenter.ShopPresenter;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShopGoods implements ShopCar {
    private static final String TAG = "ShopGoods--";
    private final ShopPresenter shopPresenter;

    public ShopGoods(ShopPresenter shopPresenter){
        this.shopPresenter = shopPresenter;
    }

    //首页
    @Override
    public void home(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<HomeBean> observable = myShopService.getHomeShop (map);

        observable.subscribeOn (Schedulers.newThread ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<HomeBean> () {
                    @Override
                    public void onCompleted() {
                        Log.d (TAG, "onCompleted: 完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d (TAG, "onError: 失败"+e.getMessage ());
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.d (TAG, "onNext: "+homeBean.getData ());
                        shopPresenter.ShowHomes (homeBean);
                    }
                });

    }

    //购物车
    @Override
    public void shop(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<ShopBean> observable = myShopService.getShops (map);

        observable.subscribeOn (Schedulers.newThread ())
                  .observeOn (AndroidSchedulers.mainThread ())
                  .subscribe (new Observer<ShopBean> () {
                      @Override
                      public void onCompleted() {
                          Log.d (TAG, "onCompleted: 完成");
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.d (TAG, "onError: 失败"+e.getMessage ());
                      }

                      @Override
                      public void onNext(ShopBean shopBean) {
                          shopPresenter.ShowGoods (shopBean.getData ());
                          Log.d (TAG, "onNext: "+shopBean);
                      }
                  });
    }

    //分类
    @Override
    public void groHome(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<GroupBean> observable = myShopService.getgroShop (map);

        observable.subscribeOn (Schedulers.newThread ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<GroupBean> () {
                    @Override
                    public void onCompleted() {
                        Log.d (TAG, "onCompleted: 完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d (TAG, "onError: 失败"+e.getMessage ());
                    }

                    @Override
                    public void onNext(GroupBean groupBean) {
                       shopPresenter.ShowgroHomes (groupBean.getData ());
                        Log.d (TAG, "九宫格: "+groupBean);
                    }
                });
    }

    //分类子接口
    @Override
    public void groChildGroup(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<GroChildBean> observable = myShopService.getgroChildShop (map);

        observable.subscribeOn (Schedulers.newThread ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<GroChildBean> () {
                    @Override
                    public void onCompleted() {
                        Log.d (TAG, "onCompleted: 完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d (TAG, "onError: 失败"+e.getMessage ());
                    }

                    @Override
                    public void onNext(GroChildBean groChildBean) {
                        Log.d (TAG, "onNext:(右) "+groChildBean);
                        shopPresenter.ShowGroChildDatas (groChildBean.getData ());
                    }
                });
    }

    //商品列表
    @Override
    public void goodsMsg(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<GoodsBean> observable = myShopService.getGoodMsg (map);

        observable.subscribeOn (Schedulers.newThread ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<GoodsBean> () {
                    @Override
                    public void onCompleted() {
                        Log.d (TAG, "onCompleted: 完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d (TAG, "onError: 失败"+e.getMessage ());
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                       shopPresenter.ShowGoodsData (goodsBean.getData ());
                    }
                });
    }

    //商品详情页
    @Override
    public void goodsPage(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<GoodsName> observable = myShopService.getGoodsPid (map);

        observable.subscribeOn (Schedulers.newThread ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<GoodsName> () {
                    @Override
                    public void onCompleted() {
                        Log.d (TAG, "onCompleted: 完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d (TAG, "onError: 失败"+e.getMessage ());
                    }

                    @Override
                    public void onNext(GoodsName goodsName) {
                        Log.d (TAG, "onNext: (**商品详情页**)"+goodsName);

                        shopPresenter.ShowGoodsNameMsg (goodsName.getData ());
                    }
                });
    }

    //添加购物车
    @Override
    public void addGoods(Map<String, String> map) {
        ShopUtils shopUtils = ShopUtils.getInstance ();

        MyShopService myShopService = shopUtils.createRequest (MyShopService.class);

        Observable<AddShopBean> observable = myShopService.getAddShop (map);

        observable.subscribeOn (Schedulers.newThread ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<AddShopBean> () {
                    @Override
                    public void onCompleted() {
                        Log.d (TAG, "onCompleted: 完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d (TAG, "onError: 失败"+e.getMessage ());
                    }

                    @Override
                    public void onNext(AddShopBean addShopBean) {
                        Log.d (TAG, "加入购物车: "+addShopBean.getMsg ());

                         shopPresenter.ShowAddgData (addShopBean.getMsg ());
                    }
                });
    }
}
