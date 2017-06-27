package com.example.jyxmyt.activity;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.XXYDGridViewAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * 学习园地
 * 
 * @author 123
 * 
 */
public class LearningGardenActivity extends BaseActivity implements
		OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	private GridView xxyd_grid; //学习园地

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView(){
		setContentView(R.layout.learning_garden_activity);
//		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		
		xxyd_grid = (GridView)findViewById(R.id.xxyd_grid);
		xxyd_grid.setAdapter(new XXYDGridViewAdapter(LearningGardenActivity.this));
		
	}

	@Override
	public void onClick(View v){
		if(v == home_but){
			finish();
		}
		else if(v == return_but){
			finish();
		}
	}

}
