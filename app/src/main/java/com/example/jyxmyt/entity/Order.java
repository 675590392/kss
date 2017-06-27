package com.example.jyxmyt.entity;

/**
 * Created by tanghao on 2017/3/9.
 */

public class Order {
    /**
     * shopUnit : 1
     * limitedNumber : 2
     * shopName : 香蕉
     * shopPrice : 1.1
     * shopType : 1
     * shopCode : 010000000
     */

    private int shopUnit;
    private int limitedNumber;
    private String shopName;
    private double shopPrice;
    private String shopType;
    private String shopCode;
    private String shopStandard;

    public int getShopUnit() {
        return shopUnit;
    }

    public void setShopUnit(int shopUnit) {
        this.shopUnit = shopUnit;
    }

    public int getLimitedNumber() {
        return limitedNumber;
    }

    public void setLimitedNumber(int limitedNumber) {
        this.limitedNumber = limitedNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopStandard() {
        return shopStandard;
    }

    public void setShopStandard(String shopStandard) {
        this.shopStandard = shopStandard;
    }
}
