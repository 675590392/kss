package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.FoodStandard;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @类名 FoodActivity.java
 * @包名 com.example.jyxmyt.activity
 * @作者 毅
 * @时间 2014年5月6日 下午3:01:38
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (伙食标准)
 */
public class FoodActivity extends BaseActivity implements OnClickListener {

	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	private	TextView	text_kssTitle;
	private TextView text_kssContext;
	private	TextView	text_food;
	private	TextView	text_green;
	private	TextView	text_meat;
	private	TextView	text_egg;
	private	TextView	text_oil;
	private	TextView	text_foot_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void initView() {
		setContentView(R.layout.food_activity);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		text_kssTitle = (TextView) findViewById(R.id.text_kssTitle);
		text_kssContext = (TextView) findViewById(R.id.text_kssContext);
		text_food = (TextView) findViewById(R.id.text_food);
		text_green = (TextView) findViewById(R.id.text_green);
		text_meat = (TextView) findViewById(R.id.text_meat);
		text_egg = (TextView) findViewById(R.id.text_egg);
		text_oil = (TextView) findViewById(R.id.text_oil);
		text_foot_content = (TextView) findViewById(R.id.text_foot_content);
		requestRight();
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
	 * 请求伙食标准
	 */
	private void requestRight() {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getFoodStandard()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<FoodStandard>>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
							doError(e);
						}

						@Override
						public void onNext(Entity<FoodStandard> foodStandardEntity) {
							if (checkEntity(foodStandardEntity)) {
								if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssTitle())) {
									text_kssTitle.setText(foodStandardEntity.getData().getKssTitle());
								} else {
									text_kssTitle.setText("");
								}
								if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssContext())) {
									text_kssContext.setText(foodStandardEntity.getData().getKssContext());
								} else {
									text_kssContext.setText("");
								}
								if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssFoodStandards())) {
									text_food.setText(foodStandardEntity.getData().getKssFoodStandards());
								} else {
									text_food.setText("");
								}if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssGreens())) {
									text_green.setText(foodStandardEntity.getData().getKssGreens());
								} else {
									text_green.setText("");
								}if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssMeat())) {
									text_meat.setText(foodStandardEntity.getData().getKssMeat());
								} else {
									text_meat.setText("");
								}if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssEgg())) {
									text_egg.setText(foodStandardEntity.getData().getKssEgg());
								} else {
									text_egg.setText("");
								}
								if (!TextUtils.isEmpty(foodStandardEntity.getData().getKssOil())) {
									text_oil.setText(foodStandardEntity.getData().getKssOil());
								} else {
									text_oil.setText("");
								}if (!TextUtils.isEmpty(foodStandardEntity.getData().getFootContext())) {
									text_foot_content.setText(foodStandardEntity.getData().getFootContext());
								} else {
									text_foot_content.setText("");
								}
							}else{
								checkEntityCode(foodStandardEntity);
							}

						}
					});
		}
	}

}
