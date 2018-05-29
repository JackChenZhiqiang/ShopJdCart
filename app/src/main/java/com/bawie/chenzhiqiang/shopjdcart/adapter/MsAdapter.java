package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MsAdapter extends RecyclerView.Adapter<MsAdapter.MyViewHodler1> {
    private final Context context;
    private final List<HomeBean.TuijianBean.ListBean> list;

    public MsAdapter(Context context, List<HomeBean.TuijianBean.ListBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate (context, R.layout.banner_item, null);

        MyViewHodler1 hodler = new MyViewHodler1 (view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler1 holder, int position) {
        String images = list.get (position).getImages ();

        String s1 = images.split ("\\|")[0];

        holder.banner_draw.setImageURI (s1);

        String title = list.get (position).getTitle ();

        holder.banner_tv.setText (title);
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHodler1 extends RecyclerView.ViewHolder{

        private final SimpleDraweeView banner_draw;
        private final TextView banner_tv;

        public MyViewHodler1(View itemView) {
            super (itemView);

            banner_draw = itemView.findViewById (R.id.banner_draw);
            banner_tv = itemView.findViewById (R.id.banner_tv);
        }
    }
}
