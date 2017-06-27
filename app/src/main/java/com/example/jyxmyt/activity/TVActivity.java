package com.example.jyxmyt.activity;

import com.example.jyxmyt.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * @类名 FoodActivity.java
 * @包名 com.example.jyxmyt.activity
 * @作者 毅  
 * @时间 2014年5月6日 下午3:01:38
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0 
 * @功能 (电视)
 */
public class TVActivity extends BaseActivity implements
		OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	private ImageView qhbdds; //切换本地电视

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView(){
		setContentView(R.layout.tv_activity);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		qhbdds = (ImageView) findViewById(R.id.qhbdds);
		qhbdds.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		if(v == home_but){
			finish();
		}else if(v == return_but){
			finish();
		}else if (v == qhbdds) { //切换本地视频
			Intent intent = new Intent(TVActivity.this,
					VideoActivity.class);
			startActivity(intent);
			finish();
		}
	}

}
