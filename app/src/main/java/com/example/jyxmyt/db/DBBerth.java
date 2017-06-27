package com.example.jyxmyt.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @类名 DBBerth 
 * @包名 com.example.jyxmyt.db 
 * @作者 ChunTian   
 * @时间 2014年4月15日 上午10:35:20 
 * @Email ChunTian1314@vip.qq.com 
 * @版本 V1.0  
 * @功能 (铺位安排)
 */

public class DBBerth {
	private DBHelper dbHelper;
	SQLiteDatabase db;

	public DBBerth(Context context) {
		dbHelper = DBHelper.getNewInstanceDBHelper(context);
	}

	/**
	 * 
	 * @方法名 DBBerth.java  
	 * @时间 2014年4月15日 上午10:32:31 
	 * @设定文件 @param jsbh 监所编号
	 * @设定文件 @param jqbh 监区编号
	 * @设定文件 @param fjbh 房间号
	 * @设定文件 @return 
	 * @返回类型 DBBerth.java 
	 * @功能 (根据 监所编号，监区编号，房间号，查询所有数据)
	 */
	public Cursor queryDBBeathDate(String jsbh,String jqbh,String fjbh) {
		// TODO Auto-generated method stub
		db = dbHelper.getWritableDatabase();
		Cursor cursor;
		if(!jsbh.equals("") && !jqbh.equals("") && !fjbh.equals("")){
			cursor = db
					.query(false, DBHelper.BEATH, null, " JSBH = '" + jsbh + "' and JQBH ='" + jqbh +"' and FJBH ='" + fjbh + "'", null, null, null, null, null);
		}else{
			cursor = null;
		}
		
		return cursor;
	}
}
