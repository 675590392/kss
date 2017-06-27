package com.example.jyxmyt.entity;

/**
 * 
 * @类名 Yy
 * @包名 com.example.jyxmyt.entity
 * @作者 ChunTian
 * @时间 2014年4月15日 上午10:48:08
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (预约工具类)
 */
public class Yy {
	private String id; // 预约项ID
	private String name; // 预约项名称
	private String state; // 预约状态
	private String stay;  //在押人员自己留言
	private String message; //管教留言

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
