package com.itheima.zhbj.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.zhbj.base.BaseFragment;

/**
 * Created by Apple on 2016/9/24.
 */
public class SettingFragment extends BaseFragment {

    @Override
    public void initTitle() {
        setIbMenuDisplayState(false);
        setIbPicTypeDisplayState(false);
        setTitle("设置");
    }

    @Override
    public View createContent() {
        return null;
    }
}
