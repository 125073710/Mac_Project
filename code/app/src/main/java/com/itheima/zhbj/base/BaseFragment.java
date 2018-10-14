package com.itheima.zhbj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itheima.zhbj.R;
import com.itheima.zhbj.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Apple on 2016/9/24.
 */
public abstract class BaseFragment extends Fragment {

    @BindView(R.id.ib_menu)
    ImageButton ibMenu;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_pic_type)
    public ImageButton ibPicType;
    @BindView(R.id.container)
    public FrameLayout container;

    //是否加载数据
    public boolean hasLoadData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
    }

    //初始化标题   让每个子类去进行实现
    public abstract void initTitle();

    //设置Menu的显示状态
    public void setIbMenuDisplayState(boolean isShow){
        ibMenu.setVisibility(isShow?View.VISIBLE:View.GONE);
    }
    //设置PicType的显示状态
    public void setIbPicTypeDisplayState(boolean isShow){
        ibPicType.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //设置标题内容
    public void setTitle(String title){
        tvTitle.setText(title);
    }

    //创建内容
    public abstract View createContent();

    //向容器里面添加内容
    public void addView(View view){
        //清空原来的内容
        container.removeAllViews();
        //添加内容
        container.addView(view);
    }


    @OnClick(R.id.ib_menu)
    public void onClick() {
        //对于侧滑菜单进行切换
        //目标：获取SlidingMenu -->MainActivity
        ((MainActivity)getActivity()).slidingMenu.toggle();
    }
}
