package com.example.jyxmyt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.EvaluationOfHeartAapter;
import com.example.jyxmyt.adapter.list_xlcp_Adapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.PsychologicalTest;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.ToastUtil;

import java.io.Serializable;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/27.
 * 心理测评试卷界面
 */

public class PsychologicalEvaluationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private ListView list_xlcp;  //试卷列表
    private list_xlcp_Adapter adapter;
    List<PsychologicalTest> list;
    private Finger finger = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    @Override
    public void initView() {
        Log.d("wh","PsychologicalEvaluationActivity");
        setContentView(R.layout.psychologicalevaluation_activity);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        list_xlcp = (ListView) findViewById(R.id.list_xlcp);

        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        finger = (Finger) bun.getSerializable("finger");

        //查询心理试卷
        queryPsychologyPaper();

        list_xlcp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("wh","list点击事件");
                String  paperNum=list.get(position).getPaperNum();
                Log.d("wh","paperNum"+paperNum);
                if(paperNum!=null){
                    Intent intent = new Intent(PsychologicalEvaluationActivity.this,
                            EvaluationOfHeartActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("paperNum",paperNum);
                    bundle.putSerializable("finger", (Serializable) finger);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }


            }
        });
    }

    /**
     * 查询心理试卷
     */
    private void queryPsychologyPaper() {
        Log.d("wh","queryPsychologyPaper");
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().queryPsychologyPaper()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<PsychologicalTest>>>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<PsychologicalTest>> listEntity) {
                            Log.d("wh","查询心理试卷");
                            list=listEntity.getData();
                            if (checkEntity(listEntity)) {
                                adapter = new list_xlcp_Adapter(getApplication(), listEntity.getData());
                                list_xlcp.setAdapter(adapter);
//                                for (int i = 0; i < listEntity.getData().size(); i++) {
//                                    Log.d("wh",listEntity.getData().get(i).getPaperName());
//
//                                }

                            } else {

                            }
                        }
                    });
        }
    }


    @Override
    public void onClick(View v) {
        if (v == home_but) {
            finish();
        } else if (v == return_but) {
            finish();
        }
    }
}
