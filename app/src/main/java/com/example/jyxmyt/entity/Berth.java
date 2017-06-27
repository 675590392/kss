package com.example.jyxmyt.entity;
/**
 * 
 * @类名 Berth 
 * @包名 com.example.jyxmyt.entity 
 * @作者 ChunTian   
 * @时间 2014年4月15日 上午10:48:08 
 * @Email ChunTian1314@vip.qq.com 
 * @版本 V1.0  
 * @功能 (铺位安排人员信息工具类)
 */
public class Berth {
	private String name; //人员姓名
	private String snbh; //人员番号
	private String image; //人员头像
	private String pwh; //铺位号
	private Boolean bool;

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

	public String getPwh() {
		return pwh;
	}

	public void setPwh(String pwh) {
		this.pwh = pwh;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSnbh() {
		return snbh;
	}

	public void setSnbh(String snbh) {
		this.snbh = snbh;
	}

}
