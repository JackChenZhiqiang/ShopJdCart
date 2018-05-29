package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.model.ModelImpel;
import com.bawie.chenzhiqiang.shopjdcart.presenter.PresenterImpel;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.ILoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends Activity implements ILoginView {
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.deng)
    Button deng;
    @BindView(R.id.qq_login)
    ImageView qqLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        setContentView (R.layout.layout_login);

        ButterKnife.bind (this);

        initViews();

        //动态权限获取 6.0
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    private void initViews() {
        register.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (MyActivity.this,RegActivity.class));
            }
        });

        deng.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                PresenterImpel presenterImpel = new PresenterImpel ();

                presenterImpel.loginPresenter (new ModelImpel (presenterImpel),MyActivity.this);
            }
        });

        qqLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                UMShareAPI.get (MyActivity.this).getPlatformInfo(MyActivity.this, SHARE_MEDIA.SINA, authListener);

                Toast.makeText (MyActivity.this, "第三方登录", Toast.LENGTH_SHORT).show ();
            }
        });
    }


    //登录监听
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MyActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MyActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MyActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    //QQ
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getMobile() {
        return edName.getText ().toString ();
    }

    @Override
    public String getPwd() {
        return edPass.getText ().toString ();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText (this, "恭喜用户登录成功！", Toast.LENGTH_SHORT).show ();
    }

    @Override
    public void loginError() {
        Toast.makeText (this, "登录失败，请重新登录！", Toast.LENGTH_SHORT).show ();
    }
}
