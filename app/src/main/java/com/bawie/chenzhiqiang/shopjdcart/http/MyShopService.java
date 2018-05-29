package com.bawie.chenzhiqiang.shopjdcart.http;

import com.bawie.chenzhiqiang.shopjdcart.bean.AddShopBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsName;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.HomeBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface MyShopService {
    //首页
    @GET("ad/getAd")
    Observable<HomeBean> getHomeShop(@QueryMap Map<String,String> map);

    //购物车
    @GET("product/getCarts")
    Observable<ShopBean> getShops(@QueryMap Map<String,String> map);

    //分类
    @GET("product/getCatagory")
    Observable<GroupBean> getgroShop(@QueryMap Map<String,String> map);

    //分类子接口
    @GET("product/getProductCatagory")
    Observable<GroChildBean> getgroChildShop(@QueryMap Map<String,String> map);

    //商品列表
    @GET("product/getProducts")
    Observable<GoodsBean> getGoodMsg(@QueryMap Map<String,String> map);

    //商品详情页
    @GET("product/getProductDetail")
    Observable<GoodsName> getGoodsPid(@QueryMap Map<String,String> map);

    //添加购物车
    @GET("product/addCart")
    Observable<AddShopBean> getAddShop(@QueryMap Map<String,String> map);


}
