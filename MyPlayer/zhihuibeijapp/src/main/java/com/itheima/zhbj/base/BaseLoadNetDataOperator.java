package com.itheima.zhbj.base;

/**
 * Created by Apple on 2016/9/26.
 * 主界面Tab联网行为的抽取
 */
public interface BaseLoadNetDataOperator {

    //联网操作   让首页、新闻中心、智慧服务、政务去实现
    public void loadNetData();
}
