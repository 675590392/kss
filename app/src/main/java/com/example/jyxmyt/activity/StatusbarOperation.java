package com.example.jyxmyt.activity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class StatusbarOperation extends Activity  {
	

	  //flag表示/system/app/SystemUI.apk存在与否的标记
	  Boolean flag;
	  //构造函数 ，对flag进行初始化
	   public  StatusbarOperation(Boolean flag){
		   this.flag=flag;
	   }
	   //StatusbarOperation成员方法，负责隐藏或显示状态栏
	   public void  run(){
	   MyOperation my=new MyOperation();
	   my.switchSystemUI(this.flag);
	   
	   
	   if(this.flag){
		   Intent intent=new Intent();
		   intent.setComponent(new ComponentName("com.android.systemui","com.android.systemui.SystemUIService"));
		   //startService(intent);
		   startService(intent);
	   }
}
}

//对于状态栏实际操作的类
class  MyOperation{
	//创建/system/app/SystemUI.apk目录下的文件对象，便于得知该文件存在与否
	File sysUIapk=new File("/system/app/SystemUI.apk");
	//根据传入的参数，实际控制状态栏隐藏与否
    public void switchSystemUI(Boolean sysUIapkFlag){
    try{
  	  Process p;
  	  p=Runtime.getRuntime().exec("su");
  	  DataOutputStream os=new DataOutputStream(p.getOutputStream());
  	  os.writeBytes("mount -o remount,rw /dev/block /system\n");
  	  if(sysUIapk.exists()){
  	     if(!sysUIapkFlag)
  	       {
  		      os.writeBytes("mv /system/app/SystemUI.apk  /system/SystemUI.apk\n");
  	       }
  	  }
  	  else{
  		  if(sysUIapkFlag){
  		  os.writeBytes("mv /system/SystemUI.apk  /system/app/SystemUI.apk\n");
  	       }
  	  }
  	  os.writeBytes("mount -o remount,ro /dev/block  /system\n");
  	  os.writeBytes("exit\n");
  	  os.flush();
  	  p.waitFor();
    }catch(Exception e){
  	    showErrorGlobal(e);
    }
}
public void showErrorGlobal(Exception e)
{
 ByteArrayOutputStream baos=new ByteArrayOutputStream();
 PrintStream stream=new PrintStream(baos);
 e.printStackTrace(stream);
 stream.flush();
 
 //new AlertDialog.Builder(this).setIconAttribute(android.R.alertDialogIcon).setTitle("Epic fail").setMessage("Erro:"+new String(baos.toByteArray())).show();
}

}
