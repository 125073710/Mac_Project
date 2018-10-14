package com.itheima.zhbj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.zhbj.R;
import com.itheima.zhbj.bean.NewsCenterGroupImageViewBean;
import com.itheima.zhbj.utils.bitmap.BitmapUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Apple on 2016/9/29.
 */
public class NewsCenterGroupImageViewAdapter extends RecyclerView.Adapter {
    private List<NewsCenterGroupImageViewBean.NewsCenterGroupImageViewNewsBean> newsCenterGroupImageViewNewsBeanList;
    private Context context;

    public NewsCenterGroupImageViewAdapter(List<NewsCenterGroupImageViewBean.NewsCenterGroupImageViewNewsBean> newsCenterGroupImageViewNewsBeanList, Context context) {
        this.newsCenterGroupImageViewNewsBeanList = newsCenterGroupImageViewNewsBeanList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newscenter_group_imageview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsCenterGroupImageViewBean.NewsCenterGroupImageViewNewsBean newsCenterGroupImageViewNewsBean = newsCenterGroupImageViewNewsBeanList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        //Picasso主要采用的是图片三级缓存技术的实现
//        Picasso.with(context).load(newsCenterGroupImageViewNewsBean.listimage).into(viewHolder.iv);
        BitmapUtils.display(context,viewHolder.iv,newsCenterGroupImageViewNewsBean.listimage);
        viewHolder.tvTitle.setText(newsCenterGroupImageViewNewsBean.title);
    }

    @Override
    public int getItemCount() {
        return newsCenterGroupImageViewNewsBeanList != null ? newsCenterGroupImageViewNewsBeanList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
