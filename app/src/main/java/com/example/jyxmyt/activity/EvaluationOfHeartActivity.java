package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.EvaluationOfHeartAapter;
import com.example.jyxmyt.adapter.PublicScheduleAapter;
import com.example.jyxmyt.adapter.ServcieActionAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.MeasurementofHeart;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.Psychometrics;
import com.example.jyxmyt.entity.PsychometricsAnswer;
import com.example.jyxmyt.entity.PublicQueryUserDuty;
import com.example.jyxmyt.entity.ServcieAction;
import com.example.jyxmyt.entity.publicWeek;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;
import com.example.jyxmyt.util.ToastUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @类名 EvaluationOfHeartActivity.java
 * @包名 com.example.jyxmyt.activity
 * @作者 李科
 * @时间 2014年6月12日 下午
 * @功能 (心里评测)
 */
public class EvaluationOfHeartActivity extends BaseActivity implements OnClickListener {
    private ImageView return_but; // 返回上一步
    private ListView list_xlcp;//listview ID
    private TextView text_room;//监室号
    private TextView edt_prison_num; //人员编号
    private List<MeasurementofHeart> list = null;
    private EvaluationOfHeartAapter adapter;
    private Finger finger = null;
    private Button btn_xlcp_submit;
    private PsychometricsAnswer psychometricsAnswer = null;
    List<PsychometricsAnswer.DataBean> dataList = new ArrayList<>();
    private   int position;
    //试卷编号
    private  String paperNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void initView() {
        Log.d("wh","EvaluationOfHeartActivity");
        setContentView(R.layout.evaluation_of_heart);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        text_room = (TextView) findViewById(R.id.text_room);
        edt_prison_num = (TextView) findViewById(R.id.edt_prison_num);
        list_xlcp = (ListView) findViewById(R.id.list_xlcp);
        btn_xlcp_submit = (Button) findViewById(R.id.btn_xlcp_submit);
        btn_xlcp_submit.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        finger = (Finger) bun.getSerializable("finger");

        paperNum=bun.getString("paperNum");

        text_room.setText("监室号：" + finger.getKssRoomNum());
        edt_prison_num.setText("人员编号：" + finger.getKssPrisonerNum());
        if (psychometricsAnswer == null) {
            psychometricsAnswer = new PsychometricsAnswer();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        adapter = new EvaluationOfHeartAapter(getApplication(), list);

        list_xlcp.setAdapter(adapter);
//        requestPsychometrics();
        queryByPaperNumP(paperNum);
    }

    @Override
    public void onClick(View v) {
        if (v == return_but) {
            finish();
        } else if (v == btn_xlcp_submit) {
            psychometricsAnswer.setKssRoomNum(finger.getKssRoomNum());
            psychometricsAnswer.setKssPrisonerNum(finger.getKssPrisonerNum());
            psychometricsAnswer.setKssPrisonerName(finger.getKssPrisonerName());
            Map<Integer, String> hashMap = adapter.getHashMap();
            Iterator iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                int id = (int) iterator.next();
                String strAnswer = hashMap.get(id);
                PsychometricsAnswer.DataBean data = new PsychometricsAnswer.DataBean();
                data.setAnswer(id + "_" + strAnswer);
                dataList.add(data);
            }
            psychometricsAnswer.setData(dataList);

           int pos= adapter.getUnselectPosition();
            if(pos!=-1){
               ToastUtil.showToast(EvaluationOfHeartActivity.this,"第"+(pos+1)+"题未做");
            return;
            }
            requestSubmitPsychometricsAnswer(psychometricsAnswer);
        }
    }

//    /**
//     * 请求查询心理测试题
//     */
//    private void requestPsychometrics() {
//
//        if (checkNetwork()) {
//            RetrofitClient.getCommonApi().getPsychometrics()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<Entity<List<Psychometrics>>>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            doError(e);
//                        }
//
//                        @Override
//                        public void onNext(Entity<List<Psychometrics>> listEntity) {
//                            if (checkEntity(listEntity)) {
//                                list.clear();
//                                list.addAll(listEntity.getData());
//                                adapter.notifyDataSetChanged();
//                            } else {
//                                checkEntityCode(listEntity);
//                            }
//                        }
//                    });
//        }
//    }


    /**
     * 通过试卷编号查询心理测评
     */
    private void queryByPaperNumP(String paperNum) {
            Log.d("wh","j进入queryByPaperNumP");
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().queryByPaperNumP(paperNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<MeasurementofHeart>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<MeasurementofHeart>> listEntity) {
                            if (checkEntity(listEntity)) {
                                Log.d("wh","进入queryByPaperNumPonNext");
                                list.clear();
                                list.addAll(listEntity.getData());
                                adapter.notifyDataSetChanged();
                            } else {
                                checkEntityCode(listEntity);
                            }
                        }
                    });
        }
    }




    /**
     * 提交心理测评答案
     */
    private void requestSubmitPsychometricsAnswer(PsychometricsAnswer psychometricsAnswer) {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getSubmitPsychometricsAnswer(psychometricsAnswer)
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
                                ToastUtil.showToast(EvaluationOfHeartActivity.this, "提交成功");
                                finish();
                            } else {
                                ToastUtil.showToast(EvaluationOfHeartActivity.this, "提交失败");
                            }
                        }
                    });
        }
    }
}
