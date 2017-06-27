package com.example.jyxmyt.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.QueryMsgPage;
import com.example.jyxmyt.entity.QueryMsgPageJson;
import com.example.jyxmyt.entity.SystemTime;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.SharedPreferencesUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/28.
 * 信息通知界面
 */

public class NotificationsActivity  extends  BaseActivity implements View.OnClickListener {

    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private XRecyclerView notyfication_list;  //试卷列表
    private TextView  text_titlt;
    CommonAdapter adapter;
    private List<QueryMsgPageJson.RowsBean> notyficationList;
    QueryMsgPage queryMsgPage;
    int pageIndex=1;
    String systime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    @Override
    public void initView() {
        setContentView(R.layout.notyfication_activity);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        String kssRoom=CommonUtil.getkssRoomNum(this);
        if(queryMsgPage==null){
            queryMsgPage=new QueryMsgPage();
        }
        queryMsgPage.setPage(pageIndex);
        queryMsgPage.setRoomNum(kssRoom);
        notyfication_list = (XRecyclerView) findViewById(R.id.rv_add_notyfication_list);
        notyficationList = new ArrayList<QueryMsgPageJson.RowsBean>();
        notyfication_list.setLayoutManager(new LinearLayoutManager(NotificationsActivity.this));
        notyfication_list.setItemAnimator(new DefaultItemAnimator());
        notyfication_list.addItemDecoration(new DividerItemDecoration(NotificationsActivity.this, DividerItemDecoration.VERTICAL));
        notyfication_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                pageIndex = 1;
                queryMsgPage();
                notyfication_list.refreshComplete();

            }
            //加载更多
            @Override
            public void onLoadMore() {
                pageIndex++;
                queryMsgPage();
                notyfication_list.loadMoreComplete();
            }
        });
        adapter = new CommonAdapter<QueryMsgPageJson.RowsBean>(NotificationsActivity.this, R.layout.notyfi_list_item, notyficationList) {
            @Override
            protected void convert(ViewHolder holder, QueryMsgPageJson.RowsBean queryMsgPageJson, int position) {
                  /* 稀疏数组：用于缓存已显示过的View */
                SparseArray<View> viewArray = null;

                //留言时间
                String systemtime=stampToDate( String .valueOf(queryMsgPageJson.getCreateTime().getTime()));
                holder.setText(R.id.text_notice_id, systemtime);
                holder.setText(R.id.text_notice_content,queryMsgPageJson.getContent());
//                //获取系统时间
//               String time= SharedPreferencesUtil.getSetting("kcc",NotificationsActivity.this,"time");
//                Log.d("wh","systime系统时间="+time);
//                Log.d("wh","systime留言时间="+systemtime);
//                Log.d("wh","留言类容="+queryMsgPageJson.getContent());
//               if (time!=null) {
//                   if (isSameDay(time, systemtime)) {
//
//                       holder.setTextColor(R.id.text_notice_id, Color.BLACK);
//                       holder.setTextColor(R.id.text_notice_content, Color.BLACK);
////                       notifyDataSetChanged();
//                   }
//               }

            }
        };
        notyfication_list.setAdapter(adapter);
        queryMsgPage();
    }

    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
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
     *查询留言分页信息
     */
    private void queryMsgPage() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().queryMsgPage(queryMsgPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<QueryMsgPageJson>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<QueryMsgPageJson> queryMsgPageJsonEntity) {
                            Log.d("wh","查询留言分页信息");
                            if (checkEntity(queryMsgPageJsonEntity)) {
                                notyficationList.clear();
                                notyficationList.addAll(queryMsgPageJsonEntity.getData().getRows());
                                adapter.notifyDataSetChanged();
                            } else {
                                checkEntityCode(queryMsgPageJsonEntity);
                            }
                        }
                    });
        }
    }
    //获取系统时间
    private void getSystemTime(final String time,final ViewHolder holder) {

        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getSystemTime()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<SystemTime>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<SystemTime> systemTimeEntity) {
                            if (checkEntity(systemTimeEntity)) {
                                //设置当前服务器时间
//                                text_time.setText(systemTimeEntity.getData().getTime());
                                systime=systemTimeEntity.getData().getTime();
                                Log.d("wh","留言时间="+time);
                                Log.d("wh=","服务系统时间"+systime);
                                if (isSameDay( time,systime)){
                                    holder.setTextColor(R.id.text_notice_id, Color.RED);
                                    holder.setTextColor(R.id.text_notice_content,Color.RED);
                                }
                            } else {
                                checkEntityCode(systemTimeEntity);
                            }
                        }
                    });
        }
    }


   //获取当前时间
    public String getTime()
    {
        long currentTime = System.currentTimeMillis() ;
        currentTime +=8*60*60*1000;
        Date date=new Date(currentTime);
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        return time;
    }

    //判断是否为当天
    public boolean isSameDay(String day1, String day2) {
        if ( day1.substring(0,10).endsWith(day2.substring(0,10))) {
            return true;
        } else {
            return false;
        }
    }
}
