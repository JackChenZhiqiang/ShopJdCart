package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.GoodsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GridegroAdapter extends BaseAdapter {
    private static final String TAG = "GridegroAdapter--";
    private final Context context;
    private final List<GroChildBean.DataBean.ListBean> list1;
    private MyViewHodler hodler;

    public GridegroAdapter(Context context, List<GroChildBean.DataBean.ListBean> list1){
        this.context = context;

        this.list1 = list1;
    }

    @Override
    public int getCount() {
        return list1.size ();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate (context, R.layout.right_item,null);

            SimpleDraweeView right_draw = convertView.findViewById (R.id.right_draw);

            TextView right_tv = convertView.findViewById (R.id.right_tv);

            hodler = new MyViewHodler (right_tv,right_draw);

            convertView.setTag (hodler);
        }else{
            hodler = (MyViewHodler) convertView.getTag ();
        }

        String icon = list1.get (position).getIcon ();

        Log.d (TAG, "getView: 图片"+icon);

        hodler.getRight_draw ().setImageURI (icon);

        String name = list1.get (position).getName ();

        Log.d (TAG, "getView: 内容数据信息"+name);

        hodler.getRight_tv ().setText (name);

        //设置点击事件
        hodler.getRight_draw ().setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, GoodsActivity.class);

                int pscid = list1.get (position).getPscid ();

                intent.putExtra ("key",pscid);

                context.startActivity (intent);
            }
        });

        return convertView;
    }

    class MyViewHodler{
        TextView right_tv;
        SimpleDraweeView right_draw;

        public MyViewHodler(TextView right_tv, SimpleDraweeView right_draw) {
            this.right_tv = right_tv;
            this.right_draw = right_draw;
        }

        public TextView getRight_tv() {
            return right_tv;
        }

        public void setRight_tv(TextView right_tv) {
            this.right_tv = right_tv;
        }

        public SimpleDraweeView getRight_draw() {
            return right_draw;
        }

        public void setRight_draw(SimpleDraweeView right_draw) {
            this.right_draw = right_draw;
        }
    }
}
