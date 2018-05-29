package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.bean.VideoBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RecycleVideoAdapter extends RecyclerView.Adapter<RecycleVideoAdapter.MyViewHoder> {
    private final Context context;
    private final List<VideoBean.DataBean> list;
    private MyViewHoder holder;
    private static final String TAG = "RecycleVideoAdapter--";

    public RecycleVideoAdapter(Context context, List<VideoBean.DataBean> list){
        this.context = context;

        Log.d (TAG, "RecycleVideoAdapter: "+list.size ());

        this.list = list;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate (context, R.layout.video_item, null);

        holder = new MyViewHoder (view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHoder holder, int position) {
        String text = list.get (position).getText ();

        //设置头像
        holder.video_draw.setImageURI (list.get (position).getProfile_image ());

        //设置客户信息
        holder.video_tv1.setText (list.get (position).getName ());

        //设置阅读量
        String cai = list.get (position).getCai ();

        holder.video_tv2.setText (cai+"天前");

        //设置视频标题
        holder.video_tv3.setText (text);

        String videouri = list.get (position).getVideouri ();

        holder.jcPlayer.setUp (videouri, JCVideoPlayer.SCREEN_WINDOW_FULLSCREEN,"");

//        holder.jcPlayer.startButton.performClick ();

//        holder.jcPlayer.startPlayLocic ();

        String image = list.get (position).getImage_small ();

        Uri uri = Uri.parse (image);

        holder.jcPlayer.thumbImageView.setImageURI (uri);

        Glide.with (context).load (uri).into (holder.jcPlayer.thumbImageView);

        holder.jcPlayer.backButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                holder.jcPlayer.release ();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    class MyViewHoder extends RecyclerView.ViewHolder{

        private final JCVideoPlayerStandard jcPlayer;
        private final TextView video_tv1;
        private final TextView video_tv2;
        private final TextView video_tv3;
        private final SimpleDraweeView video_draw;

        public MyViewHoder(View itemView) {
            super (itemView);

            jcPlayer = itemView.findViewById (R.id.jcPlayer);
            video_draw = itemView.findViewById (R.id.video_draw);
            video_tv1 = itemView.findViewById (R.id.video_tv1);
            video_tv2 = itemView.findViewById (R.id.video_tv2);
            video_tv3 = itemView.findViewById (R.id.video_tv3);
        }
    }
}
