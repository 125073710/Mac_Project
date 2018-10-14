package com.itheima.zhbj.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.zhbj.base.NewsCenterContentTabPager;
import com.itheima.zhbj.bean.NewsCenterBean;
import com.itheima.zhbj.utils.Constant;

import java.util.List;

/**
 * Created by Apple on 2016/9/27.
 */
public class NewsCenterTabVPAdapter extends PagerAdapter {

    private List<NewsCenterContentTabPager> views;
    private List<NewsCenterBean.NewsCenterNewsTabBean> tabBeanList;

    public NewsCenterTabVPAdapter(List<NewsCenterContentTabPager> views,List<NewsCenterBean.NewsCenterNewsTabBean> tabBeanList) {
        this.views = views;
        this.tabBeanList = tabBeanList;
    }

    @Override
    public int getCount() {
        return views != null?views.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position).view;
        container.addView(view);
        //进行数据的加载
        NewsCenterContentTabPager tabPager = views.get(position);
        // /10007/list_1.json 需要在前面进行路径的拼接
        String url = Constant.HOST + tabBeanList.get(position).url;
        tabPager.loadNetData(url);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabBeanList.get(position).title;
    }
}
