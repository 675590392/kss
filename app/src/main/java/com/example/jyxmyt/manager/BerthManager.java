package com.example.jyxmyt.manager;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Message;
import android.util.Log;

import com.example.jyxmyt.activity.BerthActivity;
import com.example.jyxmyt.activity.SchedulePersonnelActivity;
import com.example.jyxmyt.common.UserInformation;
import com.example.jyxmyt.db.DBBerth;
import com.example.jyxmyt.db.DBLegalaid;
import com.example.jyxmyt.entity.Berth;
import com.example.jyxmyt.entity.LegalAid;
import com.example.jyxmyt.http_url.CommonSigns;

/**
 * 
 * @类名 BerthManager
 * @包名 com.example.jyxmyt.manager
 * @作者 ChunTian
 * @时间 2014年4月15日 上午10:18:34
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (铺位安排工具类)
 */
 class BerthManager extends BaseManager {

	private Context context;
	private DBBerth dbBerth;
	private BerthActivity activity;
	private ArrayList<Berth> berths;
	private SchedulePersonnelActivity sPersonnelActivity;

	public BerthManager(Context context) {
		this.context = context;
		dbBerth = new DBBerth(context);
		if (context instanceof BerthActivity) {
			activity = (BerthActivity) context;
		}else if(context instanceof SchedulePersonnelActivity){
			sPersonnelActivity = (SchedulePersonnelActivity) context;
		}
	}

	/**
	 * 查询铺位安排
	 * 
	 * @param endPrice
	 * @param startPrice
	 * @param gjz
	 * @param lx
	 */
	public void queryBeath(final String jsbh, final String jqbh, final String fjbh) {
		getDateService.execute(new Runnable() {
			@Override
			public void run() {
				berths = new ArrayList<Berth>();
				Cursor cursor = dbBerth.queryDBBeathDate(jsbh, jqbh,
						fjbh);

				if (cursor != null && cursor.getCount() > 0) {
					while (cursor.moveToNext()) {
						Berth berth = new Berth();
						berth.setName(cursor.getString(cursor
								.getColumnIndex("xm")));
						berth.setSnbh(cursor.getString(cursor
								.getColumnIndex("snbh")));
						berth.setImage(cursor.getString(cursor
								.getColumnIndex("head")));
						berth.setPwh(cursor.getString(cursor
								.getColumnIndex("lsfx")));
						berths.add(berth);
					}
				}
				if (context instanceof BerthActivity) {
					if (berths.size() > 0) {
						Message message = new Message();
						message.what = CommonSigns.BERTH_RETRIEVE_OK;
						message.obj = berths;
						activity.handler.sendMessage(message);
					} else {
						Message message = new Message();
						message.what = CommonSigns.BERTH_RETRIEVE_ON;
						activity.handler.sendMessage(message);
					}
				}else if(context instanceof SchedulePersonnelActivity){
					if (berths.size() > 0) {
						Message message = new Message();
						message.what = CommonSigns.BERTH_RETRIEVE_OK;
						message.obj = berths;
						sPersonnelActivity.handler.sendMessage(message);
					} else {
						Message message = new Message();
						message.what = CommonSigns.BERTH_RETRIEVE_ON;
						sPersonnelActivity.handler.sendMessage(message);
					}
				}
			}
		});
	}

}
