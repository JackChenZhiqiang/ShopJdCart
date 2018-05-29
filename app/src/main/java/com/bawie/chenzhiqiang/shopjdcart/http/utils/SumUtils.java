package com.bawie.chenzhiqiang.shopjdcart.http.utils;

import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;

import java.util.List;

/**
 * Created by a on 2018/5/2.
 */

public class SumUtils {
    public static String sum(List<ShopBean.DataBean> list){
        double sum=0;

        for (int i=0;i<list.size ();i++){
            List<ShopBean.ChildBean> list1 = list.get (i).getList ();

            for (int j=0;j<list1.size ();j++){
                ShopBean.ChildBean childBean = list1.get (j);

                if(childBean.isChild_flag ()){
                    sum+=childBean.getPrice ()*childBean.getNum ();
                }
            }
        }

        return Double.toString (sum);
    }
}
