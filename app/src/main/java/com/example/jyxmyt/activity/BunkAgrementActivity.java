package com.example.jyxmyt.activity;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.BunkAgrementAapter;
import com.example.jyxmyt.adapter.WeekEeducatListAapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.RoomNumPlaceDuty;
import com.example.jyxmyt.entity.WeekEdu;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BunkAgrementActivity extends BaseActivity implements View.OnClickListener {
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private ListView listview_pw; // 显示一日作息列表
    private TextView txt_zgj_name;
    private TextView txt_xgj_name;
    private List<PersonThing> list;
    BunkAgrementAapter adapter;
    String kssRoomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zbb_activity);
        initView();
    }

    @Override
    public void initView() {
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        listview_pw = (ListView) findViewById(R.id.listview_pw);
        txt_zgj_name = (TextView) findViewById(R.id.txt_zgj_name);
        txt_xgj_name = (TextView) findViewById(R.id.txt_xgj_name);
        list = new ArrayList<>();
        adapter = new BunkAgrementAapter(BunkAgrementActivity.this, list);
        listview_pw.setAdapter(adapter);
        kssRoomNum = CommonUtil.getkssRoomNum(BunkAgrementActivity.this);
//        if(!TextUtils.isEmpty(CommonUtil.getkssRoomNum(BunkAgrementActivity.this))){
//
//        }else{
//            kssRoomNum= "0101";
//        }

        requestRoomNumPlaceDuty();
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
