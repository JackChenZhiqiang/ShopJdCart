package com.bawie.chenzhiqiang.shopjdcart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.http.utils.SumUtils;
import com.bawie.chenzhiqiang.shopjdcart.bean.ShopBean;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.AddSubView;
import com.bawie.chenzhiqiang.shopjdcart.view.activities.iview.IShopView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyExpendAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "MyAdapter----";
    private final Context context;
    private final List<ShopBean.DataBean> list;
    private final IShopView iMainView;
    private ParentViewHolder parentViewHolder;
    private ChildViewHolder childViewHolder;

    public MyExpendAdapter(Context context, List<ShopBean.DataBean> list, IShopView iMainView){

        int size = list.size ();

        Log.d (TAG, "MyAdapter: "+size);

        this.context = context;

        this.list = list;

        this.iMainView = iMainView;
    }


    @Override
    public int getGroupCount() {
        return list.size ();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList ().size ();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get (groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get (groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate (context, R.layout.shop_parent,null);

            CheckBox parent_box = convertView.findViewById (R.id.parent_cb);
            TextView parent_title = convertView.findViewById (R.id.parent_title);

            parentViewHolder = new ParentViewHolder (parent_box, parent_title);

            convertView.setTag (parentViewHolder);
        }else{
            parentViewHolder = (ParentViewHolder) convertView.getTag ();
        }

        parentViewHolder.parent_title.setText (list.get (groupPosition).getSellerName ());

        //-----------------------------------------得到复选框的状态
        parentViewHolder.getParent_cb ().setChecked (list.get (groupPosition).isParent_flag ());

        parentViewHolder.getParent_cb ().setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                boolean isChecked = list.get (groupPosition).isParent_flag ();

                isChecked=!isChecked;

                list.get (groupPosition).setParent_flag (isChecked);

                List<ShopBean.ChildBean> list_parent = MyExpendAdapter.this.list.get (groupPosition).getList ();

                for (int i=0;i<list_parent.size ();i++){
                    list_parent.get (i).setChild_flag (isChecked);
                }
                notifyDataSetChanged ();

                //计算。。。。
                String sum = SumUtils.sum (list);

                iMainView.ShowCount (sum);
            }
            //-----------------------------------------得到复选框的状态
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate (context,R.layout.shop_child,null);

            CheckBox child_cb = convertView.findViewById (R.id.child_cb);
            SimpleDraweeView child_pic = convertView.findViewById (R.id.child_pic);
            TextView child_title = convertView.findViewById (R.id.child_title);
            TextView chile_price = convertView.findViewById (R.id.child_price);
            AddSubView addSubView = convertView.findViewById (R.id.add_sub_view);

            childViewHolder = new ChildViewHolder (child_cb, child_pic, child_title, chile_price,addSubView);

            convertView.setTag (childViewHolder);


        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag ();
        }

        List<ShopBean.ChildBean> child_list = this.list.get (groupPosition).getList ();

        String images = child_list.get (childPosition).getImages ();
        String pic_url = images.split ("\\|")[0];
        childViewHolder.getChild_pic ().setImageURI (pic_url);
        childViewHolder.child_title.setText (child_list.get (childPosition).getTitle ());
        childViewHolder.child_price.setText (child_list.get (childPosition).getPrice ()+"");
        childViewHolder.addSubView.setCount (child_list.get (childPosition).getNum ());

        //设置点击事件，两个事件都要设置
        //添加的监听
        childViewHolder.getAddSubView().setOnAddClickListener(new AddSubView.AddClickListener() {
            @Override
            public void onAddClick(View view, int count) {
                list.get(groupPosition).getList().get(childPosition).setNum(count);
                //开始计算，如果选中了，就计算，没选中，就不计算
                boolean selected = list.get(groupPosition).getList().get(childPosition).isChild_flag ();
                Log.d(TAG, "onAddClick() returned: " + "计算开始");
                if (selected){
                    String sum = SumUtils.sum(list);
                    Log.d(TAG, "onAddClick() returned: " + sum);
                    iMainView.ShowCount(sum);
                }
            }
        });
        //减法
        childViewHolder.getAddSubView().setOnSubClickListener(new AddSubView.SubClickListener() {
            @Override
            public void onSubClick(View view, int count) {
                list.get(groupPosition).getList().get(childPosition).setNum(count);
                boolean selected = list.get(groupPosition).getList().get(childPosition).isChild_flag ();
                Log.d(TAG, "onAddClick() returned: " + "计算开始");
                if (selected){
                    String sum = SumUtils.sum(list);
                    Log.d(TAG, "onAddClick() returned: " + sum);
                    iMainView.ShowCount(sum);
                }
            }
        });


        //-----------------------------------------得到复选框的状态
        childViewHolder.getChild_cb ().setChecked (child_list.get (childPosition).isChild_flag ());

        //计算。。。。
        String sum = SumUtils.sum (list);

        iMainView.ShowCount (sum);

        return convertView;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class ParentViewHolder{
        private CheckBox parent_cb;
        private TextView parent_title;

        public ParentViewHolder(CheckBox parent_cb, TextView parent_title) {
            this.parent_cb = parent_cb;
            this.parent_title = parent_title;
        }

        public CheckBox getParent_cb() {
            return parent_cb;
        }

        public void setParent_cb(CheckBox parent_cb) {
            this.parent_cb = parent_cb;
        }

        public TextView getParent_title() {
            return parent_title;
        }

        public void setParent_title(TextView parent_title) {
            this.parent_title = parent_title;
        }
    }

    public class ChildViewHolder{
        private CheckBox child_cb;
        private SimpleDraweeView child_pic;
        private TextView child_title;
        private TextView child_price;
        private AddSubView addSubView;

        public ChildViewHolder(CheckBox child_cb, SimpleDraweeView child_pic, TextView child_title, TextView child_price, AddSubView addSubView) {
            this.child_cb = child_cb;
            this.child_pic = child_pic;
            this.child_title = child_title;
            this.child_price = child_price;
            this.addSubView = addSubView;
        }

        public CheckBox getChild_cb() {
            return child_cb;
        }

        public void setChild_cb(CheckBox child_cb) {
            this.child_cb = child_cb;
        }

        public SimpleDraweeView getChild_pic() {
            return child_pic;
        }

        public void setChild_pic(SimpleDraweeView child_pic) {
            this.child_pic = child_pic;
        }

        public TextView getChild_title() {
            return child_title;
        }

        public void setChild_title(TextView child_title) {
            this.child_title = child_title;
        }

        public TextView getChild_price() {
            return child_price;
        }

        public void setChild_price(TextView child_price) {
            this.child_price = child_price;
        }

        public AddSubView getAddSubView() {
            return addSubView;
        }

        public void setAddSubView(AddSubView addSubView) {
            this.addSubView = addSubView;
        }
    }
}
