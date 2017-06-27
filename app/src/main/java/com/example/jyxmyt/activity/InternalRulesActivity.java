package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.NBListViewAdapter;
import com.example.jyxmyt.adapter.ZXLisstViewAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.EveryDay;
import com.example.jyxmyt.entity.InterRules;
import com.example.jyxmyt.entity.InternalRules;
import com.example.jyxmyt.entity.LegalAid;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 内部规定
 * 
 * @author 123
 * 
 */
public class InternalRulesActivity extends BaseActivity implements
		OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步

	private ListView nb_list; // listView控件显示栏
//	String set[] = { "本市看守所犯罪嫌疑人、被告人羁押表现考核及评定实施细则(试行)", "看守所罪犯双向视频会见须知",
//			"上海市看守所在押人员用水细则", "受虐报警设备使用手册", "预防和打击“牢头狱霸”相关法律法规和规定",
//			"在押人员须知Detainees Notice" };
	String set[] = {};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.internal_rules_activity);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);

		nb_list = (ListView) findViewById(R.id.nb_list);
		nb_list.setAdapter(new NBListViewAdapter(this, set) {
			@Override
			public void showContent(int position) {
				Intent intent = new Intent(InternalRulesActivity.this,
						NBContentActivity.class);
				intent.putExtra("title", set[position]);
				intent.putExtra("address", "");
				intent.putExtra("id", position);
				startActivity(intent);
			}
		});
//		requestInterRules();
	}

	@Override
	public void onClick(View v) {
		if (v == home_but) {
			finish();
		} else if (v == return_but) {
			finish();
		}
	}
//	/**
//	 * 请求监纪监规
//	 */
//	private void requestInterRules() {
//		if (checkNetwork()) {
//			RetrofitClient.getCommonApi().getInterRules()
//					.subscribeOn(Schedulers.io())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe(new Subscriber<Entity<List<InterRules>>>() {
//						@Override
//						public void onCompleted() {
//
//						}
//
//						@Override
//						public void onError(Throwable e) {
//
//						}
//
//						@Override
//						public void onNext(Entity<List<InterRules>> listEntity) {
//							if (checkEntity(listEntity)) {
//								set = new String[listEntity.getData().size()];
//								for (int i = 0; i < listEntity.getData().size(); i++) {
//									InterRules interRules = listEntity.getData().get(i);
//									StringBuilder sb = new StringBuilder();
//									sb.append(interRules.getKssTitle());
//									set[i] = sb.toString();
//								}
//
//								nb_list.setAdapter(new NBListViewAdapter(InternalRulesActivity.this,set) {
//									@Override
//									public void showContent(int position) {
//										Intent intent = new Intent(InternalRulesActivity.this,
//												NBContentActivity.class);
//										intent.putExtra("title", set[position]);
//										intent.putExtra("address", "");
//										intent.putExtra("id", position);
//										startActivity(intent);
//									}
//								});
//							}
//						}
//					});
//		}
//	}


}
