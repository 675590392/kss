package com.example.jyxmyt.entity;

/**
 * Created by tanghao on 2017/3/2.
 */

public class Entity<T> {
    private String message;//状态
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
