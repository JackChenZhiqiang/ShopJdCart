package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RecyclegroAdapter extends RecyclerView.Adapter<RecyclegroAdapter.MyViewHodler> {
    private final Context context;
    private final List<GroupBean.DataBean> list2;
    private static final String TAG = "RecyclegroAdapter---";

    public RecyclegroAdapter(Context context, List<GroupBean.DataBean> list2){
        this.context = context;

        Log.d (TAG, "RecyclegroAdapter: "+list2.size ());

        this.list2 = list2;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate (context, R.layout.nine_item, null);

        MyViewHodler hodler = new MyViewHodler (view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
            holder.nine_draw.setImageURI (list2.get (position).getIcon ());

            holder.nine_tv.setText (list2.get (position).getName ());
    }

    @Override
    public int getItemCount() {
        return list2.size ();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        private final SimpleDraweeView nine_draw;
        private final TextView nine_tv;

        public MyViewHodler(View itemView) {
            super (itemView);

            nine_draw = itemView.findViewById (R.id.nine_draw);
            nine_tv = itemView.findViewById (R.id.nine_tv);
        }
    }
}
