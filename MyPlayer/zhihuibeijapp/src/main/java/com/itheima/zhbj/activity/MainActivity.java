package com.itheima.zhbj.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.RadioGroup;

import com.itheima.zhbj.R;
import com.itheima.zhbj.adapter.MainVPFragmentAdapter;
import com.itheima.zhbj.adapter.MenuAdapter;
import com.itheima.zhbj.base.BaseFragment;
import com.itheima.zhbj.base.BaseLoadNetDataOperator;
import com.itheima.zhbj.bean.NewsCenterBean;
import com.itheima.zhbj.fragment.GovaffairsFragment;
import com.itheima.zhbj.fragment.HomeFragment;
import com.itheima.zhbj.fragment.NewsCenterFragment;
import com.itheima.zhbj.fragment.SettingFragment;
import com.itheima.zhbj.fragment.SmartServiceFragment;
import com.itheima.zhbj.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Apple on 2016/9/23.
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.tab_vp)
    NoScrollViewPager tabVp;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;

    private List<Fragment> fragments;
    public SlidingMenu slidingMenu;

    //侧滑菜单的数据
    private List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList;
    private MenuAdapter menuAdapter;

    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
        menuAdapter.setNewsCenterMenuBeanList(newsCenterMenuBeanList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewPager();
        //设置RaidoGroup的选择改变监听
        rgTab.setOnCheckedChangeListener(this);

        initSlidingMenu();

        initRecyclerView();
    }

    //初始化Menu的RecyclerView
    private void initRecyclerView() {
        //从SlidingMenu里面找到RecyclerView
        RecyclerView rvMenu = (RecyclerView) slidingMenu.findViewById(R.id.rv_menu);
        //设置布局管理器
        rvMenu.setLayoutManager(new LinearLayoutManager(this));

        //创建适配器给RecyclerView进行数据绑定
        menuAdapter = new MenuAdapter(this,null);
        rvMenu.setAdapter(menuAdapter);
    }


    //初始化侧滑菜单
    private void initSlidingMenu() {
        //创建侧滑菜单对象
        slidingMenu = new SlidingMenu(this);
        //设置菜单从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置侧滑菜单无法滑出（）
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置侧滑菜单滑出后，主界面的宽度
        slidingMenu.setBehindOffset(getResources().getDimensionPixelOffset(R.dimen.offset));
        //以内容的形式添加到Activity
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置侧滑菜单的布局
        slidingMenu.setMenu(R.layout.activity_main_menu);
    }


    //初始化ViewPager
    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsCenterFragment());
        fragments.add(new SmartServiceFragment());
        fragments.add(new GovaffairsFragment());
        fragments.add(new SettingFragment());
        tabVp.setAdapter(new MainVPFragmentAdapter(getSupportFragmentManager(),fragments));
        //让ViewPager缓存5个页面
        tabVp.setOffscreenPageLimit(5);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId){
            case R.id.rb_home:
                item = 0;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//无法滑出侧滑菜单
                break;
            case R.id.rb_newscener:
                item = 1;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_smartservice:
                item = 2;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_govaffairs:
                item = 3;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_setting:
                item = 4;
                slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                break;
        }
        //让上面的ViewPager切换到对应的页面
        tabVp.setCurrentItem(item,false);

        //获取到对应的Fragment页面
        BaseFragment baseFragment = (BaseFragment) fragments.get(item);
        if(baseFragment instanceof BaseLoadNetDataOperator && !baseFragment.hasLoadData){
            BaseLoadNetDataOperator baseLoadNetDataOperator = (BaseLoadNetDataOperator) baseFragment;
            baseLoadNetDataOperator.loadNetData();
        }
    }

    //获取当前选中的TabFragment
    public BaseFragment getCurrentTabFragment(){
        int currentItem = tabVp.getCurrentItem();
        BaseFragment baseFragment = (BaseFragment) fragments.get(currentItem);
        return baseFragment;
    }

    //友盟统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    //友盟统计
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
