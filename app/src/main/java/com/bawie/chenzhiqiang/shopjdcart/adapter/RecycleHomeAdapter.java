package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.bawie.chenzhiqiang.shopjdcart.bean.HomeBean;
import com.bawie.chenzhiqiang.shopjdcart.qrcode.activity.CaptureActivity;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.MainActivity;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.MyBanner;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.SelectActivity;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sunfusheng.marqueeview.MarqueeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecycleHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecycleHomeAdapter---";
    private final Context context;
    private final int REQUEST_CODE = 0;
    private final List<HomeBean.DataBean> data;
    private final List<HomeBean.TuijianBean.ListBean> list1;
    private final List<GroupBean.DataBean> list2;
    private int num1 = 1;
    private int num2 = 2;
    private int num3 = 3;
    private int num4 = 4;
    private int num5 = 5;
    private MyViewHodler1 hodler1;
    private MyViewHodler2 hodler2;
    private MyViewHodler3 hodler3;
    private MyViewHodler4 hodler4;
    private MyViewHodler5 hodler5;


    public RecycleHomeAdapter(Context context, List<HomeBean.DataBean> data,List<HomeBean.TuijianBean.ListBean> list1,List<GroupBean.DataBean> list2){
        this.context = context;
        this.data = data;
        this.list1 = list1;
        this.list2 = list2;

        Log.d (TAG, "RecycleHomeAdapter:88888888888888 "+list2.size ());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == num1){
            View view = View.inflate (context, R.layout.home_header, null);

            hodler1 = new MyViewHodler1 (view);

            return hodler1;
        }
        if(viewType == num2){
            View view = View.inflate (context, R.layout.home_nine, null);

            hodler2 = new MyViewHodler2 (view);

            return hodler2;
        }
        if(viewType == num3){
            View view = View.inflate (context, R.layout.home_msg, null);

            hodler3 = new MyViewHodler3 (view);

            return hodler3;
        }
        if(viewType == num4){
            View view = View.inflate (context, R.layout.home_time, null);

            hodler4 = new MyViewHodler4 (view);

            return hodler4;
        }
        if(viewType == num5){
            View view = View.inflate (context, R.layout.home_banner, null);

            hodler5 = new MyViewHodler5 (view);

            return hodler5;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if(holder instanceof MyViewHodler1){
              hodler1 = (MyViewHodler1) holder;

              List<SimpleDraweeView> list1 = new ArrayList<> ();

              for (int i=0;i<data.size ();i++){
                  String icon = data.get (i).getIcon ();

                  SimpleDraweeView draweeView = new SimpleDraweeView (context);

                  Log.d (TAG, "home_header: "+draweeView);

                  draweeView.setScaleType (ImageView.ScaleType.CENTER_CROP);

                  Log.d (TAG, "home_header: "+icon);

                  Glide.with (context).load (icon).into (draweeView);

                  list1.add (draweeView);
              }

             MyBanner.MyPagerAdapter pagerAdapter = new MyBanner.MyPagerAdapter (list1);

             Log.d (TAG, "home_header: "+list1.size ());

              hodler1.banner_point.setAdapter (pagerAdapter);

              hodler1.banner_point.start ();

              //判定版本
             initPermission();

             //扫描
              hodler1.select_pic.setOnClickListener (new View.OnClickListener () {
                  @Override
                  public void onClick(View v) {
                      Toast.makeText (context, "扫描开始--", Toast.LENGTH_SHORT).show ();

                      Intent intent = new Intent (context, CaptureActivity.class);

                      context.startActivity (intent);
                  }
              });

              //搜索
              hodler1.select.setOnClickListener (new View.OnClickListener () {
                  @Override
                  public void onClick(View v) {
                      context.startActivity (new Intent (context,SelectActivity.class));
                  }
              });

         }else if(holder instanceof MyViewHodler2){

             hodler2 = (MyViewHodler2) holder;

             hodler2.recycle_nine.setLayoutManager (new GridLayoutManager (context, 2, OrientationHelper.HORIZONTAL,false));

             RecyclegroAdapter recyclegroAdapter = new RecyclegroAdapter (context,list2);

             Log.d (TAG, "九宫格://////////////////////777 "+list2.size ());

             hodler2.recycle_nine.setAdapter (recyclegroAdapter);

         }else if(holder instanceof MyViewHodler3){
             hodler3 = (MyViewHodler3) holder;

             List<String> notices = new ArrayList<> ();

             notices.add ("VR大潮势不可挡，政府大力扶植发展");
             notices.add ("VR渗透全行业，将成为时代主流");
             notices.add ("巨头企业VR争夺战打向响");
             notices.add ("知名公司招聘VR高薪岗位一览");
             notices.add ("迎接VR虚拟现实的出现！");

             hodler3.marqueeView.startWithList (notices);
             hodler3.marqueeView.startWithList (notices, R.anim.anim_bottom_in, R.anim.anim_top_out);

         }else if(holder instanceof MyViewHodler4){
             hodler4 = (MyViewHodler4) holder;

             handler.sendEmptyMessage(0);
         }else if(holder instanceof MyViewHodler5){
            hodler5 = (MyViewHodler5) holder;

             hodler5.banner.setLayoutManager (new GridLayoutManager (context,2, LinearLayoutManager.VERTICAL,false));

             MsAdapter msAdapter = new MsAdapter (context, list1);

             hodler5.banner.setAdapter (msAdapter);
         }
    }

    private void initPermission() {
        /*if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]	{Manifest.permission.CAMERA}, 1);
        }*/
    }

    private Handler handler = new Handler () {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            handler.sendEmptyMessageDelayed(0, 1000);
        }

        private void countDown() {
            SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date (System.currentTimeMillis());
            String format = df.format(curDate);
            StringBuffer buffer = new StringBuffer();
            String substring = format.substring(0, 11);
            buffer.append(substring);
            Log.d("ccc", substring);
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour % 2 == 0) {
                hodler4.tv_miaosha_time.setText(hour + "点场");
                buffer.append((hour + 2));
                buffer.append(":00:00");
            } else {
                hodler4.tv_miaosha_time.setText((hour - 1) + "点场");
                buffer.append((hour + 1));
                buffer.append(":00:00");
            }
            String totime = buffer.toString();
            try {
                java.util.Date date = df.parse(totime);
                java.util.Date date1 = df.parse(format);
                long defferenttime = date.getTime() - date1.getTime();
                long days = defferenttime / (1000 * 60 * 60 * 24);
                long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = defferenttime % 60000;
                long second = Math.round((float) seconds / 1000);
                hodler4.tv_miaosha_shi.setText("0" + hours + "");
                if (minute >= 10) {
                    hodler4.tv_miaosha_minter.setText(minute + "");
                } else {
                    hodler4.tv_miaosha_minter.setText("0" + minute + "");
                }
                if (second >= 10) {
                    hodler4.tv_miaosha_second.setText(second + "");
                } else {
                    hodler4.tv_miaosha_second.setText("0" + second + "");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return num1;
        }
        if(position == 1){
            return num2;
        }
        if(position == 2){
            return num3;
        }
        if(position == 3){
            return num4;
        }
        if(position == 4){
            return num5;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    //轮播图
    class MyViewHodler1 extends RecyclerView.ViewHolder{

        private final MyBanner banner_point;
        private final ImageView select_pic;
        private final ImageView select;

        public MyViewHodler1(View itemView) {
            super (itemView);

            banner_point = itemView.findViewById (R.id.banner_point);

            select_pic = itemView.findViewById (R.id.select_pic);

            select = itemView.findViewById (R.id.select);

        }
    }

    //九宫格
    class MyViewHodler2 extends RecyclerView.ViewHolder{

        private final RecyclerView recycle_nine;

        public MyViewHodler2(View itemView) {
            super (itemView);

            recycle_nine = itemView.findViewById (R.id.recycle_nine);

        }
    }

    //京东快报
    class MyViewHodler3 extends RecyclerView.ViewHolder{

        private final MarqueeView marqueeView;

        public MyViewHodler3(View itemView) {
            super (itemView);

            marqueeView = itemView.findViewById (R.id.marquee);
        }
    }

    //秒杀倒计时
    class MyViewHodler4 extends RecyclerView.ViewHolder{

        private final TextView tv_miaosha_shi;
        private final TextView tv_miaosha_time;
        private final TextView tv_miaosha_minter;
        private final TextView tv_miaosha_second;


        public MyViewHodler4(View itemView) {
            super (itemView);

            tv_miaosha_shi = itemView.findViewById(R.id.tv_miaosha_shi);
            tv_miaosha_time = itemView.findViewById(R.id.tv_miaosha_time);
            tv_miaosha_minter = itemView.findViewById(R.id.tv_miaosha_minter);
            tv_miaosha_second = itemView.findViewById (R.id.tv_miaosha_second);
        }
    }

    //推荐
    class MyViewHodler5 extends RecyclerView.ViewHolder{

        private final RecyclerView banner;

        public MyViewHodler5(View itemView) {
            super (itemView);

            banner = itemView.findViewById (R.id.banner_recycle);
        }
    }
}
