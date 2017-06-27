package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.CaidListViewAdapter;
import com.example.jyxmyt.adapter.YYListViewAdapter;
import com.example.jyxmyt.entity.BookingInfo;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.IdBooking;
import com.example.jyxmyt.entity.LegalAid;
import com.example.jyxmyt.entity.PeopleNumBooking;
import com.example.jyxmyt.entity.WeekEdu;
import com.example.jyxmyt.entity.WeekRecipes;
import com.example.jyxmyt.entity.Yy;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;
import com.example.jyxmyt.util.ToastUtil;
import com.example.jyxmyt.view.SaituDialog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 人员预约
 * 
 * @author 123
 * 
 */
public class ResearchersReportActivity extends BaseActivity implements
		OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	/* 未预约或者预约成功总布局 */
	private LinearLayout not_yy; // 未预约或者预约成功总布局
	private TextView yy_tis; // 预约提示控件
	/* 预约布局 */
	private LinearLayout yy_layout; // 预约总布局
	private TextView yy_item; // 预约项显示控件
	private EditText yy_user_ly; // 输入留言框
	private Button yy_ok_but; // 确认预约按钮
	/* 审核布局或者是审核通过 */
	private LinearLayout audit_layout; // 审核总布局
	private TextView yy_bm; // 预约项
	private EditText user_ly; // 自己留言显示
	private TextView sh_ts; // 审核提示
	private Button end_yy; // 终止预约或者是完成此次预约按钮
//	private Button return_yy; // 返回按钮

	private ListView yy_list; // 显示可预约的部门或人员列表
	private YYListViewAdapter yyListViewAdapter;

//	String set[] = { "管教预约", "驻所检察预约", "所领导预约", "心理咨询预约", "医生会诊预约" ,"分局领导预约","律师预约"};
    String set[] = {};
