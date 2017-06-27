package com.example.jyxmyt.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jyxmyt.R;
import com.example.jyxmyt.app.JYYTApplication;
import com.example.jyxmyt.common.UserInformation;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.MeasurementofHeart;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.MenuPermissions;
import com.example.jyxmyt.entity.QueryMsgInfo;
import com.example.jyxmyt.entity.RollCall;
import com.example.jyxmyt.entity.RoomInfo;
import com.example.jyxmyt.entity.ShopReq;
import com.example.jyxmyt.entity.Shopping;
import com.example.jyxmyt.entity.SynchFinger;
import com.example.jyxmyt.entity.SystemTime;
import com.example.jyxmyt.entity.db.FingerBean;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.SharedPreferencesUtil;
import com.example.jyxmyt.util.ToastUtil;
import com.example.jyxmyt.view.MarqueeText;
import com.example.jyxmyt.view.SaituDialog;
import com.test.FingerPrintUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 主界面
 *
 * @author 123
 */
public class MainActivity extends BaseMainActivity implements
        OnClickListener {
    private ImageView main_zwgk; // 政务公开
    private ImageView main_ywbl; // 业务办理
    private ImageView main_dcpg; // 信息查询
    private ImageView main_nwap; // 内务安排
    private ImageView main_xxcx; // 调查评估
    private ImageView main_sysm; // 使用说明

    private RelativeLayout relative;
    private MarqueeText tice;
    // 设置
    private ImageView sz_head;

    private PopupWindow popWindow;
    private PopupWindow popWin;

    private LinearLayout main_lin;
    private LinearLayout main_zhu;
    private UserInformation information;
    private LinearLayout main_tab_lin, main_tab_lin2, sf;
    private LinearLayout sz_lin;
    private StatusbarOperation so;

    private FingerPrintUtil fingerPrintUtil;
    private RollCall rollCall;//点名实体
    private TextView text_time;
    private TextView text_messege;//通知消息展示
//    private TextView text_message;
     TextView version;
    private String permissions;
    //提示声音类
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐去电池等图标和一切修饰部分（状态栏部分）
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐去标题栏（程序的名字）
//        DBHelper.getNewInstanceDBHelper(MainActivity.this);
        initView();
        fingerPrintUtil = new FingerPrintUtil(this);
        fingerPrintUtil.init();

    }



    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //时间轮询
            getSystemTime();
        }
    };
    Handler handlers = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //通知消息轮询
            queryMsgInfo();
        }
    };

    public void initView() {
        setContentView(R.layout.main_activity);
        if (rollCall == null) {
            rollCall = new RollCall();
        }
        version=(TextView) findViewById(R.id.view_version);
        version.setText("当前版本号："+application.getVersionName(this));
        relative = (RelativeLayout) findViewById(R.id.relative);
        relative.setOnClickListener(this);
        main_tab_lin = (LinearLayout) findViewById(R.id.main_tab_lin);
        main_tab_lin2 = (LinearLayout) findViewById(R.id.main_tab_lin2);
        main_zhu = (LinearLayout) findViewById(R.id.main_zhu);
        sf = (LinearLayout) findViewById(R.id.sf);
        sf.setOnClickListener(this);
//        sz_lin = (LinearLayout) findViewById(R.id.sz_lin);
//        sz_lin.setOnClickListener(this);
        main_zhu.setOnClickListener(this);
        main_zwgk = (ImageView) findViewById(R.id.main_zwgk);
        main_zwgk.setOnClickListener(this);
        main_ywbl = (ImageView) findViewById(R.id.main_ywbl);
        main_ywbl.setOnClickListener(this);
        main_dcpg = (ImageView) findViewById(R.id.main_dcpg);
        main_dcpg.setOnClickListener(this);
        main_nwap = (ImageView) findViewById(R.id.main_nwap);
        main_nwap.setOnClickListener(this);
        main_xxcx = (ImageView) findViewById(R.id.main_xxcx);
        main_xxcx.setOnClickListener(this);
        main_sysm = (ImageView) findViewById(R.id.main_sysm);
        main_sysm.setOnClickListener(this);
        main_lin = (LinearLayout) findViewById(R.id.main_lin);
        main_lin.setOnClickListener(this);
        sz_head = (ImageView) findViewById(R.id.sz_head);
        sz_head.setOnClickListener(this);
        text_time = (TextView) findViewById(R.id.text_time);
        text_messege= (TextView) findViewById(R.id.text_messege);

        text_messege.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,
                        NotificationsActivity.class);
                startActivity(intent);
                text_messege.setText("");
            }
        });
        so = new StatusbarOperation(false);
        so.run();

        //设置当前服务器时间
        handler.postDelayed(new TimeTaskThread(), 1000);
        //设置当前服务器时间
        handlers.postDelayed(new messegeTaskThread(), 3000);
        /*
         * login_name = (TextView) findViewById(R.id.login_name); login_fh =
		 * (TextView) findViewById(R.id.login_fh); login_sex = (TextView)
		 * findViewById(R.id.login_sex); login_jsh = (TextView)
		 * findViewById(R.id.login_jsh); login_time = (TextView)
		 * findViewById(R.id.login_time); login_mis = (TextView)
		 * findViewById(R.id.login_mis);
		 */
        instantiateDate();
