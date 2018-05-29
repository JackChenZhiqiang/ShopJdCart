package com.bawie.chenzhiqiang.shopjdcart.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.http.utils.SumUtils;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopGoods;
import com.bawie.chenzhiqiang.shopjdcart.adapter.MyExpendAdapter;
import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;
import com.bawie.chenzhiqiang.shopjdcart.presenter.ShowShop;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IShopView;

import java.util.List;

public class ShopFragment extends Fragment implements IShopView,View.OnClickListener{
    private static final String TAG = "ShopFragment-----";
    private ExpandableListView expandableListView;
    private TextView sum;
    private Button js;
    private CheckBox cb;
    private CheckBox checkBox;
    private List<ShopBean.DataBean> list;
    private MyExpendAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate (getActivity (), R.layout.fragment_shop, null);
        Log.d (TAG, "onCreateView: 11111111111111111111111111111");

        ShowShop showShop = new ShowShop ();

        showShop.ShowShopToView (new ShopGoods (showShop),this);

        Log.d (TAG, "onCreateView: 22222222222222222222222");

        expandableListView = view.findViewById (R.id.expandable_list_view);
        checkBox = view.findViewById(R.id.cb);
        sum = view.findViewById(R.id.sum);
        js = view.findViewById(R.id.js);
        cb = view.findViewById (R.id.cb);
        cb.setOnClickListener (this);
        checkBox.setOnClickListener(this);
        js.setOnClickListener(this);

        return view;
    }

    @Override
    public void ShowShopToViews(List<ShopBean.DataBean> list) {
        Log.d (TAG, "ShowShopToViews://////////// "+list.size ());

        this.list = list;

        adapter = new MyExpendAdapter (getActivity (), list, this);

        expandableListView.setAdapter(adapter);

        int childCount = expandableListView.getCount ();

        for (int i=0;i<childCount;i++){
            expandableListView.expandGroup (i);
        }
    }

    @Override
    public void ShowCount(String count) {
        Log.d (TAG, "总价: "+count);

        sum.setText ("总价："+count);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb:
                boolean checked = cb.isChecked ();

                for (int i=0;i<list.size ();i++){
                    ShopBean.DataBean dataBean = list.get (i);
                    dataBean.setParent_flag (checked);

                    List<ShopBean.ChildBean> list1 = dataBean.getList ();

                    for (int j=0;j<list1.size ();j++){
                        list1.get (j).setChild_flag (checked);
                    }
                }

                adapter.notifyDataSetChanged ();

                //计算。。。。
                String count = SumUtils.sum (list);

                sum.setText (count);
                break;
            case R.id.js:
                Toast.makeText (getActivity (),"结算了-----",Toast.LENGTH_SHORT).show ();
                break;
        }
    }
}
