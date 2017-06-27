package com.example.jyxmyt.activity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.QLYWListViewAdapter;
import com.example.jyxmyt.adapter.WeekEeducatListAapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.entity.WeekEdu;
import com.example.jyxmyt.http.RetrofitClient;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeekEeducatActivity extends BaseActivity implements View.OnClickListener {
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private ListView mzjy_list; // 显示内务的列表
    private List<WeekEdu> list;
    private WeekEeducatListAapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_eeducat);
        initView();
    }

    @Override
    public void initView() {
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        mzjy_list = (ListView) findViewById(R.id.mzjy_list);
        list = new ArrayList<>();
        mzjy_list = (ListView) findViewById(R.id.mzjy_list);
        adapter = new WeekEeducatListAapter(WeekEeducatActivity.this, list);
        mzjy_list.setAdapter(adapter);

        requestWeekEdu();
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
     * 请求每周教育
     */
    private void requestWeekEdu() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getWeekEdu()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<WeekEdu>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<WeekEdu>> listEntity) {
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
