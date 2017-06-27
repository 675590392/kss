package com.example.jyxmyt.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class B64 {

	/**
	 * 锟斤拷锟斤拷
	 * 
	 * @param cstr
	 * @return
	 */
	public static String b64(String cstr){
		return URLEncoder.encode(URLEncoder.encode(cstr));
		// byte bytes[]=cursor.getBlob(2);
	}

	/**
	 * 锟斤拷锟斤拷
	 * 
	 * @param cstr
	 * @return
	 */
	public static String d64(String cstr){
		try{
			return URLDecoder.decode(cstr, "utf-8");
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 锟斤拷取SQLite锟叫碉拷锟斤拷锟斤拷锟街讹拷时锟斤拷锟叫斤拷锟斤拷
	 * 
	 * @param val
	 * @return
	 */
	public static String gbk(byte[] val){
		try{
			return new String(val, "GBK");
		}
		catch(UnsupportedEncodingException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String gb2312(String str){
		try{
			return new String(str.trim().getBytes(), "gb2312");
		}
		catch(UnsupportedEncodingException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	// Bitmap转换成Drawable
	public static Drawable bitmap2Drawable(Bitmap bitmap){
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		Drawable d = bd;
		return d;
	}

	/**
	 * 锟街斤拷转锟斤拷锟斤拷锟斤拷
	 * 
	 * @param bytes
	 * @param opts
	 * @return
	 */
	public static Bitmap getPicFromBytes(byte[] bytes,
			BitmapFactory.Options opts){
		if(bytes != null)
			if(opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
						opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}

	// 锟斤拷锟斤拷图片
	public static byte[] readStream(InputStream in) throws Exception{
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		while((len = in.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		in.close();
		return data;
	}

	/**
	 * 16锟斤拷锟斤拷锟街凤拷锟斤拷转为byte
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString){
		if(hexString == null || hexString.equals("")){
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for(int i = 0;i < length;i++){
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		System.out.println("---->|=" + d.length);
		return d;
	}

	private static byte charToByte(char c){
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}
