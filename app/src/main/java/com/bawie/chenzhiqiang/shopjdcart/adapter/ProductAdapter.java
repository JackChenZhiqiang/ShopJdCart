package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroChildBean;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.MyGridView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHodler> {
    private static final String TAG = "ProductAdapter---";
    private final Context context;
    private final List<GroChildBean.DataBean> list;

    public ProductAdapter(Context context, List<GroChildBean.DataBean> list){
        Log.d (TAG, "ProductAdapter://////******* "+list.size ());
        
        this.context = context;
        
        this.list = list;
    }
    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate (context, R.layout.layout_right, null);

        MyViewHodler hodler = new MyViewHodler (view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, final int position) {

        holder.right_tv.setText (list.get (position).getName ());

        List<GroChildBean.DataBean.ListBean> list1 = list.get (position).getList ();

        Log.d (TAG, "分类-子类接口的+++右侧++++ "+list1.size ());

        GridegroAdapter gridegroAdapter = new GridegroAdapter (context,list1);

        holder.right_gv.setAdapter (gridegroAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        private final MyGridView right_gv;
        private final TextView right_tv;

        public MyViewHodler(View itemView) {
            super (itemView);

            right_gv = itemView.findViewById (R.id.right_gv);
            right_tv = itemView.findViewById (R.id.right_tv);
        }
    }

}
