package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.CaidListViewAdapter;
import com.example.jyxmyt.adapter.CrListAdapter;
import com.example.jyxmyt.adapter.ZcListAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.PeopleNumOrder;
import com.example.jyxmyt.entity.PersonalCr;
import com.example.jyxmyt.entity.PersonalZc;
import com.example.jyxmyt.entity.RoomInfo;
import com.example.jyxmyt.entity.WeekRecipes;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.ToastUtil;

import retrofit2.http.Query;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 
 * @类名 PersonalConsumptionActivity 
 * @包名 com.example.jyxmyt.activity 
 * @作者 ChunTian   
 * @时间 2014年4月15日 下午4:06:27 
 * @Email ChunTian1314@vip.qq.com 
 * @版本 V1.0  
 * @功能 (个人消费界面)
 */
public class PersonalConsumptionActivity extends BaseActivity implements OnClickListener{
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	/*登录总布局*/
	private ListView zclist; //支出
	private ArrayList<PeopleNumOrder.RowsBean> personalZcs;
	private ZcListAdapter zcListAdapter;
	private	String kssPrisonerNum=null;
	private	int page=1;
	//上一页
	private Button btn_up_page;
	//下一页
	private Button btn_next_page;
	private Finger finger=null;
	private TextView text_consum_name;//姓名
	private TextView text_consum_num;//编号
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalconsumption_activity);
		initView();

	}
	@Override
	public void initView() {
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		btn_up_page= (Button) findViewById(R.id.btn_up_page);
		btn_up_page.setOnClickListener(this);
		btn_next_page= (Button) findViewById(R.id.btn_next_page);
		btn_next_page.setOnClickListener(this);
		text_consum_name= (TextView) findViewById(R.id.text_consum_name);
		text_consum_num= (TextView) findViewById(R.id.text_consum_num);

		Intent intent = getIntent();
		Bundle bun = intent.getExtras();
		finger = (Finger) bun.getSerializable("finger");
		kssPrisonerNum=finger.getKssPrisonerNum();
//		kssPrisonerNum="31000011120170004";
		text_consum_name.setText("姓名： "+finger.getKssPrisonerName());
		text_consum_num.setText("编号： "+finger.getKssPrisonerNum());
		/* 登录总布局 */
//		zwdl = (ImageView) findViewById(R.id.zwdl);
//		zwdl.setOnClickListener(this);
		zclist = (ListView)findViewById(R.id.zclist);
		personalZcs = new ArrayList<>();
//		initDate();
		zcListAdapter = new ZcListAdapter(getApplication(), personalZcs);
		zclist.setAdapter(zcListAdapter);

		requestPeopleNumOrder();

	}
	@Override
	public void onClick(View v) {
		if(v == return_but){
			finish();
		}
		else if(v == home_but){
			finish();
		}	else if(v == btn_up_page){
			if (page == 1) {
				btn_up_page.setEnabled(false);
				btn_next_page.setEnabled(true);
				ToastUtil.showToast(PersonalConsumptionActivity.this, "此页为第一页");
			} else {
				btn_next_page.setEnabled(true);
				page--;
				requestPeopleNumOrder();
			}
		}	else if(v == btn_next_page){
			if (0 != personalZcs.size()) {
				if (20 > personalZcs.size()) {
					btn_next_page.setEnabled(false);
					btn_up_page.setEnabled(true);
					ToastUtil.showToast(PersonalConsumptionActivity.this, "此页为最后一页");
				} else {
					btn_up_page.setEnabled(true);
					page++;
					requestPeopleNumOrder();
				}
			} else {
				// activity_home_item.removeFooterView(views);
				btn_next_page.setEnabled(false);
				btn_up_page.setEnabled(true);
				ToastUtil.showToast(PersonalConsumptionActivity.this, "此页为最后一页");
			}


		}
//		else if (v == zwdl) {
//			zwdl.setVisibility(View.GONE);
//		}
	}

	/**
	 * 请求个人消费情况
	 */
	private void requestPeopleNumOrder() {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getPeopleNumOrder(kssPrisonerNum,page)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<PeopleNumOrder>>() {
						@Override
						public void onCompleted() {
						}

						@Override
						public void onError(Throwable e) {
//							doError(e);
						}

						@Override
						public void onNext(Entity<PeopleNumOrder> listEntity) {
							if (checkEntity(listEntity)) {
								personalZcs.clear();
								personalZcs.addAll(listEntity.getData().getRows());
								zcListAdapter.notifyDataSetChanged();
							}else{
								checkEntityCode(listEntity);
							}
						}
					});
		}
	}

}
