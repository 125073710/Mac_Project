package com.itheima.a360news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.itheima.a360news.adapter.NewsAdapter;
import com.itheima.a360news.bean.ResuleBean;
import com.itheima.a360news.view.RecycleViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //RecyclerView的初始化
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,1, Color.BLACK));

        String url = "http://10.0.2.2:8080/360/list1.json";

        //联网获取数据
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResuleBean resuleBean = new Gson().fromJson(response, ResuleBean.class);
                        rv.setAdapter(new NewsAdapter(resuleBean.data,getApplicationContext()));
                    }
                });
    }


}
