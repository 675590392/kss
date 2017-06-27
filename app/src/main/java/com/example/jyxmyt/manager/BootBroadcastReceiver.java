package com.example.jyxmyt.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jyxmyt.activity.MainActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {

    static final String action_boot = "android.intent.action.BOOT_COMPLETED";


    @Override

    public void onReceive(Context context, Intent intent) {
        Log.v("Log", "------>>>>>>一体机接受广播");
        if (intent.getAction().equals(action_boot)) {
//            Intent ootStartIntent = new Intent(context, MainActivity.class);
//            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(ootStartIntent);
//
//            try {
//                Log.v("Log", "------>>>>>>一体机唤醒更新服务");
//                Intent intent2 = new Intent();
//                intent2.setClassName("com.jch.updateserv", "com.jch.updateserv.UpdateServ");
//                ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startService(intent2);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }


    }


}
