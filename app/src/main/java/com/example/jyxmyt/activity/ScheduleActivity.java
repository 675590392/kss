package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.BunkAgrementAapter;
import com.example.jyxmyt.adapter.ScheduleGridTime;
import com.example.jyxmyt.adapter.ZRGridViewAdapter;
import com.example.jyxmyt.common.CalendarUtil;
import com.example.jyxmyt.entity.Berth;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.RoomNumPlaceDuty;
import com.example.jyxmyt.entity.Schedule;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;
import com.example.jyxmyt.util.CommonUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 
 * @类名 ScheduleActivity
 * @包名 com.example.jyxmyt.activity
 * @作者 ChunTian
 * @时间 2014年4月9日 下午4:12:24
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (值日安排)
 */

public class ScheduleActivity extends BaseActivity implements OnClickListener {
	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	private ListView zr_grid; // 显示内务的列表
	private GridView gridTime; // 星期几按钮
	private List<Schedule> schedules;
	private String todayTime; // 今日日期 //yyyy-MM-dd HH:mm:ss
	private int pos = 2; //
	private ScheduleGridTime scheduleGridTime;
//	private ImageView morning; //上午
//	private ImageView afternoon; //中午
//	private ImageView night; //晚上
	private String set[];
	private	String kssRoomNum;
	private List<PersonThing> list;
	private ZRGridViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		todayTime = CalendarUtil.getNowTime("HH:mm:ss");
		initView();
	}

	@Override
	public void initView(){
		setContentView(R.layout.schedule_activity);
//		morning = (ImageView)findViewById(R.id.morning);
//		morning.setOnClickListener(this);
//		afternoon = (ImageView)findViewById(R.id.afternoon);
//		afternoon.setOnClickListener(this);
//		night = (ImageView)findViewById(R.id.night);
//		night.setOnClickListener(this);
		zr_grid = (ListView) findViewById(R.id.zr_grid);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		gridTime = (GridView) findViewById(R.id.gridTime);
		kssRoomNum = CommonUtil.getkssRoomNum(ScheduleActivity.this);
		scheduleGridTime = new ScheduleGridTime(ScheduleActivity.this, pos) {
//			@Override
//			public void time(String time, int position){
//					pos = position;
//					upBackground(position);
//				adapter = new ZRGridViewAdapter(ScheduleActivity.this, list);
//				zr_grid.setAdapter(adapter);
//			}
		};
		gridTime.setAdapter(scheduleGridTime);
		scheduleGridTime.upBackground(pos);
//		String[] time = todayTime.split(":");
//		set = new String[]{ "05:30~06:00", "06:30~07:00", "07:30~08:00",
//					"08:30~09:00", "09:30~10:00", "10:30~11:00", "11:30~12:00"};
		list = new ArrayList<>();

		adapter = new ZRGridViewAdapter(ScheduleActivity.this, list);
		zr_grid.setAdapter(adapter);

		requestRoomNumPlaceDuty();
	}

	private List<Schedule> name(int a) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		String namen[] = { "1", "2", "3", "4" };
		String name = "";
		String snbh = "";
		if(a %2 == 0){
			for(int i = 0;i < set.length;i++){
				Schedule zrlb = new Schedule();
				zrlb.setTime(set[i]);
				if(i== 0){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "蔡平";
							snbh += "\n" + "20100127";
						}else if(j == 0){
							name = "王挺";
							snbh = "20110328";
						}
					}
					zrlb.setNwfl("垃圾清倒");
					zrlb.setContent("");
				}else if(i== 1){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "刘景隆";
							snbh += "\n" + "20120494";
						}else if(j == 0){
							name = "曹建华";
							snbh = "20120499";
						}
					}
					zrlb.setNwfl("桌面擦拭干净整洁");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 2){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "翁永舟";
							snbh += "\n" + "20120571";
						}else if(j == 0){
							name = "丰成";
							snbh = "20120581";
						}
						
					}
					zrlb.setNwfl("清洁地面");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 3){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "袁荣增";
							snbh += "\n" + "20130005";
						}else if(j == 0){
							name = "邵智华";
							snbh = "20130021";
						}
						
					}
					zrlb.setNwfl("打扫走廊并拖地");
					zrlb.setContent("");
				}else if(i== 4){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "张凌华";
							snbh = "20110367";
						}else if(j == 0){
							name = "藤井英孝";
							snbh = "20120483";
						}
					}
					zrlb.setNwfl("打扫厕所卫生");
					zrlb.setContent("");
				}else if(i== 5){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "张长法";
							snbh = "20130026";
						}else if(j == 0){
							name = "黄玉建";
							snbh = "20130028";
						}
					}
					zrlb.setNwfl("打扫并倒垃圾");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 6){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "陈明";
							snbh = "20120583";
						}else if(j == 0){
							name = "赵诺";
							snbh = "20120585";
						}
					}
					zrlb.setNwfl("打扫并清理室外卫生区垃圾");
					zrlb.setContent("要求注意地面清扫");
				}else {
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "陈健";
							snbh = "20120517";
						}else if(j == 0){
							name = "柴和国";
							snbh = "20120568";
						}
					}
				}
				zrlb.setName(name);
				zrlb.setSnbh(snbh);
				schedules.add(zrlb);
			}
		}else{
			for(int i = 0;i < set.length;i++){
				Schedule zrlb = new Schedule();
				zrlb.setTime(set[i]);
				if(i== 0){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "大山";
							snbh += "\n" + "20100127";
						}else if(j == 0){
							name = "姚福明";
							snbh = "20114528";
						}
					}
					zrlb.setNwfl("打扫并清理室外卫生区垃圾");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 1){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "阿曼";
							snbh += "\n" + "20121256";
						}else if(j == 0){
							name = "徐瑶瑶";
							snbh = "20145678";
						}
						
					}
					zrlb.setNwfl("打扫并倒垃圾");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 2){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "周伟明";
							snbh += "\n" + "20120635";
						}else if(j == 0){
							name = "黄文康";
							snbh = "20120435";
						}
						
					}
					zrlb.setNwfl("垃圾清倒");
					zrlb.setContent("");
				}else if(i== 3){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name += "\n" + "赵明";
							snbh += "\n" + "20130007";
						}else if(j == 0){
							name = "周建龙";
							snbh = "20133178";
						}
					}
					zrlb.setNwfl("清洁地面");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 4){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "宋文洲";
							snbh = "20113276";
						}else if(j == 0){
							name = "熊利兰";
							snbh = "20120875";
						}
					}
					zrlb.setNwfl("桌面擦拭干净整洁");
					zrlb.setContent("要求注意地面清扫");
				}else if(i== 5){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "罗道夫";
							snbh = "20135676";
						}else if(j == 0){
							name = "马景波";
							snbh = "20134348";
						}
					}
					zrlb.setNwfl("打扫走廊并拖地");
					zrlb.setContent("");
				}else if(i== 6){
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "曹勇";
							snbh = "201265443";
						}else if(j == 0){
							name = "李慧";
							snbh = "20134585";
						}
					}
					zrlb.setNwfl("清洁地面");
					zrlb.setContent("要求注意地面清扫");
				}else {
					for(int j = 0;j < 2;j++){
						if(j == 1){
							name = "麦克";
							snbh = "20134517";
						}else if(j == 0){
							name = "金昶";
							snbh = "20145234";
						}
					}
					zrlb.setNwfl("打扫走廊并拖地");
					zrlb.setContent("");
				}
				zrlb.setName(name);
				zrlb.setSnbh(snbh);
				schedules.add(zrlb);
			}
		}
		
		return schedules;
	}
	
	@Override
	public void onClick(View v){
		if(v == home_but){
			finish();
		}
		else if(v == return_but){
			finish();
		}
	}
	/**
	 * 请求鋪位安排
	 */
	private void requestRoomNumPlaceDuty() {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getRoomNumPlaceDuty(kssRoomNum)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<List<RoomNumPlaceDuty>>>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
							doError(e);
						}

						@Override
						public void onNext(Entity<List<RoomNumPlaceDuty>> listEntity) {
							if (checkEntity(listEntity)) {
								List<PersonThing> personThings = getPersonList(listEntity.getData().get(0));
								list.clear();
								list.addAll(personThings);
								adapter.notifyDataSetChanged();
							}else{
								checkEntityCode(listEntity);
							}
						}
					});
		}
	}

	/**
	 * private String fh;//编号
	 * private String xm;//姓名
	 * private String pw;//铺位
	 * private String yj;//眼睛
	 * private String zw;//座位
	 * private String zr;//值日
	 * private String bz;//备注
	 *
	 * @param roomNumPlaceDuty
	 */
	private List<PersonThing> getPersonList(RoomNumPlaceDuty roomNumPlaceDuty) {
		String json = JSON.toJSONString(roomNumPlaceDuty);
		Map<String, Object> hashMap = JSON.parseObject(json, HashMap.class);

		List<PersonThing> list = initPersonList();
		Iterator<String> iterator = hashMap.keySet().iterator();
		while (iterator.hasNext()) {
			String name = iterator.next();
			String value = hashMap.get(name).toString();
			for (int i = 0; i < list.size(); i++) {
				if (name.startsWith("fh")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setFh(value);
				} else if (name.startsWith("xm")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setXm(value);
				} else if (name.startsWith("pw")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setPw(value);
				} else if (name.startsWith("yj")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setYj(value);
				} else if (name.startsWith("zw")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setZw(value);
				} else if (name.startsWith("zr")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setZr(value);
				} else if (name.startsWith("bz")) {
					if (name.endsWith("_" + (i + 1)))
						list.get(i).setBz(value);
				} else if (name.startsWith("dz")) {
					if (name.endsWith("" + (i + 1)))
						list.get(i).setDz(value);
				} else if (name.startsWith("time")) {
					if (name.endsWith("" + (i + 1))) {
						list.get(i).setTime(value);
					}

				}
			}
		}

		return list;

	}

	private List<PersonThing> initPersonList() {

		List<PersonThing> personThings = new ArrayList<>();

		for (int i = 1; i <= 13; i++) {
			PersonThing personThing = new PersonThing();
			personThings.add(personThing);
		}
		return personThings;
	}
}
