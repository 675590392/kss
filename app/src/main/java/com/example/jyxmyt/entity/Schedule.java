package com.example.jyxmyt.entity;

/**
 * 
 * @类名 Zrlbjs
 * @包名 com.example.jyxmyt.entity
 * @作者 ChunTian
 * @时间 2014年4月15日 上午10:48:08
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (值日列表工具类)
 */
public class Schedule {
	private String id; // 内务id
	private String name; // 内务名称
	private String time; // 内务时间
	private String content; // 内务描述
	private String snbh;
	public String getSnbh() {
		return snbh;
	}

	public void setSnbh(String snbh) {
		this.snbh = snbh;
	}

	public String getNwfl() {
		return nwfl;
	}

	public void setNwfl(String nwfl) {
		this.nwfl = nwfl;
	}

	private String nwfl;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
