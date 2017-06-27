package com.example.jyxmyt.activity;

import java.util.ArrayList;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.FLYZListViewAdapter;
import com.example.jyxmyt.entity.LegalAid;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;

/**
 * 法律援助
 * 
 * @author 123
 * 
 */
@SuppressLint("InlinedApi")
public class LegalAidActivity extends BaseActivity implements OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	
	private EditText search_edit; // 搜索框
	private Button search_but; // 搜索按钮
	private ListView flyz_list; // list 列表显示

	String set[] = {
			"什么是宪法？_宪法是国家的根本大法。通常规定一个国家的社会制度和国家制度的基本原则、国家机关的组织和活动的基本原则，公民的基本权利和义务等重要内容，有的还规定国旗、国歌、国徽和首都以及统治阶级认为重要的其他制度，涉及到国家生活的各个方面。宪法具有最高法律效力，是制定其他法律的依据，一切法律、法规都不得同宪法相抵触。"
					+ "通常规定一个国家的社会制度和国家制度的基本原则、国家机关的组织和活动的基本原则，公民的基本权利和义务等重要内容，有的还规定国旗、国歌、国徽和首都以及统治阶级认为重要的其他制度，涉及到国家生活的各个_",
			"什么是刑法?_刑法是规定犯罪、刑事责任和刑罚的法律，是掌握政权的统治阶极为了维护本阶级政治上的统治和经济上的利益，根据其阶级意志，规定哪些行为是犯罪并应当负刑事责任，给予犯罪人何种刑事处罚的法律。_",
			"什么是刑法?_刑法是规定犯罪、刑事责任和刑罚的法律，是掌握政权的统治阶极为了维护本阶级政治上的统治和经济上的利益，根据其阶级意志，规定哪些行为是犯罪并应当负刑事责任，给予犯罪人何种刑事处罚的法律。_",
			"什么是宪法？_宪法是国家的根本大法。通常规定一个国家的社会制度和国家制度的基本原则、国家机关的组织和活动的基本原则，公民的基本权利和义务等重要内容，有的还规定国旗、国歌、国徽和首都以及统治阶级认为重要的其他制度，涉及到国家生活的各个方面。宪法具有最高法律效力，是制定其他法律的依据，一切法律、法规都不得同宪法相抵触。"
					+ "通常规定一个国家的社会制度和国家制度的基本原则、国家机关的组织和活动的基本原则，公民的基本权利和义务等重要内容，有的还规定国旗、国歌、国徽和首都以及统治阶级认为重要的其他制度，涉及到国家生活的各个_" };

	private ArrayList<LegalAid> legalaidList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.legal_aid_activity);
		legalaidList = new ArrayList<LegalAid>();

		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);

		search_edit = (EditText) findViewById(R.id.search_edit);
		search_but = (Button) findViewById(R.id.search_but);
		search_but.setOnClickListener(this);

		flyz_list = (ListView) findViewById(R.id.flyz_list);
		
		// 从本地数据库获取数据
		queryLegalaidData();
		// requestRetrieve();
	}

	@Override
	public void onClick(View v) {
		if (v == home_but) {
			finish();
		} else if (v == return_but) {
			finish();
		} else if (v == search_but) {
			if (!"".equals(search_edit.getText().toString())) {
				searchLegalaidData(search_edit.getText().toString());
			} else {
				queryLegalaidData();
			}
		}
	}

	/**
	 * 
	 * @方法名 LegalAidActivity.java
	 * @时间 2014年4月14日 上午11:51:53
	 * @设定文件 @param ZDID
	 * @返回类型 LegalAidActivity.java
	 * @功能 (查询法律援助所有数据)
	 */
	private void queryLegalaidData() {
	}

	/**
	 * @方法名 LegalAidActivity.java
	 * @时间 2014年4月14日 下午4:49:59
	 * @设定文件 @param name
	 * @返回类型 LegalAidActivity.java
	 * @功能 (搜索名称)
	 */
	private void searchLegalaidData(String name) {
	}

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case CommonSigns.Legalaid_RETRIEVE_OK: // 查询所有数据成功
				if (legalaidList.size() > 0) {
					legalaidList.clear();
				}
				legalaidList = (ArrayList<LegalAid>) msg.obj;

				flyz_list.setAdapter(new FLYZListViewAdapter(getApplication(),
						legalaidList));
				Toast.makeText(getApplication(), "查询成功", Toast.LENGTH_SHORT).show();
				break;
			case CommonSigns.Legalaid_RETRIEVE_ON: // 查询所有数据失败
				Toast.makeText(getApplication(), "查询失败", Toast.LENGTH_LONG)
						.show();
				break;

			case CommonSigns.FLYZ:
				String result = (String) msg.obj;
				try {
					JSONObject object = new JSONObject(result);
					List<LegalAid> list = new ArrayList<LegalAid>();
					JSONArray array = object.getJSONArray("row");
					for (int i = 0; i < array.length(); i++) {
						JSONObject object2;
						object2 = array.getJSONObject(i);
						LegalAid legalAid = new LegalAid();
						legalAid.setName(object2.getString("name"));
						legalAid.setContent(object2.getString("content"));
						list.add(legalAid);
					}
					if (legalaidList.size() > 0) {
						legalaidList.clear();
					}

					legalaidList = (ArrayList<LegalAid>) list;
					flyz_list.setAdapter(new FLYZListViewAdapter(
							getApplication(), legalaidList));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			super.handleMessage(msg);
		}

	};

	/**
	 * 请求检索全部的信息
	 */
	private void requestRetrieve() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("RITP", RequestCode.FLYZ));
		WebServiceUtilThreadString threadString = new WebServiceUtilThreadString(
				handler, CommonSigns.url, list, CommonSigns.FLYZ);
		CommonSigns.pool.submit(threadString);
	}

	/**
	 * 
	 * @param name
	 * @类名 LegalAidActivity.java
	 * @包名 com.example.jyxmyt.activity
	 * @作者 毅
	 * @时间 2014年4月15日 下午5:58:03
	 * @Email ChunTian1314@vip.qq.com
	 * @版本 V1.0
	 * @功能 请求检索搜索的信息
	 */
	private void requestRetrieve(String name) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("RITP", RequestCode.FLYZ_MH));
		list.add(new BasicNameValuePair("value", name));
		WebServiceUtilThreadString threadString = new WebServiceUtilThreadString(
				handler, CommonSigns.url, list, CommonSigns.FLYZ);
		CommonSigns.pool.submit(threadString);
	}

}
