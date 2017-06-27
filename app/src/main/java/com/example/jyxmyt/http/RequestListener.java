package com.example.jyxmyt.http;

import android.util.Log;

import com.example.jyxmyt.entity.Entity;

import rx.Subscriber;

/**
 * Created by tanghao on 2017/2/21.
 */
@Deprecated
public abstract class RequestListener<T> extends Subscriber<T> {


    public abstract void onResult(T t);


    @Override
    public void onCompleted() {
        Log.v("Log", "完成~~");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {

        Entity entity = new Entity();
        entity.setMessage("-1");

        onResult((T) entity);
    }

    @Override
    public void onNext(T t) {

        onResult(t);
    }

}