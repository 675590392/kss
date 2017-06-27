package com.example.jyxmyt.service;

import java.util.List;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class RestartService extends Service {

	Handler handler = new Handler()
	  {
	    public void handleMessage(Message paramMessage)
	    {
	      Log.i("handler", "handler");
	      if (paramMessage.what == 1)
	        RestartService.this.restart();
	    }
	  };

	  private void restart()
	  {
	    Boolean localBoolean = Boolean.valueOf(isAppRunning(getApplicationContext()));
	    Log.i("运行状态", localBoolean.toString());
	    if (!localBoolean.booleanValue())
	    {
	      Intent localIntent = new Intent("restart");
	      getApplicationContext().sendBroadcast(localIntent);
	    }
	    Message localMessage = this.handler.obtainMessage(1);
	    this.handler.sendMessageDelayed(localMessage, 10000L);
	  }

	  public boolean isAppRunning(Context paramContext)
	  {
	    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
	    return (localList == null) || (localList.isEmpty()) || (((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName().equals(paramContext.getPackageName()));
	  }

	  public IBinder onBind(Intent paramIntent)
	  {
	    return null;
	  }

	  public void onDestroy()
	  {
	    Log.i("结束服务", "结束服务");
	    super.onDestroy();
	  }

	  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
	  {
	    Log.i("开始服务", "开始服务");
	    restart();
	    return 1;
	  }

}
