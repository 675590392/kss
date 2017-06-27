package com.example.jyxmyt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.InterRulseAdapter;
import com.example.jyxmyt.adapter.NBListViewAdapter;
import com.example.jyxmyt.adapter.QLYWListViewAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.InterRules;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.http.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InterRulesActivity extends BaseActivity implements
        View.OnClickListener {
    private ImageView long_head; // 登陆者的头像
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步


    private RadioButton rules_chinese; // 中文
    private RadioButton rules_english; // 英文
    private RadioButton wy; // 维语
    private ListView rules_list;
    private InterRulseAdapter adapter;
    private List<InterRules> list;
    public static Boolean bool;//中英文
    public static Boolean flag;//判断维语

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_rules);
        initView();
    }
    @Override
    public void initView() {
//        long_head = (ImageView) findViewById(R.id.long_head);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);

        // 中英维文切换
        rules_chinese = (RadioButton) findViewById(R.id.rules_chinese);
        rules_chinese.setOnClickListener(this);
        rules_english = (RadioButton) findViewById(R.id.rules_english);
        rules_english.setOnClickListener(this);
        wy = (RadioButton) findViewById(R.id.wy);
        wy.setOnClickListener(this);
        list = new ArrayList<>();
        bool = true;
        flag=false;
        rules_list = (ListView) findViewById(R.id.rules_list);
        adapter = new InterRulseAdapter(InterRulesActivity.this, list);
        rules_list.setAdapter(adapter);

        requestInterRules();
    }
    @Override
    public void onClick(View v) {
        if (v == home_but) {
            finish();
        } else if (v == return_but) {
            finish();
        } else if (v == rules_chinese) {
            rules_chinese.setChecked(true);
            bool = true;
            flag=false;
            adapter.notifyDataSetChanged();
        } else if (v == rules_english) {
            rules_english.setChecked(true);
            bool = false;
            flag=false;
            adapter.notifyDataSetChanged();
        }
        else if (v == wy) {
            wy.setChecked(true);
            bool = false;
            flag=true;
            adapter.notifyDataSetChanged();
        }
    }
    /**
     * 请求监纪监规
     */
    private void requestInterRules() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getInterRules()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<InterRules>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<InterRules>> listEntity) {
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
}
