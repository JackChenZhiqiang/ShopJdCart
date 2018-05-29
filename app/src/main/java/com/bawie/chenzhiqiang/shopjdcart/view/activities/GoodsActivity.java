package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.adapter.GoodsRecycleAdapter;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsBean;
import com.bawie.chenzhiqiang.shopjdcart.model.ShopGoods;
import com.bawie.chenzhiqiang.shopjdcart.presenter.ShowShop;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IGoodsView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends AppCompatActivity implements IGoodsView{
    private static final String TAG = "GoodsActivity---";
    private RecyclerView recyclerView;
    private int pscid1;
    private Banner myBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_goods);

        recyclerView = (RecyclerView) findViewById (R.id.recycle);

        myBanner = (Banner) findViewById (R.id.myBanner);

        Intent intent = getIntent ();

        if(intent != null){
            pscid1 = intent.getIntExtra ("key", 0);

            Log.d (TAG, "onCreate://///-----***** "+pscid1);
        }

        ShowShop showShop = new ShowShop ();

        showShop.ShowGoodsToViews (new ShopGoods (showShop),this,pscid1);

        initDatas();
    }

    private void initDatas() {
        //图片路径
        String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527932296&di=ecf88046e3ce259ead97a1c61f588e7c&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F91529822720e0cf3eefe87d60146f21fbe09aa96.jpg";
        String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527337757619&di=55a51af24cf8635640dc434e9bb778d9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F036882057721e540000018c1b36a48e.jpg";
        String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527932363&di=5e8110d409054ba8dc9f98a7157fcd69&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F42166d224f4a20a47799f7ea9b529822720ed01c.jpg";
        String url4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527337774716&di=6ac0e5b5d205c1cd1853391843109ea0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F018221574bf5fa6ac72525ae8d5a25.jpg%40900w_1l_2o_100sh.jpg";
        String url5 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527932444&di=3961c2d6672bbbb20c5a1cb843c2a157&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F016ef657bd42cf0000012e7e08af75.jpg%402o.jpg";
        String url6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527337795565&di=e73f0e1f0dc02d9804fe0514dc9ee34f&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01123258819993a8012060c8045e86.jpg%401280w_1l_2o_100sh.jpg";

        List<String> list1 = new ArrayList<> ();

        list1.add (url1);
        list1.add (url2);
        list1.add (url3);
        list1.add (url4);
        list1.add (url5);
        list1.add (url6);

        myBanner.setImageLoader (new BannerActivity ());

        myBanner.setImages (list1);

        //开始
        myBanner.start ();
    }

    @Override
    public void ShowGoodsToViews(List<GoodsBean.DataBean> list) {
        Log.d (TAG, "ShowGoodsToViews: 商品列表"+list.size ());

        recyclerView.setLayoutManager (new LinearLayoutManager (this,LinearLayoutManager.VERTICAL,false));

        GoodsRecycleAdapter recycleAdapter = new GoodsRecycleAdapter (this, list);

        recyclerView.setAdapter (recycleAdapter);
    }

    @Override
    public int getPscid() {
        return pscid1;
    }
}
