package com.itheima.webview_base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);

        //在webView里面打开网页
        webView.setWebViewClient(new WebViewClient());

        //允许在webview里面弹出js的窗体
        webView.setWebChromeClient(new WebChromeClient());

        //允许js的执行
        webView.getSettings().setJavaScriptEnabled(true);

        //加载网页
//        webView.loadUrl("http://www.baidu.com");
        //加载assets目录下的网页
        webView.loadUrl("file:///android_asset/demo.html");


        //把java里面的对象传递给js
        webView.addJavascriptInterface(new JsCallJava() {
            @JavascriptInterface
            @Override
            public void onCallback() {
                Toast.makeText(getApplicationContext(),"JavaScript调用的java代码",Toast.LENGTH_SHORT).show();
            }
        }, "demo");
    }

    public interface JsCallJava{
        public void onCallback();
    }

    public void javaCallJs(View view){
        webView.loadUrl("javascript:wave()");
    }

    //回退
    public void goBack(View view){
        if(webView.canGoBack()){
            webView.goBack();
        }
    }

    //前进
    public void goForward(View view){
        if(webView.canGoForward()){
            webView.goForward();
        }
    }


}
