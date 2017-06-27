package com.example.jyxmyt.activity;

import com.example.jyxmyt.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * 登陆界面
 * @author 123
 *
 */
public class LogInActivity extends BaseActivity implements OnClickListener{
	
	private ImageView login_ok; //登陆
	private ImageView login_on; //取消
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView(){
		setContentView(R.layout.login_activity);
		login_ok = (ImageView)findViewById(R.id.login_ok);
		login_ok.setOnClickListener(this);
		login_on = (ImageView)findViewById(R.id.login_on);
		login_on.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		if(v == login_ok){
			Toast.makeText(this, "登陆...", 1).show();
		}else if (v == login_on) {
			finish();
			Toast.makeText(this, "取消...", 1).show();
		}
		
	}

}
