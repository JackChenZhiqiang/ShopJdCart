package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MyBanner extends RelativeLayout{
    //存放图片
    private static  List<SimpleDraweeView> images = new ArrayList<> ();

    //存放小圆点
    private static  List<SimpleDraweeView> points = new ArrayList<> ();

    private ViewPager vp;
    private LinearLayout ll_point;
    private Context mcontext;
    private MyHandler myHandler = new MyHandler ();
    private static final String TAG = "MyBanner----";

    public MyBanner(Context context) {
        this (context,null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this (context, attrs,0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);

        View view = View.inflate (context, R.layout.activity_banner, this);

        mcontext = context;

        vp = view.findViewById (R.id.vp);

        ll_point = view.findViewById (R.id.ll_point);

        //设置图片的滑动事件
        vp.setOnPageChangeListener (new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % points.size ();

                for (int i=0;i<points.size ();i++){
                    if(position==i){
                        points.get(position).setSelected(true);
                    }else{
                        points.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //开始
    public void start(){
        myHandler.sendEmptyMessageDelayed (0,1000);
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage (msg);

            vp.setCurrentItem (vp.getCurrentItem ()+1);

            myHandler.sendEmptyMessageDelayed (0,1000);
        }
    }

    //停止轮播
    public void stop(){
        myHandler.removeCallbacksAndMessages(null);
    }

    public void setAdapter(PagerAdapter pagerAdapter){
        vp.setAdapter (pagerAdapter);

        //添加小圆点
        for(int i=0;i<images.size ();i++){
            SimpleDraweeView draweeView = new SimpleDraweeView (mcontext);

            draweeView.setImageResource (R.drawable.point_selected);

            //实现圆点之间的距离
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(17,0,0,0);

            draweeView.setLayoutParams (params);

            points.add (draweeView);

            ll_point.addView (draweeView);

        }

        points.get (0).setSelected (true);
    }

    public static class MyPagerAdapter extends PagerAdapter{

        public MyPagerAdapter(List<SimpleDraweeView> list1){
            images = list1;
            Log.d (TAG, "MyPagerAdapter: "+list1.size ());
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position%images.size();

            container.addView(images.get(position));

            return images.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
