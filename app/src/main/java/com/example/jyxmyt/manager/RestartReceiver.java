package com.example.jyxmyt.manager;

import com.example.jyxmyt.activity.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RestartReceiver extends BroadcastReceiver
{
  static final String action = "restart";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("接受广播", "123");
    if (paramIntent.getAction().equals("restart"))
    {
      Intent localIntent = new Intent(paramContext, MainActivity.class);
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
    }
  }
}