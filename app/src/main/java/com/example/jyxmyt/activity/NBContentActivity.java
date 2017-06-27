package com.example.jyxmyt.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.jyxmyt.R;

/**
 * 内部规定内容显示
 * 
 * @author 123
 * 
 */
public class NBContentActivity extends BaseActivity implements OnClickListener {

	private WebView content_xs; // 内容显示

	private LinearLayout linearLayout_radioGroup;
	private RadioButton chinese; // 中文
	private RadioButton english; // 英文
	private LinearLayout ej_back;
	private Boolean boolean1;
	private String address;
	private ImageView return_but;
	private ImageView home_but;
	String title; // 标题
	int id; // ID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		title = getIntent().getStringExtra("title");
		id = getIntent().getIntExtra("id", 0);
		address = getIntent().getStringExtra("address");
		initView();
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void initView() {
		setContentView(R.layout.nbcontent_activity);

		boolean1 = true;

		home_but=(ImageView) findViewById(R.id.rhome_but);
		
		home_but.setOnClickListener(this);
		return_but=(ImageView) findViewById(R.id.rreturn_but);
		return_but.setOnClickListener(this);
		
		ej_back = (LinearLayout)findViewById(R.id.ej_back);
		ej_back.setOnClickListener(this);
		
		// 中英文切换
		linearLayout_radioGroup = (LinearLayout) findViewById(R.id.linearLayout_radioGroup);
		chinese = (RadioButton) findViewById(R.id.chinese);
		chinese.setOnClickListener(this);
		english = (RadioButton) findViewById(R.id.english);
		english.setOnClickListener(this);

		content_xs = (WebView) findViewById(R.id.content_xs);

		// 设置启动JS脚本
		content_xs.getSettings().setJavaScriptEnabled(true);
		// 设置可以支持缩放
		content_xs.getSettings().setSupportZoom(true);
		// 设置默认缩放方式尺寸
		// content_xs.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		// 设置出现缩放工具，鼠标左键拖动出现
		content_xs.getSettings().setBuiltInZoomControls(true);
		// 设置默认编码
		content_xs.getSettings().setDefaultTextEncodingName("UTF-8");
		content_xs.setBackgroundColor(0); // 设置背景色
		// 调用JS静态网页路径
		showHtml(id);

	}

	@Override
	public void onClick(View v) {
		if (v == ej_back) {
			finish();
		} else if(v == chinese) {
			chinese.setChecked(true);
			boolean1 = true;
//			showHtml(id);
		} else if (v == english) {
			english.setChecked(true);
			boolean1 = false;
//			showHtml(id);
		}
		else if (v == home_but) {
			Intent intent=new Intent();
			intent.setClass(NBContentActivity.this, MainActivity.class);
			startActivity(intent);
		} else if (v == return_but) {
			finish();
		}
	}

	@SuppressLint("ResourceAsColor")
	private void showHtml(int id) {
		switch (id) {
		case 0:
			linearLayout_radioGroup.setVisibility(View.GONE);
			content_xs.loadUrl("file:///android_asset/content_xs01.html");
			break;
		case 1:
			linearLayout_radioGroup.setVisibility(View.GONE);
			content_xs.loadUrl("file:///android_asset/content_xs02.html");
			break;
		case 2:
			if (boolean1) {
				content_xs.loadUrl("file:///android_asset/content_xs03.html");
			} else {
				content_xs
						.loadUrl("file:///android_asset/content_xs03_yw.html");
			}
			break;
		case 3:
			if (boolean1) {
				content_xs.loadUrl("file:///android_asset/content_xs04.html");
			} else {
				content_xs
						.loadUrl("file:///android_asset/content_xs04_yw.html");
			}
			break;
		case 4:
			if (boolean1) {
				content_xs.loadUrl("file:///android_asset/content_xs05.html");
			} else {
				content_xs
						.loadUrl("file:///android_asset/content_xs05_yw.html");
			}
			break;
		case 5:
			if (boolean1) {
				content_xs.loadUrl("file:///android_asset/content_xs06.html");
			} else {
				content_xs
						.loadUrl("file:///android_asset/content_xs06_yw.html");
			}
			break;
		case 6:
			linearLayout_radioGroup.setVisibility(View.GONE);
			content_xs.loadUrl(address);
		default:
			break;
		}
	}
}
