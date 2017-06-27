package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.EvaluationOfHeartAapter;
import com.example.jyxmyt.adapter.QuestionnaireAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.Psychometrics;
import com.example.jyxmyt.entity.PsychometricsAnswer;
import com.example.jyxmyt.entity.Questionnaire;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;
import com.example.jyxmyt.util.ToastUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
 * @类名 QuestionnaireActivity.java
 * @包名 com.example.jyxmyt.activity
 * @作者 李科
 * @时间 2014年6月12日 下午
 * @功能 (问卷调查)
 */
public class QuestionnaireActivity extends BaseActivity implements OnClickListener{
	private ImageView return_but; // 返回上一步
	private ListView list_xlcp;//listview ID
	private TextView text_room;//监室号
	private  TextView edt_prison_num; //人员编号
	private List<Questionnaire> list=null;
	private QuestionnaireAdapter adapter;
	private Finger finger = null;
	private Button btn_xlcp_submit;
	private PsychometricsAnswer psychometricsAnswer=null;
	List<PsychometricsAnswer.DataBean> dataList = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	public void initView() {
		setContentView(R.layout.questionnaire_survey);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		text_room=(TextView) findViewById(R.id.text_room);
		edt_prison_num=(TextView) findViewById(R.id.edt_prison_num);
		list_xlcp= (ListView) findViewById(R.id.list_xlcp);
		btn_xlcp_submit= (Button) findViewById(R.id.btn_xlcp_submit);
		btn_xlcp_submit.setOnClickListener(this);
		Intent intent = getIntent();
		Bundle bun = intent.getExtras();
		finger = (Finger) bun.getSerializable("finger");
		text_room.setText("监室号："+finger.getKssRoomNum());
		edt_prison_num.setText("人员编号："+finger.getKssPrisonerNum());
		if(psychometricsAnswer==null){
			psychometricsAnswer=new PsychometricsAnswer();
		}
		if (list == null) {
			list = new ArrayList<>();
		}
		adapter = new QuestionnaireAdapter(getApplication(), list);
		list_xlcp.setAdapter(adapter);
		requestQuestionnaire();
	}

	public void onClick(View v) {
		if(v == return_but){
			finish();
		} else if(v == btn_xlcp_submit){
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
				ToastUtil.showToast(QuestionnaireActivity.this,"第"+(pos+1)+"题未做");
				return;
			}
			requestSubmitQuestionnaireAnswer(psychometricsAnswer);
		}
	}
	/**
	 * 请求查询心理测试题
	 */
	private void requestQuestionnaire() {

		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getQuestionnaire()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Entity<List<Questionnaire>>>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
							doError(e);
						}

						@Override
						public void onNext(Entity<List<Questionnaire>> listEntity) {
							if (checkEntity(listEntity)) {
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

	/**
	 * 提交心理测评答案
	 */
	private void requestSubmitQuestionnaireAnswer(PsychometricsAnswer psychometricsAnswer) {
		if (checkNetwork()) {
			RetrofitClient.getCommonApi().getSubmitQuestionnaireAnswer(psychometricsAnswer)
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
								ToastUtil.showToast(QuestionnaireActivity.this, "提交成功");
								finish();
							} else {
								ToastUtil.showToast(QuestionnaireActivity.this, "提交失败");
							}
						}
					});
		}
	}
}

