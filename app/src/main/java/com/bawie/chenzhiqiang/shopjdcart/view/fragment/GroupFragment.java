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
import android.widget.Toast;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.adapter.CatagoryAdapter;
import com.bawie.chenzhiqiang.shopjdcart.adapter.ProductAdapter;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopGoods;
import com.bawie.chenzhiqiang.shopjdcart.presenter.ShowShop;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IGroChildView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IgroHomeView;

import java.util.List;

public class GroupFragment extends Fragment implements IgroHomeView,IGroChildView{
    private static final String TAG = "GroupFragment---";
    private RecyclerView left_group;
    private RecyclerView right_group;
    private static List<GroChildBean.DataBean> list;
    private ShowShop showShop;
    private int cid = 1 ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate (getActivity (), R.layout.fragment_group, null);

        left_group = view.findViewById (R.id.left_group);
        right_group = view.findViewById (R.id.right_group);

        showShop = new ShowShop ();

        showShop.ShowgroHomeToView (new ShopGoods (showShop),this);

        showShop.ShowGroChildToView (new ShopGoods (showShop),GroupFragment.this,cid);

        return view;
    }

    @Override
    public void ShowGroChildToViews(List<GroChildBean.DataBean> list) {
        Log.d (TAG, "ShowGroChildToView:(right) "+list.size ());

        right_group.setLayoutManager (new LinearLayoutManager (getActivity (), OrientationHelper.VERTICAL,false));

        ProductAdapter productAdapter = new ProductAdapter (getActivity (), list);

        right_group.setAdapter (productAdapter);
    }

    @Override
    public void ShowgroHomeViews(final List<GroupBean.DataBean> list) {
        Log.d (TAG, "ShowgroHomeViews:(left) "+list.size ());

        left_group.setLayoutManager (new LinearLayoutManager (getActivity (),LinearLayoutManager.VERTICAL,false));

        CatagoryAdapter catagoryAdapter = new CatagoryAdapter (getActivity (), list);

        catagoryAdapter.setOnItemClickListener (new CatagoryAdapter.OnItemClickListener () {

            @Override
            public void onClickChild(int position) {

                cid = list.get (position).getCid ();

                showShop.ShowGroChildToView (new ShopGoods (showShop),GroupFragment.this,cid);


                Toast.makeText(getActivity (), "cid:" + GroupFragment.this.cid, Toast.LENGTH_SHORT).show();
            }
        });

        left_group.setAdapter (catagoryAdapter);
    }

    @Override
    public int getCid() {
        return cid;
    }


}
