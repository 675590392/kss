package com.example.jyxmyt.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.PublicScheduleAapter;
import com.example.jyxmyt.adapter.ScheduleGridTime;
import com.example.jyxmyt.adapter.ZRGridViewAdapter;
import com.example.jyxmyt.common.CalendarUtil;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.EveryDay;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.PublicQueryUserDuty;
import com.example.jyxmyt.entity.RoomNumPlaceDuty;
import com.example.jyxmyt.entity.Schedule;
import com.example.jyxmyt.entity.publicWeek;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PublicScheduleActivity extends BaseActivity implements View.OnClickListener {
    //周一
    public static final  String MONDAY="一";
    //周二...
    public static final  String TUESDAY="二";
    public static final  String WEDNESDAY="三";
    public static final  String THURSDAY="四";
    public static final  String FRIDAY="五";
    public static final  String SATURDAY="六";
    public static final  String SUNDAY="日";

    private ImageView long_head; // 登陆者的头像
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private ListView zr_grid; // 显示内务的列表
    private GridView gridTime; // 星期几按钮
    private String todayTime; // 今日日期 //yyyy-MM-dd HH:mm:ss
    private int pos = 2; //
    private ScheduleGridTime scheduleGridTime;
    //	private ImageView morning; //上午
//	private ImageView afternoon; //中午
//	private ImageView night; //晚上
    private String set[];
    private	String kssRoomNum;
    private List<PublicQueryUserDuty> list;
    private PublicScheduleAapter adapter;
    private  Entity<List<PublicQueryUserDuty>> listEntityData;
    private publicWeek WeekTj=null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        todayTime = CalendarUtil.getNowTime("HH:mm:ss");
        initView();

    }

    @Override
    public void initView(){
        setContentView(R.layout.activity_public_schedule);
        zr_grid = (ListView) findViewById(R.id.zr_grid);
        long_head = (ImageView) findViewById(R.id.long_head);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        gridTime = (GridView) findViewById(R.id.gridTime);
        kssRoomNum = CommonUtil.getkssRoomNum(PublicScheduleActivity.this);
        if(WeekTj==null){
            WeekTj=new publicWeek();
        }
        WeekTj.setKssRoomNum(kssRoomNum);

        scheduleGridTime = new ScheduleGridTime(PublicScheduleActivity.this, pos) {
//            @Override
//            public void time(String time, int position){
//                pos = position;
//                upBackground(position);
//                adapter = new PublicScheduleAapter(PublicScheduleActivity.this, list);
//                zr_grid.setAdapter(adapter);
//            }
        };
        gridTime.setAdapter(scheduleGridTime);
//        scheduleGridTime.upBackground(pos);
        gridTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             switch (i){
                case 0:{
                    WeekTj.setWeekStr(MONDAY);
                    requestPublicQueryUserDuty(WeekTj);
                    scheduleGridTime.upBackground(i);
                       }
                     break;
                 case 1:{
                     WeekTj.setWeekStr(TUESDAY);
                     requestPublicQueryUserDuty(WeekTj);
                     scheduleGridTime.upBackground(i);
                 }
                 break;
                 case 2:{
                     WeekTj.setWeekStr(WEDNESDAY);
                     requestPublicQueryUserDuty(WeekTj);
                     scheduleGridTime.upBackground(i);
                 }
                 break;
                 case 3:{
                     WeekTj.setWeekStr(THURSDAY);
                     requestPublicQueryUserDuty(WeekTj);
                     scheduleGridTime.upBackground(i);
                 }
                 break;
                 case 4:{
                     WeekTj.setWeekStr(FRIDAY);
                     requestPublicQueryUserDuty(WeekTj);
                     scheduleGridTime.upBackground(i);
                 }
                 break;
                 case 5:{
                     WeekTj.setWeekStr(SATURDAY);
                     requestPublicQueryUserDuty(WeekTj);
                     scheduleGridTime.upBackground(i);
                 }
                 break;
                 case 6:{
                     WeekTj.setWeekStr(SUNDAY);
                     requestPublicQueryUserDuty(WeekTj);
                     scheduleGridTime.upBackground(i);
                 }
                 break;
}
            }
        });
        list = new ArrayList<>();
        adapter = new PublicScheduleAapter(PublicScheduleActivity.this, list);
        zr_grid.setAdapter(adapter);
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String   mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String   mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String   mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            //天
            WeekTj.setWeekStr(SUNDAY);
            requestPublicQueryUserDuty(WeekTj);
        }else if("2".equals(mWay)){
            //一
            WeekTj.setWeekStr(MONDAY);
            requestPublicQueryUserDuty(WeekTj);
        }else if("3".equals(mWay)){
            //二
            WeekTj.setWeekStr(TUESDAY);
            requestPublicQueryUserDuty(WeekTj);
        }else if("4".equals(mWay)){
            //三
            WeekTj.setWeekStr(WEDNESDAY);
            requestPublicQueryUserDuty(WeekTj);
        }else if("5".equals(mWay)){
            //四
            WeekTj.setWeekStr(THURSDAY);
            requestPublicQueryUserDuty(WeekTj);
        }else if("6".equals(mWay)){
            //五
            WeekTj.setWeekStr(FRIDAY);
            requestPublicQueryUserDuty(WeekTj);
        }else if("7".equals(mWay)){
            //六
            WeekTj.setWeekStr(SATURDAY);
            requestPublicQueryUserDuty(WeekTj);
        }
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
     * 请求通用版  通过房间号查询七天值班值日
     */
    private void requestPublicQueryUserDuty(publicWeek WeekTjs) {

        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getPublicQueryUserDuty(WeekTjs)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<PublicQueryUserDuty>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<PublicQueryUserDuty>> listEntity) {
                            if (checkEntity(listEntity)) {
                                listEntityData=listEntity;
                                list.clear();
                                list.addAll(listEntity.getData());
                                adapter.notifyDataSetChanged();
                            }else{
                                checkEntityCode(listEntity);
                            }
                        }
                    });
        }
    }
}
