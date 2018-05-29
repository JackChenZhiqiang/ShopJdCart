package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GoodsBean;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.GoodsPidActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GoodsRecycleAdapter extends RecyclerView.Adapter<GoodsRecycleAdapter.MyViewHolder> {
    private static final String TAG = "GoodsRecycleAdapter--";
    private final Context context;
    private final List<GoodsBean.DataBean> list;
    private MyViewHolder holder;

    public GoodsRecycleAdapter(Context context, List<GoodsBean.DataBean> list){
        Log.d (TAG, "GoodsRecycleAdapter: "+list.size ());

        this.context = context;

        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate (context, R.layout.goods_item, null);

        holder = new MyViewHolder (view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String images = list.get (position).getImages ();

        String s = images.split ("\\|")[0];

        holder.goods_draw.setImageURI (s);

        holder.goods_tv1.setText (list.get (position).getTitle ());

        double bargainPrice = list.get (position).getBargainPrice ();

        holder.goods_tv2.setText ("$"+bargainPrice);

        //得到当前子条目，并设置点击事件
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, GoodsPidActivity.class);

                int pid = list.get (position).getPid ();

                intent.putExtra ("key",pid);

                context.startActivity (intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView goods_draw;
        private final TextView goods_tv1;
        private final TextView goods_tv2;

        public MyViewHolder(View itemView) {
            super (itemView);

            goods_draw = itemView.findViewById (R.id.goods_draw);
            goods_tv1 = itemView.findViewById (R.id.goods_tv1);
            goods_tv2 = itemView.findViewById (R.id.goods_tv2);
        }
    }
}
