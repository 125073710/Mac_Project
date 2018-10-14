package com.itheima.jpushtest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Apple on 2016/10/7.
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //发送的自定义消息
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG,"发送的自定义消息");
            //获取数据
            Bundle bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            JSONObject extrasJson = null;
            try {
                extrasJson = new JSONObject(extras);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(extrasJson != null){
                String url = extrasJson.optString("url");
                sendNotification(context,url,message);
            }

        }
    }

    //发送通知
    public void sendNotification(Context context,String url,String message){
        //发送通知
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //点击通知后激活的事件
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,i,0);

        //创建通知的Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("标题")//标题
                .setContentText(message)//内容
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.jpush_notification_icon)//图标
                .setAutoCancel(true);//点击通知自动消失
        //发送通知
        nm.notify(1,builder.build());
    }
}
