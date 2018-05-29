package com.bawie.chenzhiqiang.shopjdcart.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.adapter.RecycleHomeAdapter;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.HomeBean;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopCar;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopGoods;
import com.bawie.chenzhiqiang.shopjdcart.presenter.ShowShop;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IHomeView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IgroHomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView, IgroHomeView {
    private static final String TAG = "HomeFragment---";
    private RecyclerView recyclerView;
    private HomeBean homeBean;
    private List<GroupBean.DataBean> list2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_home, container, false);

        Log.d (TAG, "home: ///////////////////////////");
        ShowShop showShop = new ShowShop ();

        showShop.ShowHomeToView (new ShopGoods (showShop), this);

        ShowShop showShop1 = new ShowShop ();

        showShop1.ShowgroHomeToView (new ShopGoods (showShop1), this);

        Log.d (TAG, "home: *******************************");

        recyclerView = view.findViewById (R.id.recycle);

        return view;
    }

    @Override
    public void ShowHomeToViews(HomeBean homeBean) {
        Log.d (TAG, "ShowHomeToViews: " + homeBean);

        List<HomeBean.DataBean> data = homeBean.getData ();

        List<HomeBean.TuijianBean.ListBean> list = homeBean.getTuijian ().getList ();

        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity (), OrientationHelper.VERTICAL,false));

        RecycleHomeAdapter homeAdapter = new RecycleHomeAdapter (getActivity (), data, list, list2);

        recyclerView.setAdapter (homeAdapter);
    }

    @Override
    public void ShowgroHomeViews(List<GroupBean.DataBean> list) {
        list2 = new ArrayList<> ();

        this.list2 = list;

        Log.d (TAG, "ShowgroHomeViews九宫格: " + list2.size ());
    }
}
