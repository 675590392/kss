package com.example.jyxmyt.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.jyxmyt.util.ToastUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanghao on 2017/1/26.
 * <p>
 */
@Deprecated
public class ApiReqClient {


    private RequestListener requestListener;
    private Observable observable;
    private boolean isShowDialog = true;//默认显示加载框

    private Context mContext;
    private ProgressDialog progressDialog;//static


    public ApiReqClient(Context mContext) {
        this(mContext, null, null);
    }

    public ApiReqClient(Context mContext, Observable observable) {
        this(mContext, observable, null);
    }

    public ApiReqClient(Context mContext, Observable observable, RequestListener requestListener) {
        this.mContext = mContext;
        this.observable = observable;
        this.requestListener = requestListener;

        initDialog();
    }

    public ApiReqClient setObservable(Observable observable) {
        this.observable = observable;
        return this;
    }

    public ApiReqClient setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    public ApiReqClient setIsShowDialog(boolean IsShowDialog) {
        isShowDialog = IsShowDialog;
        return this;
    }

    private ProgressDialog initDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle(null);
            progressDialog.setMessage("加载中");
            progressDialog.setCancelable(false);
        }
        return progressDialog;
    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    /**
     * 网络请求demo
     */
    public void request() {
        if (!checkNetwork()) {
            ToastUtil.showToast(mContext, "网络未连接，请检查网络！");
            return;
        }

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(requestListener);


    }

    /**
     * 检查是否有网络
     *
     * @return
     */
    public boolean checkNetwork() {
        ConnectivityManager cwjManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cwjManager.getActiveNetworkInfo();
        boolean flag = info != null && info.isAvailable();

        return flag;
    }


}

