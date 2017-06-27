package com.example.jyxmyt.entity;

import android.provider.MediaStore.Images;

public class Medical {
	public String time; // 诊断日期
	public String name; // 疾病名称
	public String hospital; // 医院
	public String backTo; // 回所时间
	public String opinion; // 医生意见
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getBackTo() {
		return backTo;
	}
	public void setBackTo(String backTo) {
		this.backTo = backTo;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	
	
}
