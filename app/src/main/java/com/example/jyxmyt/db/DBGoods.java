package com.example.jyxmyt.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBGoods {
	private DBHelper dbHelper;
	SQLiteDatabase db;

	public DBGoods(Context context){
		dbHelper = DBHelper.getNewInstanceDBHelper(context);
	}

	/**
	 * 查找数据库中所有的监区信息
	 * 
	 * @param endPrice
	 * @param startPrice
	 * @param gjz
	 * @param lx
	 * 
	 * @return
	 */
	public Cursor getGoodInfo(String lx, String gjz, Double startPrice,
			Double endPrice){
		db = dbHelper.getWritableDatabase();
		Cursor cursor = null;
		
		String sql = "select * from " + DBHelper.GOOD + " where 1=1";

		if(!"".equals(lx)){
			sql += " and xb = '" + lx + "'";
		}
		if(!"".equals(gjz)){
			sql += " and goodName like '%" + gjz + "%'";
			
		}
		if(startPrice != null && endPrice != null){
			sql += " and goodPrice >= '" + startPrice + "' and goodPrice <= '"
					+ endPrice + "'";
		}
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
}