//	int yunyue[] = { 1, 2, 3, 4, 1 ,1,1};
    int yunyue[] = {};
	int pos;
	private Finger finger=null;
	private String kssPrisonerNum;//人员编号
	private int id;//id
	private Entity<List<PeopleNumBooking>> listEntityData;
	private	BookingInfo bookingInfo;
    private Entity<IdBooking> idBookingEntityData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.researchers_report_activity);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		/* 未预约或者预约成功总布局 */
		not_yy = (LinearLayout) findViewById(R.id.not_yy);
		yy_tis = (TextView) findViewById(R.id.yy_tis);
		/* 预约布局 */
		yy_layout = (LinearLayout) findViewById(R.id.yy_layout);
		yy_item = (TextView) findViewById(R.id.yy_item);
		yy_user_ly = (EditText) findViewById(R.id.yy_user_ly);
		yy_ok_but = (Button) findViewById(R.id.yy_ok_but);
		yy_ok_but.setOnClickListener(this);
		/* 审核布局或者是审核通过 */
		audit_layout = (LinearLayout) findViewById(R.id.audit_layout);
		yy_bm = (TextView) findViewById(R.id.yy_bm);
		user_ly = (EditText) findViewById(R.id.user_ly);
		sh_ts = (TextView) findViewById(R.id.sh_ts);
		end_yy = (Button) findViewById(R.id.end_yy);
		end_yy.setOnClickListener(this);
        if(bookingInfo==null){
	        bookingInfo=new BookingInfo();
           }
		Intent intent = getIntent();
		Bundle bun = intent.getExtras();
		finger = (Finger) bun.getSerializable("finger");
		kssPrisonerNum=finger.getKssPrisonerNum();
		initDate(5);
		yy_list = (ListView) findViewById(R.id.yy_list);
		yyListViewAdapter = new YYListViewAdapter(this, set, yunyue) {
			@Override
			public void bookingButton(int position, int vales) {
				SetPostion(position);
				pos = position;
				int id=listEntityData.getData().get(pos).getId();
				requestIdBooking(id,vales);
//				initDate(vales);
			}
		};
		yy_list.setAdapter(yyListViewAdapter);

		requestPeopleNumBooking();
	}

	@Override
	public void onClick(View v) {
		if (v == home_but) {
			finish();
		} else if (v == return_but) {
			finish();
		}
		else if (v == yy_ok_but) { // 确认预约按钮
			bookingInfo.setKssArea(finger.getKssArea());
			bookingInfo.setKssPrisonerNum(finger.getKssPrisonerNum());
			bookingInfo.setKssRoomNum(finger.getKssRoomNum());
			bookingInfo.setKssPrisoner(finger.getKssPrisonerName());
			bookingInfo.setKssBookingContent(yy_user_ly.getText().toString().trim());
			bookingInfo.setKssType(listEntityData.getData().get(pos).getKssType());
			requestBookingInfo();
		} else if (v == end_yy) { // 终止或是完成此次预约
			yunyue[pos] = 3;
			initDate(listEntityData.getData().get(pos).getKssType());
			yyListViewAdapter.notifyDataSetChanged();
			yyListViewAdapter.SetPostion(-1);
		}
	}


	public void initDate(int split) {
		if (split == 5) {
			not_yy.setVisibility(View.VISIBLE);
			yy_layout.setVisibility(View.GONE);
			audit_layout.setVisibility(View.GONE);
			yy_tis.setText("您还暂未预约任何相关部门,\n\n是否需要预约处理相关事件。");
		} else if (split == 3) { // 预约界面
			not_yy.setVisibility(View.GONE);
			yy_layout.setVisibility(View.VISIBLE);
			audit_layout.setVisibility(View.GONE);
			yy_item.setText(set[pos]);
		} else if (split == 0 || split == 1|| split == 2) { // 审核中或审核通过界面
			not_yy.setVisibility(View.GONE);
			yy_layout.setVisibility(View.GONE);
			audit_layout.setVisibility(View.VISIBLE);
			yy_bm.setText(set[pos]);
            user_ly.setText(Html.fromHtml(idBookingEntityData.getData().getKssBookingContent()));
			if (split == 0) {
				sh_ts.setText("您的预约正在审核中...");
                end_yy.setVisibility(View.GONE);
			} else if (split == 1|| split == 2) {
                end_yy.setVisibility(View.VISIBLE);
				sh_ts.setText(Html.fromHtml(idBookingEntityData.getData().getKssResponses()));
			}
		}
	}

	/**
	 * 
	 * @方法名 MainActivity.java
	 * @时间 2014年4月11日 下午3:22:34
	 * @设定文件
	 * @返回类型 MainActivity.java
	 * @功能 (判断是否为空)
	 */
	private Boolean isnull(String text) {
		return !(text.equals("") || text.length() <= 0 || text == null);
	}
	/**
	 * 请求通过人员编号查询预约列表
	 */
	private void requestPeopleNumBooking() {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getPeopleNumBooking(kssPrisonerNum)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<List<PeopleNumBooking>>>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
                            doError(e);
						}

						@Override
						public void onNext(Entity<List<PeopleNumBooking>> listEntity) {
							if (checkEntity(listEntity)) {
								listEntityData=listEntity;
								//	String set[] = { "管教预约", "驻所检察预约", "所领导预约", "心理咨询预约", "医生会诊预约" ,"分局领导预约","律师预约"};
								set = new String[listEntity.getData().size()];
								for (int i = 0; i < listEntity.getData().size(); i++) {
									PeopleNumBooking dayRest = listEntity.getData().get(i);
									StringBuilder sb = new StringBuilder();
									if(dayRest.getKssType()==1){
										sb.append("律师会见预约");
									}else if(dayRest.getKssType()==2){
										sb.append("管教谈话预约");
									}else if(dayRest.getKssType()==3){
										sb.append("心理咨询预约");
									}else if(dayRest.getKssType()==4){
										sb.append("医生会诊预约");
									}
									set[i] = sb.toString();
								}
								yunyue = new int[listEntity.getData().size()];
								for (int i = 0; i < listEntity.getData().size(); i++) {
									PeopleNumBooking dayRest = listEntity.getData().get(i);
									StringBuilder sb = new StringBuilder();
										sb.append(dayRest.getKssState());
									yunyue[i] = Integer.valueOf(sb.toString());
								}
								yyListViewAdapter = new YYListViewAdapter(ResearchersReportActivity.this, set, yunyue) {
									@Override
									public void bookingButton(int position, int vales) {
										// yyState = set[position].split("_")[1];
										SetPostion(position);
										pos = position;
										int id=listEntityData.getData().get(pos).getId();
										requestIdBooking(id,vales);
									}
								};
								yy_list.setAdapter(yyListViewAdapter);
							}else{
								checkEntityCode(listEntity);
							}
						}
					});
		}
	}
	/**
	 * 请求通过预约ID查询预约信息
	 */
	private void requestIdBooking(int id, final int vales) {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getIdBooking(id)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<IdBooking>>() {
						@Override
						public void onCompleted() {
						}
						@Override
						public void onError(Throwable e) {
							doError(e);
						}
						@Override
						public void onNext(Entity<IdBooking> idBookingEntity) {
							if (checkEntity(idBookingEntity)) {
                                idBookingEntityData=idBookingEntity;
								initDate(vales);
								yyListViewAdapter.notifyDataSetChanged();
							}else{
								checkEntityCode(idBookingEntity);
							}

						}
					});
		}
	}

	/**
	 * 添加预约信息，在指纹设置时间内
	 */
	private void requestBookingInfo() {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getBookingInfo( bookingInfo)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
                            doError(e);
						}

						@Override
						public void onNext(Entity entity) {
							if (checkEntity(entity)) {
								ToastUtil.showToast(ResearchersReportActivity.this, "预约成功");
								requestPeopleNumBooking();
								not_yy.setVisibility(View.GONE);
								yy_layout.setVisibility(View.GONE);
								audit_layout.setVisibility(View.VISIBLE);
								yy_bm.setText(set[pos]);
								user_ly.setText(Html.fromHtml(yy_user_ly.getText().toString().trim()));
									sh_ts.setText("您的预约正在审核中...");
									end_yy.setVisibility(View.GONE);
								yy_user_ly.setText("");

							}else{
								ToastUtil.showToast(ResearchersReportActivity.this, "预约失败");
							}
						}
					});
		}
	}

}
