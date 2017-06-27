package com.example.jyxmyt.activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jyxmyt.R;
import com.example.jyxmyt.app.JYYTApplication;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.ToastUtil;
import com.example.jyxmyt.view.SaituDialog;

public abstract class BaseMainActivity extends Activity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_main);
//    }

    /**
     * 控件初始化
     */
    public abstract void initView();

    public ProgressDialog progressdialog = null;

    public JYYTApplication application;
    private Dialog mDialog;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (JYYTApplication) getApplication();
        application.addActivity(this);
    }

    @Override
    protected Dialog onCreateDialog(int i1) {
        if (progressdialog == null) {
            if (getParent() != null) {
                progressdialog = new ProgressDialog(getParent());
            } else
                progressdialog = new ProgressDialog(this);
            progressdialog.setMessage("正在加载中...");
            progressdialog.setIndeterminate(true);
            progressdialog.setCancelable(true);
            progressdialog.setCanceledOnTouchOutside(false);
        }
        return progressdialog;
    }

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

        SaituDialog.Builder builder = new SaituDialog.Builder(this);
        builder.setTitle("用户登录")
                .setCanceleable(false)
                .setContentView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        if (isnull(login_name.getText().toString())) {
                            if (isnull(login_pass.getText().toString())) {

                                dialog.dismiss();
                            } else {
                                Toast.makeText(getApplication(), "请输入密码", Toast.LENGTH_LONG)
                                        .show();
                            }
                        } else {
                            Toast.makeText(getApplication(), "请输入用户名", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
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

    /**
     * 进度条
     */
    /**
     * @param mContext
     * @类名 BaseMainActivity.java
     * @包名 com.example.jyxmyt.activity
     * @作者 毅
     * @时间 2014年4月17日 下午2:10:14
     * @Email ChunTian1314@vip.qq.com
     * @版本 V1.0
     * @功能 (显示进度条)
     */
    public void showDialog(Context mContext) {
        showRoundProcessDialog(mContext, R.layout.loading_process_dialog_anim);
    }

    /**
     * @类名 BaseMainActivity.java
     * @包名 com.example.jyxmyt.activity
     * @作者 毅
     * @时间 2014年4月17日 下午3:10:43
     * @Email ChunTian1314@vip.qq.com
     * @版本 V1.0
     * @功能(关闭进度条)
     */
    public void closeDialog() {
        imageView.clearAnimation();
        mDialog.dismiss();
    }

    public void showRoundProcessDialog(final Context mContext, int layout) {
        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_HOME
                        || keyCode == KeyEvent.KEYCODE_SEARCH;
            }
        };

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layout, null);

        Animation operatingAnim = AnimationUtils.loadAnimation(this,
                R.anim.dialog_style_xml_icon);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);

        imageView = (ImageView) view
                .findViewById(R.id.loading_process_dialog_progressBar);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                imageView.clearAnimation();
                finish();
                mDialog.dismiss();
            }
        });
        imageView.setAnimation(operatingAnim); // 设置动画
        operatingAnim.startNow();
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setOnKeyListener(keyListener);
        mDialog.setCancelable(false);
        mDialog.show();
        // 注意此处要放在show之后 否则会报异常
        mDialog.setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //关闭软键盘
    public boolean onTouchEvent(MotionEvent event) {

        try {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {

                im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

        } catch (Exception e) {
            Log.e("ouTouchEvent", "error", e);
        }
        return super.onTouchEvent(event);
    }





    /**
     * 检查返回结果是否合法
     *
     * @param entity
     * @return
     */
    protected boolean checkEntity(Entity entity) {
        //			ToastUtil.showToast(this, entity.getMsg());
        return entity.getMessage() != null && entity.getMessage().equals("0001");
    }

    /**
     *
     * @param
     */
    protected void  checkEntityCode(Entity entity) {
        //			ToastUtil.showToast(this, entity.getMsg());
        String strCode="";
        switch (entity.getMessage()) {
            case "0000": {
                ToastUtil.showToast(this, "操作失败");
            }
            break;
            case "0002": {
                ToastUtil.showToast(this, "系统异常");
            }
            break;
            case "0003": {
                ToastUtil.showToast(this, "参数不足");
            }
            break;
            case "0004": {
                ToastUtil.showToast(this, "数据不存在");
            }
            break; case "0005": {
                ToastUtil.showToast(this, "不在时间范围内");
            }
            break; case "0006": {
                ToastUtil.showToast(this, "在时间范围内");
            }
            break; case "0007": {
                ToastUtil.showToast(this, "指纹已录入两次");
            }
            break; case "0008": {
                ToastUtil.showToast(this, "此人无账号");
            }
            break; case "0009": {
                ToastUtil.showToast(this, "该人员已点名");
            }
            break;
        }
    }
    protected void doError(Throwable e) {
        Log.v("Log", "请求失败--->>>" + e.getMessage());
//        ToastUtil.showToast(this, "请求数据失败");
    }


    /**
     * 检查是否有网络
     *
     * @return
     */
    protected boolean checkNetwork() {
        ConnectivityManager cwjManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cwjManager.getActiveNetworkInfo();
        boolean flag = info != null && info.isAvailable();
        if (!flag)
            ToastUtil.showToast(this, "无法连接网络，请检查设置");

        return flag;
    }
}