//        controlInfo();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                areaTextAnimation(main_tab_lin2);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        main_tab_lin2.setVisibility(View.VISIBLE);
                        areaAnimation(main_tab_lin);
                    }
                }, 300);
            }
        }, 300);


        initDevice();//获取监区号，监室号
    }

    private class TimeTaskThread implements Runnable {
        private int i = 1;

        @Override
        public void run() {
            Message message = Message.obtain();
            message.obj = "leelit";
            message.what = i++;
            handler.sendMessage(message);
            handler.postDelayed(this, 1000);      //实现循环执行任务
//            handlers.sendMessage(message);
//            handlers.postDelayed(this, 3000);      //实现循环执行任务
//            if (i > i++){
//                handler.removeCallbacks(this);
//        }
        }
    }
    private class messegeTaskThread implements Runnable {
        private int i = 1;

        @Override
        public void run() {
            Message message = Message.obtain();
            message.obj = "leelit";
            message.what = i++;
            handlers.sendMessage(message);
            handlers.postDelayed(this, 3000);      //实现循环执行任务
//            if (i > i++){
//                handler.removeCallbacks(this);
//        }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //设置当前服务器时间
//        getSystemTime();
    }

    /**
     * @方法名 MainActivity.java
     * @时间 2014年4月15日 下午2:22:44
     * @设定文件
     * @返回类型 MainActivity.java
     * @功能 (实例化用户数据)
     */
    public void instantiateDate() {
        information = new UserInformation(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        Log.i("debug", "click");
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (v == sz_head) { // 设置按钮
//            userLogin();

        } else if (v == main_lin) {
            if (popWindow != null && popWindow.isShowing()) {
                popWindow.dismiss();
            }
        } else if (v == main_zwgk) { // 政务公开
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
            }
            main_zwgk.setEnabled(false);
            main_ywbl.setEnabled(true);
            main_dcpg.setEnabled(true);
            main_nwap.setEnabled(true);
            main_xxcx.setEnabled(true);
            main_sysm.setEnabled(true);
            showPopWin(MainActivity.this, v, 1);
        } else if (v == main_ywbl) { // 内务安排
//			showZWBLPopWin(v);
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
            }
            main_ywbl.setEnabled(false);
            main_zwgk.setEnabled(true);
            main_dcpg.setEnabled(true);
            main_nwap.setEnabled(true);
            main_xxcx.setEnabled(true);
            main_sysm.setEnabled(true);
            showPopWin(MainActivity.this, v, 2);
        } else if (v == main_dcpg) { // 业务办理
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
            }
            main_dcpg.setEnabled(false);
            main_zwgk.setEnabled(true);
            main_ywbl.setEnabled(true);
            main_nwap.setEnabled(true);
            main_xxcx.setEnabled(true);
            main_sysm.setEnabled(true);
            showPopWin(MainActivity.this, v, 3);
        } else if (v == main_nwap) { // 信息查询
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
            }
            main_nwap.setEnabled(false);
            main_zwgk.setEnabled(true);
            main_ywbl.setEnabled(true);
            main_dcpg.setEnabled(true);
            main_xxcx.setEnabled(true);
            main_sysm.setEnabled(true);
            showPopWin(MainActivity.this, v, 4);
        } else if (v == main_xxcx) { // 调查评估
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
            }
            main_xxcx.setEnabled(false);
            main_zwgk.setEnabled(true);
            main_ywbl.setEnabled(true);
            main_nwap.setEnabled(true);
