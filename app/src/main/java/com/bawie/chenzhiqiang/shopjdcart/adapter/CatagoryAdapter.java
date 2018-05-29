package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.GroupBean;

import java.util.List;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.MyViewHolder> {
    private final Context context;
    private final List<GroupBean.DataBean> list;
    private MyViewHolder holder;
    private OnItemClickListener monItemClickListener;

    public CatagoryAdapter(Context context, List<GroupBean.DataBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate (context, R.layout.left_item, null);

        holder = new MyViewHolder (view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
         holder.left_tv.setText (list.get (position).getName ());

        if(monItemClickListener != null){
            holder.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    monItemClickListener.onClickChild (position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView left_tv;

        public MyViewHolder(View itemView) {
            super (itemView);

            left_tv = itemView.findViewById (R.id.left_tv);
        }
    }

    public interface OnItemClickListener{
        void onClickChild(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.monItemClickListener = onItemClickListener;
    }
}
