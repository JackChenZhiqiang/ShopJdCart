package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private TextView sub;
    private TextView add;
    private EditText count;
    private AddClickListener addClickListener;
    private SubClickListener subClickListener;
    private static final String TAG = "AddSubView------------";
    public AddSubView(Context context) {
        this(context, null);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.activity_add_sub, this);
        //获取控件
        sub = view.findViewById(R.id.sub);
        add = view.findViewById(R.id.add);
        count = view.findViewById(R.id.count);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //减少
            case R.id.sub:
                String c = count.getText().toString();
                int i = Integer.parseInt(c);
                if (i <= 0) {
                    return;
                }
                count.setText(--i + "");
                subClickListener.onSubClick(v,i);
                Log.d(TAG, "减-----" + i);

                break;
            //增加
            case R.id.add:
                String c1 = count.getText().toString();
                int i1 = Integer.parseInt(c1);

                count.setText(++i1 + "");
                addClickListener.onAddClick(v,i1);
                Log.d(TAG, "onClick() returned: " + "加===");
                break;
        }
    }

    public int getCount() {
        return Integer.parseInt(count.getText().toString().trim());
    }

    public void setCount(int s) {
        count.setText(s + "");
    }

    public interface AddClickListener {
        void onAddClick(View view, int count);
    }

    public interface SubClickListener {
        void onSubClick(View view, int count);
    }

    public void setOnAddClickListener(AddClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

    public void setOnSubClickListener(SubClickListener subClickListener) {
        this.subClickListener = subClickListener;
    }

}
