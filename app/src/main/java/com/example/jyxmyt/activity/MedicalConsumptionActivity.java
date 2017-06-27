package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.MedicalListAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.Medical;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.PeopleNumOrder;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.ToastUtil;
import com.test.FingerPrintUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @类名 MedicalConsumptionActivity
 * @包名 com.example.jyxmyt.activity
 * @作者 ChunTian
 * @时间 2014年4月15日 下午4:15:15
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (就诊查询)
 */

public class MedicalConsumptionActivity extends BaseActivity implements OnClickListener {

    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    /*登录总布局*/
    private LinearLayout jzcx_dl;
    private TextView jzcx_dl_btv;

    private ListView jzlist;
    private MedicalListAdapter medicalListAdapter;
    private ArrayList<MedicalInfo.RowsBean> medicals;
    private String kssPrisonerNum = null;
    private Finger fingers=null;
    private TextView text_name;
    private TextView text_bh;
    private Button btn_medical_submit;//提交
    private Button  btn_all_select;//全选
    //上一页
    private Button btn_up_page;
    //下一页
    private Button btn_next_page;
    private	int page=1;
    //是否点击
    boolean isClick = true;
    private FingerPrintUtil fingerPrintUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicalconsumption_activity);
        initView();
    }

    @Override
    public void initView() {
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        /*登录总布局*/
        jzlist = (ListView) findViewById(R.id.jzlist);
        text_name = (TextView) findViewById(R.id.text_name);
        text_bh = (TextView) findViewById(R.id.text_bh);
        btn_medical_submit = (Button) findViewById(R.id.btn_medical_submit);
        btn_medical_submit.setOnClickListener(this);
        btn_all_select=(Button) findViewById(R.id.btn_all_select);
        btn_all_select.setOnClickListener(this);
        btn_up_page= (Button) findViewById(R.id.btn_up_page);
        btn_up_page.setOnClickListener(this);
        btn_next_page= (Button) findViewById(R.id.btn_next_page);
        btn_next_page.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        fingers = (Finger) bun.getSerializable("finger");
		kssPrisonerNum=fingers.getKssPrisonerNum();
//        kssPrisonerNum = "31000011120170008";

        text_name.setText("姓名:" + fingers.getKssPrisonerName());
        text_bh.setText("编号:" + fingers.getKssPrisonerNum());
        fingerPrintUtil = new FingerPrintUtil(this);
        fingerPrintUtil.init();
        medicals = new ArrayList<>();
        medicalListAdapter = new MedicalListAdapter(getApplication(), medicals);
        jzlist.setAdapter(medicalListAdapter);
        requestMedicalInfo();
    }

    @Override
    public void onClick(View v) {
        if (v == return_but) {
            finish();
        } else if (v == home_but) {
            finish();
        } else
        if (v == jzcx_dl_btv) { // 登录按钮
            jzcx_dl.setVisibility(View.GONE);
        }	else if(v == btn_up_page){
            if (page == 1) {
                btn_up_page.setEnabled(false);
                btn_next_page.setEnabled(true);
                ToastUtil.showToast(MedicalConsumptionActivity.this, "此页为第一页");
            } else {
                btn_next_page.setEnabled(true);
                page--;
                requestMedicalInfo();
            }
        }	else if(v == btn_next_page){
            if (0 != medicals.size()) {
                if (20 > medicals.size()) {
                    btn_next_page.setEnabled(false);
                    btn_up_page.setEnabled(true);
                    ToastUtil.showToast(MedicalConsumptionActivity.this, "此页为最后一页");
                } else {
                    btn_up_page.setEnabled(true);
                    page++;
                    requestMedicalInfo();
                }
            } else {
                // activity_home_item.removeFooterView(views);
                btn_next_page.setEnabled(false);
                btn_up_page.setEnabled(true);
                ToastUtil.showToast(MedicalConsumptionActivity.this, "此页为最后一页");
            }


        }else if (v == btn_all_select) { // 全选按钮
            // 遍历list的长度，将MyAdapter中的map值全部设为true
            medicalListAdapter.setSelectAll(true);
            medicalListAdapter.notifyDataSetChanged();
        } else if (v == btn_medical_submit) { // 提交按鈕
            matchFingerOwn(new MainActivity.MatchFingerLintener() {
                @Override
                public void OnSuccess(Finger finger) {
                    if(finger.getKssPrisonerNum().equals(fingers.getKssPrisonerNum())){
                        Map<Integer, MedicalInfo.RowsBean> hashMap = medicalListAdapter.getCheckItems();
                        requestMedicalById(hashMap);
                    }else{
                        ToastUtil.showToast(MedicalConsumptionActivity.this, "当前指纹不一致");
                    }

                }
            });

        }
    }
    /**
     * 比对指纹 指纹时间单独
     *
     * @param matchFingerLintener
     */
    private void matchFingerOwn(final MainActivity.MatchFingerLintener matchFingerLintener) {
        if (isClick) {
            isClick = false;
            new AlertDialog.Builder(MedicalConsumptionActivity.this)
                    .setMessage("请验证指纹退出")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            byte[] data = fingerPrintUtil.extract();
                            Finger finger = CommonUtil.machFinger(data, MedicalConsumptionActivity.this, fingerPrintUtil);
                            if (finger != null) {//比对成功
                                matchFingerLintener.OnSuccess(finger);
                            } else {
                                ToastUtil.showToast(MedicalConsumptionActivity.this, "指紋比对失败");
                            }
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(MedicalConsumptionActivity.this)
                    .setMessage("请不要连续点击")
                    .setPositiveButton("确定", null)
                    .show();
        }


        CountDownTimer timer = new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                isClick = true;
            }
        };
    }


    /**
     * 请求医药信息
     */
    private void requestMedicalInfo() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getMedicalInfo(kssPrisonerNum,page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<MedicalInfo>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<MedicalInfo> medicalInfoEntity) {
                            if (checkEntity(medicalInfoEntity)) {
                            medicals.clear();
                            medicals.addAll(medicalInfoEntity.getData().getRows());
                            jzlist.setAdapter(medicalListAdapter);
                            }else{
                                checkEntityCode(medicalInfoEntity);
                            }
                        }
                    });
        }
    }

    /**
     * 通过就医信息ID,批量修改指定数据状态、时间
     */
    private void requestMedicalById(Map<Integer, MedicalInfo.RowsBean> hashMap) {
        if (checkNetwork()) {
            String ids = mapToString(hashMap);
            RetrofitClient.getCommonApi().getMedicalById(ids)
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
                                ToastUtil.showToast(MedicalConsumptionActivity.this, "数据提交成功");
                                finish();
                            } else {
                                ToastUtil.showToast(MedicalConsumptionActivity.this, "数据提交失败");
                            }
                        }
                    });
        }
    }

    private String mapToString(Map<Integer, MedicalInfo.RowsBean> map) {
        Iterator<Integer> iterator = map.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            int id = iterator.next();
            sb.append(id);
            sb.append(",");
        }

        return sb.toString();
    }
}