//            main_dcpg.setEnabled(true);
            main_sysm.setEnabled(true);
            showPopWin(MainActivity.this, v, 5);
        } else if (v == main_sysm) { // 使用说明
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
            }
            main_sysm.setEnabled(false);
            main_zwgk.setEnabled(true);
            main_ywbl.setEnabled(true);
            main_nwap.setEnabled(true);
            main_xxcx.setEnabled(true);
            main_dcpg.setEnabled(true);
            showPopWin(MainActivity.this, v, 6);
        } else if (v == sf) {
            Log.d("TAG", "点击了该处");
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
                main_xxcx.setEnabled(true);
                main_zwgk.setEnabled(true);
                main_ywbl.setEnabled(true);
                main_nwap.setEnabled(true);
                main_dcpg.setEnabled(true);
                main_sysm.setEnabled(true);
            }
        } else if (v == tice) {
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
                main_xxcx.setEnabled(true);
                main_zwgk.setEnabled(true);
                main_ywbl.setEnabled(true);
                main_nwap.setEnabled(true);
                main_dcpg.setEnabled(true);
                main_sysm.setEnabled(true);
            }
        } else if (v == relative) {
            if (popWin != null && popWin.isShowing()) {
                popWin.dismiss();
                popWin = null;
                main_xxcx.setEnabled(true);
                main_zwgk.setEnabled(true);
                main_ywbl.setEnabled(true);
                main_nwap.setEnabled(true);
                main_dcpg.setEnabled(true);
                main_sysm.setEnabled(true);
            }
        } else if (v == sz_lin) {
            userLogin();
        } else if (main_zhu == v) {
            Log.d("TAG", "点击了图片");
        }
    }

    /**
     * @方法名 MainActivity.java
     * @时间 2014年4月11日 下午2:55:34
     * @设定文件 @param context
     * @设定文件 @param parent
     * @返回类型 MainActivity.java
     * @功能 (设置界面)
     */
    private void showZWBLPopWin(View parent) {
        LayoutInflater inflater = (LayoutInflater) MainActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View vPopWindow = inflater.inflate(R.layout.mypopwindow, null,
                false);
        popWindow = new PopupWindow(vPopWindow, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, true);
        popWindow.setFocusable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置动画效果
        popWindow.setAnimationStyle(R.style.AnimationFade);
        // 点击其他地方消失
        parent.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (popWindow != null && popWindow.isShowing()) {
                    popWindow.dismiss();
                    popWindow = null;
                }
                return false;
            }
        });

        // 主管姓名
        final EditText zg_xm = (EditText) vPopWindow.findViewById(R.id.zg_xm);
        // 修改
        Button xg = (Button) vPopWindow.findViewById(R.id.xg);
        popWindow.showAtLocation(sz_head, Gravity.CENTER, 0, 0);

    }

    /**
     * @方法名 MainActivity.java
     * @时间 2014年4月11日 下午2:55:34
     * @设定文件 @param context
     * @设定文件 @param parent
     * @返回类型 MainActivity.java
     * @功能 (设置界面)
     */
    private void showPopWindow(Context context, View parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View vPopWindow = inflater.inflate(R.layout.mypopwindow, null,
                false);
        popWindow = new PopupWindow(vPopWindow, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, true);
        popWindow.setFocusable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置动画效果
        popWindow.setAnimationStyle(R.style.AnimationFade);
        // 点击其他地方消失
        parent.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (popWindow != null && popWindow.isShowing()) {
                    popWindow.dismiss();
                    popWindow = null;
                }
                return false;
            }
        });

        // 主管姓名
        final EditText zg_xm = (EditText) vPopWindow.findViewById(R.id.zg_xm);
        // 主管警号
        final EditText zg_jh = (EditText) vPopWindow.findViewById(R.id.zg_jh);
        // 协管姓名
        final EditText xg_xm = (EditText) vPopWindow.findViewById(R.id.xg_xm);
        // 协管警号
        final EditText xg_jh = (EditText) vPopWindow.findViewById(R.id.xg_jh);

        // 监所编号
        final EditText js_bh = (EditText) vPopWindow.findViewById(R.id.js_bh);
        // 监区编号
        final EditText jq_bh = (EditText) vPopWindow.findViewById(R.id.jq_bh);
        // 房间号
        final EditText fj_h = (EditText) vPopWindow.findViewById(R.id.fj_h);

        // 读取用户信息
        zg_xm.setText(information.getZg_xm());
        zg_jh.setText(information.getZg_jh());
        xg_xm.setText(information.getXg_xm());
        xg_jh.setText(information.getXg_jh());

        js_bh.setText(information.getGaolsNumber());
        jq_bh.setText(information.getPrisonNumber());
        fj_h.setText(information.getRoomNumber());
        // 修改
        Button xg = (Button) vPopWindow.findViewById(R.id.xg);
        xg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isnull(js_bh.getText().toString())
                        && isnull(jq_bh.getText().toString())
                        && isnull(fj_h.getText().toString())) {
                    // 保存用户信息
                    information.setZg_xm(zg_xm.getText().toString());
                    information.setZg_jh(zg_jh.getText().toString());
                    information.setXg_xm(xg_xm.getText().toString());
                    information.setXg_jh(xg_jh.getText().toString());

                    information.setGaolsNumber(js_bh.getText().toString());
                    information.setPrisonNumber(jq_bh.getText().toString());
                    information.setRoomNumber(fj_h.getText().toString());

                    Toast.makeText(getApplication(), "修改成功", Toast.LENGTH_LONG).show();
//                    controlInfo();
                    popWindow.dismiss();
                } else {
                    Toast.makeText(getApplication(), "信息填写不准确", Toast.LENGTH_LONG).show();
                }

            }
        });
        // 退出
        Button tc = (Button) vPopWindow.findViewById(R.id.tc);
        tc.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        popWindow.showAsDropDown(sz_head, Gravity.RIGHT, 5);
        //popWindow.showAtLocation(sz_head, Gravity.CENTER, 0, 0);
    }

    /**
     * @方法名 MainActivity.java
     * @时间 2014年4月11日 下午2:55:34
     * @设定文件 @param context
     * @设定文件 @param parent
     * @返回类型 MainActivity.java
     * @功能 (设置界面)
     */
    private void showPopWin(Context context, View parent, int styte) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View vPopWindow = inflater.inflate(R.layout.main_pop, null,
                false);
        if (popWin == null) {
            popWin = new PopupWindow(vPopWindow, LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT, true);
            popWin.setFocusable(false);
            popWin.setBackgroundDrawable(new BitmapDrawable());
            // 设置动画效果
            popWin.setAnimationStyle(R.style.AnimationFadee);
        }
        LinearLayout Lin1 = (LinearLayout) vPopWindow.findViewById(R.id.lin1);
        LinearLayout Lin2 = (LinearLayout) vPopWindow.findViewById(R.id.lin2);
        LinearLayout Lin3 = (LinearLayout) vPopWindow.findViewById(R.id.lin3);
        LinearLayout Lin4 = (LinearLayout) vPopWindow.findViewById(R.id.lin4);
        LinearLayout Lin5 = (LinearLayout) vPopWindow.findViewById(R.id.lin5);
        LinearLayout Lin6 = (LinearLayout) vPopWindow.findViewById(R.id.lin6);
        ImageView img1 = (ImageView) vPopWindow.findViewById(R.id.img1);
        ImageView img2 = (ImageView) vPopWindow.findViewById(R.id.img2);
        ImageView img3 = (ImageView) vPopWindow.findViewById(R.id.img3);
        ImageView img4 = (ImageView) vPopWindow.findViewById(R.id.img4);
        ImageView img5 = (ImageView) vPopWindow.findViewById(R.id.img5);
        ImageView img6 = (ImageView) vPopWindow.findViewById(R.id.img6);
        ImageView img_text = (ImageView) vPopWindow.findViewById(R.id.img_text);

        switch (styte) {
            //政务公开
            case 1:
                Lin5.setVisibility(View.GONE);
                img5.setVisibility(View.GONE);
                Lin6.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
                img_text.setImageResource(R.drawable.zwgk);
                img1.setImageResource(R.drawable.qlyw);
                img2.setImageResource(R.drawable.hssw);
                img3.setImageResource(R.drawable.nbgd);
                img4.setImageResource(R.drawable.mzsp);
                img1.setOnClickListener(zwgklistener);
                img2.setOnClickListener(zwgklistener);
                img3.setOnClickListener(zwgklistener);
                img4.setOnClickListener(zwgklistener);

                break;
            //内务安排
            case 2:
                Lin6.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
                img_text.setImageResource(R.drawable.nwap_text);
                img1.setImageResource(R.drawable.pwap);
                img2.setImageResource(R.drawable.yrzx);
                img3.setImageResource(R.drawable.llzr);//
                img4.setImageResource(R.drawable.mzjy);
                img5.setImageResource(R.drawable.xxtz);
                img5.setImageResource(R.drawable.message);//消息通知

                img1.setOnClickListener(nwaplistener);
                img2.setOnClickListener(nwaplistener);
                img3.setOnClickListener(nwaplistener);
                img4.setOnClickListener(nwaplistener);
                img5.setOnClickListener(nwaplistener);
                break;
            //业务办理
            case 3:
                Lin5.setVisibility(View.GONE);
                img5.setVisibility(View.GONE);
//                Lin4.setVisibility(View.GONE);
//                img4.setVisibility(View.GONE);
//                Lin3.setVisibility(View.GONE);
//                img3.setVisibility(View.GONE);
                Lin6.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
                img_text.setImageResource(R.drawable.ywbl_text);
                img1.setImageResource(R.drawable.dzgw);
                img2.setImageResource(R.drawable.yyts);
                img3.setImageResource(R.drawable.zwlr);
                img4.setImageResource(R.drawable.dm);
                img1.setOnClickListener(ywbllistener);
                img2.setOnClickListener(ywbllistener);
                img3.setOnClickListener(ywbllistener);
                img4.setOnClickListener(ywbllistener);

                break;
            //消息查询
            case 4:
                Lin6.setVisibility(View.GONE);
                Lin4.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
                img4.setVisibility(View.GONE);
                img_text.setImageResource(R.drawable.xxcx_text);
                img1.setImageResource(R.drawable.grxfcx);
                img2.setImageResource(R.drawable.jzcx);
                img3.setImageResource(R.drawable.jjd);
//                img4.setImageResource(R.drawable.gsnr);
//                img5.setImageResource(R.drawable.flyz);
                img5.setImageResource(R.drawable.rjz);
                img1.setOnClickListener(xxcxlistener);
                img2.setOnClickListener(xxcxlistener);
                img3.setOnClickListener(xxcxlistener);
//                img4.setOnClickListener(xxcxlistener);
                img5.setOnClickListener(xxcxlistener);
//                img6.setOnClickListener(xxcxlistener);
                break;
            //调查评估
            case 5:
                Lin6.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
                Lin5.setVisibility(View.GONE);
                img5.setVisibility(View.GONE);
                Lin4.setVisibility(View.GONE);
                img4.setVisibility(View.GONE);
                Lin3.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
                img_text.setImageResource(R.drawable.wjdc_text);
                img1.setImageResource(R.drawable.xlpc);
                img2.setImageResource(R.drawable.wjdc);
                img1.setOnClickListener(wjdclistener);
                img2.setOnClickListener(wjdclistener);
                break;
            //使用说明
            case 6:
                Lin6.setVisibility(View.GONE);
                img6.setVisibility(View.GONE);
                Lin5.setVisibility(View.GONE);
                img5.setVisibility(View.GONE);
                Lin4.setVisibility(View.GONE);
                img4.setVisibility(View.GONE);
                Lin3.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
                Lin2.setVisibility(View.GONE);
                img2.setVisibility(View.GONE);
                img_text.setImageResource(R.drawable.sysm_text);
                img1.setImageResource(R.drawable.sysm);
                img1.setOnClickListener(sysmlistener);
                break;

        }

        popWin.showAtLocation(sz_head, Gravity.CENTER, 0, 0);

    }

    //政务公开
    OnClickListener zwgklistener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = null;
            switch (v.getId()) {
                case R.id.img1: // 权利义务
//                    ToastUtil.showToast(MainActivity.this,"1111111111");
//                    File file = new File(Environment.getExternalStorageDirectory(), "app-debug.apk");
//                    InstallApkUtils.installAndStartApk(MainActivity.this, file.getPath());
//                    return;

//                    CommonUtil.openFile(file, MainActivity.this);
                    intent = new Intent(MainActivity.this, RightsObligationsActivity.class);
                    break;
                case R.id.img2: //伙食实物量标准
                    intent = new Intent(MainActivity.this,
                            FoodActivity.class);
                    break;
                case R.id.img3: // 监纪监规
                    intent = new Intent(MainActivity.this,
                            InterRulesActivity.class);
                    break;
                case R.id.img4: // 每周食谱
                    intent = new Intent(MainActivity.this,
                            MoreCaidActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };

    //内务安排
    OnClickListener nwaplistener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = null;
            switch (v.getId()) {
                case R.id.img1: // 铺位安排
                    //市所版本
//                    intent = new Intent(MainActivity.this,
//                            BunkAgrementActivity.class);
                    //通用版本
                    intent = new Intent(MainActivity.this,
                            PublicBunkAgrementActivity.class);
                    break;
                case R.id.img2: //一日作息
                    intent = new Intent(MainActivity.this,
                            DayRestActivity.class);
                    break;
                case R.id.img3: // 轮流值日
                    //市所版本
//                    intent = new Intent(MainActivity.this,
//                            ScheduleActivity.class);
                    //通用版本
                    intent = new Intent(MainActivity.this,
                            PublicScheduleActivity.class);
                    break;
                case R.id.img4: // 每周教育
                    intent = new Intent(MainActivity.this,
                            WeekEeducatActivity.class);
                    break;

                case R.id.img5: // 留言消息
                    Log.d("wh","留言通知");
                    intent = new Intent(MainActivity.this,
                            NotificationsActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };
    //业务办理
    OnClickListener ywbllistener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.img1: // 大帐购物
                    checkShopTime();
                    break;
                case R.id.img2: //预约投诉
                    matchFinger(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            Intent intent = new Intent(MainActivity.this,
                                    ResearchersReportActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("finger", (Serializable) finger);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    },"预约投诉");
                    break;
                case R.id.img3://指纹录入
                    Intent intent = new Intent(MainActivity.this,
                            FingerPrintActivity.class);
                    startActivity(intent);
                    break;
                case R.id.img4://点名
                    checkDMTime();
                    break;
            }

        }
    };
    //信息查询
    OnClickListener xxcxlistener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img1: //个人消费查询
                    matchFingerOwn(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            Intent intent = new Intent(MainActivity.this,
                                    PersonalConsumptionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("finger", (Serializable) finger);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    },"个人消费查询");

                    break;
                case R.id.img2: //就诊查询

                    matchFingerOwn(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            requestMedicalInfo(finger);


                        }
                    },"就诊查询");
                    break;
                case R.id.img3: // 接济单
                    matchFinger(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            Intent intent = new Intent(MainActivity.this,
                                    ServcieActionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("finger", (Serializable) finger);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    },"接济单");
                    break;
                case R.id.img5: // 房间日记载
                    matchFinger(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            Intent intent = new Intent(MainActivity.this,
                                    RoomRecordActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("finger", (Serializable) finger);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    },"房间日记载");
                    break;
            }
        }
    };

    //调查评估
    OnClickListener wjdclistener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img1: // 心理评测
                    matchFinger(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            Intent intent = new Intent(MainActivity.this,
                                    PsychologicalEvaluationActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("finger", (Serializable) finger);
                            intent.putExtras(bundle);
                            startActivity(intent);
//                            Log.d("wh", "心理评测");
//                            Intent intent = new Intent(MainActivity.this,
//                                    PsychologicalEvaluationActivity.class);
//                            startActivity(intent);
                        }
                    },"心理测评");
                    break;
                case R.id.img2: //问卷调查
                    matchFinger(new MatchFingerLintener() {
                        @Override
                        public void OnSuccess(Finger finger) {
                            Intent intent = new Intent(MainActivity.this,
                                    QuestionnairesActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("finger", (Serializable) finger);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            Log.d("wh", "问卷调查");
//                            Intent intent = new Intent(MainActivity.this,
//                                    QuestionnaireActivity.class);
//                            startActivity(intent);
                        }
                    },"问卷调查");
                    break;
            }
        }
    };
    //使用说明
    OnClickListener sysmlistener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.img1: // 使用说明
                    Intent intent = new Intent(MainActivity.this,
                            ExplainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    /**
     * @方法名 MainActivity.java
     * @时间 2014年4月11日 下午2:58:01
     * @设定文件
     * @返回类型 MainActivity.java
     * @功能 (用户登录)
     */
    private void userLogin() {

        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.userlogin, null);

        final EditText login_name = (EditText) view
                .findViewById(R.id.login_name);
        final EditText login_pass = (EditText) view
                .findViewById(R.id.login_pass);

        SaituDialog.Builder builder = new SaituDialog.Builder(MainActivity.this);
        builder.setTitle("用户登录")
                .setCanceleable(false)
                .setContentView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        if (login_name.getText().toString().trim().equals("sc") && login_pass.getText().toString().trim().equals("sc")) {
                            showPopWindow(MainActivity.this, (View) login_pass.getParent());
                            information.setIspolicelogin(true);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplication(), "用户名或者密码错误", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        information.setIspolicelogin(false);
                    }
                });
        // 创建并显示对话框
        builder.create().show();
    }

    /**
     * @方法名 MainActivity.java
     * @时间 2014年4月11日 下午3:22:34
     * @设定文件
     * @返回类型 MainActivity.java
     * @功能 (判断是否为空)
     */
    private Boolean isnull(String text) {

        return !(text.equals("") || text.length() <= 0 || text == null);

    }

    public void areaAnimation(LinearLayout tu) {
        this.doStartAnimation(R.anim.area_animation, tu);
    }

    private void doStartAnimation(int animId, LinearLayout tu) {
        tu.setVisibility(View.VISIBLE);
        // 加载动画
        Animation animation = AnimationUtils.loadAnimation(this, animId);
        // 动画开始
        tu.startAnimation(animation);
    }

    public void areaTextAnimation(LinearLayout text) {
        this.doStartTextAnimation(R.anim.area_text_animation, text);
    }

    private void doStartTextAnimation(int animId, LinearLayout text) {
        text.setVisibility(View.VISIBLE);
        // 加载动画
        Animation animation = AnimationUtils.loadAnimation(this, animId);
        // 动画开始
        text.startAnimation(animation);
    }

    private void sfdh(LinearLayout text) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);

        text.startAnimation(scaleAnimation);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("debug", "onTouchEvent");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("TAG", "触摸了屏幕");
        }
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.d("TAG", "长按了按钮");

        if (keyCode == KeyEvent.KEYCODE_F12) {
            Log.d("TAG", "长按了报警键");
        }


        return super.onKeyLongPress(keyCode, event);
    }

    /**
     * 通过IP地址获取监区号监视号
     */
    private void initDevice() {
        String ip = CommonUtil.getHostIP();
        //"192.168.31.132"
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getRoomInfo(ip)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<RoomInfo>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<RoomInfo> roomInfoEntity) {
                            if (checkEntity(roomInfoEntity)) {
                                CommonUtil.setKssArea(MainActivity.this, roomInfoEntity.getData().getKssArea());
                                CommonUtil.setkssRoomNum(MainActivity.this, roomInfoEntity.getData().getKssRoomNum());
                                initFingers();
                            }
                        }
                    });
        }
    }

    private void initFingers() {
        String roomNum = CommonUtil.getkssRoomNum(this);
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().initFingers(roomNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<SynchFinger>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<SynchFinger>> listEntity) {
                            if (checkEntity(listEntity)) {

                                List<FingerBean> fingerBeanList = CommonUtil.synchFinger2DBList(listEntity.getData());
                                JYYTApplication.getDaoInstant().getFingerBeanDao().deleteAll();
                                JYYTApplication.getDaoInstant().getFingerBeanDao().insertInTx(fingerBeanList);
                            }
                        }
                    });
        }
    }


    @Override
    protected void onDestroy() {
//        fingerPrintUtil.onDestroy();
        Log.v("Log", "destroy");
        super.onDestroy();
        System.gc();
        throw new NullPointerException("resstart");
    }
    boolean isClick = true;
    CountDownTimer timer = new CountDownTimer(3 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }
        @Override
        public void onFinish() {
            isClick = true;
        }
    };

    /**
     * 比对指纹统一指纹时间
     *
     * @param matchFingerLintener
     */
    private void matchFinger(final MatchFingerLintener matchFingerLintener,final String MenuName) {
        checkFingerTime(matchFingerLintener, MenuName);

    }

    /**
     * 比对指纹 指纹时间单独
     *
     * @param matchFingerLintener
     */
    private void matchFingerOwn(final MatchFingerLintener matchFingerLintener ,final String MenuName ) {
        if (isClick) {
            isClick = false;
            timer.start();
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("请验证指纹")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            byte[] data = fingerPrintUtil.extract();
                            Finger finger = CommonUtil.machFinger(data, MainActivity.this, fingerPrintUtil);
                            if (finger != null) {//比对成功
                                //通过人员编号查询菜单权限
                                    queryByPeopleNum( finger.getKssPrisonerNum(),MenuName,finger,matchFingerLintener);

                            } else {
                                ToastUtil.showToast(MainActivity.this, "指紋比对失败");
                            }
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("请不要连续点击")
                    .setPositiveButton("确定", null)
                    .show();
        }

    }

    interface MatchFingerLintener {
        void OnSuccess(Finger finger);

    }

    /**
     * 提交点名
     */
    private void requestRollCall() {

        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getRollCall(rollCall)
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
                                ToastUtil.showToast(MainActivity.this, "点名成功");
                            } else {
                                checkEntityCode(entity);
                            }

                        }
                    });
        }
    }

    //检查当前时间，是否在大帐设置时间范围内
    private void checkShopTime() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().checkShopTime()
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
                            if (entity.getMessage().equals("0006")) {
                                matchFingerOwn(new MatchFingerLintener() {
                                    @Override
                                    public void OnSuccess(Finger finger) {
                                        ShopReq shopReq = new ShopReq();
                                        shopReq.setPage(1 + "");
                                        shopReq.setKssPrisonerNum(finger.getKssPrisonerNum());
                                        shopReq.setShopType("");
                                        shopReq.setShopName("");
                                        shopReq.setStartPrice(0);
                                        shopReq.setEndPrice(0);
                                        getShops(shopReq, finger);
                                    }
                                },"大帐购物");
                            } else {
                                ToastUtil.showToast(MainActivity.this, "不在购物时间内");
                            }

                        }
                    });

        }
    }

    //检查当前时间，是否在点名设置时间范围内
    private void checkDMTime() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().checkDMTime()
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
                            if (entity.getMessage().equals("0006")) {
                                matchFingerOwn(new MatchFingerLintener(){
                                    @Override
                                    public void OnSuccess(Finger finger) {
                                        if (!TextUtils.isEmpty(finger.getKssPrisonerNum())) {
                                            rollCall.setKssPrisonerNum(finger.getKssPrisonerNum());
                                            rollCall.setKssState(1);
                                            requestRollCall();
                                        } else {
                                            ToastUtil.showToast(MainActivity.this, "点名失败");
                                        }
                                    }
                                },"点名");
                            } else {
                                ToastUtil.showToast(MainActivity.this, "不在点名时间内");
                            }

                        }
                    });
        }
    }

    private void getShops(ShopReq shopReq, final Finger finger) {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getShops(shopReq)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<Shopping>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<Shopping> shoppingEntity) {
                            if (checkEntity(shoppingEntity)) {
                                Intent intent = new Intent(MainActivity.this,
                                        ShoppingActivity.class);
                                intent.putExtra("finger", finger);
                                startActivity(intent);
                            } else {
                                checkEntityCode(shoppingEntity);
                            }
                        }
                    });
        }
    }
