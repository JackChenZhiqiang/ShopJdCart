package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.model.ModelImpel;
import com.bawie.chenzhiqiang.shopjdcart.presenter.PresenterImpel;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IRegView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegActivity extends Activity implements IRegView {
    @BindView(R.id.fan)
    TextView fan;
    @BindView(R.id.ename)
    EditText ename;
    @BindView(R.id.epass)
    EditText epass;
    @BindView(R.id.but_zc)
    Button butZc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        setContentView (R.layout.layout_reg);

        ButterKnife.bind (this);

        fan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (RegActivity.this,MyActivity.class));
            }
        });

        butZc.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                PresenterImpel presenterImpel = new PresenterImpel ();

                presenterImpel.regPresenter (new ModelImpel (presenterImpel),RegActivity.this);
            }
        });
    }

    @Override
    public String getMobile() {
        return ename.getText ().toString ();
    }

    @Override
    public String getPwd() {
        return epass.getText ().toString ();
    }

    @Override
    public void regSuccess() {
        Toast.makeText (this, "恭喜新用户注册成功，请登录！", Toast.LENGTH_SHORT).show ();

        startActivity (new Intent (RegActivity.this,MyActivity.class));
    }

    @Override
    public void regError() {
        Toast.makeText (this, "用户名和密码有误，请重新注册！", Toast.LENGTH_SHORT).show ();
    }
}
