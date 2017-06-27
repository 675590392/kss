package com.example.jyxmyt.entity;
/**
 * 
 * @类名 Berth 
 * @包名 com.example.jyxmyt.entity 
 * @作者 ChunTian   
 * @时间 2014年4月15日 上午10:48:08 
 * @Email ChunTian1314@vip.qq.com 
 * @版本 V1.0  
 * @功能 (视频信息工具类)
 */
public class Video {
	private String name; //电影名称
	private String user; //创建人
	private String time; //时间
	private String focus; //看点
	private String timeLeng; //电影时长
	private String content; //描述
	private String url; //电影播放地址
	private String image; //视频图像
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
	}
	public String getTimeLeng() {
		return timeLeng;
	}
	public void setTimeLeng(String timeLeng) {
		this.timeLeng = timeLeng;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
