package com.itheima.videoplayerlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.itheima.videoplayerlist.bean.VideoPlayerItemInfo;
import com.itheima.videoplayerlist.utils.MediaHelper;
import com.itheima.videoplayerlist.view.RecycleViewDivider;
import com.itheima.videoplayerlist.adapter.VideoPlayListAdatper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private List<VideoPlayerItemInfo> videoPlayerItemInfoList;
    private LinearLayoutManager lm;
    private VideoPlayListAdatper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //网络视频路径
        String url = "http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4";

        //数据的初始化
        videoPlayerItemInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            videoPlayerItemInfoList.add(new VideoPlayerItemInfo(i,url));
        }

        //初始化RecyclerView
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,1, Color.BLACK));

        adapter = new VideoPlayListAdatper(this, videoPlayerItemInfoList);
        rv.setAdapter(adapter);
        //设置滑动监听
        rv.addOnScrollListener(onScrollListener);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        //进行滑动
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //获取屏幕上显示的第一个条目和最后一个条目的下标
            int firstVisibleItemPosition = lm.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
            //获取播放条目的下标
            int currentPosition = adapter.currentPosition;
            if((firstVisibleItemPosition > currentPosition || lastVisibleItemPosition < currentPosition) && currentPosition > -1){
                //让播放隐藏的条目停止
                MediaHelper.release();
                adapter.currentPosition = -1;
                adapter.notifyDataSetChanged();
            }
        }
    };

}
