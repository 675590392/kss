package com.example.jyxmyt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.StringCodec;
import com.example.jyxmyt.R;
import com.example.jyxmyt.app.JYYTApplication;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.FingerReq;
import com.example.jyxmyt.entity.db.FingerBean;
import com.example.jyxmyt.entity.db.FingerBeanDao;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.ToastUtil;
import com.test.FingerPrintUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.HTTP;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanghao on 2017/3/4.
 */

public class FingerPrintActivity extends BaseActivity {

    private RecyclerView rv;
    private FingerPrintUtil fingerPrintUtil;
    private CommonAdapter commonAdapter;
    private List<Finger> fingers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        initView();


        fingerPrintUtil = new FingerPrintUtil(this);
        fingerPrintUtil.init();
    }

    @Override
    public void initView() {
        findViewById(R.id.home_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FingerPrintActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.return_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        commonAdapter = new CommonAdapter<Finger>(this, R.layout.layout_finger_item, fingers) {
            @Override
            protected void convert(ViewHolder holder, final Finger finger, final int position) {
                holder.setText(R.id.tv_people_num, finger.getKssPrisonerNum());
                holder.setText(R.id.tv_people_name, finger.getKssPrisonerName());

                final Button btnClick = holder.getView(R.id.btn_click);
                if (finger.getKssState().equals("0")) {
                    btnClick.setText("指纹一录入");
                } else if (finger.getKssState().equals("1")) {
                    btnClick.setText("指纹二录入");
                } else if (finger.getKssState().equals("2")) {
//                    btnClick.setEnabled(false);
                }

                btnClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (finger.getKssState().equals("0")) {
                            byte[] data = fingerPrintUtil.extract();
                            if (data != null) {

                                Finger fingerBean = CommonUtil.machFinger(data, mContext, fingerPrintUtil);
                                if (fingerBean == null) {//指纹库中不存在
                                    fingers.get(position).setKssState("1");
                                    FingerReq fingerReq = new FingerReq();
                                    fingerReq.setKssPrisonerNum(finger.getKssPrisonerNum());
                                    fingerReq.setKssFeatureNumSecond("");
                                    fingerReq.setKssFeatureNumFirst(CommonUtil.arrayToString(data));

                                    addFingers(fingerReq);
                                } else
                                    ToastUtil.showToast(mContext, "该指纹已录入");


                            }
                        } else if (finger.getKssState().equals("1")) {
                            byte[] data = fingerPrintUtil.extract();

                            if (data != null) {

                                Finger fingerBean = CommonUtil.machFinger(data, mContext, fingerPrintUtil);
                                if (fingerBean == null) {//指纹库中不存在

                                    fingers.remove(finger);
                                    FingerReq fingerReq = new FingerReq();
                                    fingerReq.setKssPrisonerNum(finger.getKssPrisonerNum());
                                    fingerReq.setKssFeatureNumFirst("");
                                    fingerReq.setKssFeatureNumSecond(CommonUtil.arrayToString(data));

                                    addFingers(fingerReq);
                                } else
                                    ToastUtil.showToast(mContext, "该指纹已录入");
                            }

                        } else if (finger.getKssState().equals("2")) {

                            fingers.remove(finger);
                        }

                        notifyDataSetChanged();
                    }
                });


            }


        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(commonAdapter);


        getFingers();
    }

    @Override
    protected void onDestroy() {
//        fingerPrintUtil.onDestroy();
        super.onDestroy();
        System.gc();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return fingerPrintUtil.onKeyDown(keyCode, event, super.onKeyDown(keyCode, event));
    }

    /**
     *   通过监室号查询人员指纹信息列表
     */
    private void getFingers() {
        String kssRoomNum = CommonUtil.getkssRoomNum(this);
        if (checkNetwork()) {
        RetrofitClient.getCommonApi().getPeopleFinger(kssRoomNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Entity<List<Finger>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        doError(e);
                    }

                    @Override
                    public void onNext(Entity<List<Finger>> listEntity) {
                        if (checkEntity(listEntity)) {
                            List<Finger> list = new ArrayList<>();
                            list.addAll(listEntity.getData());

                            for (Finger finger : listEntity.getData()) {
                                if (finger.getKssState().equals("2")) {
                                    list.remove(finger);
                                }
                            }
                            fingers.clear();
                            fingers.addAll(list);
                            if(fingers.size()==0){
                                ToastUtil.showToast(FingerPrintActivity.this, "当前房间指纹已录入完成");
                            }
                            commonAdapter.notifyDataSetChanged();
                        }else{
                            checkEntityCode(listEntity);
                        }
                    }
                });}

    }

    private void addFingers(FingerReq fingerReq) {
//        String kssRoomNum = CommonUtil.getkssRoomNum(this);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(fingerReq));


        FingerBeanDao fingerBeanDao = JYYTApplication.getDaoInstant().getFingerBeanDao();
        if (fingerReq.getKssFeatureNumFirst().equals("")) {//更新，取第二个值

            List<FingerBean> list = fingerBeanDao.queryBuilder()
                    .where(FingerBeanDao.Properties.KssPrisonerNum.eq(fingerReq.getKssPrisonerNum()))
                    .list();
            if (list.size() > 0) {
                FingerBean fingerBean = list.get(0);
                fingerBean.setKssFeatureNumSecond(fingerReq.getKssFeatureNumSecond());

                fingerBeanDao.update(fingerBean);
            }

        } else {//添加
            FingerBean fingerBean = new FingerBean();
            fingerBean.setKssFeatureNumFirst(fingerReq.getKssFeatureNumFirst());
            fingerBean.setKssFeatureNumSecond(fingerReq.getKssFeatureNumSecond());
            fingerBean.setKssPrisonerNum(fingerReq.getKssPrisonerNum());
            fingerBeanDao.insert(fingerBean);
        }
        if (checkNetwork()) {
        RetrofitClient.getCommonApi().getAddFinger(fingerReq)
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

                        }else{
                            checkEntityCode(entity);
                        }
                    }
                });}

    }

}
