package com.example.jyxmyt.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

/**
 * 
 * @类名 CalendarUtil
 * @包名 com.example.jyxmyt.common
 * @作者 ChunTian
 * @时间 2014年4月15日 上午11:36:53
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (用一句话描述该文件做什么)
 */
public class CalendarUtil {

	public static Bitmap bit = null;

	public static Bitmap getBitmapFromByte(byte[] temp){
		if(temp != null && temp.length != 0){
			bit = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			return comp(bit);
		}
		return null;
	}

	public static void coliceBitmap(){
		bit.recycle();
		bit = null;
	}

	public static Bitmap compSp(Bitmap image){

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if(baos.toByteArray().length / 1024 > 512){
			baos.reset();
			image.compress(Bitmap.CompressFormat.JPEG, 90, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 40f;
		float ww = 60f;
		int be = 1;
		if(w > h && w > ww){
			be = (int) (newOpts.outWidth / ww);
		}
		else if(w < h && h > hh){
			be = (int) (newOpts.outHeight / hh);
		}
		if(be <= 0)
			be = 1;
		newOpts.inSampleSize = be;
		newOpts.inPreferredConfig = Config.RGB_565;
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);
	}

	public static Bitmap comp(Bitmap image){

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if(baos.toByteArray().length / 1024 > 512){
			baos.reset();
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 80f;
		float ww = 40f;
		int be = 1;
		if(w > h && w > ww){
			be = (int) (newOpts.outWidth / ww);
		}
		else if(w < h && h > hh){
			be = (int) (newOpts.outHeight / hh);
		}
		if(be <= 0)
			be = 1;
		newOpts.inSampleSize = be;
		newOpts.inPreferredConfig = Config.RGB_565;
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);
	}

	private static Bitmap compressImage(Bitmap image){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 30, baos);
		int options = 50;
		while(baos.toByteArray().length / 1024 > 50){
			baos.reset();
			options -= 50;
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	public static int getMondayPlus(){
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		System.out.println("星期几--->>" + cd.get(Calendar.DAY_OF_WEEK));
		if(dayOfWeek == 1){
			return 0;
		}
		else{
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获取一周的每日日期
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getMondayOFWeek(int day){
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + day);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	/**
	 * 当天是星期几
	 * 
	 * @return
	 */
	public static int getDayOfWeek(){
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当天日期
	 * 
	 * @param dateformat
	 * @return2014-02-27 10:11:11
	 */
	public static String getNowTime(String dateformat){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		if(Calendar.getInstance().getTime().getHours() > 12
				&& hehe.length() == 19){
			String str[] = hehe.split(" ");
			String time[] = str[1].split(":");
			hehe = str[0] + " " + Calendar.getInstance().getTime().getHours()
					+ ":" + time[1] + ": " + time[2];
		}
		return hehe;
	}
}
