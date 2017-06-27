package com.example.jyxmyt.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.jyxmyt.app.JYYTApplication;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.SynchFinger;
import com.example.jyxmyt.entity.db.FingerBean;
import com.example.jyxmyt.entity.db.FingerBeanDao;
import com.example.jyxmyt.http.RetrofitClient;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanghao on 2017/3/7.
 */

public class SyncBiz {


    /**
     * 人员指纹
     */
    public static void synchFinger(Context mContext) {
        String kssRoomNum = CommonUtil.getkssRoomNum(mContext);
        if (kssRoomNum != null && !kssRoomNum.isEmpty())
            RetrofitClient.getCommonApi().synchFingers(kssRoomNum)
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
                                for (FingerBean fingerbean : fingerBeanList) {

                                    //bean人员编号匹配本地数据
                                    FingerBeanDao fingerBeanDao = JYYTApplication.getDaoInstant().getFingerBeanDao();
                                    List<FingerBean> list = fingerBeanDao.queryBuilder()
                                            .where(FingerBeanDao.Properties.KssPrisonerNum.eq(fingerbean.getKssPrisonerNum()))
                                            .list();
                                    if (list.size() > 0)
                                        fingerbean.setId(list.get(0).getId());


                                    switch (fingerbean.getType()) {
                                        case 1://添加
//                                            fingerBeanDao.insert(fingerbean);
//                                            break;
                                        case 2://修改
                                            fingerBeanDao.update(fingerbean);
                                            break;
                                        case 3://删除
                                            fingerBeanDao.delete(fingerbean);
                                            break;
                                    }
                                }

                                if (fingerBeanList.size() != 0)
                                    SyncBiz.shakeHands(fingerBeanList);//握手

                            }
                        }
                    });

    }


    /**
     * 握手
     */
    public static void shakeHands(List<FingerBean> fingerBeanList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fingerBeanList.size(); i++) {
            sb.append(fingerBeanList.get(i).getFingerSynoId());
            if (i == fingerBeanList.size() - 1)
                break;
            sb.append(",");
        }

        HashMap hashMap = new HashMap();
        hashMap.put("handshakeType", 1);
        hashMap.put("idStr", sb.toString());
        RetrofitClient.getCommonApi().handshake(hashMap)
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
                        }
                    }
                });

    }

    /**
     * 检查返回结果是否合法
     *
     * @param entity
     * @return
     */
    private static boolean checkEntity(Entity entity) {
        //			ToastUtil.showToast(this, entity.getMsg());
        return entity.getMessage() != null && entity.getMessage().equals("0001");
    }

    private static void doError(Throwable e) {
        Log.v("Log", "请求失败--->>>" + e.getMessage());
//        ToastUtil.showToast(this, "请求数据失败");
    }


    /**
     * 检查是否有网络
     *
     * @return
     */
    private static boolean checkNetwork(Context mContext) {
        ConnectivityManager cwjManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cwjManager.getActiveNetworkInfo();
        boolean flag = info != null && info.isAvailable();
        if (!flag)
            ToastUtil.showToast(mContext, "无法连接网络，请检查设置");

        return flag;
    }
}
