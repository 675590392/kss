package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.CaidListViewAdapter;
import com.example.jyxmyt.adapter.ZXLisstViewAdapter;
import com.example.jyxmyt.app.JYYTApplication;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.EveryDay;
import com.example.jyxmyt.entity.MoreCaid;
import com.example.jyxmyt.entity.WeekEdu;
import com.example.jyxmyt.entity.WeekRecipes;
import com.example.jyxmyt.entity.Yy;
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
import android.widget.TextView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 每日菜单界面
 * 
 * @author 123
 * 
 */
public class MoreCaidActivity extends BaseActivity implements OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步

	private ListView caid_list; // 菜单显示列表

//	String set[] = {"3/24_一_包瓜片_宫爆肉丁_冬瓜胡萝卜木耳肉米",
//			"3/25_二_地姜片_榨菜干丝肉丝_塔菜肉皮", "3/26_三_咸蛋_剁椒鱼块  炸鸡排  青菜_清蒸香肠  炒素",
//			"3/27_四_脆瓜段_辣味肉米粉丝_花菜胡萝卜木耳肉片", "3/28_五_精制什锦菜_青豆粟米胡白萝卜虾仁_鱼香茄子肉米",
//			"3/29_六_美味大头菜_咸菜黄豆芽肉米_大白菜胡萝卜木耳肉皮鱼元", "3/30_日_八宝菜_大白菜炒蛋_辣味海带肉丝" };
	String set[] ={};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.more_caid_activity);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		caid_list = (ListView) findViewById(R.id.caid_list);
		caid_list.setAdapter(new CaidListViewAdapter(this, set));

		requestWeekEdu();
	}

	@Override
	public void onClick(View v) {
		if (v == home_but) {
			finish();
		} else if (v == return_but) {
			finish();
		}
	}
	/**
	 * 请求每周食谱
	 */
	private void requestWeekEdu() {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getWeekRecipes()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<List<WeekRecipes>>>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
							doError(e);
						}

						@Override
						public void onNext(Entity<List<WeekRecipes>> listEntity) {
							if (checkEntity(listEntity)) {
								//"3/24_一_包瓜片_宫爆肉丁_冬瓜胡萝卜木耳肉米"
								//06：30－07：00_起床、整理内务、洗漱、室内有秩序活动", "07：00－07：30_早餐、餐后卫生
								set = new String[listEntity.getData().size()];
								for (int i = 0; i < listEntity.getData().size(); i++) {
									WeekRecipes dayRest = listEntity.getData().get(i);
									StringBuilder sb = new StringBuilder();
									sb.append(dayRest.getKssDayTime());
									sb.append("_");
									sb.append(dayRest.getKssWeekTime());
									sb.append("_");
									sb.append(dayRest.getKssBreakfast());
									sb.append("_");
									sb.append(dayRest.getKssLunch());
									sb.append("_");
									sb.append(dayRest.getKssDinner());
									sb.append("_");
									sb.append(dayRest.getKssRemarks());
									set[i] = sb.toString();
								}
								caid_list.setAdapter(new CaidListViewAdapter(MoreCaidActivity.this, set));
							}else{
								checkEntityCode(listEntity);
							}
						}
					});
		}
	}

}
