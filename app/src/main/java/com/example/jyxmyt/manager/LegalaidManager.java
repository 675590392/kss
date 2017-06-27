package com.example.jyxmyt.manager;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Message;

import com.example.jyxmyt.activity.LegalAidActivity;
import com.example.jyxmyt.db.DBLegalaid;
import com.example.jyxmyt.entity.LegalAid;
import com.example.jyxmyt.http_url.CommonSigns;

/**
 * 
 * @类名 LegalaidManager
 * @包名 com.example.jyxmyt.manager
 * @作者 ChunTian
 * @时间 2014年4月14日 上午11:55:03
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (法律援助工具类)
 */
public class LegalaidManager extends BaseManager {

	private Context context;
	private DBLegalaid legalaid;
	private LegalAidActivity activity;

	private ArrayList<LegalAid> legalaidList;

	public LegalaidManager(Context context){
		this.context = context;
		legalaid = new DBLegalaid(context);
		if(context instanceof LegalAidActivity){
			activity = (LegalAidActivity) context;
		}
	}

	/**
	 * 查询法律援助
	 * 
	 * @param endPrice
	 * @param startPrice
	 * @param gjz
	 * @param lx
	 */
	public void queryLegalaid(){
		getDateService.execute(new Runnable() {
			@Override
			public void run(){
				legalaidList = new ArrayList<LegalAid>();
				// 本地获取
				Cursor cursor = legalaid.queryDBLegalaidDate();
				if(cursor != null && cursor.getCount() > 0){
					while(cursor.moveToNext()){
						LegalAid legalaid = new LegalAid();
						legalaid.setName(cursor.getString(cursor
								.getColumnIndex("legalName")));
						legalaid.setContent(cursor.getString(cursor
								.getColumnIndex("legalroow")));
						legalaidList.add(legalaid);
					}
				}
				if(legalaidList.size() > 0){
					Message message = new Message();
					message.what = CommonSigns.Legalaid_RETRIEVE_OK;
					message.obj = legalaidList;
					activity.handler.sendMessage(message);
				}
				else{
					Message message = new Message();
					message.what = CommonSigns.Legalaid_RETRIEVE_ON;
					activity.handler.sendMessage(message);
				}
			}
		});
	}

	/**
	 * 
	 * @方法名 LegalaidManager.java
	 * @时间 2014年4月14日 下午4:51:42
	 * @设定文件 @param name
	 * @返回类型 LegalaidManager.java
	 * @功能 (搜索法律援助名称)
	 */
	public void searchLegalaid(final String name){
		// TODO Auto-generated method stub
		getDateService.execute(new Runnable() {
			@Override
			public void run(){
				legalaidList = new ArrayList<LegalAid>();
				// 本地获取
				Cursor cursor = legalaid.searchDBLegalaidDate(name);
				if(cursor != null && cursor.getCount() > 0){
					while(cursor.moveToNext()){
						LegalAid legalaid = new LegalAid();
						legalaid.setName(cursor.getString(cursor
								.getColumnIndex("legalName")));
						legalaid.setContent(cursor.getString(cursor
								.getColumnIndex("legalroow")));
						legalaidList.add(legalaid);
					}
				}
				if(legalaidList.size() > 0){
					Message message = new Message();
					message.what = CommonSigns.Legalaid_RETRIEVE_OK;
					message.obj = legalaidList;
					activity.handler.sendMessage(message);
				}
				else{
					Message message = new Message();
					message.what = CommonSigns.Legalaid_RETRIEVE_ON;
					activity.handler.sendMessage(message);
				}
			}
		});
	}

}
