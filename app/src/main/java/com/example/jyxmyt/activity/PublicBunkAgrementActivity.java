package com.example.jyxmyt.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.BunkAgrementAapter;
import com.example.jyxmyt.adapter.PublicBunkAgrementAapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.PublicIpBunkInfo;
import com.example.jyxmyt.entity.RoomNumPlaceDuty;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PublicBunkAgrementActivity extends BaseActivity implements View.OnClickListener  {

    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private ListView listview_pw; // 显示一日作息列表
    private TextView txt_zgj_name;
    private TextView txt_xgj_name;
    private List<PublicIpBunkInfo.RowBean> list;
    private PublicBunkAgrementAapter adapter;
    String kssRoomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_bunk_agrement);
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
        adapter = new PublicBunkAgrementAapter(PublicBunkAgrementActivity.this, list);
        listview_pw.setAdapter(adapter);
        kssRoomNum = CommonUtil.getkssRoomNum(PublicBunkAgrementActivity.this);

        requestPublicIpBunkInfo();
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
     * 请求通用版鋪位安排
     */
    private void requestPublicIpBunkInfo() {

        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getPublicIpBunkInfo(kssRoomNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<PublicIpBunkInfo>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                                 doError(e);
                        }

                        @Override
                        public void onNext(Entity<PublicIpBunkInfo> publicIpBunkInfoEntity) {
                            if (checkEntity(publicIpBunkInfoEntity)) {
                                //管教
                                txt_zgj_name.setText(publicIpBunkInfoEntity.getData().getSurelyName());
                                //协管教
                                txt_xgj_name.setText(publicIpBunkInfoEntity.getData().getAssistName());

//                                List<PersonThing> personThings = getPersonList(listEntity.getData().get(0));
                                list.clear();
                                list.addAll(publicIpBunkInfoEntity.getData().getRow());
                                adapter.notifyDataSetChanged();
                            }else{
                                checkEntityCode(publicIpBunkInfoEntity);
                            }
                        }
                    });
        }
    }


}
