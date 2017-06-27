package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.QLYWListViewAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.http.CommonApi;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 权利义务
 *
 * @author 123
 */
public class RightsObligationsActivity extends BaseActivity implements
        OnClickListener {

    private ImageView long_head; // 登陆者的头像
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步

    private TextView qlyw_title, qlyw_titlee;
    private RadioButton chinese; // 中文
    private RadioButton english; // 英文
    private RadioButton wy; // 维语
    private ListView qlyw_list;
    private QLYWListViewAdapter adapter;
    private List<Right> list;
    public static Boolean bool;//中英文

    public static Boolean flag;//判断维语

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView() {
        setContentView(R.layout.rights_obligations_activity);
        long_head = (ImageView) findViewById(R.id.long_head);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);

        qlyw_title = (TextView) findViewById(R.id.qlyw_title);
        qlyw_titlee = (TextView) findViewById(R.id.qlyw_titlee);

        // 中英维文切换
        chinese = (RadioButton) findViewById(R.id.chinese);
        chinese.setOnClickListener(this);
        english = (RadioButton) findViewById(R.id.english);
        english.setOnClickListener(this);
        wy = (RadioButton) findViewById(R.id.wy);
        wy.setOnClickListener(this);

        list = new ArrayList<>();
//        list.add("一、在押人员享有的救济渠道");
//        list.add("二、在押人员应当履行以下义务");
//        list.add("三、在押人员依法享有以下权利");

        bool = true;
        flag=false;

        qlyw_list = (ListView) findViewById(R.id.qlyw_list);
        adapter = new QLYWListViewAdapter(RightsObligationsActivity.this, list);
        qlyw_list.setAdapter(adapter);

        requestRight();
    }

    @Override
    public void onClick(View v) {
        if (v == home_but) {
            finish();
        } else if (v == return_but) {
            finish();
        } else if (v == chinese) {
            chinese.setChecked(true);
            qlyw_title.setText("上海市看守所在押人员权利和义务");
            qlyw_titlee.setText("(上海市公安局监管总队)");
//            list.clear();
//            list.add("一、在押人员享有的救济渠道");
//            list.add("二、在押人员应当履行以下义务");
//            list.add("三、在押人员依法享有以下权利");
            bool = true;
            flag=false;
            adapter.notifyDataSetChanged();
        } else if (v == english) {
            english.setChecked(true);
            qlyw_title.setText("Rights and Obligations of Detainees in ShangHai Detention House");
            qlyw_titlee.setText("(Penitentiary Administration Department of Shanghai Municipal Public Security Bureau)");
            bool = false;
            flag=false;
            adapter.notifyDataSetChanged();
        }
        else if (v == wy) {
            wy.setChecked(true);
            qlyw_title.setText(" شاڭخەي شەھىرىنازارەتخانا، كۆزەتخاناقاماقتىكى جىنايەتچى ھوقۇق ۋە مەجبۇرىيەت");
            qlyw_titlee.setText("(شاڭخەي شەھىرىجامائەت خەۋپسىزلىكىازارەت قىلماق ۋەدىۋىزىئون)");
            bool = false;
            flag=true;
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 请求权利义务
     */
    private void requestRight() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getRight()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<Right>>>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }
                        @Override
                        public void onNext(Entity<List<Right>> listEntity) {
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
