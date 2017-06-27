package com.example.jyxmyt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jyxmyt.util.SyncBiz;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tanghao on 2017/3/7.
 */

public class SynFingerServ extends Service {
    Context mContext;
    Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (timer == null) {
            Log.v("Log", "初始化");
            timer = new Timer();
            timer.schedule(timerTask, 0, 30 * 1000);
        } else
            Log.v("Log", "启动服务未初始化");

        return START_STICKY;//
    }


    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.v("Log", "Log" + new Date().getSeconds());

            SyncBiz.synchFinger(mContext);//同步信息

        }
    };

    @Override
    public void onDestroy() {

        Intent intent = new Intent(this, SynFingerServ.class);
        startService(intent);
        Log.v("Log", "重新启动服务");

        super.onDestroy();
    }
}
