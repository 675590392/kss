package com.example.jyxmyt.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @类名 DBLegalaid
 * @包名 com.example.jyxmyt.db
 * @作者 ChunTian
 * @时间 2014年4月14日 上午11:41:53
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (法律援助)
 */

public class DBLegalaid {

	private DBHelper dbHelper;
	SQLiteDatabase db;

	public DBLegalaid(Context context) {
		dbHelper = DBHelper.getNewInstanceDBHelper(context);
	}
	
	/**
	 * 
	 * @方法名 DBLegalaid.java  
	 * @时间 2014年4月14日 上午11:46:38 
	 * @设定文件 @return 
	 * @返回类型 DBLegalaid.java 
	 * @功能 (查询法律援助表所有数据)
	 */
	public Cursor queryDBLegalaidDate(){
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(false, DBHelper.LEGALAID, null, null, null, null, null, null, null);
		return cursor;
	}

	/**
	 * 
	 * @方法名 DBLegalaid.java  
	 * @时间 2014年4月14日 下午4:53:24 
	 * @设定文件 @param name
	 * @设定文件 @return 
	 * @返回类型 DBLegalaid.java 
	 * @功能 (搜索法律援助表所有数据)
	 */
	public Cursor searchDBLegalaidDate(String name) {
		// TODO Auto-generated method stub
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(false, DBHelper.LEGALAID, null, "legalName like '%" + name + "%'", null, null, null, null, null);
		return cursor;
	}

}
