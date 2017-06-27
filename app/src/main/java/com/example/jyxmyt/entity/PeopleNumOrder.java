package com.example.jyxmyt.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */

public class PeopleNumOrder {


    /**
     * page : 1
     * total : 1
     * records : 10
     * rows : [{"id":21,"shopName":"牛奶3","shopType":"1","shopUnit":1,"shopPrice":3.1,"limitedNumber":3,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-06 00:00:00"},{"id":20,"shopName":"榴莲3","shopType":"1","shopUnit":1,"shopPrice":42.1,"limitedNumber":2,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-06 00:00:00"},{"id":17,"shopName":"牛奶2","shopType":"1","shopUnit":1,"shopPrice":3.1,"limitedNumber":3,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-06 00:00:00"},{"id":16,"shopName":"榴莲2","shopType":"1","shopUnit":1,"shopPrice":42.1,"limitedNumber":2,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-06 00:00:00"},{"id":15,"shopName":"牛奶","shopType":"1","shopUnit":1,"shopPrice":3.1,"limitedNumber":3,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-06 00:00:00"},{"id":14,"shopName":"榴莲","shopType":"1","shopUnit":1,"shopPrice":42.1,"limitedNumber":2,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-06 00:00:00"},{"id":13,"shopName":"苹果醋","shopType":"1","shopUnit":1,"shopPrice":14.1,"limitedNumber":3,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-04 00:00:00"},{"id":12,"shopName":"香蕉","shopType":"1","shopUnit":1,"shopPrice":1.1,"limitedNumber":2,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-04 00:00:00"},{"id":11,"shopName":"哇哈哈","shopType":"1","shopUnit":1,"shopPrice":13.1,"limitedNumber":3,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-04 00:00:00"},{"id":10,"shopName":"苹果","shopType":"1","shopUnit":1,"shopPrice":11.1,"limitedNumber":2,"kssPrisonerNum":"31000011120170004","kssArea":"01","kssInsideNum":"20170004","kssPrisonerName":"庄剑","kssRoomNum":"0101","createTime":"2017-03-04 00:00:00"}]
     */

    private int page;
    private int total;
    private int records;
    private List<RowsBean> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 21
         * shopName : 牛奶3
         * shopType : 1
         * shopUnit : 1
         * shopPrice : 3.1
         * limitedNumber : 3
         * kssPrisonerNum : 31000011120170004
         * kssArea : 01
         * kssInsideNum : 20170004
         * kssPrisonerName : 庄剑
         * kssRoomNum : 0101
         * createTime : 2017-03-06 00:00:00
         */

        private int id;
        private String shopName;
        private String shopType;
        private int shopUnit;
        private double shopPrice;
        private int limitedNumber;
        private String kssPrisonerNum;
        private String kssArea;
        private String kssInsideNum;
        private String kssPrisonerName;
        private String kssRoomNum;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public int getShopUnit() {
            return shopUnit;
        }

        public void setShopUnit(int shopUnit) {
            this.shopUnit = shopUnit;
        }

        public double getShopPrice() {
            return shopPrice;
        }

        public void setShopPrice(double shopPrice) {
            this.shopPrice = shopPrice;
        }

        public int getLimitedNumber() {
            return limitedNumber;
        }

        public void setLimitedNumber(int limitedNumber) {
            this.limitedNumber = limitedNumber;
        }

        public String getKssPrisonerNum() {
            return kssPrisonerNum;
        }

        public void setKssPrisonerNum(String kssPrisonerNum) {
            this.kssPrisonerNum = kssPrisonerNum;
        }

        public String getKssArea() {
            return kssArea;
        }

        public void setKssArea(String kssArea) {
            this.kssArea = kssArea;
        }

        public String getKssInsideNum() {
            return kssInsideNum;
        }

        public void setKssInsideNum(String kssInsideNum) {
            this.kssInsideNum = kssInsideNum;
        }

        public String getKssPrisonerName() {
            return kssPrisonerName;
        }

        public void setKssPrisonerName(String kssPrisonerName) {
            this.kssPrisonerName = kssPrisonerName;
        }

        public String getKssRoomNum() {
            return kssRoomNum;
        }

        public void setKssRoomNum(String kssRoomNum) {
            this.kssRoomNum = kssRoomNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
