package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsName;
import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopGoods;
import com.bawie.chenzhiqiang.shopjdcart.presenter.ShowShop;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IAddgView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IGoodsPageView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IShopView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GoodsPidActivity extends AppCompatActivity implements IGoodsPageView,IAddgView,IShopView{
    private static final String TAG = "GoodsPidActivity--";
    private RecyclerView recyclerView;
    private int pid;
    private SimpleDraweeView pid_draw;
    private TextView pid_name;
    private TextView pid_shop;
    private TextView pid_price;
    private TextView pid_num;
    private Button add_shop;
    private ShowShop showShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_goods_pid);

        recyclerView = (RecyclerView) findViewById (R.id.recycle);

        Intent intent = getIntent ();

        if(intent != null){
            pid = intent.getIntExtra ("key", 0);
        }

        showShop = new ShowShop ();

        showShop.ShowGoodsPageToViews (new ShopGoods (showShop),this,pid);

        initViews();
    }

    private void initViews() {
        pid_draw = (SimpleDraweeView) findViewById (R.id.pid_draw);
        pid_name = (TextView) findViewById (R.id.pid_name);
        pid_shop = (TextView) findViewById (R.id.pid_shop);
        pid_price = (TextView) findViewById (R.id.pid_price);
        pid_num = (TextView) findViewById (R.id.pid_num);
        add_shop = (Button) findViewById (R.id.add_shop);

        add_shop.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                add();

                showShopping();
            }
        });
    }

    private void add() {
        showShop.ShowAddgToViews (new ShopGoods (showShop),this,pid);
    }

    private void showShopping() {
        showShop.ShowShopToView (new ShopGoods (showShop),GoodsPidActivity.this);
    }

    @Override
    public void ShowGoodsPageToView(GoodsName.DataBean data) {
        Log.d (TAG, "ShowGoodsPageToView: "+data.getTitle ());

        String images = data.getImages ();

        String s = images.split ("\\|")[0];

        pid_draw.setImageURI (s);

        pid_name.setText (data.getTitle ());

        double bargainPrice = data.getBargainPrice ();

        Log.d (TAG, "ShowGoodsPageToView: 价格"+bargainPrice);

        pid_price.setText ("商品价格：￥"+bargainPrice);

        int sellerid = data.getPid ();

        Log.d (TAG, "ShowGoodsPageToView:点击率 "+sellerid);

        pid_num.setText ("快递量："+sellerid);
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public void ShowAddgToViews(String msg) {
        Toast.makeText (this, msg + "--", Toast.LENGTH_SHORT).show ();
    }

    @Override
    public int getAddPid() {
        return pid;
    }

    @Override
    public void ShowShopToViews(List<ShopBean.DataBean> list) {

    }

    @Override
    public void ShowCount(String count) {

    }
}