//获取系统时间
    private void getSystemTime() {
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
                                text_time.setText(systemTimeEntity.getData().getTime());
                                SharedPreferencesUtil.setSetting("kcc",MainActivity.this,"time",systemTimeEntity.getData().getTime());
                            } else {
                                checkEntityCode(systemTimeEntity);
                            }
                        }
                    });
        }
    }


    //    检查当前时间，是否在指纹设置时间范围内
    private void checkFingerTime(final MatchFingerLintener matchFingerLintener ,final String MenuName) {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().checkFingerTime()
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
                            if (entity.getMessage().equals("0006")) {
                                if (isClick) {
                                    isClick = false;
                                    timer.start();
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("请验证指纹")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    byte[] data = fingerPrintUtil.extract();
                                                    Finger finger = CommonUtil.machFinger(data, MainActivity.this, fingerPrintUtil);
                                                    if (finger != null) {//比对成功
                                                        //通过人员编号查询菜单权限
                                                        //通过人员编号查询菜单权限
                                                        queryByPeopleNum( finger.getKssPrisonerNum(),MenuName,finger,matchFingerLintener);
                                                    } else {
                                                        ToastUtil.showToast(MainActivity.this, "指紋比对失败");
                                                    }
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("请不要连续点击")
                                            .setPositiveButton("确定", null)
                                            .show();
                                }
                            } else {
                                ToastUtil.showToast(MainActivity.this, "不在指纹时间内");
                            }
                        }
                    });
        }
    }

    /**
     * 请求医药信息
     */
    private void requestMedicalInfo(final Finger finger) {
        String kssPrisonerNum = finger.getKssPrisonerNum();
        int page = 1;
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getMedicalInfo(kssPrisonerNum, page)
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
                                if (medicalInfoEntity.getData().getRows().size() != 0) {
                                    Intent intent = new Intent(MainActivity.this,
                                            MedicalConsumptionActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("finger", (Serializable) finger);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                } else {
                                    ToastUtil.showToast(MainActivity.this, "此人最近无医药信息");
                                }
                            } else {
                                checkEntityCode(medicalInfoEntity);
                            }
                        }
                    });
        }
    }

    /**
     *通过房间号查询留言信息
     */
    private void queryMsgInfo() {
      String kssRoom=CommonUtil.getkssRoomNum(this);
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().queryMsgInfo(kssRoom)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<QueryMsgInfo>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                            Log.d("wh","queryMsgInfo 消息推送 onError");
                        }

                        @Override
                        public void onNext(Entity<List<QueryMsgInfo>> listEntity) {
                            if (checkEntity(listEntity)) {
                                Log.d("wh","queryMsgInfo 消息推送 onNext");
                                String msgIdStr = "";
                                for (int i=0;i<listEntity.getData().size();i++){
                                    int msgId= listEntity.getData().get(i).getId();
                                     msgIdStr=msgId+","+msgIdStr;
                                    int msgloadState=listEntity.getData().get(i).getLoadState();
                                    Log.d("wh","msgloadState="+msgloadState);
                                    if (msgloadState==0){
                                        Log.d("wh","播放声音");
                                        soundPool= new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
                                        soundPool.load(MainActivity.this,R.raw.newmessage,1);
                                        soundPool.play(1,1, 1, 0, 0, 1);
//                                        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,
//                                                R.raw.newmessage);
//                                        mediaPlayer.start();
                                    }
                                }
                                if(listEntity.getData().size()==1){
                                    msgIdStr=msgIdStr.substring(0, msgIdStr.length()-1);
                                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,
                                            R.raw.newmessage);
                                    mediaPlayer.start();
                                }
                                Log.e("",msgIdStr);
                                if(listEntity.getData().size()!=0){
                                updateMsgState(msgIdStr.toString().trim(),listEntity.getData().size()); }
                            } else {
//                                checkEntityCode(listEntity);
                            }
                        }
                    });
        }
    }



    /**
     *客户端留言获取成功通知服务器
     */
    private void updateMsgState(String idStr, final int msgSize) {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().updateMsgState(idStr)
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
                                //

                                    text_messege.setText("");
                                    text_messege.setText("请接收新的消息！");


                            } else {
//                                checkEntityCode(entity);
                            }
                        }
                    });
        }
    }

    /**
     * 通过人员编号查询菜单权限
     */
    private void  queryByPeopleNum(final  String kssPrisonerNum,final String MenuName, final Finger finger ,final MatchFingerLintener matchFingerLintener ) {
        Log.d("wh","通过人员编号查询菜单权限 kssPrisonerNum="+kssPrisonerNum);
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().queryByPeopleNum(kssPrisonerNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<MenuPermissions>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                            Log.d("wh"," 通过人员编号查询菜单权限失败");
                        }

                        @Override
                        public void onNext(Entity<MenuPermissions> MenuPermissions) {
                            Log.d("wh","MenuName="+MenuName);
                            if (checkEntity(MenuPermissions)) {
                                permissions=MenuPermissions.getData().getGrantMenu()+",就诊查询";
                                   Log.d("wh","permissions"+permissions);
                                    if ( permissions!=null&&permissions.indexOf(MenuName)!=-1) {
                                        matchFingerLintener.OnSuccess(finger);
                                    } else {
                                        ToastUtil.showToast(MainActivity.this, "权限不足");
                                    }
                                Log.d("wh"," 通过人员编号查询菜单权限"+permissions);
                            }
                        }
                    });
        }
    }
}
