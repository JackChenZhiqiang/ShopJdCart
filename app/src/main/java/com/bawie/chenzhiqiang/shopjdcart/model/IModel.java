package com.bawie.chenzhiqiang.shopjdcart.model;

import java.util.Map;

public interface IModel {
    //登录
    void login(Map<String,String> map);

    //注册
    void reg(Map<String,String> map);
}
