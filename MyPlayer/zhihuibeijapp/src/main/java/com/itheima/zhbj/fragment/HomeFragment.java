package com.itheima.zhbj.fragment;

import android.view.View;

import com.itheima.zhbj.base.BaseFragment;
import com.itheima.zhbj.base.BaseLoadNetDataOperator;

/**
 * Created by Apple on 2016/9/24.
 */
public class HomeFragment extends BaseFragment implements BaseLoadNetDataOperator{

    @Override
    public void initTitle() {
        setIbMenuDisplayState(false);
        setIbPicTypeDisplayState(false);
        setTitle("首页");
    }

    @Override
    public View createContent() {
        return null;
    }

    @Override
    public void loadNetData() {

    }
}
