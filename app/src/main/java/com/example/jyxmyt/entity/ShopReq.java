package com.example.jyxmyt.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tanghao on 2017/3/8.
 */

public class ShopReq {
    /**
     * page : 1
     * shopType :
     * shopName :
     * startPrice : 0.0
     * endPrice : 0.0
     * “kssPrisonerNum” : ”31000011120170004”
     */
    private String page;
    private String shopType;////商品类型  1、食品类 2、日常用品  3、学习用品
    private String shopName;
    private double startPrice;
    private double endPrice;
    private String kssPrisonerNum; // FIXME check this code

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public String getKssPrisonerNum() {
        return kssPrisonerNum;
    }

    public void setKssPrisonerNum(String kssPrisonerNum) {
        this.kssPrisonerNum = kssPrisonerNum;
    }
}
