package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;

public class SplashActivity extends BaseActivity {
    private View view;
    private TextView animation_tv;
    private MyHandler myHandler = new MyHandler();
    private int time = 5;

    @Override
    protected void initViews() {
        ImageView animation_icon=view.findViewById(R.id.splash_pic);

        animation_tv = view.findViewById(R.id.splash_tv);

        //计时
        myHandler.sendEmptyMessage(0);
    }

    @Override
    View creatView() {
        view = View.inflate(this, R.layout.activity_splash, null);
        return view;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            animation_tv.setText(time+"s跳转");

            if(time==1){
                myHandler.removeCallbacksAndMessages(null);

                startActivity(new Intent (SplashActivity.this,MainActivity.class));
            }
            time--;

            myHandler.sendEmptyMessageDelayed(0,1000);
        }
    }

    //初始化数据信息
    @Override
    protected void initDatas() {

    }

}
