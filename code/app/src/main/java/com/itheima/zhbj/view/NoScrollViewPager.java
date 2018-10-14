package com.itheima.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Apple on 2016/9/24.
 * 不能够滑动的ViewPager
 * ViewPager本身是能够去执行滑动操作：事件的处理   onTouchEvent()  return false;不处理事件
 *                                     不要去拦截事件  onIntercepTouchEvent() 处理拦截事件    return false 不拦截
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不拦击事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//不处理事件
    }
}
