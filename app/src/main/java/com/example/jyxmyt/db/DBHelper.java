package com.example.jyxmyt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jyxmyt.common.FilelUtil;

public class DBHelper extends SQLiteOpenHelper {

	public SQLiteDatabase db;
	private static final int DATABASE_VERSION = 1; //

	private static final String DB_NAME = FilelUtil.SDDIR + "/ytj.db"; //

	private DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		db = getWritableDatabase();
	}

	public static DBHelper getNewInstanceDBHelper(Context context) {
		return new DBHelper(context);
	}

	protected static final String GOOD = "good"; // 物品表
	protected static final String LEGALAID = "legal_aid"; // 法律援助
	protected static final String BEATH = "basic_information"; // 铺位安排

	// 物品表语法
	private static final String CREATE_GOOD_TABLE = "create table "
			+ GOOD
			+ " (_id integer primary key autoincrement,goodId text unique not null,goodName text,goodPrice REAL,goodStandard text)";

	// 法律援助
	private static final String CREATE_LEGALAID_TABLE = "create table "
			+ LEGALAID
			+ " (_id integer primary key autoincrement,legalName text unique not null,legalroow text)";

	// 铺位安排
	private static final String CREATE_BEATH_TABLE = "create table "
			+ BEATH
			+ " (_id integer primary key autoincrement,snbh text,areaCode text, roomNumber text,"
			+ "zjh text, xm text, xb text, csrq text, bm text, ssjd text, whcd text, mz text, sf text,jzd text, hjd text, "
			+ "zc text, ay text, rsrq text, badw text, xq text, rybh text unique not null, rsyy text, Yejfc text, "
			+ "fxzs text, zszt text, lsfx text, gyqx text,azb text, jj text, jb text, ygry text, head text, uptime text, uptype text, JSBH text,JQBH text, FJBH text,BHLX text,cssj text,JY text,GJ text)";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * 创建表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("创建数据库表--------------->>开始--");
		// 创建登陆
		db.execSQL(CREATE_GOOD_TABLE);
		// 创建法律援助
		db.execSQL(CREATE_LEGALAID_TABLE);
		// 创建铺位安排
		db.execSQL(CREATE_BEATH_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * 删除所有表
	 */
	public void clearTable() {
		db.execSQL("delete from " + GOOD + ";");
		db.execSQL("delete from " + CREATE_LEGALAID_TABLE + ";");
		db.execSQL("delete from " + CREATE_BEATH_TABLE + ";");
	}
}
