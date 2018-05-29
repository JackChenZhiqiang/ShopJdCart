package com.bawie.chenzhiqiang.shopjdcart.presenter;

import com.bawie.chenzhiqiang.shopjdcart.model.IModel;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.ILoginView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IRegView;

import java.util.HashMap;
import java.util.Map;

public class PresenterImpel implements IPresenter {
    private ILoginView iLoginView;
    private IRegView iRegView;

    @Override
    public void loginPresenter(IModel iModel, ILoginView iLoginView) {
        this.iLoginView = iLoginView;

        Map<String,String> map = new HashMap<> ();

        map.put ("mobile",iLoginView.getMobile ());

        map.put ("password",iLoginView.getPwd ());

        iModel.login (map);
    }

    @Override
    public void getLogin() {
        iLoginView.loginSuccess ();
    }

    @Override
    public void getLoginError() {
        iLoginView.loginError ();
    }

    @Override
    public void regPresenter(IModel iModel, IRegView iRegView) {
        this.iRegView = iRegView;

        Map<String,String> map = new HashMap<> ();

        map.put ("mobile",iRegView.getMobile ());

        map.put ("password",iRegView.getPwd ());

        iModel.reg (map);

    }

    @Override
    public void getReg() {
       iRegView.regSuccess ();
    }

    @Override
    public void getRegError() {
        iRegView.regError ();
    }
}
