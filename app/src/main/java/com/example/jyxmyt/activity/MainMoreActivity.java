package com.example.jyxmyt.activity;

import com.example.jyxmyt.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 更多界面
 * 
 * @author 123
 * 
 */
public class MainMoreActivity extends BaseActivity implements OnClickListener {

//	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步

	private ImageView caid_but; // 菜单按钮

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView(){
		setContentView(R.layout.main_more_activity);
//		long_head = (ImageView) findViewById(R.id.long_head);
//		return_but = (ImageView) findViewById(R.id.return_but);
//		return_but.setOnClickListener(this);
//		home_but = (ImageView) findViewById(R.id.home_but);
//		home_but.setOnClickListener(this);

		//caid_but = (ImageView) findViewById(R.id.caid_but);
		//caid_but.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		if(v == home_but){
			finish();
		}
		else if(v == return_but){
			finish();
		}
		else if(v == caid_but){
			Intent intent = new Intent(this,MoreCaidActivity.class);
			startActivity(intent);
			finish();
		}
	}
}
