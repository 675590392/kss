package com.example.jyxmyt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.entity.RoomRecord;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.ToastUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RoomRecordActivity extends BaseActivity implements
        View.OnClickListener {
    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private LinearLayout fangjianlay;
    private TextView room_title, room_name;
    private EditText room_neirong;
    private Button room_submit;
    private   RoomRecord roomRecord = null;
    private Finger finger=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_record);
        initView();
    }

    @Override
    public void initView() {
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);
        fangjianlay = (LinearLayout) findViewById(R.id.fangjianlay);
        room_title = (TextView) findViewById(R.id.room_title);
        room_name = (TextView) findViewById(R.id.room_name);
        room_neirong = (EditText) findViewById(R.id.room_neirong);
        room_submit = (Button) findViewById(R.id.room_submit);
        room_submit.setOnClickListener(this);
        room_submit.setOnClickListener(this);
        room_submit.setOnClickListener(this);
        if (roomRecord == null) {
            roomRecord = new RoomRecord();
        }
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
        finger = (Finger) bun.getSerializable("finger");
        room_title.setText(finger.getKssRoomNum()+"监室房间日记载");
        room_name.setText("提交人："+finger.getKssPrisonerName());
        fangjianlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == home_but) {
            finish();
        } else if (v == return_but) {
            finish();
        } else if (v == room_submit) {
            roomRecord.setKssArea(finger.getKssArea());
            roomRecord.setKssContent(room_neirong.getText().toString().trim());
            roomRecord.setKssPrisoner(finger.getKssPrisonerName());
            roomRecord.setKssPrisonerNum(finger.getKssPrisonerNum());
            roomRecord.setKssRoomNum(finger.getKssRoomNum());
            requestRoomRecord();

        }
    }

    /**
     * 提交房间日记载数据
     */
    private void requestRoomRecord() {
        if (checkNetwork()) {
            RetrofitClient.getCommonApi().getRoomRecord(roomRecord)
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
                                ToastUtil.showToast(RoomRecordActivity.this, "数据提交成功");
                                finish();
                            }else{
                                ToastUtil.showToast(RoomRecordActivity.this, "数据提交失败");
                            }
                        }
                    });
        }
    }
}
