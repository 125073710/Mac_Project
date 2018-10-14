package com.itheima.zhbj.fragment;

import android.view.View;

import com.itheima.zhbj.base.BaseFragment;
import com.itheima.zhbj.base.BaseLoadNetDataOperator;

/**
 * Created by Apple on 2016/9/24.
 */
public class GovaffairsFragment extends BaseFragment implements BaseLoadNetDataOperator {

    @Override
    public void initTitle() {
        setIbMenuDisplayState(true);
        setIbPicTypeDisplayState(false);
        setTitle("人口管理");
    }

    @Override
    public View createContent() {
        return null;
    }

    @Override
    public void loadNetData() {

    }
}
