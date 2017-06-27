package com.example.jyxmyt.entity;

import java.util.List;

/**
 * Created by tanghao on 2017/3/9.
 */

public class OrderReq {
    private String kssPrisonerNum;
    private List<Order> data;

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
