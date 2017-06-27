package com.example.jyxmyt.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @类名 UserInformation
 * @包名 com.example.jyxmyt.common
 * @作者 ChunTian
 * @时间 2014年4月14日 上午9:54:45
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (使用SharedPreferences保存用户信息)
 */

public class UserInformation {

	private static SharedPreferences sharedPre;

	private String zg_xm; // 主管姓名
	private String zg_jh; // 主管警号
	private String xg_xm; // 协管姓名
	private String xg_jh; // 协管警号
	private String gaolsNumber; // 设置监所编号
	private String prisonNumber; // 设置监区编号
	private String roomNumber; // 设置房间号
	private Boolean isuserlogin; // 保存用户是否登录
	private Boolean ispolicelogin; // 保存干警是否登录

	public UserInformation(Context context) {
		// 获取SharedPreferences对象
		sharedPre = context.getSharedPreferences("userInformation",
				Context.MODE_PRIVATE);
	}

	public String getZg_xm() {
		zg_xm = sharedPre.getString("zg_xm", "");
		return zg_xm;
	}

	public void setZg_xm(String zg_xm) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("zg_xm", zg_xm);
		// 提交
		editor.commit();
	}

	public String getZg_jh() {
		zg_jh = sharedPre.getString("zg_jh", "");
		return zg_jh;
	}

	public void setZg_jh(String zg_jh) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("zg_jh", zg_jh);
		// 提交
		editor.commit();
	}

	public String getXg_xm() {
		xg_xm = sharedPre.getString("xg_xm", "");
		return xg_xm;
	}

	public void setXg_xm(String xg_xm) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("xg_xm", xg_xm);
		// 提交
		editor.commit();
	}

	public String getXg_jh() {
		xg_jh = sharedPre.getString("xg_jh", "");
		return xg_jh;
	}

	public void setXg_jh(String xg_jh) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("xg_jh", xg_jh);
		// 提交
		editor.commit();
	}

	public String getGaolsNumber() {
		gaolsNumber = sharedPre.getString("gaolsNumber", "");
		return gaolsNumber;
	}

	public void setGaolsNumber(String gaolsNumber) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("gaolsNumber", gaolsNumber);
		// 提交
		editor.commit();
	}

	public String getPrisonNumber() {
		prisonNumber = sharedPre.getString("prisonNumber", "");
		return prisonNumber;
	}

	public void setPrisonNumber(String prisonNumber) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("prisonNumber", prisonNumber);
		// 提交
		editor.commit();
	}

	public String getRoomNumber() {
		roomNumber = sharedPre.getString("roomNumber", "");
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putString("roomNumber", roomNumber);
		// 提交
		editor.commit();
	}

	public Boolean getIsuserlogin() {
		isuserlogin = sharedPre.getBoolean("isuserlogin", false);
		return isuserlogin;
	}

	public void setIsuserlogin(Boolean isuserlogin) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putBoolean("isuserlogin", isuserlogin);
		// 提交
		editor.commit();
	}

	public Boolean getIspolicelogin() {
		ispolicelogin = sharedPre.getBoolean("ispolicelogin", false);
		return ispolicelogin;
	}

	public void setIspolicelogin(Boolean ispolicelogin) {
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置服务器IP
		editor.putBoolean("ispolicelogin", ispolicelogin);
		// 提交
		editor.commit();
	}

}
