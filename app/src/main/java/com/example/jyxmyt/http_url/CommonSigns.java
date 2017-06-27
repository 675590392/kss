package com.example.jyxmyt.http_url;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonSigns {
	
	public static ExecutorService pool = Executors.newFixedThreadPool(3); // 线程池
	
	public static final int GOOD_RETRIEVE_OK = 0x1001; //物品检索成功
	public static final int GOOD_RETRIEVE_ON = 0x1002; //物品检索失败
	public static final int GOOD_COUNT_PIRC = 0x1003; //计算购买物品总价
	public static final int Legalaid_RETRIEVE_OK = 0x1004; //法律援助检索成功
	public static final int Legalaid_RETRIEVE_ON = 0x1005; //法律援助检索失败
	public static final int BERTH_RETRIEVE_OK = 0x1006; //铺位信息检索成功
	public static final int BERTH_RETRIEVE_ON = 0x1007; //铺位信息检索失败
	
	public static final String url = "";
	
	public static final int FLYZ = 0x2001; //法律援助检索成功
	public static final int YY_NR = 0x2004; //检索查看预约内容成功
	public static final int QLYW = 0x2005; //检索权利与义务成功     
	public static final int SPDB_LB = 0x2006; //检索视频点播成功     
	public static final int PW_LB = 0x2007; //检索铺位安排成功    
	public static final int  CD = 0x2008; //检索每日一餐成功 
	public static final int  ZX = 0x2009; //检索一日作息成功     
	public static final int  ZR_LB = 0x2010; //检索值日安排列表成功     
	public static final int  ZRRY_LB = 0x2011; //检索值日安排人员成功    
	public static final int  NBGD_LB = 0x2012; //检索内部规定成功 
	public static final int  XLCP_LB = 0x2013; //检索心理测评成功 
	public static final int  WJDC_LB = 0x2014; //检索问卷调查成功 
	
	
	/*预约界面*/
	public static final int YY_OK = 0x1011; //预约
	public static final int YY = 0x2002; //预约界面检索成功
	public static final int YY_lb = 0x2003; //预约列表检索成功
}
