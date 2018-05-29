package com.bawie.chenzhiqiang.shopjdcart.model;

import android.util.Log;

import com.bawie.chenzhiqiang.shopjdcart.http.MyService;
import com.bawie.chenzhiqiang.shopjdcart.http.utils.RetrofitUtils;
import com.bawie.chenzhiqiang.shopjdcart.bean.LoginBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.RegBean;
import com.bawie.chenzhiqiang.shopjdcart.presenter.IPresenter;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpel implements IModel {
    private final IPresenter iPresenter;
    private static final String TAG = "ModelImpel-------";

    public ModelImpel(IPresenter iPresenter){
        this.iPresenter = iPresenter;
    }

    @Override
    public void login(Map<String, String> map) {
        RetrofitUtils retrofitUtils = RetrofitUtils.getInstance ();

        MyService myService = retrofitUtils.createRequest (MyService.class);

        Observable<LoginBean> observable = myService.loginPost (map);

        observable.subscribeOn (Schedulers.newThread ())
                  .observeOn (AndroidSchedulers.mainThread ())
                  .subscribe (new Observer<LoginBean> () {
                      @Override
                      public void onCompleted() {
                          Log.d (TAG, "onCompleted: 登录成功-----");
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.d (TAG, "onError: 登录失败--"+e.getMessage ());
                      }

                      @Override
                      public void onNext(LoginBean loginBean) {
                            if(loginBean.getCode ().equals ("0")){
                                iPresenter.getLogin ();
                            }else{
                                iPresenter.getLoginError ();
                            }
                      }
                  });
    }

    @Override
    public void reg(Map<String, String> map) {
        RetrofitUtils retrofitUtils = RetrofitUtils.getInstance ();

        MyService myService = retrofitUtils.createRequest (MyService.class);

        Observable<RegBean> observable = myService.regPost (map);

        observable.subscribeOn (Schedulers.newThread ())
                  .observeOn (AndroidSchedulers.mainThread ())
                  .subscribe (new Observer<RegBean> () {
                      @Override
                      public void onCompleted() {
                          Log.d (TAG, "onCompleted: 注册成功-----");
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.d (TAG, "onError: 注册失败--"+e.getMessage ());
                      }

                      @Override
                      public void onNext(RegBean regBean) {
                           if(regBean.getCode ().equals ("0")){
                                iPresenter.getReg ();
                           }else{
                               iPresenter.getRegError ();
                           }
                      }
                  });
    }
}
