package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.PWGridViewAdapter;
import com.example.jyxmyt.adapter.RYGridViewAdapter;
import com.example.jyxmyt.common.UserInformation;
import com.example.jyxmyt.entity.Berth;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;

/**
 * 铺位安排
 * 
 * @author 123
 * 
 */
public class BerthActivity extends BaseActivity implements OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步

	private LinearLayout shop_cainilove; // 铺位安排列表显示

//	private BerthManager berthManager; // 铺位安排访问数据库类

	private String info; // 获取用户保存信息

	private List<Berth> list;
	/* 登录总布局 */
	private LinearLayout pw_dl;
	private Button pw_dl_btv;

	private RYGridViewAdapter gridViewAdapter;
	private PWGridViewAdapter pwGridViewAdapter;

	private UserInformation information;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@SuppressLint("NewApi")
	@Override
	public void initView() {
		setContentView(R.layout.berth_activity);

		information = new UserInformation(BerthActivity.this);
		
		list = new ArrayList<Berth>();
//		berthManager = new BerthManager(BerthActivity.this);
		
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		shop_cainilove = (LinearLayout)findViewById(R.id.shop_cainilove);

		/* 登录总布局 */
		pw_dl = (LinearLayout) findViewById(R.id.pw_dl);
		pw_dl_btv = (Button) findViewById(R.id.pw_dl_btv);
		pw_dl_btv.setOnClickListener(this);

		queryBerthData(information.getGaolsNumber(),
				information.getPrisonNumber(), information.getRoomNumber());

	}

	@SuppressLint("NewApi")
	private void HosScrllViewAddAdapter(PWGridViewAdapter ian_Love) {
		shop_cainilove.removeAllViews();
		for (int i = 0; i < ian_Love.getCount(); i++) {
			final View view = ian_Love.getView(i, null, null);
			Berth bean=(Berth)ian_Love.getItem(i);
			view.setOnClickListener(new GGAndLoveClick(bean.getSnbh()));
			shop_cainilove.addView(view);
		}
	}
	Boolean boo = true;
	private class GGAndLoveClick implements View.OnClickListener{
		private String id;
		
		public GGAndLoveClick(String id) {
			super();
			this.id = id;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
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
				if (list.size() > 0) {
					list.clear();
				}
				for (int i = 0; i < arrayList.size(); i++) {
					Berth berth = new Berth();
					berth.setName(arrayList.get(i).getName());
					berth.setSnbh(arrayList.get(i).getSnbh());
					berth.setImage(arrayList.get(i).getImage());
					berth.setPwh(arrayList.get(i).getPwh());
					berth.setBool(false);
					list.add(berth);
				}
				
				pwGridViewAdapter = new PWGridViewAdapter(
						getApplication(), list);
				HosScrllViewAddAdapter(pwGridViewAdapter);

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
		if (v == home_but) {
			finish();
		} else if (v == return_but) {
			finish();
		} else if (v == pw_dl_btv) { // 登录按钮

		}
	}

	/**
	 * 
	 * @param jsbh
	 *            监所编号
	 * @param jqbh
	 *            监区编号
	 * @param fjbh
	 *            房间编号
	 * @类名 BerthActivity.java
	 * @包名 com.example.jyxmyt.activity
	 * @作者 毅
	 * @时间 2014年4月16日 上午11:35:41
	 * @Email ChunTian1314@vip.qq.com
	 * @版本 V1.0
	 * @功能 用(检索铺位安排内容)
	 */
	private void requestpwap(String jsbh, String jqbh, String fjbh) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("RITP", RequestCode.PW_LB));
		list.add(new BasicNameValuePair("JSPH", jsbh));
		list.add(new BasicNameValuePair("JQBH", jqbh));
		list.add(new BasicNameValuePair("FJBH", fjbh));
		WebServiceUtilThreadString threadString = new WebServiceUtilThreadString(
				handler, CommonSigns.url, list, CommonSigns.PW_LB);
		CommonSigns.pool.submit(threadString);
	}

}
