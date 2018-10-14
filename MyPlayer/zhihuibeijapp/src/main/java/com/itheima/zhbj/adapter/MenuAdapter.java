package com.itheima.zhbj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.zhbj.R;
import com.itheima.zhbj.activity.MainActivity;
import com.itheima.zhbj.base.BaseFragment;
import com.itheima.zhbj.bean.NewsCenterBean;
import com.itheima.zhbj.fragment.NewsCenterFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Apple on 2016/9/26.
 */
public class MenuAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList;

    //默认选中的条目下标
    private int selectedPosition;

    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
        //刷新显示
        notifyDataSetChanged();
    }

    public MenuAdapter(Context context, List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.context = context;
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final NewsCenterBean.NewsCenterMenuBean newsCenterMenuBean = newsCenterMenuBeanList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvMenuTitle.setText(newsCenterMenuBean.title);

        //选中
        if(selectedPosition == position){
            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_select);
            viewHolder.tvMenuTitle.setTextColor(Color.RED);
        }else{
            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_normal);
            viewHolder.tvMenuTitle.setTextColor(Color.WHITE);
        }

        //处理条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            //条目点击事件
            @Override
            public void onClick(View v) {
                if(selectedPosition != position){
                    selectedPosition = position;
                    //刷新界面
                    notifyDataSetChanged();
                    //修改对应tab页面的标题
                    BaseFragment baseFragment = ((MainActivity)context).getCurrentTabFragment();
                    baseFragment.setTitle(newsCenterMenuBean.title);
                    if(baseFragment instanceof NewsCenterFragment){
                        NewsCenterFragment newsCenterFragment = (NewsCenterFragment) baseFragment;
                        //切换内容
                        newsCenterFragment.switchContent(position);
                    }

                }
                //关闭侧滑菜单
                ((MainActivity)context).slidingMenu.toggle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsCenterMenuBeanList != null ? newsCenterMenuBeanList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        @BindView(R.id.tv_menu_title)
        TextView tvMenuTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
