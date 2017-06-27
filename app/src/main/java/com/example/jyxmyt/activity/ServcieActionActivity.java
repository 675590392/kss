package com.example.jyxmyt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.MedicalListAdapter;
import com.example.jyxmyt.adapter.ServcieActionAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.ServcieAction;
import com.example.jyxmyt.entity.ServcieActionDetailed;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServcieActionActivity extends BaseActivity implements View.OnClickListener {
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private Finger finger = null;
    private List<ServcieAction> servcieActionList = null;
    private List<ServcieActionDetailed> servcieActionDetailed = null;
    private GridView gview_jjd;
    private Button btn_jjd_submit;//接济单
    private ServcieActionAdapter servcieActionAdapter;
   //人民币
    private  TextView    text_rmb;
    private  EditText edt_rmb_num;
    private TextView text_rmb_unit;
   //家属名称
   private EditText   edt_famali_name;
    //联系电话
    private EditText  edt_phone;
    private LinearLayout lin_jjd_all;
    private  TextView  text_room;//监室号
    private  TextView edt_prison_num; //人员编号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servcie_action);
        initView();
    }

    @Override
    public void initView() {
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        gview_jjd = (GridView) findViewById(R.id.gview_jjd);
        btn_jjd_submit = (Button) findViewById(R.id.btn_jjd_submit);
        btn_jjd_submit.setOnClickListener(this);
        //人民币
       text_rmb = (TextView) findViewById(R.id.text_rmb);
        edt_rmb_num = (EditText) findViewById(R.id.edt_rmb_num);
       text_rmb_unit = (TextView) findViewById(R.id.text_rmb_unit);
       //家属名称
        edt_famali_name = (EditText) findViewById(R.id.edt_famali_name);
        //联系电话
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        lin_jjd_all= (LinearLayout) findViewById(R.id.lin_jjd_all);
        text_room=(TextView) findViewById(R.id.text_room);
        edt_prison_num=(TextView) findViewById(R.id.edt_prison_num);
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        finger = (Finger) bun.getSerializable("finger");
        text_room.setText("监室号："+finger.getKssRoomNum());
        edt_prison_num.setText("人员编号："+finger.getKssPrisonerNum());

        if (servcieActionList == null) {
            servcieActionList = new ArrayList<>();
        }
        servcieActionDetailed = new ArrayList<>();
        servcieActionAdapter = new ServcieActionAdapter(getApplication(), servcieActionDetailed);
        gview_jjd.setAdapter(servcieActionAdapter);
        requestServcieActionDetailed();

        gview_jjd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == return_but) {
            finish();
        } else if (v == home_but) {
            finish();
        } else if (v == btn_jjd_submit) {
            if(edt_rmb_num.getText().toString().trim().equals("")){
                ToastUtil.showToast(ServcieActionActivity.this, "请输入人民币金额");
                return;
            }else{
            if(Integer.parseInt(edt_rmb_num.getText().toString().trim())>2000){
                ToastUtil.showToast(ServcieActionActivity.this, "人民币金额不能大于2000元");
                return;
            }}
            if(edt_famali_name.getText().toString().trim().equals("")){
                ToastUtil.showToast(ServcieActionActivity.this, "请输入家属姓名");
                return;
            }else if(edt_phone.getText().toString().trim().equals("")){
                ToastUtil.showToast(ServcieActionActivity.this, "请输入电话号码");
                return;
            }

            Map<Integer, ServcieAction> selectMap = servcieActionAdapter.getSelectMap();
            //....
            Iterator iterator = selectMap.keySet().iterator();
//            selectMap.put(selectMap.size()+1, servcieActionEntity);
            while (iterator.hasNext()) {

                ServcieAction servcieAction = selectMap.get(iterator.next());
                //..
                servcieAction.setKssArea(finger.getKssArea());
                servcieAction.setKssPrisonerNum(finger.getKssPrisonerNum());
                servcieAction.setKssName(edt_famali_name.getText().toString().trim());
                servcieAction.setKssPhone( edt_phone.getText().toString().trim());
                servcieAction.setKssPrisoner(finger.getKssPrisonerName());
                servcieAction.setKssRoomNum(finger.getKssRoomNum());
                servcieActionList.add(servcieAction);
            }
try {

    if(edt_rmb_num.equals("")){

    }else{
    ServcieAction servcieActionEntity = new ServcieAction();//赋值
    servcieActionEntity.setKssProductName( text_rmb.getText().toString().trim());
    servcieActionEntity.setKssProductUnit(text_rmb_unit.getText().toString().trim());
    String rmb_num=edt_rmb_num.getText().toString().trim();
    servcieActionEntity.setKssProductNum(Integer.valueOf(rmb_num));
    servcieActionEntity.setKssArea(finger.getKssArea());
    servcieActionEntity.setKssPrisonerNum(finger.getKssPrisonerNum());
    servcieActionEntity.setKssName(edt_famali_name.getText().toString().trim());
    servcieActionEntity.setKssPhone( edt_phone.getText().toString().trim());
    servcieActionEntity.setKssPrisoner(finger.getKssPrisonerName());
    servcieActionEntity.setKssRoomNum(finger.getKssRoomNum());
    servcieActionList.add(servcieActionEntity);}
}catch (Exception e){
    e.printStackTrace();
}

            requestBookingInfo();

        }
    }

    /**
     * 请求查询全部接济单分类
     */
    private void requestServcieActionDetailed() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getServcieActionDetailed()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<List<ServcieActionDetailed>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<List<ServcieActionDetailed>> listEntity) {
                            if (checkEntity(listEntity)) {
                                servcieActionDetailed.clear();
                                servcieActionDetailed.addAll(listEntity.getData());
                                gview_jjd.setAdapter(servcieActionAdapter);
                                servcieActionAdapter.notifyDataSetChanged();
                            }else{
                                checkEntityCode(listEntity);
                            }
                        }
                    });
        }
    }

    /**
     * 添加批量救济单
     */
    private void requestBookingInfo() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getServcieAction(servcieActionList)
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
                                ToastUtil.showToast(ServcieActionActivity.this, "添加成功");
                                finish();
                            } else {
                                ToastUtil.showToast(ServcieActionActivity.this, "添加失败");
                            }
                        }
                    });
        }
    }
}
