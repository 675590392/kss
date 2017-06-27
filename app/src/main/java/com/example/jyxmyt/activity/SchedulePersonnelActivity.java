package com.example.jyxmyt.activity;

import java.util.ArrayList;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.PWGridViewAdapter;
import com.example.jyxmyt.adapter.ZRRYGridViewAdapter;
import com.example.jyxmyt.common.UserInformation;
import com.example.jyxmyt.entity.Berth;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.view.GridScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 人员安排
 * 
 * @author 123
 * 
 */
public class SchedulePersonnelActivity extends BaseActivity implements
		OnClickListener {

	private TextView zr_name; // 值日名称
	private ImageView cancel_but; // 退出按钮
	private TextView zr_describe; // 描述
	private GridScrollView ry_grid; // 人员列表
	String MX; // 描述

//	private BerthManager berthManager; // 铺位安排访问数据库类

	private ArrayList<Berth> legalaidList;
	
	private UserInformation information;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MX = getIntent().getStringExtra("MX");
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.schedule_personnel_activity);
		
		information = new UserInformation(SchedulePersonnelActivity.this);
		
		legalaidList = new ArrayList<Berth>();
//		berthManager = new BerthManager(SchedulePersonnelActivity.this);

		zr_name = (TextView) findViewById(R.id.zr_name);
		cancel_but = (ImageView) findViewById(R.id.cancel_but);
		cancel_but.setOnClickListener(this);
		zr_describe = (TextView) findViewById(R.id.zr_describe);
		zr_describe.setText("描述：" + MX);
		ry_grid = (GridScrollView) findViewById(R.id.ry_grid);

		queryBerthData(information.getGaolsNumber(),
				information.getPrisonNumber(), information.getRoomNumber());

	}

	/**
	 * 
	 * @方法名 BerthActivity.java
	 * @时间 2014年4月15日 上午10:17:08
	 * @设定文件
	 * @返回类型 BerthActivity.java
	 * @功能 (查询铺位安排人员信息所有数据)
	 */
	private void queryBerthData(String jsbh, String jqbh, String fjbh) {
//		berthManager.queryBeath(jsbh, jqbh, fjbh);
	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case CommonSigns.BERTH_RETRIEVE_OK:
				ArrayList<Berth> arrayList = (ArrayList<Berth>) msg.obj;
				if (legalaidList.size() > 0) {
					legalaidList.clear();
				}
				legalaidList = arrayList;
				ry_grid.setAdapter(new ZRRYGridViewAdapter(getApplication(),
						legalaidList));

				break;
			case CommonSigns.BERTH_RETRIEVE_ON:
				Toast.makeText(getApplication(), "查询信息失败", Toast.LENGTH_SHORT)
						.show();
				break;

			}
			super.handleMessage(msg);
		}

	};

	@Override
	public void onClick(View v) {
		if (v == cancel_but) {
			finish();
		}
	}
}
