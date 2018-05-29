package com.bawie.chenzhiqiang.shopjdcart.presenter;

import com.bawie.chenzhiqiang.shopjdcart.model.IModel;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.ILoginView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IRegView;

public interface IPresenter {
    //登录
    void loginPresenter(IModel iModel, ILoginView iLoginView);

    void getLogin();

    void getLoginError();

    //注册
    void regPresenter(IModel iModel, IRegView iRegView);

    void getReg();

    void getRegError();
}
