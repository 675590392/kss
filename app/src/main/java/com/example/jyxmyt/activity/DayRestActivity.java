package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.ZXLisstViewAdapter;
import com.example.jyxmyt.entity.DayRest;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.EveryDay;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;
import com.example.jyxmyt.util.CommonUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 一日作息
 *
 * @author 123
 */
public class DayRestActivity extends BaseActivity implements OnClickListener {

    private ImageView long_head; // 登陆者的头像
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步

    private ListView zx_list; // 显示一日作息列表
    private RadioButton chinese; // 中文
    private RadioButton english; // 英文

    private LinearLayout transition; // 点击进入新入所人员过渡作息
    private Button text; // 字体
    private ImageView arrow; // 箭头

    private LinearLayout fhlinear; //返回箭头
    private TextView fhtext;

    private Boolean boo; // 判断作息表 true为今日作息 false 为新人所过渡作息
    private String kssSeasonState = "0";
//    String[] set = {"06：30－07：00_起床、整理内务、洗漱、室内有秩序活动", "07：00－07：30_早餐、餐后卫生",
//            "07：30－08：00_舒缓时间(听音乐或室内有序活动)", "08：00－09：00_音乐读书时间(靠墙安静就坐收听广播、看书)",
//            "09：00－09：15_室内有序活动", "09：15－09：30_做广播操",
//            "09：30－10：15_素质培育(教育课1，学习位置安静就坐，姿势端正)", "10：15－11：15_放风或室内有序活动",
//            "11：15－12：00_午餐、餐后卫生、室内有序活动", "12：00－13：30_午间小憩",
//            "13：30－14：00_起床、整理内务", "14：00－14：45_素质培育(教育课2，学习位置安静就坐，姿势端正)",
//            "14：45－15：00_课间休息", "15：00－15：30_学习沙龙(讨论或民警点评等)",
//            "15：30－16：30_放风或室内有序活动(15分钟做广播操、45分钟放风或室内有序活动)",
//            "16：30－17：00_晚餐、餐后卫生", "17：00－19：00_洗澡、洗衣、整理内务、室内有序活动",
//            "19：00－21：30_观看电视、翻阅书报、洗衣、整理内务", "21：30－06：30_关电视，就寝"};

    String[] set = {};

    String seteng[] = {
            "06：30－07：00_Get up,Room organizing,Cleaning and washing,In-room regulated activities",
            "07：00－07：30_Breakfast,Dish washing and cleaning",
            "07：30－08：00_Soothing Time (Music/In-room regulated activities)",
            "08：00－09：00_Music and Reading Time(Sit against wall quietly,Listen to radio,Reading)",
            "09：00－09：15_In-room regulated activities",
            "09：15－09：30_Exercise",
            "09：30－10：15_Education(Class Ⅰ,Sit in studying position quietly and strictly)",
            "10：15－11：15_Outdoor refreshing or In-room regulated activities",
            "11：15－12：00_Lunch,Dish washing and cleaning,In-room regulated activities",
            "12：00－13：30_Afternoon nap",
            "13：30－14：00_Get up,Room organizing",
            "14：00－14：45_Education(Class Ⅱ,Sit in studying position quietly and strictly)",
            "14：45－15：00_Breaks",
            "15：00－15：30_Educational Saloon(Discussions or Police comments)",
            "15：30－16：30_Outdoor refreshing or In-room regulated activities(15 min exercise,60 min outdoor refreshing or In-room regulated activities)",
            "16：30－17：00_Dinner,Dish washing and cleaning",
            "17：00－19：00_Shower,Laundry,Room organizing,In-room regulated activities",
            "19：00－21：30_TV viewing,Reading,Laundry,Room organizing",
            "21：30－06：30_TV off,Sleep"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void initView() {
        setContentView(R.layout.day_rest_activity);
        boo = false;

        fhlinear = (LinearLayout) findViewById(R.id.fhlinear);
        fhlinear.setOnClickListener(this);
        fhtext = (TextView) findViewById(R.id.fhtext);
        fhtext.setOnClickListener(this);

        long_head = (ImageView) findViewById(R.id.long_head);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);

        zx_list = (ListView) findViewById(R.id.zx_list);

        chinese = (RadioButton) findViewById(R.id.chinese);
        chinese.setOnClickListener(this);
        english = (RadioButton) findViewById(R.id.english);
        english.setOnClickListener(this);

        text = (Button) findViewById(R.id.text);

        arrow = (ImageView) findViewById(R.id.arrow);
        transition = (LinearLayout) findViewById(R.id.transition);
        text.setOnClickListener(this);
        text.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    arrow.setBackgroundResource(R.drawable.zx_arrow_ok);
                    text.setTextColor(getResources()
                            .getColor(R.color.lan));
                    System.out.println("-----------ACTION_UP----");
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    arrow.setBackgroundResource(R.drawable.zx_arrow_on);
                    text.setTextColor(getResources().getColor(
                            R.color.lan));
                    System.out.println("-----------ACTION_DOWN----");
                }
                return false;
            }
        });


        zx_list.setAdapter(new ZXLisstViewAdapter(this, set));

        requestDayRest();
    }

    @Override
    public void onClick(View v) {
        if (v == home_but) {
            finish();
        } else if (v == return_but) {
            onBackPressed();
            boo = false;
        } else if (v == chinese) {// 中文
            kssSeasonState = "0";
            requestDayRest();
            if (set != null)
                zx_list.setAdapter(new ZXLisstViewAdapter(this, set));
        } else if (v == english) {// 英文
            kssSeasonState = "1";
            requestDayRest();
            if (set != null)
                zx_list.setAdapter(new ZXLisstViewAdapter(this, set));
        } else if (v == text) {
            if (set != null) {
                transition.setVisibility(View.GONE);
                fhlinear.setVisibility(View.VISIBLE);
                zx_list.setAdapter(new ZXLisstViewAdapter(this, set));
                chinese.setChecked(true);
                boo = true;
            }
        } else if (v == fhtext) {
            onBackPressed();
            boo = false;
        }
    }

    @Override
    public void onBackPressed() {
        if (boo && set != null) {
            boo = false;
            transition.setVisibility(View.VISIBLE);
            fhlinear.setVisibility(View.GONE);
            zx_list.setAdapter(new ZXLisstViewAdapter(this, set));
            chinese.setChecked(true);
        } else {
            finish();
        }
    }

    /**
     * 请求一日作息
     */
    private void requestDayRest() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getDayRest(kssSeasonState)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<EveryDay>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<EveryDay>> listEntity) {
                            if (checkEntity(listEntity)) {
                                //06：30－07：00_起床、整理内务、洗漱、室内有秩序活动", "07：00－07：30_早餐、餐后卫生
                                set = new String[listEntity.getData().size()];
                                for (int i = 0; i < listEntity.getData().size(); i++) {
                                    EveryDay dayRest = listEntity.getData().get(i);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(dayRest.getKssStarttime());
                                    sb.append("－");
                                    sb.append(dayRest.getKssEndtime());
                                    sb.append("_");
                                    sb.append(dayRest.getKssContentFisrt());
                                    sb.append("_");
                                    sb.append(dayRest.getKssContentSecond());
                                    set[i] = sb.toString();
                                }

                                zx_list.setAdapter(new ZXLisstViewAdapter(DayRestActivity.this, set));
                            } else {
                                checkEntityCode(listEntity);
                            }
                        }
                    });
        }
    }
}
