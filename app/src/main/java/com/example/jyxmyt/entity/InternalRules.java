package com.example.jyxmyt.entity;

/**
 * 
 * @类名 InternalRules.java
 * @包名 com.example.jyxmyt.entity
 * @作者 毅
 * @时间 2014年4月16日 下午2:50:33
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (内务规定)
 */
public class InternalRules {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String name; // 内务规定名称
	public String content; // 内容地址

}
