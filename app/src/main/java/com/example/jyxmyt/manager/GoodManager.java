package com.example.jyxmyt.manager;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Message;

import com.example.jyxmyt.activity.ShoppingActivity;
import com.example.jyxmyt.db.DBGoods;
import com.example.jyxmyt.entity.Goods;
import com.example.jyxmyt.http_url.CommonSigns;

class GoodManager extends BaseManager {

	private Context context;
	private DBGoods goods;
	private ShoppingActivity activity;

	public GoodManager(Context context){
		this.context = context;
		goods = new DBGoods(context);
		if(context instanceof ShoppingActivity){
			activity = (ShoppingActivity) context;
		}
	}

	/**
	 * 查询物品信息
	 * 
	 * @param endPrice
	 * @param startPrice
	 * @param gjz
	 * @param lx
	 */
	public void QueryGoods(final ArrayList<Goods> goodsList, final String lx,
			final String gjz, final Double startPrice, final Double endPrice){
		getDateService.execute(new Runnable() {
			@Override
			public void run(){
				if(goodsList != null && goodsList.size() > 0){
					goodsList.clear();
				}
				// 本地获取
				Cursor cursor = goods.getGoodInfo(lx,gjz,startPrice,endPrice);
				if(cursor != null && cursor.getCount() > 0){
					while(cursor.moveToNext()){
						Goods goods = new Goods();
						goods.setGoodId(cursor.getString(cursor
								.getColumnIndex("goodId")));
						goods.setGoodName(cursor.getString(cursor
								.getColumnIndex("goodName")));
						goods.setGoodPrice(cursor.getString(cursor
								.getColumnIndex("goodPrice")));
						goods.setGoodStandard(cursor.getString(cursor
								.getColumnIndex("goodStandard")));
						goodsList.add(goods);
					}
				}
				if(goodsList.size() > 0){
					Message message = new Message();
					message.what = CommonSigns.GOOD_RETRIEVE_OK;
//					activity.handler.sendMessage(message);
				}
			}
		});
	}

}
